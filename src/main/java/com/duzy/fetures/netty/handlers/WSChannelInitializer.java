package com.duzy.fetures.netty.handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class WSChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Value("${netty.websocket.path}")
    private String path;
    @Autowired private WsHeartBeatHandler heartBeatHandler;
    @Autowired private WSClientMsgHandler clientMsgHandler;

    @Override
    protected void initChannel(@NotNull SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 块写入
        pipeline.addLast(new ChunkedWriteHandler());
        // 将请求报文聚合为完整报文，设置最大请求报文 10M
        pipeline.addLast(new HttpObjectAggregator(10 * 1024 * 1024));
        // 心跳
        pipeline.addLast(new IdleStateHandler(10, 10, 30, TimeUnit.MINUTES));
        // 处理心跳
        pipeline.addLast(heartBeatHandler);
        // 处理ws信息
        pipeline.addLast(new WebSocketServerProtocolHandler(path));
        pipeline.addLast(clientMsgHandler);
    }
}
