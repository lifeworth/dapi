package com.duzy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;

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
@TableName("bd_car_customer_parking")
public class BdCarCustomerParkingModel extends CustomerModel {


    @Schema(description = "租户ID")
    @TableField("tenant_id")
    private Long tenantId;

    @Schema(description = "车辆ID")
    @TableField("car_id")
    private Long carId;

    @Schema(description = "车牌号")
    @TableField("car_license")
    private String carLicense;

    @Schema(description = "客户ID")
    @TableField("customer_id")
    private Long customerId;

    @Schema(description = "客户")
    @TableField("customer")
    private String customer;

    @Schema(description = "车位id")
    @TableField("house_id")
    private Long houseId;

    @Schema(description = "车位名称")
    @TableField("house")
    private String house;

    @Schema(description = "附加说明")
    @TableField("remarks")
    private String remarks;

    @Schema(description = "经手人ID")
    @TableField("operator_id")
    private Long operatorId;

    @Schema(description = "经手人")
    @TableField("operator")
    private String operator;

    @Schema(description = "经手时间")
    @TableField("operator_time")
    private LocalDateTime operatorTime;

    @Schema(description = "租赁开始日期")
    @TableField("lease_start_date")
    private LocalDate leaseStartDate;

    @Schema(description = "租赁结束日期")
    @TableField("lease_end_date")
    private LocalDate leaseEndDate;

    @Schema(description = "租赁类型（新租，续租）")
    @TableField("lease_type")
    private String leaseType;

    @Schema(description = "所属房屋id")
    @TableField("belong_house_id")
    private Long belongHouseId;

    @Schema(description = "所属房屋name")
    @TableField("belong_house_name")
    private String belongHouseName;

    @Schema(description = "来源")
    @TableField("source")
    private String source;
}
