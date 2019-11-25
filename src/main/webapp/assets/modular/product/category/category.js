layui.use(['table', 'admin', 'ax'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;

    /**
     * 系统管理--部门管理
     */
    var Category = {
        tableId: "categoryTable",
        condition: {
            categoryName:''
        }
    };

    /**
     * 初始化表格的列
     */
    Category.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'categoryName', sort: true, title: '分类名称'},
            {field: 'pName', sort: true, title: '上级分类'},
            {field: 'level', sort: true, title: '分类等级'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Category.search = function () {
        var queryData = {};
        queryData['categoryName'] = $("#searchCategoryName").val();
        table.reload(Category.tableId, {where: queryData});
    };

    /**
     * 弹出添加
     */
    Category.openAddCategory = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加分类信息',
            area: ['380px', '550px'],
            content: Feng.ctxPath + '/category/to/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Category.tableId);
            }
        });
    };


    /**
     * 点击编辑分类信息
     *
     * @param data 点击按钮时候的行数据
     */
    Category.onEditCategory = function (data) {

        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改修改',
            content: Feng.ctxPath + '/category/to/update?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Category.tableId);
            }
        });
    };

    /**
     * 点击删除分类
     *
     * @param data 点击按钮时候的行数据
     */
    Category.onDeleteCategory = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/category/delete", function () {
                Feng.success("删除成功!");
                table.reload(Category.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除分类 " + data.categoryName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Category.tableId,
        url: Feng.ctxPath + '/category/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Category.initColumn()
    });


    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Category.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Category.openAddCategory();
    });



    // 工具条点击事件
    table.on('tool(' + Category.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            console.log('edit..');
            Category.onEditCategory(data);
        } else if (layEvent === 'delete') {
            Category.onDeleteCategory(data);
        }
    });
});
