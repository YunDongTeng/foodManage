/**
 * 商品详情对话框
 */
var SaleInfoDlg = {
    data: {
        pid: "",
        pName: ""
    }
}
layui.use(['layer', 'form', 'admin', 'ax', 'laydate'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var laydate = layui.laydate;
    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    laydate.render({
        elem: '#validDate'
    });

    SaleInfoDlg.initColumn = function () {
        return [[
            {type: 'checkbox'},
            {field: 'productName', hide: true, sort: true, title: '商品名称'},
            {field: 'productNo', sort: true, title: '编号'},
            {field: 'category', sort: true, title: '分类'},
            {field: 'price', sort: true, title: '单价',minWidth: 60},
            {field: 'totalPrice', sort: true, title: '总价'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]];
    };

    table.render({
        elem: '#' + SaleInfoDlg.tableId,
        url: Feng.ctxPath + '/sale/list',
        page: true,
        height: "full-98",
        cellMinWidth: 100,
        cols: SaleInfoDlg.initColumn()
    });
    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/goods/add", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加成功！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});