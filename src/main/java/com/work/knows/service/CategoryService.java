package com.work.knows.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.work.knows.domain.Category;
import com.work.knows.domain.CategoryExample;
import com.work.knows.mapper.CategoryMapper;
import com.work.knows.req.CategoryQueryReq;
import com.work.knows.req.CategorySaveReq;
import com.work.knows.resp.CategoryQueryResp;
import com.work.knows.resp.PageResp;
import com.work.knows.util.CopyUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    CategoryMapper categoryMapper;


    //查询所有，没有分页功能
    public List<CategoryQueryResp> all(){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        // 列表复制  为了让返回类型为CategoryQueryResp
        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);
        return list;
    }

    //查询list(可以按照名字查询，支持分页)
    //封装返回参数CategoryResp   请求参数CategoryReq
    public PageResp<CategoryQueryResp> list(CategoryQueryReq categoryQueryReq){
        CategoryExample categoryExample = new CategoryExample();

        //支持分页，一页，三个
        PageHelper.startPage(categoryQueryReq.getPage(), categoryQueryReq.getSize());
        List<Category> categorylist = categoryMapper.selectByExample(categoryExample);

        //分页 可以获得总行数，总页数(就是总数全部一般为list)
        PageInfo<Category> pageInfo = new PageInfo<>(categorylist);
        System.out.println("总行数："+pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());

        //列表复制
        List<CategoryQueryResp> list = CopyUtil.copyList(categorylist, CategoryQueryResp.class);

        //使返回值为PageResp类型
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setList(list);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }

    //主键自增
    private static long initId = 1001;

    /**
     * 保存
     */
    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            category.setId(initId++);
            categoryMapper.insert(category);
        } else {
            // 更新
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    public void delete(Long id){
        categoryMapper.deleteByPrimaryKey(id);
    }
}
