package com.duzy.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
@TableName("ssh_log")
@ApiModel(value = "SshLogModel对象", description = "ssh日志")
public class SshLogModel {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "ip")
    private String ip;
    @TableField(value = "source")
    private String source;
    @TableField(value = "trigger_time")
    private String triggerTime;

    @ApiModelProperty("创建人")
    @TableField("created_by")
    private String createdBy;
    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;
    @ApiModelProperty("更新人")
    @TableField("updated_by")
    private String updatedBy;
    @ApiModelProperty("更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
