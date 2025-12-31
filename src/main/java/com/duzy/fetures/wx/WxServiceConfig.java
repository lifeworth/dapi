package com.duzy.fetures.wx;

import com.duzy.common.config.ProjectWxConfig;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.redis.RedisTemplateWxRedisOps;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpRedisConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@Slf4j
public class WxServiceConfig {

    @Autowired
    private ProjectWxConfig projectWxConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        RedisTemplateWxRedisOps redisTemplateWxRedisOps = new RedisTemplateWxRedisOps(stringRedisTemplate);
        WxMpRedisConfigImpl config = new WxMpRedisConfigImpl(redisTemplateWxRedisOps, "wx_mp_config:");
        config.setAppId(projectWxConfig.getAppid());
        config.setSecret(projectWxConfig.getSecret());
        config.setToken(projectWxConfig.getToken());
        config.setAesKey(projectWxConfig.getAesKey());
        log.info("加载微信配置完成: {}", projectWxConfig);
        return config;
    }

    @Bean
    public WxMpService wxService(WxMpConfigStorage wxMpConfigStorage) {
        WxMpService wxService = new WxMpServiceImpl();
        wxService.setWxMpConfigStorage(wxMpConfigStorage);
        return wxService;
    }

}
