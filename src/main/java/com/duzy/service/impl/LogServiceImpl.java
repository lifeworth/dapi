package com.duzy.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.converter.SshLogConverter;
import com.duzy.dao.SshLogDao;
import com.duzy.dto.SshLogQueryDTO;
import com.duzy.model.NginxLogModel;
import com.duzy.model.SshLogModel;
import com.duzy.service.LogService;
import com.duzy.service.SshLogService;
import com.duzy.vo.SshLogVo;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
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
    SshLogService sshLogService;
    @Autowired
    SshLogDao sshLogDao;

    AtomicInteger count = new AtomicInteger(0);
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

    public static void main(String[] args) {
        String line = "111.205.14.34 - - [26/Nov/2022:01:42:34 +0800] ＂OPTIONS /wp-admin/admin-ajax.php HTTP/2.0＂ 200 20 ＂http://a.shxrfz.com/＂＂Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36＂ ＂-＂ ＂a.shxrfz.com＂ ＂0.393＂ ＂0.393＂ ＂0.000＂ ＂0.393＂\n";
        new LogServiceImpl().progressNginxLine(line);
    }

    @Override
    public void loadSshLogFileToDb() {
        List<File> files = FileUtil.loopFiles(sshLogPath);
        log.info("共有{}个文件.", files.size());
        sshLogModels = Collections.synchronizedList(new ArrayList<>(files.size()));
        files.parallelStream().forEach(this::progressFile);
        log.info("list size:{}", sshLogModels.size());
        CollUtil.split(sshLogModels, 5000)
                .forEach(entityList -> sshLogService.saveBatch(entityList, 5000));
        log.info("结束");
    }

    @Override
    public void loadNginxLogFileToDb() {
        List<File> files = FileUtil.loopFiles(nginxLogPath);
        log.info("共有{}个文件.", files.size());
        sshLogModels = Collections.synchronizedList(new ArrayList<>(files.size()));
        files.parallelStream().forEach(this::progressNginxFile);
        log.info("list size:{}", sshLogModels.size());
        CollUtil.split(sshLogModels, 5000)
                .forEach(entityList -> sshLogService.saveBatch(entityList, 5000));
        log.info("结束");
    }

    private void progressNginxFile(File file) {
        FileReader fileReader = FileReader.create(file);
        List<String> lines = fileReader.readLines();
        lines.stream().parallel().forEach(this::progressNginxLine);
    }

    private void progressNginxLine(String line) {
        try {
            nginxLogReg = "((?<requestMethod>[A-Z[/url]]+)(?<t2> )(?<requestUrl>\\S+\\s)(?<protocol>\\S+\")(?<status> \\d+)(?<bytes> \\d+)(?<referer> \"(.*?)\")(?<agent> \"(.*?)\")(?<forwarded> \"(.*?)\")(?<host> \"(.*?)\")(?<requestTime> \"(.*?)\")(?<responseTime> \"(.*?)\")(?<connectTime> \"(.*?)\")(?<headerTime> \"(.*?)\"))";
            Pattern r = Pattern.compile(nginxLogReg);
            Matcher m = r.matcher(line);

            if (m.find()) {
                String ip = ReUtil.get("(?\\d+\\.\\d+\\.\\d+\\.\\d+)", line, "ip");
                String datetime = ReUtil.get("- - \\[(.*?)])(?<t1>\\s[\\\"]+", line, "datetime");
                String requestMethod = ReUtil.get(nginxLogReg, line, "requestMethod");
                String requestUrl = ReUtil.get(nginxLogReg, line, "requestUrl");
                String protocol = ReUtil.get(nginxLogReg, line, "protocol");
                String status = ReUtil.get(nginxLogReg, line, "status");
                String bytes = ReUtil.get(nginxLogReg, line, "bytes");
                String referer = ReUtil.get(nginxLogReg, line, "referer");
                String agent = ReUtil.get(nginxLogReg, line, "agent");
                String forwarded = ReUtil.get(nginxLogReg, line, "forwarded");
                String host = ReUtil.get(nginxLogReg, line, "host");
                String requestTime = ReUtil.get(nginxLogReg, line, "requestTime");
                String responseTime = ReUtil.get(nginxLogReg, line, "responseTime");
                String connectTime = ReUtil.get(nginxLogReg, line, "connectTime");
                String headerTime = ReUtil.get(nginxLogReg, line, "headerTime");
            }


            List<String> allGroups = ReUtil.getAllGroups(Pattern.compile(nginxLogReg), line);


            int lineLength = line.length();
            if (lineLength > 1000) {
                log.error("line:{}", line);
                throw new RuntimeException("超长");
            }
            NginxLogModel nginxLogModel = new NginxLogModel();
//            nginxLogModel.setVisit_times();
//            nginxLogModel.setRemote_addr();
//            nginxLogModel.setTime_local();
//            nginxLogModel.setRequestMethod();
//            nginxLogModel.setRequestUrl();
//            nginxLogModel.setProtocol();
//            nginxLogModel.setStatus();
//            nginxLogModel.setBytes();
//            nginxLogModel.setHttp_referer();
//            nginxLogModel.setRequest_time();
//            nginxLogModel.setResponse_time();
//            nginxLogModel.setConnect_time();
//            nginxLogModel.setHeader_time();
//            nginxLogModel.setLog_date();
//            nginxLogModel.setMax_request_time();
//            nginxLogModel.setMax_response_time();
//            nginxLogModel.setMin_request_time();
//            nginxLogModel.setMin_response_time();
//            nginxLogModel.setAverage_request_time();
//            nginxLogModel.setAverage_response_time();
//            nginxLogModel.setSucceed_visit_times();
            nginxLogModels.add(nginxLogModel);
        } catch (Exception e) {
            log.error("异常:{}.line:{}", Throwables.getStackTraceAsString(e), line);
        }
    }

    @Override
    public void sshTrans() {
        sshLogDao.insertIntoIpLocation();
    }

    @Override
    public void nginxTrans() {

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

    private void progressFile(File file) {
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
