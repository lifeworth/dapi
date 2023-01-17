package com.duzy.common.util;

import com.duzy.dto.MailRequest;
import com.duzy.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhiyuandu
 * @since 2022/11/21 14:58
 * @description
 */
@Component
public class MailUtil {
    @Autowired
    private MailService mailService;


    public void sendMail(String content) {
        MailRequest mailRequest = new MailRequest();
        mailRequest.setSendTo("164408623@qq.com");
        mailRequest.setSubject("App Error");
        mailRequest.setText(content);
        mailService.sendSimpleMail(mailRequest);
    }

}
