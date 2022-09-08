package com.duzy.config;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhiyuandu
 * @since 2022/9/8 11:09
 * @description
 */
@Configuration
@Slf4j
public class ProjectAsyncConfigurer implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(1000);
        executor.setKeepAliveSeconds(300);
        executor.setThreadNamePrefix("async-Executor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (arg0, arg1, arg2) -> {
            log.error("==========================" + arg0.getMessage() + "=======================", arg0);
            log.error("exception method:" + arg1.getName());
            log.error("exception Object:" + arg2);
            String format = StrUtil.format("异步任务执行出错:{}.{}.exception method:{}.exception Object:{}", arg0.getMessage(),
                                           arg0, arg1.getName(), arg2);
        };
    }
}
