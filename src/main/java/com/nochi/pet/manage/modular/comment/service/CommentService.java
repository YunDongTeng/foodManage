package com.nochi.pet.manage.modular.comment.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nochi.pet.manage.modular.comment.entity.Comment;
import com.nochi.pet.manage.modular.comment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {

    @Autowired
    private CommentMapper commentMapper;


}
