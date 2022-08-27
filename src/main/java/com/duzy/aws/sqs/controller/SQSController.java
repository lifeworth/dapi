package com.duzy.aws.sqs.controller;

import com.duzy.aws.sqs.producer.Producer;
import com.duzy.aws.sqs.vo.SQSMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @author zhiyuandu
 * @since 2022/8/27 23:11
 * @description
 */
@RestController
@RequestMapping("/sqs")
public class SQSController {

    @Autowired
    private Producer producer;

    @GetMapping
    public SQSMessage produce() {
        SQSMessage sqsMessage = new SQSMessage();
        sqsMessage.setId(UUID.randomUUID().toString());
        sqsMessage.setContent("张三");
        sqsMessage.setSendDate(new Date());
        producer.send("aws-queue-string", sqsMessage);
        return sqsMessage;
    }
}
