package com.duzy.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duzy.dao.SshLogDao;
import com.duzy.model.SshLogModel;
import com.duzy.service.SshLogService;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author zhiyuandu
 * @since 2022/11/20 15:13
 * @description
 */
@Service
@Slf4j
public class SshLogServiceImpl extends ServiceImpl<SshLogDao, SshLogModel> implements SshLogService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Value("${ssh.log.path}")
    private String sshLogPath;
    @Value("${ssh.log.reg}")
    private String sshLogReg;

    private List<SshLogModel> sshLogModels;

    @Override
    public void loadSshLogFileToDb() {
        List<File> files = FileUtil.loopFiles(sshLogPath);
        log.info("共有{}个文件.", files.size());
        sshLogModels = Collections.synchronizedList(new ArrayList<>(files.size()));
        files.parallelStream().forEach(this::progressFile);
        log.info("list size:{}", sshLogModels.size());
        CollUtil.split(sshLogModels, 5000)
                .forEach(entityList -> saveBatch(entityList, 5000));
        log.info("结束");
    }

    private void progressFile(File file) {
        FileReader fileReader = FileReader.create(file);
        List<String> lines = fileReader.readLines();
        new ForkJoinPool(64).submit(() -> lines.stream().parallel().forEach(
                this::progressLine
        )).join();
    }

    private void progressLine(String line) {
        try {
            String ip = ReUtil.get(sshLogReg, line, 0);
            line = line.replaceAll("'", "\\");
            SshLogModel sshLogModel = new SshLogModel();
            sshLogModel.setSource(line);
            sshLogModel.setIp(ip);
            sshLogModels.add(sshLogModel);
        } catch (Exception e) {
            log.error("异常:{}.line:{}", Throwables.getStackTraceAsString(e), line);
        }
    }
}
