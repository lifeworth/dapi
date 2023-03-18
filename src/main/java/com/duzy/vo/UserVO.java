package com.duzy.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-08-24
 */
@Data
@Schema(description = "UserVO对象")
public class UserVO {

    @Schema(description = "id")
    @JsonProperty("userid")
    private Integer id;

    @Schema(description = "用户名")
    @JsonProperty("name")
    private String username;

    @Schema(description = "密码")
    @JsonProperty("password")
    private String password;

    @Schema(description = "昵称")
    @JsonProperty("nick")
    private String nick;

    @Schema(description = "头像url")
    private String avatar;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "个人签名")
    private String signature;

    @Schema(description = "头衔")
    private String title;

    @Schema(description = "部门")
    private String group;

    @Schema(description = "标签")
    private List<Tag> tags;

    @Schema(description = "消息数量")
    private Integer notifyCount;

    @Schema(description = "未读消息数量")
    private Integer unreadCount;

    @Schema(description = "国家")
    private String country;

    @Schema(description = "角色")
    private String access;

    @Schema(description = "坐标")
    private Geographic geographic;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "手机")
    private String phone;


}
