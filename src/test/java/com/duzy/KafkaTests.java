package com.duzy;

import cn.hutool.core.util.RandomUtil;
import com.duzy.kafka.KafkaConsumer;
import com.duzy.kafka.KafkaProducer;
import com.duzy.kafka.KafkaSampleMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhiyuandu
 * @since 2022/8/24 15:32
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTests {
    @Autowired
    KafkaProducer kafkaProducer;
    @Autowired
    KafkaConsumer kafkaConsumer;

    @Test
    public void send() {
        KafkaSampleMessage kafkaSampleMessage = new KafkaSampleMessage();
        kafkaSampleMessage.setId(RandomUtil.randomInt());
        kafkaSampleMessage.setMessage(RandomUtil.randomString("hello worldwide", 6));
        kafkaProducer.send(kafkaSampleMessage);
    }

}
