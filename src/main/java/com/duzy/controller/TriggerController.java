package com.duzy.controller;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhiyuandu
 * @description
 * @since 2022/10/31 17:23
 */
@RestController
@RequestMapping("/t")
@Slf4j
public class TriggerController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public void ssh(){
        FileReader fileReader = new FileReader("/Users/zhiyuandu/Desktop/secure");
        List<String> strings = fileReader.readLines();
        strings.forEach(line -> {
            String s = ReUtil.get("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)", line, 0);
            String sqlTemplate = "";
            line = line.replaceAll("'", "\\");
            if (!Strings.isNullOrEmpty(s)) {
                sqlTemplate = StrUtil.format("insert into ssh_log (ip,source) values ('{}','{}')", s, line);
            } else {
                sqlTemplate = StrUtil.format("insert into ssh_log (source) values ('{}')", line);
            }
            log.info("sqlTemplate:{}",sqlTemplate);
            jdbcTemplate.execute(sqlTemplate);

        });
    }
}
