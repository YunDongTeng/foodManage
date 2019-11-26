package com.nochi.pet.manage.modular.food.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nochi.pet.manage.core.common.page.LayuiPageFactory;
import com.nochi.pet.manage.core.util.IDUtil;
import com.nochi.pet.manage.modular.comment.entity.Comment;
import com.nochi.pet.manage.modular.comment.mapper.CommentMapper;
import com.nochi.pet.manage.modular.food.entity.Food;
import com.nochi.pet.manage.modular.food.mapper.FoodMapper;
import com.nochi.pet.manage.modular.food.vo.CommentVo;
import com.nochi.pet.manage.modular.food.vo.FoodDetailVo;
import com.nochi.pet.manage.modular.user.entity.UserInfo;
import com.nochi.pet.manage.modular.user.mapper.UserInfoMapper;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FoodService extends ServiceImpl<FoodMapper, Food> {

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CommentMapper commentMapper;


    /**
     * 获取美食详情，包括评论和回复信息
     * @param foodId
     * @return
     */
    public FoodDetailVo detail(String foodId) {
        Food food = foodMapper.selectById(foodId);
        if (food == null) {
            throw new RequestEmptyException();
        }
        FoodDetailVo foodDetailVo = new FoodDetailVo();

        BeanUtils.copyProperties(food,foodDetailVo);
        foodDetailVo.setNickName(getNickName(food.getUserId()));

        if (!StringUtils.isEmpty(food.getImg())) {
            String[] imageArray = food.getImg().split(";");
            foodDetailVo.setImageArray(Collections.arrayToList(imageArray));
        }

        //获取评论数据
        List<Comment> comments = commentMapper.getCommentByFoodId(foodId);

        List<CommentVo> commentVos = new ArrayList<>();

        if (comments != null) {
            for (Comment comment : comments) {
                CommentVo commentVo = new CommentVo(comment.getId(), comment.getContent(), comment.getCreateTime());
                commentVo.setNickName(getNickName(comment.getFromUser()));

                //查询该评论下的回复内容
                List<CommentVo> replies = new ArrayList<>();

                List<Comment> replyList = commentMapper.getReplyByCid(comment.getId());
                for (Comment reply : replyList) {
                    CommentVo replyVo = new CommentVo(reply.getId(), reply.getContent(), reply.getCreateTime());
                    replyVo.setNickName(getNickName(reply.getFromUser()));
                    replies.add(replyVo);
                }
                commentVo.setReplyArray(replies);
                commentVos.add(commentVo);
            }
        }
        foodDetailVo.setCommentVoList(commentVos);

        return foodDetailVo;
    }

    public String getNickName(String userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        return userInfo == null ? "匿名用户" : userInfo.getNickname();
    }

    /**
     * 保存
     *
     * @param food
     * @return
     */
    public Food saveFood(Food food) {
        if (ToolUtil.isOneEmpty(food, food.getTitle(), food.getSummary())) {
            throw new RequestEmptyException();
        }

        food.setId(IDUtil.getId() + "");
        food.setCreateTime(new Date());

        this.save(food);

        return food;
    }

    public Page<Map<String, Object>> list(String title) {
        Page page = LayuiPageFactory.defaultPage();

        Page<Map<String, Object>> pages = foodMapper.list(page, title);

        List<Map<String, Object>> dataList = pages.getRecords();

        for (Map<String, Object> dataMap : dataList) {
            String foodId = (String) dataMap.get("id");

            //评论数量
            List<Comment> cList = commentMapper.getCommentByFoodId(foodId);
            if (cList != null) {
                dataMap.put("commentNum", cList.size());
            }

            //图片
            String img = (String) dataMap.get("img");
            if (!StringUtils.isEmpty(img)) {
                String[] imageArray = img.split(";");
                dataMap.put("imageArray", imageArray);
            }


        }
        return pages;
    }

    public List<Food> topTen() {
        return foodMapper.topTen();
    }

    /**
     * 点赞
     *
     * @param foodId
     * @return
     */
    public boolean zan(String foodId) {

        Food food = this.getById(foodId);
        if (food != null) {
            food.setPraise(food.getPraise() + 1);
            this.saveOrUpdate(food);
        }
        return true;
    }

    /**
     * 获取详情
     *
     * @param foodId
     * @return
     */
    public Food getOne(String foodId) {
        return this.getById(foodId);
    }


}
