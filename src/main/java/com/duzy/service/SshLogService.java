package com.duzy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.duzy.model.SshLogModel;

/**
 * @author zhiyuandu
 * @since 2022/11/20 15:12
 * @description
 */
public interface SshLogService extends IService<SshLogModel> {
    void loadSshLogFileToDb();
}
