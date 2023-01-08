package com.duzy.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author zhiyuandu
 * @since 2023/1/8 15:52
 * @description
 */
@Data
public class IpVo {
    private Integer id;

    private String country;

    private String status;

    private String message;

    private String currency;

    private String district;

    private String continent;

    private String offset;

    private String continentCode;

    private String countryCode;

    private String region;

    private String regionName;

    private String city;

    private String zip;

    private String lat;

    private String lon;

    private String timezone;

    private String isp;

    private String org;

    private String as;

    private String asname;

    private String query;

    private String mobile;

    private String proxy;

    private String hosting;

    private String ip;
}
