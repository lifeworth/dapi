package com.duzy.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "wx")
public class ProjectWxConfig {
    private String appid;
    private String secret;
    private String token;
    private String aesKey;
}
