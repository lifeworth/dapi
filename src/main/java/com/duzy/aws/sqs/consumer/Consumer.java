package com.duzy.aws.sqs.consumer;

import com.duzy.aws.sqs.vo.SqsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author zhiyuandu
 * @since 2022/8/28 18:41
 * @description
 */
@Component
@Slf4j
public class Consumer {
    /**
     * 监听器，用于消费消息队列
     * 对消息队列中信息进行处理
     */


    @JmsListener(destination = "${queue.test}")
    public void listenQueueTest(@Payload final Message<SqsVO> message) {
        log.info("Listening {} in queue test", message.getPayload().content);
    }
}
