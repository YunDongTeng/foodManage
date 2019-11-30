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
import com.nochi.pet.manage.modular.system.entity.User;
import com.nochi.pet.manage.modular.user.entity.UserInfo;
import com.nochi.pet.manage.modular.user.mapper.UserInfoMapper;
import io.jsonwebtoken.lang.Collections;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FoodService extends ServiceImpl<FoodMapper, Food> {

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CommentMapper commentMapper;


    public void deleteFood(String id) {
        this.removeById(id);
    }

    /**
     * 获取美食详情，包括评论和回复信息
     *
     * @param foodId
     * @return
     */
    public FoodDetailVo detail(String foodId) {
        Food food = foodMapper.selectById(foodId);
        if (food == null) {
            throw new RequestEmptyException();
        }
        FoodDetailVo foodDetailVo = new FoodDetailVo();

        BeanUtils.copyProperties(food, foodDetailVo);
        UserInfo userInfo = getUser(food.getUserId());
        foodDetailVo.setNickName(userInfo.getNickname());
        foodDetailVo.setUserId(userInfo.getId());

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
                UserInfo commentUser = getUser(comment.getFromUser());
                commentVo.setNickName(commentUser == null ? "匿名用户" : commentUser.getNickname());
                commentVo.setUserId(commentUser.getId());
                //查询该评论下的回复内容
                List<CommentVo> replies = new ArrayList<>();

                List<Comment> replyList = commentMapper.getReplyByCid(comment.getId());
                for (Comment reply : replyList) {
                    CommentVo replyVo = new CommentVo(reply.getId(), reply.getContent(), reply.getCreateTime());
                    UserInfo replyUser = getUser(reply.getFromUser());
                    replyVo.setNickName(replyUser == null ? "匿名用户" : replyUser.getNickname());
                    replyVo.setUserId(replyUser.getId());
                    replyVo.setReplyFlag(true);
                    replies.add(replyVo);
                }
                commentVo.setReplyArray(replies);
                commentVos.add(commentVo);
            }
        }
        foodDetailVo.setCommentVoList(commentVos);

        return foodDetailVo;
    }

    public UserInfo getUser(String userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        return userInfo;
    }

    /**
     * 保存
     *
     * @param food
     * @return
     */
    public Food saveFood(Food food) {
        if (ToolUtil.isOneEmpty(food, food.getTitle())) {
            throw new RequestEmptyException();
        }

        if (StringUtils.isEmpty(food.getId())) {
            food.setId(IDUtil.getId() + "");
            food.setCreateTime(new Date());
            this.save(food);
        } else {
            this.updateById(food);
        }

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
         /*   String img = (String) dataMap.get("img");
            if (!StringUtils.isEmpty(img)) {
                String[] imageArray = img.split(";");
                dataMap.put("imageArray", imageArray);


            }
*/
            String content = (String) dataMap.get("content");
            if (!StringUtils.isEmpty(content)) {
                List<String> images = getImgStr(content);
                dataMap.put("imageArray", images);
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

    public static List<String> getImgStr(String htmlStr) {
        List<String> list = new ArrayList<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        // String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                list.add(m.group(1));
            }
        }
        return list;
    }

    public static String removeImg(String htmlStr) {
        String img = "";
        Pattern p_image;
        Matcher m_image;
        // String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                htmlStr.replace(img, "");
            }
        }
        return null;
    }
}
