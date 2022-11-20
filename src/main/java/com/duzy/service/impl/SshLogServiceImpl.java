package com.duzy.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.duzy.service.SshLogService;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @author zhiyuandu
 * @since 2022/11/20 15:13
 * @description
 */
@Service
@Slf4j
public class SshLogServiceImpl implements SshLogService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${ssh.log.path}")
    private String sshLogPath;
    @Value("${ssh.log.reg}")
    private String sshLogReg;

    @Override
    public void loadSshLogFileToDb() {
        List<File> files = FileUtil.loopFiles(sshLogPath);
        log.info("共有{}个文件.", files.size());
        files.parallelStream().forEach(file -> {
            FileReader fileReader = FileReader.create(file);
            List<String> strings = fileReader.readLines();
            int total = strings.size();
            AtomicInteger count = new AtomicInteger(0);
            AtomicInteger errorCount = new AtomicInteger(0);
            Stream<String> stream = strings.stream();
            new ForkJoinPool(64).submit(() -> stream.parallel().forEach(
                    line -> progressLine(total, count, errorCount, line)
            )).join();
        });
        log.info("结束");
    }

    private void progressLine(int total, AtomicInteger count, AtomicInteger errorCount, String line) {
        try {
            String s = ReUtil.get(sshLogReg, line, 0);
            String sqlTemplate = "";
            line = line.replaceAll("'", "\\");
            if (!Strings.isNullOrEmpty(s)) {
                sqlTemplate = StrUtil.format("insert into ssh_log (ip,source) values ('{}','{}')", s, line);
            } else {
                sqlTemplate = StrUtil.format("insert into ssh_log (source) values ('{}')", line);
            }
            jdbcTemplate.execute(sqlTemplate);
        } catch (Exception e) {
            errorCount.getAndIncrement();
            log.error("异常:{}.line:{}", Throwables.getStackTraceAsString(e), line);
        } finally {
            log.info("当前行:{}.总行数:{}.错误行数:{}", count.getAndIncrement(), total,errorCount.get() );
        }
    }
}
