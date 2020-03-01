package com.cj.bishe.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test1 {

    @RequestMapping("/")
    public String show () {
        return "Hello World!";
    }
}
