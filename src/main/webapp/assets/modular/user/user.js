layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--部门管理
     */
    var User = {
        tableId: "userTable",
        condition: {
            userName:''
        }
    };

    /**
     * 初始化表格的列
     */
    User.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'username', sort: true, title: '用户名'},
            {field: 'nickname', sort: true, title: '昵称'},
            {field: 'email', sort: true, title: '邮箱'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    User.search = function () {
        var queryData = {};
        queryData['userName'] = $("#searchUserName").val();
        table.reload(User.tableId, {where: queryData});
    };

    /**
     * 弹出添加
     */
    User.openAddUser = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            area:['760px','460px'],
            title: '添加会员',
            content: Feng.ctxPath + '/userInfo/to/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(User.tableId);
            }
        });
    };


    /**
     *
     * @param data 点击按钮时候的行数据
     */
    User.onEditUser = function (data) {

        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改用户',
            area:['760px','460px'],
            content: Feng.ctxPath + '/userInfo/to/update?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(User.tableId);
            }
        });
    };

    /**
     * 点击删除用户
     *
     * @param data 点击按钮时候的行数据
     */
    User.onDeleteUser = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/userInfo/delete", function () {
                Feng.success("删除成功!");
                table.reload(User.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除会员 " + data.username + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + User.tableId,
        url: Feng.ctxPath + '/userInfo/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: User.initColumn()
    });



    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        User.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        User.openAddUser();
    });


    // 工具条点击事件
    table.on('tool(' + User.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            User.onEditUser(data);
        } else if (layEvent === 'delete') {
            User.onDeleteUser(data);
        }
    });
});
