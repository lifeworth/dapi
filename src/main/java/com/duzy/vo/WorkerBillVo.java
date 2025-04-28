package com.duzy.vo;

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
public class WorkerBillVo extends CustomerVo{

    @Schema(description = "")
    private String workTypeParentName;
    @Schema(description = "")
    private String workTypeSecondName;
    @Schema(description = "")
    private String workTypeFullName;
    @Schema(description = "")
    private String content;
    @Schema(description = "")
    private String urgent;
}
