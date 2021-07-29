package com.work.knows.controller;

import com.work.knows.domain.Test;
import com.work.knows.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HelloController {

    @Resource
    TestService testService;


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
        return "hello worldddssdd" + name;
    }

    @RequestMapping("/test/list")
    public List<Test> test(){
        return testService.list();
    }

}
