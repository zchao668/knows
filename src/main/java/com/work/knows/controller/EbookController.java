package com.work.knows.controller;

import com.work.knows.req.EbookQueryReq;
import com.work.knows.req.EbookSaveReq;
import com.work.knows.resp.CommonResp;
import com.work.knows.resp.EbookQueryResp;
import com.work.knows.resp.PageResp;
import com.work.knows.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    EbookService ebookService;

    @RequestMapping("/list")
    //EbookReq  请求参数的封装
    public CommonResp list(EbookQueryReq ebookQueryReq){
        //封装了返回类型CommonResp类型
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(ebookQueryReq);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    //EbookReq  请求参数的封装
    //RequestBody 代表参数是json方式的post提交
    public CommonResp save(@RequestBody EbookSaveReq ebookSaveReq){
        //封装了返回类型CommonResp类型
        CommonResp resp = new CommonResp<>();
        ebookService.save(ebookSaveReq);
        return resp;
    }
}
