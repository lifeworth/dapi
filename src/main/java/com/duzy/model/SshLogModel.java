package com.duzy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Schema(description = "ssh日志")
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
