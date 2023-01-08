package com.duzy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * nginx日志
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-01-08
 */
@Getter
@Setter
@TableName("nginx_log")
@ApiModel(value = "NginxLogModel对象", description = "nginx日志")
public class NginxLogModel {

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("ip地址")
    @TableField("ip")
    private String ip;

    @ApiModelProperty("请求时间")
    @TableField("date_time")
    private String dateTime;

    @ApiModelProperty("请求方法")
    @TableField("request_method")
    private String requestMethod;

    @ApiModelProperty("请求URL")
    @TableField("request_url")
    private String requestUrl;

    @ApiModelProperty("请求协议")
    @TableField("protocol")
    private String protocol;

    @ApiModelProperty("响应状态")
    @TableField("status")
    private String status;

    @ApiModelProperty("响应大小")
    @TableField("bytes")
    private String bytes;

    @ApiModelProperty("refere")
    @TableField("referer")
    private String referer;

    @ApiModelProperty("agent")
    @TableField("agent")
    private String agent;

    @ApiModelProperty("原请求记录")
    @TableField("source")
    private String source;

    @TableField("created_time")
    private LocalDateTime createdTime;

    @TableField("updated_time")
    private LocalDateTime updatedTime;

    @ApiModelProperty("创建人")
    @TableField("created_by")
    private String createdBy;

    @ApiModelProperty("更新人")
    @TableField("updated_by")
    private String updatedBy;
}
