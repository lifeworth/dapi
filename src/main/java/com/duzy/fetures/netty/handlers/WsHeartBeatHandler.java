package com.duzy.fetures.netty.handlers;

import com.duzy.fetures.netty.service.ChannelService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ChannelHandler.Sharable
public class WsHeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    private ChannelService channelService;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent event) {
            if (event.state() == IdleState.READER_IDLE) {
                log.debug("没有收到读数据包");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                log.debug("没有发送写数据包");
            } else if (event.state() == IdleState.ALL_IDLE) {
                Channel channel = ctx.channel();
                log.error("长时间没有读写，关闭连接: {}", channel.id().asLongText());
                channelService.remove(channel);
                channel.close();
            }
        }
    }

}
