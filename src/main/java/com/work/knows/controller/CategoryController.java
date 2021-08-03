package com.work.knows.controller;

import com.work.knows.req.CategoryQueryReq;
import com.work.knows.req.CategorySaveReq;
import com.work.knows.resp.CommonResp;
import com.work.knows.resp.CategoryQueryResp;
import com.work.knows.resp.PageResp;
import com.work.knows.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //CategoryReq  请求参数的封装
    // 加入Valid检验规则，并加入ExceptionHandler，对错误信息进行拦截，将错误信息返回至CommonResp，让前端接收
    @RequestMapping("/list")
    public CommonResp list(@Valid CategoryQueryReq categoryQueryReq){
        //categoryQueryReq 是为了按照名字进行查询
        //封装了返回类型CommonResp类型
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();
        PageResp<CategoryQueryResp> list = categoryService.list(categoryQueryReq);
        resp.setContent(list);
        return resp;
    }


    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req) {
        //封装了返回类型CommonResp类型
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        //封装了返回类型CommonResp类型
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }
}
