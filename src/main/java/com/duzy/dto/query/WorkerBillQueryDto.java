package com.duzy.dto.query;

import io.swagger.v3.oas.annotations.media.Schema;
import com.duzy.dto.BaseQueryDTO;
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
@Schema(name = "WorkerBillQueryDto")
public class WorkerBillQueryDto extends BaseQueryDTO{

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
