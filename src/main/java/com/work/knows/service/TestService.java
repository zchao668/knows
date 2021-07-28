package com.work.knows.service;

import com.work.knows.domain.Test;
import com.work.knows.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    TestMapper testMapper;

    public List<Test> list(){
        List<Test> list = testMapper.list();
        return list;
    }
}
