package com.nochi.pet.manage.modular.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nochi.pet.manage.modular.user.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    UserInfo queryByUsername(String username);

    void updatePwd(@Param("password")String password,@Param("username")String username);
}
