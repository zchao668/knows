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
import com.work.knows.util.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {

    @Resource
    CategoryMapper categoryMapper;

    @Resource
    SnowFlake snowFlake;

    //查询list
    //封装返回参数CategoryResp   请求参数CategoryReq
    public PageResp<CategoryQueryResp> list(CategoryQueryReq categoryQueryReq){
        //模糊查询name
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
//        //动态Sql，如果参数为空，则不选择参数  name有参数值按照name查，无值时按照全部查
//        if (!ObjectUtils.isEmpty(categoryQueryReq.getName())) {
//            criteria.andNameLike("%" + categoryQueryReq.getName() + "%");
//        }
        //支持分页，一页，三个
        PageHelper.startPage(categoryQueryReq.getPage(), categoryQueryReq.getSize());
        List<Category> categorylist = categoryMapper.selectByExample(categoryExample);

        //分页 可以获得总行数，总页数(就是总数全部一般为list)
        PageInfo<Category> pageInfo = new PageInfo<>(categorylist);
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());

        //可以替代底下的列表复制
//        ArrayList<CategoryResq> resqList = new ArrayList<>();
//        for(Category category: categorylist){
//            CategoryResq categoryResq = new CategoryResq();
//            //工具类 复制list中每个元素
//            BeanUtils.copyProperties(category,categoryResq);
        //对象复制
//        CategoryResq categoryResq = CopyUtil.copy(category, CategoryResq.class);
//            resqList.add(categoryResq);
//        }
        //列表复制
        List<CategoryQueryResp> list = CopyUtil.copyList(categorylist, CategoryQueryResp.class);

        //使返回值为PageResp类型
        PageResp<CategoryQueryResp> pageResp = new PageResp<>();
        pageResp.setList(list);
        pageResp.setTotal(pageInfo.getTotal());
        return pageResp;
    }

    //主键自增
    private static long initId = 1003;

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
