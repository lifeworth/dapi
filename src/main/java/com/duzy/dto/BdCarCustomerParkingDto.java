package com.duzy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 车辆人员车位关系
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "BdCarCustomerParkingDto")
public class BdCarCustomerParkingDto extends CustomerDto{

    @Schema(description = "租户ID")
    private Long tenantId;
    @Schema(description = "车辆ID")
    private Long carId;
    @Schema(description = "车牌号")
    private String carLicense;
    @Schema(description = "客户ID")
    private Long customerId;
    @Schema(description = "客户")
    private String customer;
    @Schema(description = "车位id")
    private Long houseId;
    @Schema(description = "车位名称")
    private String house;
    @Schema(description = "附加说明")
    private String remarks;
    @Schema(description = "经手人ID")
    private Long operatorId;
    @Schema(description = "经手人")
    private String operator;
    @Schema(description = "经手时间")
    private LocalDateTime operatorTime;
    @Schema(description = "租赁开始日期")
    private LocalDate leaseStartDate;
    @Schema(description = "租赁结束日期")
    private LocalDate leaseEndDate;
    @Schema(description = "租赁类型（新租，续租）")
    private String leaseType;
    @Schema(description = "所属房屋id")
    private Long belongHouseId;
    @Schema(description = "所属房屋name")
    private String belongHouseName;
    @Schema(description = "来源")
    private String source;
}
