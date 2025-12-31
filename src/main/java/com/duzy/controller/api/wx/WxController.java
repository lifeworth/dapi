package com.duzy.controller.api.wx;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wx")
@Tag(name = "微信公众号相关接口")
@Slf4j
public class WxController {
    @Autowired
    private WxMpService wxMpService;
    @Autowired
    private WxMpConfigStorage wxMpConfigStorage;

    @GetMapping("/check")
    @Operation(summary = "校验微信公众号接入")
    public String check(
            @RequestParam String signature,
            @RequestParam String nonce,
            @RequestParam String timestamp,
            @RequestParam String echostr) {
        log.info("收到微信公众号接入验证请求：signature={}, nonce={}, timestamp={}, echostr={}",signature, nonce, timestamp, echostr);
        if (!wxMpService.checkSignature(timestamp, nonce, signature)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            return "非法请求";
        }
        if (StrUtil.isNotBlank(echostr)) {
            // 说明是一个仅仅用来验证的请求，回显echostr
            return echostr;
        }
        return "success";
    }
}
