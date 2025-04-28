package com.duzy.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-25
 */
@Data
@Schema(name = "WorkerBillVo")
@EqualsAndHashCode(callSuper = false)
public class WorkerBillExportVo extends CustomerVo {

    @Schema(description = "")
    @ExcelProperty(value = "分类1")
    private String workTypeParentName;
    @Schema(description = "")
    @ExcelProperty(value = "分类2")
    private String workTypeSecondName;
    @Schema(description = "")
    @ExcelProperty(value = "分类3")
    private String workTypeFullName;
    @Schema(description = "")
    @ExcelProperty(value = "内容")
    private String content;
    @Schema(description = "")
    @ExcelIgnore
    private String urgent;
}
