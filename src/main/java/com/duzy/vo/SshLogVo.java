package com.duzy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * ssh日志
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-11-20
 */
@Getter
@Setter
@ApiModel(value = "SshLogModel对象", description = "ssh日志")
public class SshLogVo {

    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("ip")
    private String ip;
    @ApiModelProperty("源")
    private String source;
    @ApiModelProperty("时间")
    private String triggerTime;
}
