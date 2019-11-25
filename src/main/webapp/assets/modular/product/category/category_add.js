/**
 * 角色详情对话框
 */
var CategoryInfoDlg = {
    data: {
        pid: "",
        pName: ""
    }
};

layui.use(['layer', 'form', 'admin','ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();



/*    var ajax = new $ax(Feng.ctxPath + "/category/detail?id=" + Feng.getUrlParam("id"));
    var result = ajax.start();
    form.val('categoryForm', result);*/
    $("#pName").click(function () {

        var formName = encodeURIComponent("parent.CategoryInfoDlg.data.pName");
        var formId = encodeURIComponent("parent.CategoryInfoDlg.data.pid");
        var treeUrl = encodeURIComponent("/category/tree?level=1");

        layer.open({
            type: 2,
            title: '分类',
            area: ['300px', '350px'],
            content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
            end: function () {
                $("#pid").val(CategoryInfoDlg.data.pid);
                $("#pName").val(CategoryInfoDlg.data.pName);
            }
        });
    })
    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/category/add", function (data) {
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