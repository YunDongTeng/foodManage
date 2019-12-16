package com.food.manage.modular.food.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentVo {

    private String id;
    private String content;
    private String nickName;
    private Date createTime;
    private String url;
    private String userId;

    private Boolean replyFlag;
    private List<CommentVo> replyArray = new ArrayList<>();
    public CommentVo(String id, String content, Date createTime) {
        this.id = id;
        this.content = content;
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CommentVo(String id, String content, String nickName, Date createTime) {
        this.id = id;
        this.content = content;
        this.nickName = nickName;
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public CommentVo() {
    }


    public Boolean getReplyFlag() {
        return replyFlag;
    }

    public void setReplyFlag(Boolean replyFlag) {
        this.replyFlag = replyFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<CommentVo> getReplyArray() {
        return replyArray;
    }

    public void setReplyArray(List<CommentVo> replyArray) {
        this.replyArray = replyArray;
    }
}
