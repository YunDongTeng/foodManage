package com.nochi.pet.manage.modular.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nochi.pet.manage.modular.comment.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> getReplyByCid(String cId);

    List<Comment> getCommentByFoodId(String foodId);
}
