layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--部门管理
     */
    var Member = {
        tableId: "memberTable",
        condition: {
            memberNo:'',
            memberName:'',
            phone: ''
        }
    };

    /**
     * 初始化表格的列
     */
    Member.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'memberNo', sort: true, title: '会员编号'},
            {field: 'memberName', sort: true, title: '会员姓名'},
            {field: 'phone', sort: true, title: '手机号'},
            {field: 'integral', sort: true, title: '积分'},
            {field: 'money', sort: true, title: '余额'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Member.search = function () {
        var queryData = {};
        queryData['memberNo'] = $("#searchMemberNo").val();
        queryData['memberName'] = $("#searchMemberName").val();
        queryData['phone'] = $("#searchPhone").val();
        table.reload(Member.tableId, {where: queryData});
    };

    /**
     * 弹出添加
     */
    Member.openAddMember = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area:['760px','460px'],
            title: '添加会员',
            content: Feng.ctxPath + '/member/to/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Member.tableId);
            }
        });
    };


    /**
     * 点击编辑会员信息
     *
     * @param data 点击按钮时候的行数据
     */
    Member.onEditMember = function (data) {

        console.log('-----')
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改部门',
            area:['760px','460px'],
            content: Feng.ctxPath + '/member/to/update?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Member.tableId);
            }
        });
    };

    /**
     * 点击删除部门
     *
     * @param data 点击按钮时候的行数据
     */
    Member.onDeleteMember = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/member/delete", function () {
                Feng.success("删除成功!");
                table.reload(Member.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除会员 " + data.memberName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Member.tableId,
        url: Feng.ctxPath + '/member/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Member.initColumn()
    });

   /* //初始化左侧部门树
    var ztree = new $ZTree("deptTree", "/dept/tree");
    ztree.bindOnClick(Dept.onClickDept);
    ztree.init();*/

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Member.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Member.openAddMember();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Member.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Member.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            console.log('edit..');
            Member.onEditMember(data);
        } else if (layEvent === 'delete') {
            Member.onDeleteMember(data);
        }
    });
});
