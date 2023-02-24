package com.duzy.common.handler;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.duzy.common.enums.HttpCodeAndMessageEnum;
import com.duzy.common.exception.BizException;
import com.duzy.common.util.JwtUtil;
import com.duzy.common.util.RedisUtil;
import com.duzy.model.SysUserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.Optional;


/**
 * @author zhiyuandu
 */
@Slf4j
@Component
public class SecurityInterceptor implements HandlerInterceptor {
    private final String bearerName = SpringUtil.getProperty("security.token.prefix");
    private final String headerName = SpringUtil.getProperty("security.token.header-name");
    private final String whiteListUrl = SpringUtil.getProperty("white-url-list");

    @Autowired
    RedisUtil redisUtil;

    /**
     * 解析路由
     * @param request
     * @return
     */
    private String getUrl(HttpServletRequest request) {
        String url = request.getRequestURI();
        return url.substring(request.getContextPath().length());
    }

    private void addUserToContext(DecodedJWT jwt) {
        String data = jwt.getClaim("data").asString();
        SysUserModel user = JSONObject.parseObject(data, SysUserModel.class);
        SecurityUserContext.addUserInfo(user);
    }


    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        TimeInterval timer = DateUtil.timer();
        String url = getUrl(request);
        Optional<String> any = Arrays.stream(whiteListUrl.split(","))
                .filter(whiteUrl -> {
                    AntPathMatcher antPathMatcher = new AntPathMatcher();
                    return antPathMatcher.match(whiteUrl, url);
                })
                .findAny();
        log.info("请求地址:{} 结果:{}", url, any.isPresent());
        if (any.isPresent()) {
            return true;
        }
        // 获取Header头
        String token = request.getHeader(headerName);

        if (StringUtils.isEmpty(token) || !StringUtils.startsWith(token, bearerName)) {
            throw new BizException(HttpCodeAndMessageEnum.AUTH_NOT_EXIST);
        }
        // 验证token信息
        DecodedJWT jwt;
        try {
            jwt = JwtUtil.verifyToken(StrUtil.subAfter(token, bearerName, false));
            // 将用户信息添加到 ThreadLocal
            addUserToContext(jwt);
        } catch (Exception e) {
            throw new BizException(HttpCodeAndMessageEnum.AUTH_NOT_EXIST);
        }
        //通过redis的时间 校验是否过期
        Integer id = SecurityUserContext.getUserInfo().getId();
        if (redisUtil.validToken(id)) {
            //没有过期 则延长redis中的过期时间
            redisUtil.delayTokenTime(id);
        } else {
            throw new BizException(HttpCodeAndMessageEnum.AUTH_NOT_EXIST);
        }
        log.info("preHandle 消耗时间:{}ms", timer.interval());
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
        log.info("[{}] => 移出用户信息", Thread.currentThread().getName());
        SecurityUserContext.removeUser();
    }
}
