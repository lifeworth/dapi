package com.duzy.common.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.duzy.vo.TokenVO;
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
public class RedisUtil {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 保存到redis中 k: token:{uid} v: tokenVo
     * @param id
     * @param tokenVO
     */
    public void saveToken(Integer id, TokenVO tokenVO) {
        String hashKey = StrUtil.format("token:{}", id);
        stringRedisTemplate.opsForValue().set(hashKey, JSONUtil.toJsonPrettyStr(tokenVO), TOKEN_EXPIRE_SECOND, TimeUnit.SECONDS);
    }
}
