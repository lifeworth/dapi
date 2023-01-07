package com.duzy.controller;

import com.duzy.service.PublicApiService;
import com.duzy.util.MailUtil;
import com.duzy.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhiyuandu
 * @since 2022/11/21 13:55
 * @description
 */
@RestController
@RequestMapping("/api")
@Slf4j
@io.swagger.annotations.Api(tags = "publicApi")
public class Api {

    @Autowired
    MailUtil mailUtil;

    @Autowired
    private PublicApiService publicApiService;

    @GetMapping("/mail")
    public void mail(@RequestParam String msg) {
        mailUtil.sendMail(msg);
    }

    @GetMapping("/public/{apiName}")
    public ResultVO<String> publicApi(@PathVariable("apiName") String apiName) {
        String result = publicApiService.publicApi(apiName);
        return ResultVO.SUCCESS(result);
    }


}
