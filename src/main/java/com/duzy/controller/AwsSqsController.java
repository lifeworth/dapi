package com.duzy.controller;

import com.duzy.aws.sqs.producer.Producer;
import com.duzy.aws.sqs.vo.SqsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @author zhiyuandu
 * @since 2022/8/28 19:46
 * @description
 */
@RestController
@RequestMapping("/aws/sqs")
public class AwsSqsController {
    @Autowired
    Producer producer;

    @PostMapping
    public void sendMessage(String message) {
        SqsVO sqsVO = new SqsVO(UUID.randomUUID().toString(), message, new Date());
        producer.sendToTestQueue(sqsVO);
    }
}
