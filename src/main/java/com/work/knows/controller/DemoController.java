package com.work.knows.controller;

import com.work.knows.domain.Demo;
import com.work.knows.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    DemoService demoService;

    @RequestMapping("/list1")
    public List<Demo> demo(){
        return demoService.list();
    }
}
