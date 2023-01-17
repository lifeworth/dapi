package com.duzy.fetures.redis.entity;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhiyuandu
 * @since 2023/1/16 17:24
 * @description
 */
@Data
@RedisHash(value ="token:${id}")
public class TokenVo implements Serializable {

    private String keyName;
    private String value;
    private String expireSecond;

    private Integer id;
    private String username;
    private String nick;
    private String password;
    private String phone;
    private Integer roleType;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean canView;

}
