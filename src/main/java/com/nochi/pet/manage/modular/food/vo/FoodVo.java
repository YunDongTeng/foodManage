package com.nochi.pet.manage.modular.food.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoodVo {

    private String id;
    private String title;
    private String summary;
    private String content;
    private List<String> imgArray = new ArrayList<String>();
    private Integer status;
    private Integer praise;
    private Date createTime;
    private String nickName;
    private Integer commentNum;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
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

    public List<String> getImgArray() {
        return imgArray;
    }

    public void setImgArray(List<String> imgArray) {
        this.imgArray = imgArray;
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
