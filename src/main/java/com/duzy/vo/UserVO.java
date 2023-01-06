package com.duzy.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "UserVO对象", description = "UserVO对象")
public class UserVO {

    @ApiModelProperty("id")
    @JsonProperty("userid")
    private Integer id;

    @ApiModelProperty("昵称")
    @JsonProperty("name")
    private String username;

    @ApiModelProperty("头像url")
    private String avatar;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("个人签名")
    private String signature;

    @ApiModelProperty("头衔")
    private String title;

    @ApiModelProperty("部门")
    private String group;

    @ApiModelProperty("标签")
    private List<Tag> tags;

    @ApiModelProperty("消息数量")
    private Integer notifyCount;

    @ApiModelProperty("未读消息数量")
    private Integer unreadCount;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("角色")
    private String access;

    @ApiModelProperty("坐标")
    private Geographic geographic;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("手机")
    private String phone;


}
