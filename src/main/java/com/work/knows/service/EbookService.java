package com.work.knows.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.knows.domain.Ebook;
import com.work.knows.domain.EbookExample;
import com.work.knows.mapper.EbookMapper;
import com.work.knows.req.EbookReq;
import com.work.knows.resp.EbookResp;
import com.work.knows.resp.PageResp;
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
    public PageResp<EbookResp> list(EbookReq ebookReq){
        //模糊查询name
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        //动态Sql，如果参数为空，则不选择参数  name有参数值按照name查，无值时按照全部查
        if (!ObjectUtils.isEmpty(ebookReq.getName())) {
            criteria.andNameLike("%" + ebookReq.getName() + "%");
        }
        //支持分页，一页，三个
        PageHelper.startPage(ebookReq.getPage(),ebookReq.getSize());
        List<Ebook> ebooklist = ebookMapper.selectByExample(ebookExample);

        //分页 可以获得总行数，总页数(就是总数全部一般为list)
        PageInfo<Ebook> pageInfo = new PageInfo<>(ebooklist);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());

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

        //使返回值为PageResp类型
        PageResp<EbookResp> pageResp = new PageResp<>();
        pageResp.setList(list);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }
}
