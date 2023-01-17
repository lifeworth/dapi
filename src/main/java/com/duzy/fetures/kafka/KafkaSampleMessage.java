package com.duzy.fetures.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author zhiyuandu
 * @since 2022/8/24 15:08
 * @description
 */
@Data
public class KafkaSampleMessage {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("message")
    private String message;
}
