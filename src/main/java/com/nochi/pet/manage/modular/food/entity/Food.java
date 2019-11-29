package com.nochi.pet.manage.modular.food.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.beans.Transient;
import java.util.Date;

@TableName("tb_food")
public class Food {

    private String id;
    private String title;
    private String content;
    private String img;
    private Integer status=1;
    private Integer praise=0;
    private Date createTime;
    private String userId;

    public Food() {
    }

    public Food(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
