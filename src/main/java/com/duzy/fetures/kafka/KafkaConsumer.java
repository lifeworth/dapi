package com.duzy.fetures.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhiyuandu
 * @since 2022/8/24 15:10
 * @description
 */

@Component
@Slf4j
public class KafkaConsumer {
//    @KafkaListener(topics = "${kafka.topic}")
    public void processMessage(String message, @Header(KafkaHeaders.PARTITION) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("kafka-consumer收到消息：{}-{}[{}] \"{}\"\n", topics.get(0), partitions.get(0), offsets.get(0), message);
    }
}
