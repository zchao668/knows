package com.work.knows.controller;

import com.work.knows.req.EbookReq;
import com.work.knows.resp.CommonResp;
import com.work.knows.resp.EbookResp;
import com.work.knows.service.EbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Autowired
    EbookService ebookService;

    @RequestMapping("/list")
    //EbookReq  请求参数的封装
    public CommonResp ebook(EbookReq ebookReq){
        //封装了返回类型CommonResp类型
        CommonResp<List<EbookResp>> resp = new CommonResp<>();
        List<EbookResp> list = ebookService.list(ebookReq);
        resp.setContent(list);
        return resp;
    }
}
