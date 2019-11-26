package com.nochi.pet.manage.modular.food.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nochi.pet.manage.modular.food.entity.Food;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FoodMapper extends BaseMapper<Food> {
    Page<Map<String, Object>> list(@Param("page") Page page, @Param("title") String title);

    List<Food> topTen();
}
