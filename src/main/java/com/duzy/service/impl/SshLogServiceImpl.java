package com.duzy.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duzy.converter.SshLogConverter;
import com.duzy.dao.SshLogDao;
import com.duzy.dto.SshLogQueryDTO;
import com.duzy.model.SshLogModel;
import com.duzy.service.SshLogService;
import com.duzy.vo.SshLogVo;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.duzy.common.Constant.DEFAULT_PAGE_INDEX;
import static com.duzy.common.Constant.DEFAULT_PAGE_SIZE;

/**
 * @author zhiyuandu
 * @since 2022/11/20 15:13
 * @description
 */
@Service
@Slf4j
public class SshLogServiceImpl extends ServiceImpl<SshLogDao, SshLogModel> implements SshLogService {

    @Autowired
    SshLogConverter sshLogConverter;
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

    @Override
    public void sshTrans() {
        baseMapper.insertIntoIpLocation();
    }

    private void progressFile(File file) {
        FileReader fileReader = FileReader.create(file);
        List<String> lines = fileReader.readLines();
        lines.stream().parallel().forEach(this::progressLine);
    }

    private void progressLine(String line) {
        try {
            String ip = ReUtil.get(sshLogReg, line, 0);
            line = line.replaceAll("'", "\\");
            int lineLength = line.length();
            if (lineLength > 1000) {
                log.error("line:{}", line);
                throw new RuntimeException("超长");
            }
            SshLogModel sshLogModel = new SshLogModel();
            sshLogModel.setSource(line);
            sshLogModel.setIp(Strings.isNullOrEmpty(ip) ? "" : ip);
            sshLogModels.add(sshLogModel);
        } catch (Exception e) {
            log.error("异常:{}.line:{}", Throwables.getStackTraceAsString(e), line);
        }
    }

    @Override
    public Page<SshLogVo> page(SshLogQueryDTO queryDTO) {
        Integer pageIndex = queryDTO.getPageIndex();
        Integer pageSize = queryDTO.getPageSize();
        Integer index = Objects.isNull(pageIndex) ? DEFAULT_PAGE_INDEX : pageIndex;
        Integer size = Objects.isNull(pageSize) ? DEFAULT_PAGE_SIZE : pageSize;


        LambdaQueryWrapper<SshLogModel> queryWrapper = new LambdaQueryWrapper<SshLogModel>()
                .like(SshLogModel::getIp, queryDTO.getIp());

        Page<SshLogModel> page = page(Page.of(index, size), queryWrapper);
        Page<SshLogVo> voList = sshLogConverter.model2PageVo(page);
        return voList;
    }
}
