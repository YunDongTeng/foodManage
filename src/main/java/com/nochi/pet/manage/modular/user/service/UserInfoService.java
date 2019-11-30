package com.nochi.pet.manage.modular.user.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nochi.pet.manage.core.common.exception.BizExceptionEnum;
import com.nochi.pet.manage.core.common.page.LayuiPageFactory;
import com.nochi.pet.manage.core.util.IDUtil;
import com.nochi.pet.manage.core.util.MD5Util;
import com.nochi.pet.manage.modular.base.entity.Result;
import com.nochi.pet.manage.modular.user.entity.UserInfo;
import com.nochi.pet.manage.modular.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {

    @Autowired
    private UserInfoMapper userInfoMapper;


    public Page<Map<String, Object>> list(String userName) {
        Page page = LayuiPageFactory.defaultPage();

        Page<Map<String, Object>> pages = userInfoMapper.list(page, userName);

        return pages;
    }

    /**
     * 获取用户详情
     *
     * @param userId
     * @return
     */
    public UserInfo get(String userId) {
        return getById(userId);
    }

    /**
     * 用户登陆
     *
     * @param username
     * @param password
     * @return
     */
    public Result login(String username, String password) {
        UserInfo userInfo = userInfoMapper.queryByUsername(username);
        if (userInfo == null) {
            return new Result().fail("不存在该用户");
        }

        if (!userInfo.getPassword().equals(MD5Util.getMD5String(password))) {
            return new Result().fail("密码错误");
        }

        return new Result().success(userInfo);
    }

    /**
     * 用户注册
     *
     * @param userInfo
     * @return
     */
    public Result register(UserInfo userInfo) {
        if (ToolUtil.isOneEmpty(userInfo, userInfo.getUsername(), userInfo.getNickname(), userInfo.getPassword())) {
            throw new RequestEmptyException();
        }
        UserInfo existUser = userInfoMapper.queryByUsername(userInfo.getUsername());
        if (existUser != null) {
            throw new ServiceException(BizExceptionEnum.USER_ALREADY_REG);
        }
        userInfo.setId(String.valueOf(IDUtil.getId()));
        userInfo.setPassword(MD5Util.getMD5String(userInfo.getPassword()));
        userInfo.setCreateTime(new Date());
        this.save(userInfo);

        return new Result().success(userInfo);
    }

    public Object delete(String id){
        return this.removeById(id);
    }

    /**
     * 更新密码
     *
     * @param userInfo
     * @return
     */
    public Result updatePwd(UserInfo userInfo) throws Exception {
        if (ToolUtil.isOneEmpty(userInfo.getUsername(), userInfo.getPassword())) {
            throw new RequestEmptyException();
        }
        UserInfo userInfo1 = this.userInfoMapper.queryByUsername(userInfo.getUsername());
        if (userInfo1 == null) {
            return new Result().fail("用户名不存在");
        }
        userInfo1.setPassword(MD5Util.getMD5String(userInfo.getPassword()));
        userInfoMapper.updatePwd(userInfo1.getPassword(), userInfo1.getUsername());

        return new Result().success(userInfo1);
    }


    /**
     * 用户信息修改
     *
     * @param userInfo
     * @return
     */
    public UserInfo update(UserInfo userInfo) {
        if (ToolUtil.isOneEmpty(userInfo, userInfo.getId(), userInfo.getUsername(), userInfo.getNickname())) {
            throw new RequestEmptyException();
        }
        this.updateById(userInfo);
        return userInfo;
    }

}
