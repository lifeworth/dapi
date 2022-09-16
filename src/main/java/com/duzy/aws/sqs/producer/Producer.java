package com.duzy.aws.sqs.producer;

import com.amazon.sqs.javamessaging.SQSMessagingClientConstants;
import com.duzy.aws.sqs.vo.SqsVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.Message;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author zhiyuandu
 * @since 2022/8/28 18:38
 * @description
 */
@Slf4j
@Component
public class Producer {
    @Autowired
    protected JmsTemplate jmsTemplate;

    @Value("${queue.test}")
    String testQueue;

    @Autowired
    ObjectMapper objectMapper;

    public void sendToTestQueue(SqsVO message) {
        log.info("Sending message {} to queue {}", message, "testQueue");
        send(testQueue, message);
    }

    /**
     * 发送信息
     * @param queue
     * @param payload
     * @param <MESSAGE>
     */
    public <MESSAGE extends Serializable> void send(String queue, MESSAGE payload) {
        jmsTemplate.send(queue, session -> {
            try {
                Message createMessage = session.createTextMessage(objectMapper.writeValueAsString(payload));
                createMessage.setStringProperty(SQSMessagingClientConstants.JMSX_GROUP_ID, "messageGroup1");
                createMessage.setStringProperty(SQSMessagingClientConstants.JMS_SQS_DEDUPLICATION_ID,
                                                "2022" + System.currentTimeMillis());
                createMessage.setStringProperty("documentType", payload.getClass().getName());
                return createMessage;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.error("Fail to send message {} ,err {}", payload, e.getMessage());
                throw new RuntimeException(e);
            }
        });
    }

    //发起请求
//    @PostConstruct
    public void postConstruct() {
        SqsVO demoMessage = new SqsVO(UUID.randomUUID().toString(), "Test queue", new Date());
        sendToTestQueue(demoMessage);
    }
}
