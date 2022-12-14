package com.duzy.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.hibernate.validator.HibernateValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@MapperScan("com.duzy.dao")
@EnableSwagger2
@EnableKnife4j
@EnableCaching
public class ProjectConfig {

    @Value("${application.name:dapi}")
    private String projectName;

    /**
     * ?????????????????????
     *
     * @return ???????????????
     */
    @Bean("caffeineCacheManager")
    @Primary
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                // ????????????????????????????????????????????????????????????
                .expireAfterAccess(60, TimeUnit.SECONDS)
                // ???????????????????????????
                .initialCapacity(100)
                // ?????????????????????
                .maximumSize(1000));
        return cacheManager;
    }

    @Bean("redisCacheManager")
    public CacheManager redisCacheManager(@Autowired LettuceConnectionFactory lettuceConnectionFactory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);
        //???????????????????????????????????????
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // ??????????????????????????????????????????
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                redisSerializer))
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        return RedisCacheManager.builder(lettuceConnectionFactory).cacheDefaults(config).build();
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.DisableCircularReferenceDetect);
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        fastConvert.setSupportedMediaTypes(fastMediaTypes);
        fastConvert.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConvert);
    }

    /**
     * ??????????????????,?????????????????????mybatis?????????,
     * ???????????? MybatisConfiguration#useDeprecatedExecutor = false
     * ????????????????????????(?????????????????????????????????????????????)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * redis?????????
     * @param lettuceConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.afterPropertiesSet();//??????????????????
        return redisTemplate;
    }

    @Bean
    public RedissonClient redissonClient(@Autowired RedisProperties redisProperties) {
        Config config = new Config();
        String host = redisProperties.getHost();
        String password = redisProperties.getPassword();
        int port = redisProperties.getPort();
        config.useSingleServer().setAddress(StrUtil.format("redis://{}:{}", host, port));
        config.useSingleServer().setPassword(password);
        config.useSingleServer().setConnectionPoolSize(3);
        config.useSingleServer().setConnectionMinimumIdleSize(1);
        return Redisson.create(config);
    }


    /**
     * ??????@RequestBody??????????????????????????????
     */
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    /**
     * ??????@RequestParam??????????????????????????????
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(@Autowired Validator validator) {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        postProcessor.setValidator(validator);
        return postProcessor;
    }


    /**
     * ??????RestApi ????????????controller
     *
     * @return
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.duzy.controller")
                        .or(RequestHandlerSelectors.basePackage("com.duzy.api"))
                )
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * ??????Swagger?????? ??????
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(projectName + "????????????")
                .version("1.0")
                .license("no-license")
                .licenseUrl("")
                .termsOfServiceUrl("")
                .description(projectName + "????????????description")
                .build();
    }

    /**
     * rabbitmq ??????exchange queue
     */
    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(projectName + "-exchange", true, false);
    }

    @Bean
    public Queue getQueue() {
        return new Queue(projectName + "-queue", true, false, false, null);
    }

    @Bean
    public Binding getBinding(TopicExchange topicExchange, Queue queue) {
        return BindingBuilder.bind(queue).to(topicExchange).with("*.red.*");
    }
}
