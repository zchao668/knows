package com.work.knows.service;

import com.work.knows.domain.Demo;
import com.work.knows.mapper.DemoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DemoService {

    @Resource
    DemoMapper demoMapper;

    public List<Demo> list(){
        List<Demo> list = demoMapper.selectByExample(null);
        return list;
    }
}
