package com.nochi.pet.manage.modular.comment.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nochi.pet.manage.core.util.IDUtil;
import com.nochi.pet.manage.modular.base.entity.Result;
import com.nochi.pet.manage.modular.comment.entity.Comment;
import com.nochi.pet.manage.modular.comment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {

    @Autowired
    private CommentMapper commentMapper;

    public Result saveComment(Comment comment){

        if (ToolUtil.isOneEmpty(comment,comment.getContent(),comment.getFromUser(),comment.getFoodId())) {
            throw new RequestEmptyException();
        }
        comment.setId(IDUtil.getId()+"");
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);

        return new Result().success(comment);
    }

}
