package com.duzy.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhiyuandu
 * @since 2022/11/21 14:59
 * @description
 */
@Data
public class MailRequest implements Serializable {
    /**
     * 接收人
     */
    private String sendTo;

    /**
     *  邮件主题
     */
    private String subject;

    /**
     *  邮件内容
     */
    private String text;

    /**
     *  附件路径
     */
    private String filePath;


}
