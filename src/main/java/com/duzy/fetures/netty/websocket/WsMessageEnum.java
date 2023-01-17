package com.duzy.fetures.netty.websocket;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author zhiyuandu
 */

@Getter
public enum WsMessageEnum implements Serializable {

    AUTH(1, "权限鉴定消息"),
    KEEP(2, "心跳消息"),
    SYSTEM(3, "系统消息"),
    TERMINAL(4, "WEB_SHELL")

    ;
    private final Integer type;

    private final String brief;

    WsMessageEnum(Integer type, String brief) {
        this.type = type;
        this.brief = brief;
    }

}
