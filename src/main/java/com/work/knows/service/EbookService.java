package com.work.knows.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.knows.domain.Ebook;
import com.work.knows.domain.EbookExample;
import com.work.knows.mapper.EbookMapper;
import com.work.knows.req.EbookQueryReq;
import com.work.knows.req.EbookSaveReq;
import com.work.knows.resp.EbookQueryResp;
import com.work.knows.resp.PageResp;
import com.work.knows.util.CopyUtil;
import com.work.knows.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    EbookMapper ebookMapper;

    @Resource
    SnowFlake snowFlake;

    //查询list
    //封装返回参数EbookResp   请求参数EbookReq
    public PageResp<EbookQueryResp> list(EbookQueryReq ebookQueryReq){
        //根据某个条件执行操作，运用到EbookExample
        //模糊查询name
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        //动态Sql，如果参数为空，则不选择参数  name有参数值按照name查，无值时按照全部查
        if (!ObjectUtils.isEmpty(ebookQueryReq.getName())) {
            criteria.andNameLike("%" + ebookQueryReq.getName() + "%");
        }
        //动态Sql查询分类下的电子书
        if (!ObjectUtils.isEmpty(ebookQueryReq.getCategory2Id())) {
            criteria.andCategory2IdEqualTo (ebookQueryReq.getCategory2Id());
        }

        //支持分页，一页，三个
        PageHelper.startPage(ebookQueryReq.getPage(), ebookQueryReq.getSize());
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
        List<EbookQueryResp> list = CopyUtil.copyList(ebooklist, EbookQueryResp.class);

        //使返回值为PageResp类型
        PageResp<EbookQueryResp> pageResp = new PageResp<>();
        pageResp.setList(list);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }

    //主键自增
    private static long initId = 1003;

    /**
     * 保存
     */
    public void save(EbookSaveReq req) {
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            ebook.setId(initId++);
            ebookMapper.insert(ebook);
        } else {
            // 更新
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }

    public void delete(Long id){
        ebookMapper.deleteByPrimaryKey(id);
    }
}
