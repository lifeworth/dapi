package com.duzy.fetures.netty.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WsMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 4317189626331101870L;

    private Integer msgType;

    private String data;

    private Integer id;

    public String msgType() {
        WsMessageEnum messageEnum = Arrays.stream(WsMessageEnum.values())
                .filter(item -> Objects.equals(item.getType(), msgType))
                .findFirst()
                .orElse(null);
        if (Objects.isNull(messageEnum)) {
            return "未识别消息";
        }
        return messageEnum.getBrief();
    }

    @Override
    public String toString() {

        return String.format("[%s] => %s", msgType(), data);
    }

    public WsMessage(Integer msgType, String data) {
        this(msgType, data, null);
    }


}
