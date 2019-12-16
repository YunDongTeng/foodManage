package com.food.manage.modular.comment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.food.manage.modular.base.entity.Result;
import com.food.manage.modular.comment.entity.Comment;
import com.food.manage.modular.system.warpper.UserWrapper;
import com.food.manage.core.common.page.LayuiPageFactory;
import com.food.manage.modular.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    private String PREFIX = "/modular/comment/";
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

    @RequestMapping("/toList")
    public String toList(){
        return PREFIX+"comment.html";
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public Object delete(@PathVariable String id){
        commentService.removeById(id);
        return new Result().success(true);
    }

    @RequestMapping("/list")
    @ResponseBody
    public Object list(){

        Page<Map<String, Object>> list = commentService.list("");

        Page<Map<String, Object>> wrap = new UserWrapper(list).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }
}
