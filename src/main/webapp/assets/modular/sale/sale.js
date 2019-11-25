layui.use(['table', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var laydate = layui.laydate;

    /**
     * 系统管理--部门管理
     */
    var Sale = {
        tableId: "SaleTable",
        condition: {
            productName: '',
            buyPhone: '',
            startDate: '',
            endDate: ''
        }
    };

    /**
     * 初始化表格的列
     */
    Sale.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'buyer', sort: true, title: '购买人'},
            {field: 'buyPhone', sort: true, title: '手机号'},
            {field: 'totalPrice', sort: true, title: '金额',minWidth: 60},
            {field: 'createTime', sort: true, title: '添加时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Sale.search = function () {
        var queryData = {};
        queryData['buyPhone'] = $("#searchBuyPhone").val();
        queryData['startDate'] = $("#searchStartDate").val();
        queryData['endDate'] = $("#searchEndDate").val();
        table.reload(Sale.tableId, {where: queryData});
    };

    /**
     * 弹出添加
     */
    Sale.openAddSale = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加商品',
            area: ['760px', '660px'],
            content: Feng.ctxPath + '/sale/to/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Sale.tableId);
            }
        });
    };



    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Sale.tableId,
        url: Feng.ctxPath + '/sale/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: Sale.initColumn()
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
        Sale.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Sale.openAddSale();
    });



    // 工具条点击事件
    table.on('tool(' + Sale.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

    });
});
