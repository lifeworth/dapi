package com.duzy.fetures.netty.handlers;

import com.alibaba.fastjson2.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.duzy.model.UserModel;
import com.duzy.fetures.netty.service.ChannelService;
import com.duzy.fetures.netty.websocket.WsMessage;
import com.duzy.fetures.netty.websocket.WsMessageEnum;
import com.duzy.common.util.JwtUtil;
import com.jcraft.jsch.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;

@Slf4j
@Component
@ChannelHandler.Sharable
public class WSClientMsgHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Autowired
    private ChannelService channelService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String json = msg.text();
        log.info("收到数据:{}", json);
        WsMessage message = null;
        try {
            message = JSONObject.parseObject(json, WsMessage.class);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
            return;
        }
        if (message.getMsgType().equals(WsMessageEnum.AUTH.getType())) {
            checkoutUserHandler(message, ctx);
        } else if (message.getMsgType().equals(WsMessageEnum.KEEP.getType())) {
            keepHandler(message);
        } else if (message.getMsgType().equals(WsMessageEnum.SYSTEM.getType())) {
            systemHandler(message, ctx);
        } else if (message.getMsgType().equals(WsMessageEnum.TERMINAL.getType())) {
            terminalHandler(message, ctx);
        } else {
            log.info("[{}] => 当前消息类型未识别:[{}]", Thread.currentThread().getName(), message);
        }
    }


    /**
     * 校验用户认证信息
     * @param message
     * @return
     */
    private void checkoutUserHandler(WsMessage message, ChannelHandlerContext ctx) {
        log.info("[{}] => 当前消息是鉴权消息:[{}]", Thread.currentThread().getName(), message);
        DecodedJWT jwt = JwtUtil.verifyToken(message.getData());
        String data = jwt.getClaim("data").asString();
        UserModel user = JSONObject.parseObject(data, UserModel.class);
        channelService.add(user.getId(), ctx.channel());
        // 初始化jsch链接
        channelService.add(ctx.channel(), message.getId());
    }

    /**
     * 系统信息
     * @param message
     * @param ctx
     */
    private void systemHandler(WsMessage message, ChannelHandlerContext ctx) {

    }

    private void terminalHandler(WsMessage message, ChannelHandlerContext ctx) {
        log.info("[{}] => 当前消息是终端消息:[{}] => [{}]", Thread.currentThread().getName(), message, message.getData().charAt(0));
        try {
            Channel channel = channelService.getChannel(ctx.channel());
            OutputStream outputStream = channel.getOutputStream();
            outputStream.write(message.getData().getBytes());
            outputStream.flush();
        } catch (Exception e) {
            log.error("[{}] => 往服务器发送命令异常:[{}]", Thread.currentThread().getName(), e.getMessage());
        }
    }

    /**
     * 系统信息
     * @param message
     */
    private void keepHandler(WsMessage message) {
        log.info("[{}] => 当前消息是心跳消息:[{}]", Thread.currentThread().getName(), message);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channelService.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
        channelService.remove(ctx.channel());
    }
}
