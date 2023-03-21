package com.duzy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duzy.converter.SshLogConverter;
import com.duzy.dao.SshLogDao;
import com.duzy.dto.query.SshLogQueryDTO;
import com.duzy.model.SshLogModel;
import com.duzy.service.SshLogService;
import com.duzy.vo.SshLogVo;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public Page<SshLogVo> page(SshLogQueryDTO queryDTO) {
        Integer pageIndex = queryDTO.getCurrent();
        Integer pageSize = queryDTO.getSize();
        int index = Objects.isNull(pageIndex) ? DEFAULT_PAGE_INDEX : pageIndex;
        int size = Objects.isNull(pageSize) ? DEFAULT_PAGE_SIZE : pageSize;


        LambdaQueryWrapper<SshLogModel> queryWrapper = new LambdaQueryWrapper<SshLogModel>()
                .like(!Strings.isNullOrEmpty(queryDTO.getIp()), SshLogModel::getIp, queryDTO.getIp());

        Page<SshLogModel> page = page(Page.of(index, size), queryWrapper);
        Page<SshLogVo> voList = sshLogConverter.model2PageVo(page);
        return voList;
    }
}
