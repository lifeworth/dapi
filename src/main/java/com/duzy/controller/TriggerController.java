package com.duzy.controller;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author zhiyuandu
 * @description
 * @since 2022/10/31 17:23
 */
@RestController
@RequestMapping("/t")
@Slf4j
public class TriggerController {
    @Value("${post.ip.url}")
    String postUrl;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public void ssh() {
        FileReader fileReader = new FileReader("/Users/zhiyuandu/Desktop/secure");
        List<String> strings = fileReader.readLines();
        int total = strings.size();
        AtomicInteger count = new AtomicInteger(0);
        Stream<String> stream = strings.stream();
        new ForkJoinPool(64).submit(() -> stream.parallel().forEach(
                line -> {
                    try {
                        String s = ReUtil.get("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)", line, 0);
                        String sqlTemplate = "";
                        line = line.replaceAll("'", "\\");
                        if (!Strings.isNullOrEmpty(s)) {
                            sqlTemplate = StrUtil.format("insert into ssh_log (ip,source) values ('{}','{}')", s, line);
                        } else {
                            sqlTemplate = StrUtil.format("insert into ssh_log (source) values ('{}')", line);
                        }
                        log.info("count:{}.total:{}", count.getAndIncrement(), total);
                        jdbcTemplate.execute(sqlTemplate);
                    } catch (Exception e) {
                        log.error("异常:{}.line:{}", Throwables.getStackTraceAsString(e), line);
                    }
                }
        )).join();
        log.info("结束");
    }

    @GetMapping("/location")
    public void location() {
        List<String> strings = jdbcTemplate.queryForList("select  distinct ip from ssh_log where ip is not null limit 100", String.class);
        ResponseEntity<JSONArray> response = restTemplate.exchange(postUrl, HttpMethod.POST, new HttpEntity<>(strings), JSONArray.class);
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.TOO_MANY_REQUESTS) {
            log.info("TOO_MANY_REQUESTS");
        }
        List<String> rateLimit = response.getHeaders().get("X-Rl");
        List<String> ttl = response.getHeaders().get("X-Ttl");
        log.info("rateLimit:{},ttl{}.", rateLimit, ttl);
        log.info("size:{}", strings.size());
        log.info("response:{}", response.getBody());
        log.info("response:{}", response.getBody());
    }


}
