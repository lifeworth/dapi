package com.duzy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.NginxLogQueryDTO;
import com.duzy.dto.SshLogQueryDTO;
import com.duzy.vo.NginxLogVO;
import com.duzy.vo.SshLogVo;

/**
 * @author zhiyuandu
 * @since 2022/11/26 01:50
 * @description
 */
public interface LogService {
    void loadSshLogFileToDb();

    void loadNginxLogFileToDb();

    void sshTrans();

    void nginxTrans();

    Page<SshLogVo> listSsh(SshLogQueryDTO queryDTO);

    Page<NginxLogVO> listNginx(NginxLogQueryDTO queryDTO);

    void loadSshSource();
}
