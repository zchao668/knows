package com.work.knows.service;

import com.work.knows.domain.Ebook;
import com.work.knows.domain.EbookExample;
import com.work.knows.mapper.EbookMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    EbookMapper ebookMapper;

    public List<Ebook> list(String name){
        //模糊查询name
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + name + "%");
        List<Ebook> list = ebookMapper.selectByExample(ebookExample);
        return list;
    }
}
