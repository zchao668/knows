package com.work.knows.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    //get put request delete 浏览器查询只支持get
//    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @RequestMapping("/hello")
    public String hello(){
        return "hello World";
    }

    @PostMapping("/hello/post")
    public String hello1(String name){
        return "hello world" + name;
    }
}
