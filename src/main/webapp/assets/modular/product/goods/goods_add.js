/**
 * 商品详情对话框
 */
var GoodsDialog = {
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

    $("#categoryName").click(function () {

        console.log('click...')
        var formName = encodeURIComponent("parent.GoodsDialog.data.pName");
        var formId = encodeURIComponent("parent.GoodsDialog.data.pid");
        var treeUrl = encodeURIComponent("/category/tree");

        layer.open({
            type: 2,
            title: '分类',
            area: ['300px', '350px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#categoryId").val(GoodsDialog.data.pid);
                $("#categoryName").val(GoodsDialog.data.pName);
            }
        });
    })
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