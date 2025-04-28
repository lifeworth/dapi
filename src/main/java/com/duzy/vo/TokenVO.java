package com.duzy.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhiyuandu
 * @since 2023/1/3 21:48
 * @description
 */
@Data
@Schema(description = "Token对象")
public class TokenVO {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("nick")
    private String nick;
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("jti")
    private String jti;
}
