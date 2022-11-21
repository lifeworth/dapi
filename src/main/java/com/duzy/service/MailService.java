package com.duzy.service;

import com.duzy.dto.MailRequest;

/**
 * @author zhiyuandu
 * @since 2022/11/21 15:00
 * @description
 */
public interface MailService {
    /**
     * 简单文本邮件
     *
     * @param mailRequest
     * @return
     */
    void sendSimpleMail(MailRequest mailRequest);


    /**
     * Html格式邮件,可带附件
     *
     * @param mailRequest
     * @return
     */
    void sendHtmlMail(MailRequest mailRequest);
}
