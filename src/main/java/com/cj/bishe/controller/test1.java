package com.cj.bishe.controller;


import com.alibaba.fastjson.JSONObject;
import com.cj.bishe.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test1 {

    @RequestMapping("/")
    public String show () {
        return "Hello World!";
    }

    public static void main(String[] args) {
        User user = new User();
        user.setName("张三");
        user.setUsername("qqq");
        user.setPwd("123");
        user.setType("1");
        System.out.println(JSONObject.toJSON(user).toString());
    }
}
