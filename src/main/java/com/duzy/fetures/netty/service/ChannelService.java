package com.duzy.fetures.netty.service;

import io.netty.channel.Channel;

import java.util.Set;

public interface ChannelService {

    void sendMessage(Integer uId, String msg);

    void remove(Integer uId);

    void remove(Channel channel);

    void removeAll();

    void add(Integer uId, Channel channel);

    void add(Channel channel, Integer serverId);

    Set<Channel> getChannels(Integer uId);

    com.jcraft.jsch.Channel getChannel(Channel channel);


    /**
     * 执行命令
     * @param channel
     * @param cmd
     */
    void terminalCommand(Channel channel, String cmd);


}
