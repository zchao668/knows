package com.work.knows.service;

import com.work.knows.domain.Ebook;
import com.work.knows.domain.EbookExample;
import com.work.knows.mapper.EbookMapper;
import com.work.knows.req.EbookReq;
import com.work.knows.resp.EbookResq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    EbookMapper ebookMapper;

    //封装返回参数EbookResq   请求参数EbookRep
    public List<EbookResq> list(EbookReq ebookReq){
        //模糊查询name
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + ebookReq.getName() + "%");
        List<Ebook> ebooklist = ebookMapper.selectByExample(ebookExample);

        ArrayList<EbookResq> resqList = new ArrayList<>();
        for(Ebook ebook: ebooklist){
            EbookResq ebookResq = new EbookResq();
            //工具类 复制list中每个元素
            BeanUtils.copyProperties(ebook,ebookResq);
            resqList.add(ebookResq);
        }
        return resqList;
    }
}
