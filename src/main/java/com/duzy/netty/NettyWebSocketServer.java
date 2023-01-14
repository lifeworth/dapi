package com.duzy.netty;

import cn.hutool.extra.spring.SpringUtil;
import com.duzy.netty.handlers.WebSocketServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
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

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workGroup = new NioEventLoopGroup();

    @Value("${netty.websocket.port}")
    private int port;
    @Value("${netty.websocket.ip}")
    private String ip;

    @Value("${netty.websocket.path}")
    private String path;
    @Value("${netty.websocket.max-frame-size}")
    private long maxFrameSize;
    // 配置客户端是否为https的控制
    @Value("${netty.ssl-enabled:false}")
    private Boolean useSsl;

    private Channel channel;


    /**
     * 启动服务
     *
     * @param port
     */
    public void start() {
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workGroup)
                    //非阻塞异步服务端TCP Socket 连接
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(ip, port))
                    //设置为前端WebSocket可以连接
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpObjectAggregator(65536));
                            pipeline.addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    if (msg instanceof FullHttpRequest fullHttpRequest) {
                                        String uri = fullHttpRequest.uri();
                                        if (!uri.equals(path)) {
                                            // 访问的路径不是 websocket的端点地址，响应404
                                            ctx.channel().writeAndFlush(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND))
                                                    .addListener(ChannelFutureListener.CLOSE);
                                            return;
                                        }
                                    }
                                    super.channelRead(ctx, msg);
                                }
                            });
                            pipeline.addLast(new WebSocketServerCompressionHandler());
                            pipeline.addLast(new WebSocketServerProtocolHandler(path, null, true, maxFrameSize));
                            pipeline.addLast(SpringUtil.getBean(WebSocketServerHandler.class));
                        }
                    });
            Channel channel = server.bind(port).sync().channel();
            this.channel = channel;
            log.info("websocket 服务启动，ip={},port={}", this.ip, this.port);
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
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
