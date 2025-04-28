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
 * 车位变更记录
 * </p>
 *
 * @author zhiyuandu
 * @since 2025-04-17
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bd_car_change_record")
public class BdCarChangeRecordModel extends CustomerModel {


    @Schema(description = "租户ID")
    @TableField("tenant_id")
    private Long tenantId;

    @Schema(description = "变更前车辆id")
    @TableField("before_car_id")
    private Long beforeCarId;

    @Schema(description = "变更前车辆车牌号")
    @TableField("before_car_license")
    private String beforeCarLicense;

    @Schema(description = "变更后车辆ID")
    @TableField("after_car_id")
    private Long afterCarId;

    @Schema(description = "变更后车牌号")
    @TableField("after_car_license")
    private String afterCarLicense;

    @Schema(description = "操作人ID")
    @TableField("operator_id")
    private Long operatorId;

    @Schema(description = "操作人")
    @TableField("operator")
    private String operator;

    @Schema(description = "操作时间")
    @TableField("operator_time")
    private LocalDateTime operatorTime;

    @Schema(description = "变更日期")
    @TableField("change_date")
    private LocalDate changeDate;

    @Schema(description = "来源")
    @TableField("source")
    private String source;

    @Schema(description = "附加说明")
    @TableField("remarks")
    private String remarks;

    @Schema(description = "车位id")
    @TableField("house_id")
    private Long houseId;
}
