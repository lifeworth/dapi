package com.duzy.aws.sqs.producer;

import com.amazon.sqs.javamessaging.SQSMessagingClientConstants;
import com.duzy.aws.sqs.vo.SQSMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhiyuandu
 * @since 2022/8/27 23:09
 * @description
 */
@Component
@Slf4j
public class Producer {

    @Autowired
    protected JmsTemplate jmsTemplate;

    @Value("${aws.sqs.queue-stand}")
    String standQueue;

    @Value("${aws.sqs.queue-string-fifo}")
    String fifoQueue;

    @Value("${aws.sqs.queue-object-email}")
    String testQueue;

    @Value("${aws.sqs.queue-string}")
    String stringQueue;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 发送信息
     *
     * @param queue 队列名称
     * @param payload 消息体
     */
    public <MESSAGE extends Serializable> void send(String queue, MESSAGE payload) {
        jmsTemplate.send(
                queue,
                session -> {
                    Message createMessage = null;
                    try {
                        createMessage = session.createTextMessage(objectMapper.writeValueAsString(payload));
                    } catch (JsonProcessingException e) {
                        log.error("消息转json失败:{}", payload);
                    }
                    createMessage.setStringProperty(
                            SQSMessagingClientConstants.JMSX_GROUP_ID, "messageGroup1");
                    createMessage.setStringProperty(
                            SQSMessagingClientConstants.JMS_SQS_DEDUPLICATION_ID,
                            "2020" + System.currentTimeMillis());
                    createMessage.setStringProperty("_type", payload.getClass().getName());
                    return createMessage;
                });
    }

    @PostConstruct
    public void sendMessage() {
        SQSMessage sqsMessage = new SQSMessage(UUID.randomUUID().toString(), "init", new Date());
        send(stringQueue, sqsMessage);
        log.info("queue初始化完成，发送到测试queue:{}", sqsMessage);
    }
}
