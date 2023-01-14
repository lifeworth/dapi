package com.duzy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-08-24
 */
@TableName("user")
@Schema(description = "用户")
@RedisHash
@Data
@EqualsAndHashCode(callSuper = false)
public class UserModel extends BaseModel {

    @Schema(description = "id")
    @TableId(value = "id", type = IdType.AUTO)
    @Id
    private Integer id;

    @Schema(description = "登录名")
    @TableField("username")
    private String username;

    @Schema(description = "昵称")
    @TableField("nick")
    private String nick;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "手机号")
    @TableField("phone")
    private String phone;


}
