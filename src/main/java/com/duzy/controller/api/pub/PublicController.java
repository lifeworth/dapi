package com.duzy.controller.api.pub;

import com.duzy.service.PublicApiService;
import com.duzy.common.util.MailUtil;
import com.duzy.vo.ResultVO;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "publicApi")
public class PublicController {

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
        return ResultVO.success(result);
    }


}
