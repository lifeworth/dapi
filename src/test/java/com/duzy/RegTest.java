package com.duzy;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

/**
 * @author zhiyuandu
 * @since 2023/1/8 16:14
 * @description
 */
public class RegTest {
    public static void main(String[] args) {
        AtomicInteger successAtomicInteger = new AtomicInteger(0);
        AtomicInteger errAtomicInteger = new AtomicInteger(0);
        AtomicInteger totalAtomicInteger = new AtomicInteger(0);
        String regx = "(?<ip>\\d+\\.\\d+\\.\\d+\\.\\d+)(?<datetime> - - \\[(.*?)])(?<t1>\\s[\\\\\"]+)(?<requestMethod>[A-Z[/url]]+)(?<t2> )(?<requestUrl>\\S+\\s+)(?<protocol>\\S+\")(?<status> \\d+)(?<bytes> \\d+)(?<referer> \"(.*?)\")(?<agent> \"(.*?)\")";
        File file = FileUtil.file("/Users/zhiyuandu/Desktop/log/nginx/shxrfz.com.log");
        boolean exists = file.exists();
        if (exists) {
            List<String> strings = FileUtil.readUtf8Lines(file);
            strings.forEach(line -> {
                boolean match = ReUtil.isMatch(regx, line);
                if (match) {
                    successAtomicInteger.incrementAndGet();
                    Map<String, String> names = ReUtil.getAllGroupNames(Pattern.compile(regx), line);
                    String ip = names.get("ip");
                    String datetime = names.get("datetime");
                    String requestMethod = names.get("requestMethod");
                    String requestUrl = names.get("requestUrl");
                    String protocol = names.get("protocol");
                    String status = names.get("status");
                    String bytes = names.get("bytes");
                    String referer = names.get("referer");
                    String agent = names.get("agent");
                } else {
                    errAtomicInteger.incrementAndGet();
                    System.err.println(line);
                }
                System.out.println(StrUtil.format("list size:{}.一共读取了{}行数据.匹配:{}行。不匹配{}行。",
                        strings.size(),
                        totalAtomicInteger.getAndIncrement(),
                        successAtomicInteger.get(),
                        errAtomicInteger.get()
                ));
            });
        }
    }
}
