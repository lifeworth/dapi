package com.duzy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhiyuandu
 * @since 2023/1/7 17:12
 * @description
 */
@Data
public class CustomerModel {
    @Schema(description = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
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
    @TableField("is_delete")
    @TableLogic
    @Schema(description = "删除")
    private Integer isDelete;
}
