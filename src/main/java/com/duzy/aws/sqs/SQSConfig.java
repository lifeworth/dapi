package com.duzy.aws.sqs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter ;
import software.amazon.awssdk.awscore.client.builder.AwsClientBuilder;

import javax.annotation.PostConstruct;
import javax.jms.Session;

/**
 * @author zhiyuandu
 * @since 2022/8/27 23:08
 * @description
 */
@Configuration
@Slf4j
@EnableJms
public class SQSConfig {
    private final AmazonSQSClientBuilder SQSClientBuilder;
    private final SQSConnectionFactory connectionFactory;

    public SQSConfig(
            @Value("${aws.credentials.access-key}") String accessKeyId,
            @Value("${aws.credentials.secret-key}") String secretAccessKey,
            @Value("${aws.endpoint}") String endPoint,
            @Value("${aws.region}") String awsRegion) {
        SQSClientBuilder =
                AmazonSQSClientBuilder.standard()
                                      .withCredentials(
                                              new AWSStaticCredentialsProvider(
                                                      new BasicAWSCredentials(accessKeyId, secretAccessKey)))
                                      .withEndpointConfiguration(
                                              new AwsClientBuilder.EndpointConfiguration(endPoint, awsRegion));
        connectionFactory =
                new SQSConnectionFactory(new ProviderConfiguration(), this.SQSClientBuilder);
    }

    @PostConstruct
    public void init() {
        log.info("aws sqs Started init");
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(this.connectionFactory);
        jmsTemplate.setMessageConverter(JMSmessageConverter());
        return jmsTemplate;
    }

    @Bean
    public MessageConverter JMSmessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // 设置连接工厂
        factory.setConnectionFactory(this.connectionFactory);
        // 设置动态解决程序
        factory.setDestinationResolver(new DynamicDestinationResolver());
        // 设置并发性
        factory.setConcurrency("3-10");
        // 确认模式
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        // 信息转换
        factory.setMessageConverter(JMSmessageConverter());
        return factory;
    }
}
