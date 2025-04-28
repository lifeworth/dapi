package com.duzy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 车位变更记录
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Data
@Schema(name = "BdCarChangeRecordVo")
@EqualsAndHashCode(callSuper = false)
public class BdCarChangeRecordVo extends CustomerVo{

    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "变更前车辆id")
    private Long beforeCarId;
    @Schema(description = "变更前车辆车牌号")
    private String beforeCarLicense;
    @Schema(description = "变更后车辆ID")
    private Long afterCarId;
    @Schema(description = "变更后车牌号")
    private String afterCarLicense;
    @Schema(description = "操作人ID")
    private Long operatorId;
    @Schema(description = "操作人")
    private String operator;
    @Schema(description = "操作时间")
    private LocalDateTime operatorTime;
    @Schema(description = "变更日期")
    private LocalDate changeDate;
    @Schema(description = "来源")
    private String source;
    @Schema(description = "附加说明")
    private String remarks;
    @Schema(description = "车位id")
    private Long houseId;
}
