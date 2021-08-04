package com.work.knows.controller;

import com.work.knows.req.DocQueryReq;
import com.work.knows.req.DocSaveReq;
import com.work.knows.resp.DocQueryResp;
import com.work.knows.resp.CommonResp;
import com.work.knows.resp.PageResp;
import com.work.knows.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doc")
public class DocController {

    @Autowired
    DocService docService;

    @RequestMapping("/all")
    public CommonResp all(){
        //封装了返回类型CommonResp类型
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list = docService.all();
        resp.setContent(list);
        return resp;
    }


    //DocReq  请求参数的封装
    // 加入Valid检验规则，并加入ExceptionHandler，对错误信息进行拦截，将错误信息返回至CommonResp，让前端接收
    @RequestMapping("/list")
    public CommonResp list(@Valid DocQueryReq docQueryReq){
        //docQueryReq 是为了按照名字进行查询
        //封装了返回类型CommonResp类型
        CommonResp<PageResp<DocQueryResp>> resp = new CommonResp<>();
        PageResp<DocQueryResp> list = docService.list(docQueryReq);
        resp.setContent(list);
        return resp;
    }


    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq req) {
        //封装了返回类型CommonResp类型
        CommonResp resp = new CommonResp<>();
        docService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        //封装了返回类型CommonResp类型
        CommonResp resp = new CommonResp<>();
        docService.delete(id);
        return resp;
    }
}
