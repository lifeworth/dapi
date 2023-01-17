package com.duzy.common.config;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zhiyuandu
 * @since 2023/1/16 15:24
 * @description
 */

public class CustomerThreadFactory implements ThreadFactory {
    private final LongAdder threadNumber = new LongAdder();
    private final String prefix;

    public CustomerThreadFactory(String prefix) {
        this.prefix = prefix;
    }

    /**
     * 获取netty的工作线程池, 通过自定义的线程工厂设置线程池名字
     * @param size
     * @param name
     * @return
     */
    public static EventLoopGroup getEventLoop(int size, String name) {
        return new NioEventLoopGroup(size, new CustomerThreadFactory(name));
    }

    /**
     * 重载方法，默认是0，netty会自定义核心线程数
     * @param name
     * @return
     */
    public static EventLoopGroup getEventLoop(String name) {
        return getEventLoop(0, name);
    }

    @Override
    public Thread newThread(@NotNull Runnable runnable) {
        threadNumber.add(1);
        return new Thread(runnable, prefix + " => " + threadNumber.intValue());
    }
}
