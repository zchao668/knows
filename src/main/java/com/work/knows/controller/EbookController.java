package com.work.knows.controller;

import com.work.knows.req.EbookQueryReq;
import com.work.knows.req.EbookSaveReq;
import com.work.knows.resp.CommonResp;
import com.work.knows.resp.EbookQueryResp;
import com.work.knows.resp.PageResp;
import com.work.knows.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    EbookService ebookService;

    //EbookReq  请求参数的封装
    // 加入Valid检验规则，并加入ExceptionHandler，对错误信息进行拦截，将错误信息返回至CommonResp，让前端接收
    @RequestMapping("/list")
    public CommonResp list(@Valid EbookQueryReq ebookQueryReq){
        //封装了返回类型CommonResp类型
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(ebookQueryReq);
        resp.setContent(list);
        return resp;
    }


    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        //封装了返回类型CommonResp类型
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        //封装了返回类型CommonResp类型
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}
