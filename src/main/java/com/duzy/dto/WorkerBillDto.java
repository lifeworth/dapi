package com.duzy.dto;

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
@EqualsAndHashCode(callSuper = false)
@Schema(name = "WorkerBillDto")
public class WorkerBillDto extends CustomerDto{

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
