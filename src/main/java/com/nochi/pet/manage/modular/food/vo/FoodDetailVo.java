package com.nochi.pet.manage.modular.food.vo;

import com.nochi.pet.manage.modular.food.entity.Food;
import com.nochi.pet.manage.modular.user.entity.UserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoodDetailVo {

    private String id;
    private String title;
    private String summary;
    private String content;
    private String img;
    private Integer status=1;
    private Integer praise=0;
    private Date createTime;
    private String nickName;

    private List<String> imageArray = new ArrayList<>();


    private List<CommentVo> commentVoList = new ArrayList<>();


    public List<String> getImageArray() {
        return imageArray;
    }

    public void setImageArray(List<String> imageArray) {
        this.imageArray = imageArray;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public List<CommentVo> getCommentVoList() {
        return commentVoList;
    }

    public void setCommentVoList(List<CommentVo> commentVoList) {
        this.commentVoList = commentVoList;
    }
}
