package com.duzy.kafka;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zhiyuandu
 * @since 2022/8/24 15:05
 * @description
 */
@Component
@Slf4j
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value("${kafka.topic}")
    private String topic;

//    @Scheduled(fixedRate = 1000)
    public void sendRandomMsg() {
        KafkaSampleMessage kafkaSampleMessage = new KafkaSampleMessage();
        kafkaSampleMessage.setId(RandomUtil.randomInt());
        kafkaSampleMessage.setMessage(RandomUtil.randomString("hello worldwide", 6));
        send(kafkaSampleMessage);
    }

    public void send(KafkaSampleMessage message) {
        this.kafkaTemplate.send(topic, message.getMessage());
        log.info("KafkaProducer 发送消息:{}到{}.", message, topic);
    }
}
