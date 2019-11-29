package com.nochi.pet.manage.modular.comment.controller;

import com.nochi.pet.manage.modular.base.entity.Result;
import com.nochi.pet.manage.modular.comment.entity.Comment;
import com.nochi.pet.manage.modular.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 评论
     * @param comment
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Comment comment){
        return commentService.saveComment(comment);
    }
}
