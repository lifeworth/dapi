package com.duzy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * ip地理位置信息
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-11-14
 */
@Getter
@Setter
@TableName("ip_location")
@ToString
@ApiModel(value = "IpLocationModel对象", description = "ip地理位置信息")
public class IpLocationModel {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("country")
    private String country;

    @TableField("`status`")
    private String status;

    @TableField("`message`")
    private String message;

    @TableField("`currency`")
    private String currency;

    @TableField("`district`")
    private String district;

    @TableField("continent")
    private String continent;

    @TableField("`offset`")
    private String offset;

    @TableField("continent_code")
    private String continentCode;

    @TableField("country_code")
    private String countryCode;

    @TableField("region")
    private String region;

    @TableField("region_name")
    private String regionName;

    @TableField("city")
    private String city;

    @TableField("zip")
    private String zip;

    @TableField("lat")
    private String lat;

    @TableField("lon")
    private String lon;

    @TableField("timezone")
    private String timezone;

    @TableField("isp")
    private String isp;

    @TableField("org")
    private String org;

    @TableField("`as`")
    private String as;

    @TableField("`as_name`")
    private String asname;

    @TableField("query")
    private String query;

    @TableField("mobile")
    private String mobile;

    @TableField("proxy")
    private String proxy;

    @TableField("hosting")
    private String hosting;

    @TableField("ip")
    private String ip;


    @ApiModelProperty("创建人")
    @TableField("created_by")
    private String createdBy;
    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;
    @ApiModelProperty("更新人")
    @TableField("updated_by")
    private String updatedBy;
    @ApiModelProperty("更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
