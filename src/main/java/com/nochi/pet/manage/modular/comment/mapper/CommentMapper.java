package com.nochi.pet.manage.modular.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nochi.pet.manage.modular.comment.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> getReplyByCid(String cId);

    List<Comment> getCommentByFoodId(String foodId);

    Page<Map<String,Object>> list(@Param("page")Page page);
}
