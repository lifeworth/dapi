package com.duzy.aws.sqs.consumer;

import com.duzy.aws.sqs.vo.SQSMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author zhiyuandu
 * @since 2022/8/27 23:10
 * @description
 */
@Component
@Slf4j
public class Consumer {
    @Value("${aws.sqs.queue-string}")
    String queue;

    @JmsListener(destination = "${aws.sqs.queue-string}")
    public void listenQueueFifo(@Payload SQSMessage message) {
        log.info("Listening in queue:{},receive:{}", queue, message);
    }
}
