package com.work.knows.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //get put 浏览器查询只支持get
    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
