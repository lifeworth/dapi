package com.duzy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duzy.dto.SshLogQueryDTO;
import com.duzy.model.SshLogModel;
import com.duzy.vo.SshLogVo;

import java.util.List;

/**
 * @author zhiyuandu
 * @since 2022/11/20 15:12
 * @description
 */
public interface SshLogService extends IService<SshLogModel> {
    void loadSshLogFileToDb();

    void sshTrans();

    Page<SshLogVo> page(SshLogQueryDTO queryDTO);
}
