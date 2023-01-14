package com.duzy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhiyuandu
 * @since 2023/1/7 17:12
 * @description
 */
@Data
public class BaseModel {
    @Schema(description = "创建人")
    @TableField("created_by")
    private String createdBy;
    @Schema(description = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;
    @Schema(description = "更新人")
    @TableField("updated_by")
    private String updatedBy;
    @Schema(description = "更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
