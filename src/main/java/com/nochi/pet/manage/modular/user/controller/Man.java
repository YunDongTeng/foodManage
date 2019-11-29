package com.nochi.pet.manage.modular.user.controller;

import org.springframework.web.util.HtmlUtils;

public class Man {

    public static void main(String[] args) {
        System.out.println( HtmlUtils.htmlUnescape("& lt;p& gt;sdfasdf& lt;img src=\"http://122.51.148.46/food/photo/2019/11/29//1575025656614.jpeg\" style=\"max-width: 100%;\"& gt;& lt;/p& gt;& lt;p& gt;asdfasdf& lt;/p& gt;".replace(" ","")));
    }
}
