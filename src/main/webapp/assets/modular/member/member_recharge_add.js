/**
 * 角色详情对话框
 */

var MemberRechargeInfoDlg = {
    data: {
        pid: "",
        pName: ""
    }
/*    validateFields: {
        memberName: {
            validators: {
                stringLength: {
                    max: 20,
                    message: '姓名过长'
                }
            }
        }
    }*/
};


layui.use(['layer', 'form', 'admin', 'laydate', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var laydate = layui.laydate;


    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    /*  // 点击上级角色时
      $('#pName').click(function () {
          var formName = encodeURIComponent("parent.MemberInfoDlg.data.pName");
          var formId = encodeURIComponent("parent.MemberInfoDlg.data.pid");
          var treeUrl = encodeURIComponent("/dept/tree");

          layer.open({
              type: 2,
              title: '父级部门',
              area: ['300px', '200px'],
              content: Feng.ctxPath + '/system/commonTree?formName=' + formName + "&formId=" + formId + "&treeUrl=" + treeUrl,
              end: function () {
                  $("#pid").val(DeptInfoDlg.data.pid);
                  $("#pName").val(DeptInfoDlg.data.pName);
              }
          });
      });*/
    // 渲染时间选择框
    /*  laydate.render({
          elem: '#birthday'
      });*/

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {

        var ajax = new $ax(Feng.ctxPath + "/memberRecharge/add", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});

/*MemberInfoDlg.validate = function(){
    $('#memberForm').data("bootstrapValidator").resetForm();
    $('#memberForm').bootstrapValidator('validate');
    return $("#memberForm").data('bootstrapValidator').isValid();

}*/

/*
$(document).ready(function() {
  //  Feng.initValidator("memberForm", MemberInfoDlg.validateFields);


    $('#memberForm').bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: MemberInfoDlg.validateFields,
        live: 'enabled',
        message: '该字段不能为空'
    });
});*/
