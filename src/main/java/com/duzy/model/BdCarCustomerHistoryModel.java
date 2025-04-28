package com.duzy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;

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
@EqualsAndHashCode(callSuper = false)
@TableName("bd_car_customer_history")
public class BdCarCustomerHistoryModel extends CustomerModel {


    @Schema(description = "租户ID")
    @TableField("tenant_id")
    private Long tenantId;

    @Schema(description = "项目id")
    @TableField("project_id")
    private Long projectId;

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

    @Schema(description = "解除绑定原因")
    @TableField("unbind_reason")
    private String unbindReason;

    @Schema(description = "经手人ID")
    @TableField("operator_id")
    private Long operatorId;

    @Schema(description = "经手人")
    @TableField("operator")
    private String operator;

    @Schema(description = "经手时间")
    @TableField("operator_time")
    private LocalDateTime operatorTime;

    @Schema(description = "所属房屋id")
    @TableField("belong_house_id")
    private Long belongHouseId;

    @Schema(description = "所属房屋name")
    @TableField("belong_house_name")
    private String belongHouseName;
}
