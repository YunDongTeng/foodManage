package com.nochi.pet.manage.modular.food.service;

import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nochi.pet.manage.core.common.page.LayuiPageFactory;
import com.nochi.pet.manage.core.util.IDUtil;
import com.nochi.pet.manage.modular.food.entity.Food;
import com.nochi.pet.manage.modular.food.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FoodService extends ServiceImpl<FoodMapper, Food> {

    @Autowired
    private FoodMapper foodMapper;


    /**
     * 保存
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
        return foodMapper.list(page, title);
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
