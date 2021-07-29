package com.work.knows.service;

import com.work.knows.domain.Ebook;
import com.work.knows.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    EbookMapper ebookMapper;

    public List<Ebook> list(){
        List<Ebook> list = ebookMapper.selectByExample(null);
        return list;
    }
}
