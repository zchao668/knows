package com.work.knows.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.knows.domain.Doc;
import com.work.knows.domain.DocExample;
import com.work.knows.mapper.DocMapper;
import com.work.knows.req.DocQueryReq;
import com.work.knows.req.DocSaveReq;
import com.work.knows.resp.DocQueryResp;
import com.work.knows.resp.PageResp;
import com.work.knows.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DocService {

    @Resource
    DocMapper docMapper;


    //查询所有，没有分页功能
    public List<DocQueryResp> all(){
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        List<Doc> docList = docMapper.selectByExample(docExample);

        // 列表复制  为了让返回类型为DocQueryResp
        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);
        return list;
    }

    //查询list(可以按照名字查询，支持分页)
    //封装返回参数DocResp   请求参数DocReq
    public PageResp<DocQueryResp> list(DocQueryReq docQueryReq){
        DocExample docExample = new DocExample();

        //支持分页，一页，三个
        PageHelper.startPage(docQueryReq.getPage(), docQueryReq.getSize());
        List<Doc> doclist = docMapper.selectByExample(docExample);

        //分页 可以获得总行数，总页数(就是总数全部一般为list)
        PageInfo<Doc> pageInfo = new PageInfo<>(doclist);
        System.out.println("总行数："+pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());

        //列表复制
        List<DocQueryResp> list = CopyUtil.copyList(doclist, DocQueryResp.class);

        //使返回值为PageResp类型
        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setList(list);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }

    //主键自增
    private static long initId = 1001;

    /**
     * 保存
     */
    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            doc.setId(initId++);
            docMapper.insert(doc);
        } else {
            // 更新
            docMapper.updateByPrimaryKey(doc);
        }
    }

    public void delete(Long id){
        docMapper.deleteByPrimaryKey(id);
    }
}
