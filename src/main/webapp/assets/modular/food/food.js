layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--部门管理
     */
    var Food = {
        tableId: "foodTable",
        condition: {
            title:''
        }
    };

    /**
     * 初始化表格的列
     */
    Food.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'title', sort: true, title: '标题'},
            {field: 'content', sort: true, title: '内容'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Food.search = function () {
        var queryData = {};
        queryData['title'] = $("#searchTitle").val();
        table.reload(Food.tableId, {where: queryData});
    };

    /**
     * 弹出添加
     */
    Food.openAddUser = function () {
       /* admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area:['760px','460px'],
            title: '添加美食',
            content: Feng.ctxPath + '/food/to/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Food.tableId);
            }
        });*/
        window.location.href=Feng.ctxPath + '/food/to/add?'
    };


    /**
     *
     * @param data 点击按钮时候的行数据
     */


    Food.onEditUser = function (data) {

      /*  admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改用户',
            area:['760px','660px'],
            content: Feng.ctxPath + '/food/to/update?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Food.tableId);
            }
        });*/
        window.location.href=Feng.ctxPath + '/food/to/update?id=' + data.id
    };

    /**
     * 点击删除用户
     *
     * @param data 点击按钮时候的行数据
     */
    Food.onDeleteUser = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/food/delete/"+data.id, function () {
                Feng.success("删除成功!");
                table.reload(Food.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除标题 " + data.title + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Food.tableId,
        url: Feng.ctxPath + '/food/list2',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Food.initColumn()
    });



    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Food.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Food.openAddUser();
    });


    // 工具条点击事件
    table.on('tool(' + Food.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Food.onEditUser(data);
        } else if (layEvent === 'delete') {
            Food.onDeleteUser(data);
        }
    });
});
