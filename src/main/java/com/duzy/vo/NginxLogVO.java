package com.duzy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * nginx日志
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-01-08
 */
@Data
@ApiModel(value = "NginxLogModel对象", description = "nginx日志")
public class NginxLogVO {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("ip地址")
    private String ip;

    @ApiModelProperty("请求时间")
    private String dateTime;

    @ApiModelProperty("请求方法")
    private String requestMethod;

    @ApiModelProperty("请求URL")
    private String requestUrl;

    @ApiModelProperty("请求协议")
    private String protocol;

    @ApiModelProperty("响应状态")
    private String status;

    @ApiModelProperty("响应大小")
    private String bytes;

    @ApiModelProperty("refere")
    private String referer;

    @ApiModelProperty("agent")
    private String agent;

    @ApiModelProperty("原请求记录")
    private String source;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("更新人")
    private String updatedBy;
}
