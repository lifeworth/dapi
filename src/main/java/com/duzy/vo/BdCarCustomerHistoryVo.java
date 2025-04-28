package com.duzy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 车位历史客户
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Data
@Schema(name = "BdCarCustomerHistoryVo")
@EqualsAndHashCode(callSuper = false)
public class BdCarCustomerHistoryVo extends CustomerVo{

    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "项目id")
    private Long projectId;
    @Schema(description = "车辆ID")
    private Long carId;
    @Schema(description = "车牌号")
    private String carLicense;
    @Schema(description = "客户ID")
    private Long customerId;
    @Schema(description = "客户")
    private String customer;
    @Schema(description = "解除绑定原因")
    private String unbindReason;
    @Schema(description = "经手人ID")
    private Long operatorId;
    @Schema(description = "经手人")
    private String operator;
    @Schema(description = "经手时间")
    private LocalDateTime operatorTime;
    @Schema(description = "所属房屋id")
    private Long belongHouseId;
    @Schema(description = "所属房屋name")
    private String belongHouseName;
}
