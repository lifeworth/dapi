package com.duzy.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author zhiyuandu
 * @since 2022/11/21 17:54
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "nginx日志查询对象", description = "nginx日志查询对象")
public class NginxLogQueryDTO extends BaseQueryDTO {

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
