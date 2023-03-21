package com.duzy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
@Schema(description = "nginx日志")
public class NginxLogModel {

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "ip地址")
    @TableField("ip")
    private String ip;

    @Schema(description = "请求时间")
    @TableField("date_time")
    private String dateTime;

    @Schema(description = "请求方法")
    @TableField("request_method")
    private String requestMethod;

    @Schema(description = "请求URL")
    @TableField("request_url")
    private String requestUrl;

    @Schema(description = "请求协议")
    @TableField("protocol")
    private String protocol;

    @Schema(description = "响应状态")
    @TableField("status")
    private String status;

    @Schema(description = "响应大小")
    @TableField("bytes")
    private String bytes;

    @Schema(description = "refere")
    @TableField("referer")
    private String referer;

    @Schema(description = "agent")
    @TableField("agent")
    private String agent;

    @Schema(description = "原请求记录")
    @TableField("source")
    private String source;

    @TableField("created_time")
    private LocalDateTime createdTime;

    @TableField("updated_time")
    private LocalDateTime updatedTime;

    @Schema(description = "创建人")
    @TableField("created_by")
    private String createdBy;

    @Schema(description = "更新人")
    @TableField("updated_by")
    private String updatedBy;
}
