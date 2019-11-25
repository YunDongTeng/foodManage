
var GoodsDialog = {
    data: {
        pid: "",
        pName: ""
    }
}
layui.use(['layer', 'form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var laydate = layui.laydate;


    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    laydate.render({
        elem: '#validDate'
    });
    var ajax = new $ax(Feng.ctxPath + "/goods/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('goodsForm', result);

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
        var ajax = new $ax(Feng.ctxPath + "/goods/update", function (data) {
            Feng.success("修改成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});


