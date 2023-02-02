package com.duzy.common.util;

import com.alibaba.fastjson2.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.duzy.common.enums.HttpCodeAndMessageEnum;
import com.duzy.common.exception.BizException;
import com.duzy.model.UserModel;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {

    /**
     * 过期时间
     */
    private static final Long EXPIRATION_DAY = 1L;

    /**
     * 秘钥
     */
    private static final String SECRET = "duzy.top";

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(UserModel user) {
        //过期时间
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                // 添加头部
                .withHeader(map)
                // 数据
                .withClaim("data", JSONObject.toJSONString(user))
                // 超时设置,设置过期的日期
                .withExpiresAt(LocalDateTime.now().plusDays(EXPIRATION_DAY).toInstant(ZoneOffset.ofHours(8)))
                // 签发时间
                .withIssuedAt(LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)))
                // SECRET加密
                .sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 验证
     * @param token
     * @return
     */
    public static DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (JWTDecodeException e) {
            throw new BizException(HttpCodeAndMessageEnum.JWT_PARSE_FAIL);
        } catch (TokenExpiredException e) {
            throw new BizException(HttpCodeAndMessageEnum.AUTH_TIME_EXPIRED);
        }
        return jwt;
    }

    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjoie1wiY2FuVmlld1wiOnRydWUsXCJpZFwiOjEsXCJuaWNrXCI6XCLnrqHnkIblkZhcIixcInBhc3N3b3JkXCI6XCJEdXpoaXl1YW4xMjNcIixcInBob25lXCI6XCIxMzUyNjQwOTczNVwiLFwicm9sZVR5cGVcIjoxLFwidXNlcm5hbWVcIjpcImFkbWluXCJ9IiwiZXhwIjoxNjc0MDI4MDE5LCJpYXQiOjE2NzM5NDE2MTl9.ggO3zxWNiA7JtrwsWvQCCXCENdvPxHTi5TdugHRQLVI";
        verifyToken(token);
    }

}
