package com.nochi.pet.manage.modular.user.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nochi.pet.manage.core.common.page.LayuiPageFactory;
import com.nochi.pet.manage.core.log.LogObjectHolder;
import com.nochi.pet.manage.modular.base.entity.Result;
import com.nochi.pet.manage.modular.system.warpper.DeptWrapper;
import com.nochi.pet.manage.modular.user.entity.UserInfo;
import com.nochi.pet.manage.modular.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/userInfo")
@Controller
public class UserInfoController extends BaseController {


    private String PREFIX = "/modular/user/";

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/toList")
    public String toList() {
        return PREFIX + "user.html";
    }

    /**
     * 添加页面
     *
     * @return
     */
    @GetMapping("/to/add")
    public String toAdd() {
        return PREFIX + "user_add.html";
    }

    /**
     * 修改页面
     *
     * @return
     */
    @GetMapping("/to/update")
    public String toEdit(@RequestParam("id") String id) {
        if (ToolUtil.isEmpty(id)) {
            throw new RequestEmptyException();
        }
        UserInfo userInfo = userInfoService.get(id);
        LogObjectHolder.me().set(userInfo);
        return PREFIX + "user_edit.html";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(value = "userName", required = false) String userName) {
        Page<Map<String, Object>> list = this.userInfoService.list(userName);

        Page<Map<String, Object>> wrap = new DeptWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }


    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(String username, String password) {
        return userInfoService.login(username, password);
    }

    /**
     * 用户注册
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public Result register(UserInfo userInfo) {
        return userInfoService.register(userInfo);
    }

    @PostMapping("/update")
    @ResponseBody
    public Result update(UserInfo userInfo) {
        return new Result().success(userInfoService.update(userInfo));
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam("id") String id) {
        this.userInfoService.delete(id);
        return SUCCESS_TIP;
    }

    /**
     * 更新密码
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/updatePwd")
    @ResponseBody
    public Result updatePwd(UserInfo userInfo) throws Exception {
        return userInfoService.updatePwd(userInfo);
    }

    /**
     * 获取用户详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/get/{id}")
    @ResponseBody
    public Result get(@PathVariable String id) {
        return new Result().success(userInfoService.get(id));
    }
}
