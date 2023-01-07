package com.duzy.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Data
@TableName("ssh_log")
@ApiModel(value = "SshLogModel对象", description = "ssh日志")
@EqualsAndHashCode(callSuper = false)
public class SshLogModel extends BaseModel {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "ip")
    private String ip;
    @TableField(value = "source")
    private String source;
    @TableField(value = "trigger_time")
    private String triggerTime;
}
