package com.duzy.common.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.duzy.vo.TokenVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.duzy.common.Constant.TOKEN_EXPIRE_SECOND;

/**
 * @author zhiyuandu
 * @since 2023/1/17 15:25
 * @description
 */
@Component
@Slf4j
public class RedisUtil {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 根据id 创建token的key
     * @param id id
     * @return
     */
    private static String buildTokenKeyByUserId(Long id) {
        return StrUtil.format("token:{}", id);
    }

    /**
     * 延长token过期时间
     * @param id
     */
    public void delayTokenTime(Long id) {
        String hashKey = buildTokenKeyByUserId(id);
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(hashKey))) {
            stringRedisTemplate.expire(hashKey, TOKEN_EXPIRE_SECOND, TimeUnit.SECONDS);
        }
    }

    /**
     * 保存到redis中 k: token:{uid} v: tokenVo
     * @param id
     * @param tokenVO
     */
    public void saveToken(Long id, TokenVO tokenVO) {
        String hashKey = buildTokenKeyByUserId(id);
        stringRedisTemplate.opsForValue().set(hashKey, JSONUtil.toJsonPrettyStr(tokenVO), TOKEN_EXPIRE_SECOND, TimeUnit.SECONDS);
    }

    /**
     * 移除token
     * @param id
     */
    public void removeToken(Long id) {
        String hashKey = buildTokenKeyByUserId(id);
        String json = stringRedisTemplate.opsForValue().getAndDelete(hashKey);
        log.info("用户{} 退出登录", json);
    }

    /**
     * 校验token是否过期
     *
     */
    public boolean validToken(Long id) {
        String hashKey = buildTokenKeyByUserId(id);
        Boolean hasKey = stringRedisTemplate.hasKey(hashKey);
        return Boolean.TRUE.equals(hasKey);
    }
}
