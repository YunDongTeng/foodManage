package com.food.manage.modular.comment.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.food.manage.modular.base.entity.Result;
import com.food.manage.modular.comment.entity.Comment;
import com.food.manage.modular.comment.mapper.CommentMapper;
import com.food.manage.modular.food.entity.Food;
import com.food.manage.modular.food.mapper.FoodMapper;
import com.food.manage.modular.user.entity.UserInfo;
import com.food.manage.modular.user.mapper.UserInfoMapper;
import com.food.manage.core.common.page.LayuiPageFactory;
import com.food.manage.core.util.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private FoodMapper foodMapper;

    public Result saveComment(Comment comment){

        if (ToolUtil.isOneEmpty(comment,comment.getContent(),comment.getFromUser(),comment.getFoodId())) {
            throw new RequestEmptyException();
        }
        comment.setId(IDUtil.getId()+"");
        comment.setCreateTime(new Date());
        commentMapper.insert(comment);

        return new Result().success(comment);
    }
    public Page<Map<String, Object>> list(String dd) {
        Page page = LayuiPageFactory.defaultPage();

        Page<Map<String, Object>> pages = commentMapper.list(page);

        List<Map<String, Object>> dataList = pages.getRecords();

        for (Map<String, Object> dataMap : dataList) {
            String foodId = (String) dataMap.get("foodId");


            Food food = foodMapper.selectById(foodId);

            if(food!=null){
                dataMap.put("title",food.getTitle());
            }
            String userId = (String) dataMap.get("fromUser");

           UserInfo userInfo =  userInfoMapper.selectById(userId);

           if(userInfo!=null){
               dataMap.put("userName",userInfo.getNickname());
           }
        }
        return pages;
    }
}
