package com.duzy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * <p>
 * 
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-25
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("worker_bill")
public class WorkerBillModel extends CustomerModel {


    @Schema(description = "")
    @TableField("work_type_parent_name")
    private String workTypeParentName;

    @Schema(description = "")
    @TableField("work_type_second_name")
    private String workTypeSecondName;

    @Schema(description = "")
    @TableField("work_type_full_name")
    private String workTypeFullName;

    @Schema(description = "")
    @TableField("content")
    private String content;

    @Schema(description = "")
    @TableField("urgent")
    private String urgent;
}
