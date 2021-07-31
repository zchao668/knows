package com.work.knows.service;

import com.work.knows.domain.Ebook;
import com.work.knows.domain.EbookExample;
import com.work.knows.mapper.EbookMapper;
import com.work.knows.req.EbookReq;
import com.work.knows.resp.EbookResp;
import com.work.knows.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    EbookMapper ebookMapper;

    //封装返回参数EbookResp   请求参数EbookReq
    public List<EbookResp> list(EbookReq ebookReq){
        //模糊查询name
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        //动态Sql，如果参数为空，则不选择参数  name有参数值按照name查，无值时按照全部查
        if (!ObjectUtils.isEmpty(ebookReq.getName())) {
            criteria.andNameLike("%" + ebookReq.getName() + "%");
        }
        List<Ebook> ebooklist = ebookMapper.selectByExample(ebookExample);

        //可以替代底下的列表复制
//        ArrayList<EbookResq> resqList = new ArrayList<>();
//        for(Ebook ebook: ebooklist){
//            EbookResq ebookResq = new EbookResq();
//            //工具类 复制list中每个元素
//            BeanUtils.copyProperties(ebook,ebookResq);
        //对象复制
//        EbookResq ebookResq = CopyUtil.copy(ebook, EbookResq.class);
//            resqList.add(ebookResq);
//        }
        //列表复制
        List<EbookResp> list = CopyUtil.copyList(ebooklist, EbookResp.class);
        return list;
    }
}
