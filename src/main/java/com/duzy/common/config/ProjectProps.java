package com.duzy.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zhiyuandu
 * @since 2022/11/21 15:49
 * @description
 */
@Component
@Data
@ConfigurationProperties(prefix = "project-prop")
public class ProjectProps {
    private List<Map<String,String>> apis;
}
