package com.work.knows.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    //取得配置文件中的自定义属性,若没写，则默认：后面的内容
    @Value("${test.hello:xixixi}")
    private String testHello;

    //get put request delete 浏览器查询只支持get
    //@RequestMapping(value = "/hello",method = RequestMethod.GET)
    @RequestMapping("/hello")
    public String hello(){
        return "hello World" + testHello;
    }

    @PostMapping("/hello/post")
    public String hello1(String name){
        return "hello world" + name;
    }
}
