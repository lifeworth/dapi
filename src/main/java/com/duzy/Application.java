package com.duzy;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.duzy.netty.NettyWebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhiyuandu
 */
@SpringBootApplication
@EnableSpringUtil
@EnableAspectJAutoProxy
@EnableScheduling
@EnableCaching
@EnableRedisRepositories(basePackages = "com.duzy.repository")
@Slf4j
public class Application implements CommandLineRunner {

    @Autowired
    NettyWebSocketServer nettyWebSocketServer;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Callback used to run the bean.
     * @param args incoming main method arguments
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("start!");
        nettyWebSocketServer.start();
    }
}
