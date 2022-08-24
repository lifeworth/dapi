package com.duzy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "UserModel对象", description = "用户")
public class UserVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("登录名")
    private String username;

    @ApiModelProperty("昵称")
    private String nick;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新人")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;


}
