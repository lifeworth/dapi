package com.duzy.service;

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

}
