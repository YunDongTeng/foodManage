package com.nochi.pet.manage.modular.food.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nochi.pet.manage.modular.base.entity.Result;
import com.nochi.pet.manage.modular.food.entity.Food;
import com.nochi.pet.manage.modular.food.service.FoodService;
import com.nochi.pet.manage.modular.system.warpper.DeptWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/food")
public class FoodController {


    @Autowired
    private FoodService foodService;

    /**
     * 获取美食信息
     *
     * @param title
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result list(@RequestParam(value = "title", required = false) String title) {
        Page<Map<String, Object>> list = foodService.list(title);
        Page<Map<String, Object>> wrap = new DeptWrapper(list).wrap();
        return new Result().success(wrap);
    }

    @RequestMapping("/detail/{id}")
    @ResponseBody
    public Result detail(@PathVariable("id") String id) {
        return new Result().success(foodService.detail(id));
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public Result detete(@PathVariable("id") String id) {
        foodService.deleteFood(id);
        return new Result().success(true);
    }

    /**
     * 点赞
     *
     * @param id
     * @return
     */
    @RequestMapping("/zan")
    @ResponseBody
    public Result topTen(@RequestParam("id") String id) {
        return new Result().success(foodService.zan(id));
    }

    /**
     * 获取到前10条美食信息(点赞排行)
     *
     * @return
     */
    @RequestMapping("/topTen")
    @ResponseBody
    public Result topTen() {
        return new Result().success(foodService.topTen());
    }


    @RequestMapping("/save")
    @ResponseBody
    public Result save(HttpServletRequest request) {

        Map<String, String[]> parameterMap = request.getParameterMap();

        Food food = new Food();
        for (Map.Entry entry : parameterMap.entrySet()) {
            if (entry.getKey().equals("title")) {
                String[] titleArray = (String[]) entry.getValue();
                food.setTitle(titleArray[0]);
                continue;
            }
            if (entry.getKey().equals("content")) {
                String[] contentArray = (String[]) entry.getValue();
                food.setContent(contentArray[0]);
                continue;
            }
            if (entry.getKey().equals("userId")) {
                String[] userArray = (String[]) entry.getValue();
                food.setUserId(userArray[0]);
                continue;
            }
        }
        return new Result().success(foodService.saveFood(food));
    }

}
