package com.nochi.pet.manage.modular.user.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import com.nochi.pet.manage.modular.base.entity.Result;
import com.nochi.pet.manage.modular.user.entity.UserInfo;
import com.nochi.pet.manage.modular.user.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/userInfo")
@Controller
public class UserInfoController extends BaseController {


    @Autowired
    private UserInfoService userInfoService;

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result login(@RequestParam String username, String password) {
        return new Result().success(userInfoService.login(username, password));
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
        return new Result().success(userInfoService.register(userInfo));
    }

    /**
     * 更新密码
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/updatePwd")
    @ResponseBody
    public Result updatePwd(UserInfo userInfo) {
        return new Result().success(userInfoService.updatePwd(userInfo));
    }

    /**
     * 获取用户详情
     *
     * @param userId
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public Result get(String userId) {
        return new Result().success(userInfoService.get(userId)
        );
    }
}
