package com.duzy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.converter.NginxLogConverter;
import com.duzy.converter.SshLogConverter;
import com.duzy.dao.NginxLogDao;
import com.duzy.dao.SshLogDao;
import com.duzy.dto.NginxLogQueryDTO;
import com.duzy.dto.SshLogQueryDTO;
import com.duzy.model.NginxLogModel;
import com.duzy.model.SshLogModel;
import com.duzy.service.LogService;
import com.duzy.service.NginxLogService;
import com.duzy.service.SshLogService;
import com.duzy.vo.NginxLogVO;
import com.duzy.vo.SshLogVo;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static com.duzy.common.Constant.DEFAULT_PAGE_INDEX;
import static com.duzy.common.Constant.DEFAULT_PAGE_SIZE;

/**
 * @author zhiyuandu
 * @since 2022/11/26 01:50
 * @description
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {
    @Autowired
    SshLogConverter sshLogConverter;

    @Autowired
    NginxLogConverter nginxLogConverter;
    @Autowired
    SshLogService sshLogService;
    @Autowired
    NginxLogService nginxLogService;
    @Autowired
    SshLogDao sshLogDao;
    AtomicInteger count = new AtomicInteger(0);
    AtomicInteger successAtomicInteger = new AtomicInteger(0);
    AtomicInteger errAtomicInteger = new AtomicInteger(0);
    AtomicInteger totalAtomicInteger = new AtomicInteger(0);
    @Autowired
    NginxLogDao nginxLogDao;
    @Value("${ssh.log.path}")
    private String sshLogPath;
    @Value("${nginx.log.path}")
    private String nginxLogPath;
    @Value("${ssh.log.reg}")
    private String sshLogReg;
    @Value("${nginx.log.reg}")
    private String nginxLogReg;
    private List<SshLogModel> sshLogModels;
    private List<NginxLogModel> nginxLogModels;

    @Override
    public void loadSshLogFileToDb() {
        List<File> files = FileUtil.loopFiles(sshLogPath);
        log.info("共有{}个文件.", files.size());
        sshLogModels = Collections.synchronizedList(new ArrayList<>(files.size()));
        files.parallelStream().forEach(this::progressSshLogFile);
        log.info("list size:{}", sshLogModels.size());
        CollUtil.split(sshLogModels, 5000)
                .forEach(entityList -> sshLogService.saveBatch(entityList, 5000));
        log.info("结束");
    }

    @Override
    public void loadNginxLogFileToDb() {
        List<File> files = FileUtil.loopFiles(nginxLogPath);
        nginxLogModels = Collections.synchronizedList(new ArrayList<>(files.size()));
        files.parallelStream().forEach(this::progressNginxLogFile);
        log.info("list size:{}", nginxLogModels.size());
        CollUtil.split(nginxLogModels, 5000)
                .forEach(entityList -> nginxLogService.saveBatch(entityList, 5000));
        log.info("结束");
    }

    private void progressNginxLogFile(File file) {
        FileReader fileReader = FileReader.create(file);
        List<String> lines = fileReader.readLines();
        lines.stream().parallel().forEach(this::progressNginxLine);
    }

    private void progressNginxLine(String line) {
        NginxLogModel nginxLogModel = new NginxLogModel();
        CopyOptions copyOptions = new CopyOptions();
        copyOptions.setIgnoreCase(true);
        try {
            boolean match = ReUtil.isMatch(Pattern.compile(nginxLogReg), line);
            if (match) {
                successAtomicInteger.incrementAndGet();
                Map<String, String> names = ReUtil.getAllGroupNames(Pattern.compile(nginxLogReg), line);
                nginxLogModel = BeanUtil.mapToBean(names, NginxLogModel.class, true, copyOptions);
            } else {
                errAtomicInteger.incrementAndGet();
                System.err.println(line);
            }
            log.info(StrUtil.format("一共读取了{}行数据.匹配:{}行。不匹配{}行。", totalAtomicInteger.getAndIncrement(), successAtomicInteger.get(), errAtomicInteger.get()));
            nginxLogModels.add(nginxLogModel);
        } catch (Exception e) {
            log.error("异常:{}.line:{}", Throwables.getStackTraceAsString(e), line);
        } finally {
            nginxLogModel.setSource(line);
            nginxLogModels.add(nginxLogModel);
        }
    }

    @Override
    public void sshTrans() {
        sshLogDao.insertIntoIpLocation();
    }

    @Override
    public void nginxTrans() {
        nginxLogDao.insertIntoIpLocation();
    }

    @Override
    public Page<SshLogVo> listSsh(SshLogQueryDTO queryDTO) {
        Page<SshLogModel> page;
        if (Objects.isNull(queryDTO)) {
            page = sshLogService.page(Page.of(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE));
        } else {
            int pageIndex = Objects.isNull(queryDTO.getPageIndex()) ? DEFAULT_PAGE_INDEX : queryDTO.getPageIndex();
            int pageSize = Objects.isNull(queryDTO.getPageSize()) ? DEFAULT_PAGE_SIZE : queryDTO.getPageSize();
            LambdaQueryWrapper<SshLogModel> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Objects.nonNull(queryDTO.getId()), SshLogModel::getId, queryDTO.getId());
            queryWrapper.like(!Strings.isNullOrEmpty(queryDTO.getIp()), SshLogModel::getIp, queryDTO.getIp());
            queryWrapper.like(!Strings.isNullOrEmpty(queryDTO.getSource()), SshLogModel::getIp, queryDTO.getSource());
            page = sshLogService.page(Page.of(pageIndex, pageSize), queryWrapper);
        }
        Page<SshLogVo> sshLogVoPage = sshLogConverter.model2PageVo(page);
        return sshLogVoPage;
    }

    @Override
    public Page<NginxLogVO> listNginx(NginxLogQueryDTO queryDTO) {
        Page<NginxLogModel> page;
        if (Objects.isNull(queryDTO)) {
            page = nginxLogService.page(Page.of(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE));
        } else {
            int pageIndex = Objects.isNull(queryDTO.getPageIndex()) ? DEFAULT_PAGE_INDEX : queryDTO.getPageIndex();
            int pageSize = Objects.isNull(queryDTO.getPageSize()) ? DEFAULT_PAGE_SIZE : queryDTO.getPageSize();
            LambdaQueryWrapper<NginxLogModel> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Objects.nonNull(queryDTO.getId()), NginxLogModel::getId, queryDTO.getId());
            queryWrapper.eq(Objects.nonNull(queryDTO.getIp()), NginxLogModel::getIp, queryDTO.getIp());
            queryWrapper.like(!Strings.isNullOrEmpty(queryDTO.getSource()), NginxLogModel::getIp, queryDTO.getSource());
            queryWrapper.like(!Strings.isNullOrEmpty(queryDTO.getAgent()), NginxLogModel::getAgent, queryDTO.getAgent());
            queryWrapper.eq(!Strings.isNullOrEmpty(queryDTO.getBytes()), NginxLogModel::getBytes, queryDTO.getBytes());
            queryWrapper.eq(!Strings.isNullOrEmpty(queryDTO.getDateTime()), NginxLogModel::getDateTime, queryDTO.getDateTime());
            queryWrapper.eq(!Strings.isNullOrEmpty(queryDTO.getProtocol()), NginxLogModel::getProtocol, queryDTO.getProtocol());
            queryWrapper.eq(!Strings.isNullOrEmpty(queryDTO.getRequestMethod()), NginxLogModel::getRequestMethod, queryDTO.getRequestMethod());
            queryWrapper.like(!Strings.isNullOrEmpty(queryDTO.getRequestUrl()), NginxLogModel::getRequestUrl, queryDTO.getRequestUrl());
            queryWrapper.between(Objects.nonNull(queryDTO.getCreateTimeStart()) && Objects.nonNull(queryDTO.getCreateTimeEnd()), NginxLogModel::getCreatedTime, queryDTO.getCreateTimeStart(), queryDTO.getCreateTimeEnd());

            page = nginxLogService.page(Page.of(pageIndex, pageSize), queryWrapper);
        }
        Page<NginxLogVO> sshLogVoPage = nginxLogConverter.model2PageVo(page);
        return sshLogVoPage;
    }

    private void progressSshLogFile(File file) {
        FileReader fileReader = FileReader.create(file, StandardCharsets.UTF_8);
        List<String> lines = fileReader.readLines();
        lines.stream().parallel().forEach(this::progressLine);
    }

    private void progressLine(String line) {
        try {
            String ip = ReUtil.get(sshLogReg, line, 0);
            line = line.replaceAll("'", "\\");
            int lineLength = line.length();
            if (lineLength > 1000) {
                log.error("超长.line:{}", line);
            }
            SshLogModel sshLogModel = new SshLogModel();
            sshLogModel.setSource(line);
            sshLogModel.setIp(Strings.isNullOrEmpty(ip) ? "" : ip);
            sshLogModels.add(sshLogModel);
        } catch (Exception e) {
            log.error("错误数量:{}", count.getAndAdd(1));
            SshLogModel sshLogModel = new SshLogModel();
            sshLogModel.setSource(line);
            sshLogModel.setIp("");
            sshLogModels.add(sshLogModel);
        }
    }
}
