package com.food.manage.modular.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.food.manage.modular.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo queryByUsername(String username);

    Page<Map<String, Object>> list(@Param("page") Page page, @Param("userName") String userName);

    void updatePwd(@Param("password") String password, @Param("username") String username);
}
