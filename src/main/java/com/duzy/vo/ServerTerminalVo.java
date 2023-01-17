package com.duzy.vo;

import com.duzy.model.Server;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ServerTerminalVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 7984805212346288950L;

    /**
     * 服务连接信息
     */
    private Server info;

    /**
     * ssh
     */
    private Session session;


    private Channel channel;

    public void close() {
        if (Objects.nonNull(channel)) {
            channel.disconnect();
        }
        if (Objects.nonNull(session)) {
            session.disconnect();
        }
    }


}
