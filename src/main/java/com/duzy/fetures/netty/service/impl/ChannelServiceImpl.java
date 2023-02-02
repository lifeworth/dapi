package com.duzy.fetures.netty.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.duzy.common.exception.BizException;
import com.duzy.common.enums.HttpCodeAndMessageEnum;
import com.duzy.dao.ServerMapper;
import com.duzy.dto.TestSocketMsg;
import com.duzy.fetures.netty.service.ChannelService;
import com.duzy.model.Server;
import com.duzy.vo.ServerTerminalVo;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhiyuandu
 */
@Slf4j
@Service
public class ChannelServiceImpl implements ChannelService {

    @Getter
    private final ConcurrentHashMap<Integer, Set<Channel>> useChannelMap = new ConcurrentHashMap<>(1 << 8);
    @Getter
    private final ConcurrentHashMap<Channel, ServerTerminalVo> sshChannelMap = new ConcurrentHashMap<>(1 << 8);
    private final Lock lock = new ReentrantLock();
    @Autowired
    private Executor asyncExecutor;
    @Autowired
    private ServerMapper serverMapper;

    @Override
    public void sendMessage(Integer uId, String msg) {
        Set<Channel> channelSet = useChannelMap.get(uId);
        if (ObjectUtils.isEmpty(channelSet) || channelSet.size() == 0) {
            log.warn("不存在可用的通道");
            return;
        }
        for (Channel channel : channelSet) {
            if (channel.isActive()) {
                String json = JSONObject.toJSONString(new TestSocketMsg(msg, 1));
                TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(json);
                ChannelFuture channelFuture = channel.writeAndFlush(textWebSocketFrame);
                channelFuture.addListener((ChannelFutureListener) future -> {
                    log.debug("对uid：{}, 发送websocket消息：{}", uId, json);
                });

            }
        }
    }

    @Override
    public void remove(Integer uId) {
        useChannelMap.remove(uId);
    }

    @Override
    public void remove(Channel channel) {

        ServerTerminalVo terminalVo = sshChannelMap.get(channel);
        if (Objects.nonNull(terminalVo)) {
            terminalVo.close();
        }
        sshChannelMap.remove(channel);
        useChannelMap.entrySet().stream().filter(entry -> entry.getValue().contains(channel)).forEach(entry -> entry.getValue().remove(channel));
    }

    @Override
    public void removeAll() {
        sshChannelMap.clear();
        useChannelMap.clear();
    }

    @Override
    public void add(Integer uId, Channel channel) {
        lock.lock();
        try {
            Set<Channel> channels = useChannelMap.get(uId);
            if (ObjectUtils.isEmpty(channels) || channels.size() == 0) {
                Set<Channel> channelSet = new HashSet<>();
                channelSet.add(channel);
                useChannelMap.put(uId, channelSet);
            } else {
                channels.add(channel);
                useChannelMap.put(uId, channels);
            }
        } finally {
            lock.unlock();
        }


    }

    @Override
    public void add(Channel channel, Integer serverId) {
        Server server = serverMapper.selectOne(new LambdaQueryWrapper<Server>().eq(Server::getId, serverId).eq(Server::getCanView, true));
        if (Objects.isNull(server)) {
            return;
        }
        try {
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            Session session = new JSch().getSession(server.getUsername(), server.getHost(), server.getPort());
            session.setConfig(config);
            session.setPassword(server.getPassword());
            session.connect(30000);
            com.jcraft.jsch.Channel shell = session.openChannel("shell");
            shell.connect(30000);
            // 设置channel
            ServerTerminalVo result = new ServerTerminalVo(server, session, shell);
            // 启动线程获取数据
            sshChannelMap.put(channel, result);
            asyncExecutor.execute(new TerminalThread(result, channel));
        } catch (JSchException e) {
            log.error("连接服务器失败:{}", e.getMessage());
            throw new BizException(HttpCodeAndMessageEnum.SERVER_CONNECT_FAIL);
        }
    }

    @Override
    public Set<Channel> getChannels(Integer uId) {
        return useChannelMap.get(uId);
    }

    @Override
    public com.jcraft.jsch.Channel getChannel(Channel channel) {
        return sshChannelMap.get(channel).getChannel();
    }

    @Override
    public void terminalCommand(Channel channel, String cmd) {
        if (Objects.isNull(channel)) {
            channel = sshChannelMap.keys().nextElement();
        }
        ServerTerminalVo terminalVo = sshChannelMap.get(channel);
        try {
            OutputStream outputStream = terminalVo.getChannel().getOutputStream();
            outputStream.write(cmd.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (Exception e) {
            log.error("[{}] => 执行命令[{}]发生异常:[{}]", Thread.currentThread().getName(), cmd, e.getMessage());
        }

    }

    static class TerminalThread implements Runnable {

        private final ServerTerminalVo serverTerminal;

        private final Channel channel;

        public TerminalThread(ServerTerminalVo serverTerminal, Channel channel) {
            this.serverTerminal = serverTerminal;
            this.channel = channel;
        }

        @Override
        public void run() {
            try (InputStream inputStream = serverTerminal.getChannel().getInputStream()) {
                int i = 0;
                byte[] buffer = new byte[2048];
                while ((i = inputStream.read(buffer)) != -1) {
                    byte[] bytes = Arrays.copyOfRange(buffer, 0, i);
                    String msg = new String(bytes);
                    channel.writeAndFlush(new TextWebSocketFrame(msg)).addListener((ChannelFutureListener) future -> {
                        log.debug("[{}] => 发送websocket消息：{}", Thread.currentThread().getName(), msg);
                    });
                }
            } catch (Exception e) {
                log.error("[{}] 读取服务器数据失败:[{}]", Thread.currentThread().getName(), e.getMessage());
            }
        }
    }
}
