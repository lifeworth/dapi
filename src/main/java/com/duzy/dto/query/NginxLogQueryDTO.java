package com.duzy.dto.query;

import com.duzy.dto.BaseQueryDTO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "nginx日志查询对象")
public class NginxLogQueryDTO extends BaseQueryDTO {

    @Schema(description = "主键")
    private Integer id;

    @Schema(description = "ip地址")
    private String ip;

    @Schema(description = "请求时间")
    private String dateTime;

    @Schema(description = "请求方法")
    private String requestMethod;

    @Schema(description = "请求URL")
    private String requestUrl;

    @Schema(description = "请求协议")
    private String protocol;

    @Schema(description = "响应状态")
    private String status;

    @Schema(description = "响应大小")
    private String bytes;

    @Schema(description = "refere")
    private String referer;

    @Schema(description = "agent")
    private String agent;

    @Schema(description = "原请求记录")
    private String source;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "更新人")
    private String updatedBy;

}
