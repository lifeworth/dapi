package com.duzy.common.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import java.io.Serializable;

@Configuration
@MapperScan("com.duzy.dao")
@EnableKnife4j()
@EnableCaching
public class ProjectConfig {

    @Value("${application.name:dapi}")
    private String projectName;

    @Bean("redisCacheManager")
    public CacheManager redisCacheManager(@Autowired LettuceConnectionFactory lettuceConnectionFactory) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper().registerModule(new JavaTimeModule())
                .activateDefaultTyping(
                        LaissezFaireSubTypeValidator.instance,
                        ObjectMapper.DefaultTyping.NON_FINAL,
                        JsonTypeInfo.As.PROPERTY
                );
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);


        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(om,
                Object.class);

        // 配置序列化（解决乱码的问题）
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

    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,
     * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
     * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
        return interceptor;
    }

    /**
     * redis序列化
     *
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
        redisTemplate.afterPropertiesSet();//配置立即生效
        return redisTemplate;
    }

    @Bean
    public RedissonClient redissonClient(@Autowired RedisProperties redisProperties) {
        Config config = new Config();
        String host = redisProperties.getHost();
        String password = redisProperties.getPassword();
        int port = redisProperties.getPort();
        String username = redisProperties.getUsername();
        config.useSingleServer().setUsername(username);
        config.useSingleServer().setAddress(StrUtil.format("redis://{}:{}", host, port));
        config.useSingleServer().setPassword(password);
        config.useSingleServer().setConnectionPoolSize(3);
        config.useSingleServer().setConnectionMinimumIdleSize(1);
        return Redisson.create(config);
    }


    /**
     * 效验@RequestBody时，采用快速失败模式
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
     * 效验@RequestParam时，采用快速失败模式
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(@Autowired Validator validator) {
        MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
        postProcessor.setValidator(validator);
        return postProcessor;
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .openapi("3.0.1")
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("DAPI")
                        .version("1.0.0"));
    }
}
