package com.duzy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-08-24
 */
@Getter
@Setter
@Schema(description = "UserModel对象")
public class UserDTO extends BaseQueryDTO {

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "登录名")
    private String username;

    @Schema(description = "昵称")
    private String nick;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新人")
    private String updatedBy;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;


}
