package com.duzy.dto;

import com.duzy.dto.validater.CreateValidGroup;
import com.duzy.dto.validater.UpdateValidGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

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
public class UserDto {

    @Schema(description = "id")
    @NotNull(message = "id不能为空", groups = {UpdateValidGroup.class})
    private Integer id;

    @Schema(description = "登录名")
    @NotEmpty(message = "登录名不能为空", groups = {CreateValidGroup.class})
    private String username;

    @Schema(description = "昵称")
    private String nick;

    @Schema(description = "密码")
    @NotEmpty(message = "密码不能为空", groups = {CreateValidGroup.class})
    private String password;

    @Schema(description = "手机号")
    private String phone;

}
