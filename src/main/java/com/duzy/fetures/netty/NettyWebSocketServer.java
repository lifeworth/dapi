package com.duzy.fetures.netty;

import com.duzy.common.config.CustomerThreadFactory;
import com.duzy.fetures.netty.handlers.WSChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author zhiyuandu
 * @since 2023/1/15 03:38
 * @description
 */
@Component
@Slf4j
public class NettyWebSocketServer {
    @Value("${netty.websocket.port}")
    private int port;
    @Value("${netty.websocket.ip}")
    private String ip;
    @Value("${netty.websocket.boss-thread-name}")
    private String bossThreadName;
    EventLoopGroup bossGroup = CustomerThreadFactory.getEventLoop(bossThreadName);
    @Value("${netty.websocket.worker-thread-name}")
    private String workerThreadName;
    EventLoopGroup workGroup = CustomerThreadFactory.getEventLoop(workerThreadName);
    @Value("${netty.websocket.back-log}")
    private int backLog;
    private Channel channel;
    @Autowired
    private WSChannelInitializer wsChannelInitializer;

    /**
     * 启动服务
     *
     */
    public void start() {

        try {
            ChannelFuture future = new ServerBootstrap()
                    .group(bossGroup, workGroup)
                    .localAddress(new InetSocketAddress(ip, port))
                    .option(ChannelOption.SO_BACKLOG, backLog)
                    //非阻塞异步服务端TCP Socket 连接
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(wsChannelInitializer)
                    .bind(port)
                    .sync();
            this.channel = future.channel();
            log.info("WS服务器启动......ip={},port={}", this.ip, this.port);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error("WS服务器发生异常: [{}]", e.getMessage(), e);
        }
    }

    @PreDestroy
    public void destroy() {
        log.info("=================Netty服务关闭==================");
        if (channel != null) {
            channel.close();
        }
        bossGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }


}
