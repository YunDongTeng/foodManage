layui.use(['table', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var laydate = layui.laydate;

    /**
     * 系统管理--部门管理
     */
    var Goods = {
        tableId: "goodsTable",
        condition: {
            goodsNo: '',
            goodsName: '',
            startDate: '',
            endDate: ''
        }
    };

    /**
     * 初始化表格的列
     */
    Goods.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'goodsNo', sort: true, title: '商品编号'},
            {field: 'goodsName', sort: true, title: '商品名称'},
            {field: 'categoryName', sort: true, title: '商品分类'},
            {field: 'stock', sort: true, title: '库存',minWidth: 60},
            {field: 'salePrice', sort: true, title: '售价',minWidth: 60},
            {field: 'createTime', sort: true, title: '添加时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Goods.search = function () {
        var queryData = {};
        queryData['goodsNo'] = $("#searchGoodsNo").val();
        queryData['goodsName'] = $("#searchGoodsName").val();
        queryData['startDate'] = $("#searchStartDate").val();
        queryData['endDate'] = $("#searchEndDate").val();
        table.reload(Goods.tableId, {where: queryData});
    };

    /**
     * 弹出添加
     */
    Goods.openAddGoods = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加商品',
            area: ['760px', '660px'],
            content: Feng.ctxPath + '/goods/to/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Goods.tableId);
            }
        });
    };


    /**
     * 点击编辑商品
     *
     * @param data 点击按钮时候的行数据
     */
    Goods.onEditGoods = function (data) {

        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '修改商品',
            area: ['760px', '460px'],
            content: Feng.ctxPath + '/goods/to/update?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Goods.tableId);
            }
        });
    };

    /**
     * 点击删除商品
     *
     * @param data 点击按钮时候的行数据
     */
    Goods.onDeleteGoods = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/goods/delete", function () {
                Feng.success("删除成功!");
                table.reload(Goods.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除商品 " + data.goodsName + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Goods.tableId,
        url: Feng.ctxPath + '/goods/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Goods.initColumn()
    });
    laydate.render({
        elem: '#searchStartDate'
    });
    laydate.render({
        elem: '#searchEndDate'
    });
    /* //初始化左侧部门树
     var ztree = new $ZTree("deptTree", "/dept/tree");
     ztree.bindOnClick(Dept.onClickDept);
     ztree.init();*/

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Goods.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Goods.openAddGoods();
    });

    // 导出excel
    $('#btnExp').click(function () {
        Goods.exportExcel();
    });

    // 工具条点击事件
    table.on('tool(' + Goods.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Goods.onEditGoods(data);
        } else if (layEvent === 'delete') {
            Goods.onDeleteGoods(data);
        }
    });
});
