package com.food.manage.core.util;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMD5String(String password){
        //通过信息摘要单例的构造函数获取md5加密对象
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String md5Str = "";
        try {
            //信息摘要对象是对字节数组进行摘要的,所以先获取字符串的字节数组.
            byte[] bytes = password.getBytes("utf-8");
            //信息摘要对象对字节数组进行摘要,得到摘要字节数组:
            byte[] digest = md5.digest(bytes);
            //利用BASE64Encoder编码加密
            md5Str = base64en.encode(digest);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5Str;
    }

}
