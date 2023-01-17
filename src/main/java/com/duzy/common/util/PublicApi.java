package com.duzy.common.util;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhiyuandu
 * @since 2022/9/13 14:18
 * @description
 */
@Slf4j
public class PublicApi {
    public static void main(String[] args) {
        yiyan();
    }

    public static void gushici() {
        String result = HttpUtil.get("https://v1.jinrishici.com/all.json");
        log.info(result);
    }

    public static void yiyan() {
        String result = HttpUtil.get("https://v1.hitokoto.cn/");
        log.info(result);
    }
}
