package com.duzy.aws.sqs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.destination.DynamicDestinationResolver;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;

import javax.annotation.PostConstruct;
import javax.jms.Session;

/**
 * @author zhiyuandu
 * @since 2022/8/28 18:14
 * @description
 */
@Configuration
@EnableJms
@Slf4j
public class SqsConfig {
    //创建链接SQS服务对象
    @Value("${aws.credentials.accessKey}")
    String awsAccessKey;
    @Value("${aws.credentials.secretKey}")
    String awsSecretKey;
    @Value("${aws.region}")
    String awsRegion;
    @Value("${aws.sqs.endpoint}")
    String endPoint;

    @Bean
    public AmazonSQSClientBuilder sqsClientBuilder() {
        return AmazonSQSClientBuilder.standard()
                                     .withCredentials(new AWSStaticCredentialsProvider(
                                             new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                                     .withEndpointConfiguration(
                                             new AwsClientBuilder.EndpointConfiguration(endPoint, awsRegion));
    }

    @Bean
    public SQSConnectionFactory sqsConnectionFactory() {
        return new SQSConnectionFactory(new ProviderConfiguration(), sqsClientBuilder());
    }


    @PostConstruct
    public void init() {
        log.info("Sqs init");
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(sqsConnectionFactory());
        jmsTemplate.setMessageConverter(messageConverter());
        return jmsTemplate;
    }

    /**
     * 格式 Format
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
        builder.dateFormat(new StdDateFormat());
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        mappingJackson2MessageConverter.setObjectMapper(builder.build());
        mappingJackson2MessageConverter.setTargetType(MessageType.TEXT);
        mappingJackson2MessageConverter.setTypeIdPropertyName("documentType");
        return mappingJackson2MessageConverter;
    }


    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

        //设置连接工厂
        factory.setConnectionFactory(sqsConnectionFactory());
        //设置动态解决程序
        factory.setDestinationResolver(new DynamicDestinationResolver());

        //设置并发性
        //The values we provided to Concurrency show that we will create a minimum of 3 listeners that will scale up to 10 listeners
        factory.setConcurrency("3-10");

        //确认模式
        /**
         *  SESSION_TRANSACTED
         *      事务提交并确认
         *  CLIENT_ACKNOWLEDGE
         *      客户端确认完成后，客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法,确认后jms服务器才会删除信息
         *  AUTO_ACKNOWLEDGE
         *      自动确认，客户端发送和接收消息不需要做额外的工作
         *  DUPS_OK_ACKNOWLEDGE
         *      允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。在需要考虑资源使用时，这种模式非常有效。
         */
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        //信息转换
        factory.setMessageConverter(messageConverter());
        return factory;
    }
}
