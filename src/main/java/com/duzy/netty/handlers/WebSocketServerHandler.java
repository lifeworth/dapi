package com.duzy.netty.handlers;

import com.duzy.netty.service.MessageService;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketCloseStatus;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhiyuandu
 * @since 2023/1/15 03:36
 * @description
 */
@ChannelHandler.Sharable
@Slf4j
@Component
public class WebSocketServerHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    @Autowired
    MessageService messageService;


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame msg) {
        log.info("收到消息:{}", msg);
        if (msg instanceof TextWebSocketFrame textWebSocketFrame) {
            // 业务层处理数据
            messageService.discard(textWebSocketFrame.text());
            // 响应客户端
            channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("我收到了你的消息：" + textWebSocketFrame.text()));
        } else {
            // 不接受文本以外的数据帧类型
            channelHandlerContext.channel().writeAndFlush(WebSocketCloseStatus.INVALID_MESSAGE_TYPE).addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.info("链接断开：{}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("链接创建：{}", ctx.channel().remoteAddress());
    }

    /**
     * 出现异常时
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        log.info("异常：{}", ctx.channel().remoteAddress());
        ctx.close();
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        ctx.flush();
    }
}
