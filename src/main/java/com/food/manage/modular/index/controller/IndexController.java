package com.food.manage.modular.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    private String PREFIX = "/modular/index/";

    @RequestMapping("/home")
    public String home(){
        return PREFIX+"home.html";
    }
}
