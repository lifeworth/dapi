package com.duzy.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
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
}
