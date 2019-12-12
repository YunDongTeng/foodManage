package com.nochi.pet.manage.modular.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("tb_user")
public class UserInfo {

    private String id;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String phone;
    private Date createTime;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
