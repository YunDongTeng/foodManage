/**
 * 角色详情对话框
 */

layui.use(['layer', 'form', 'admin','ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;

    // 让当前iframe弹层高度适应
    admin.iframeAuto();


    var E = window.wangEditor
    var editor = new E('#editor')

    // 或者 var editor = new E( document.getElementById('editor') )
    editor.customConfig.uploadImgServer = 'http://122.51.148.46/api/file/upload/photo';
    //配置属性名称，绑定请求的图片数据
    //controller会用到，可以随便设置，但是一定要与controller一致
    editor.customConfig.uploadFileName = 'img';
    editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024
    editor.customConfig.uploadImgMaxLength = 5

    editor.create()
    /*editor.txt.html($("#contentVal").val())*/
    //获取富文本编辑器中的内容



    var ajax = new $ax(Feng.ctxPath + "/food/get/" + Feng.getUrlParam("id"));
    var result = ajax.start();

    form.val('foodForm', result.data);
    $("#foodId").val(result.data.id)
    editor.txt.html(result.data.content);

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        $.ajax({
            url: Feng.ctxPath + "/food/save",
            type: 'post',
            dataType: 'json',
            data: {title: $("#title").val(),id: $("#foodId").val(),content: editor.txt.html()},
            success: function (data) {
                Feng.success("修改成功！");

                //传给上个页面，刷新table用
                admin.putTempData('formOk', true);

                //关掉对话框
                admin.closeThisDialog();
            }
        })

    });
});