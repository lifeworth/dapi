package com.duzy.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhiyuandu
 * @since 2023/1/8 15:53
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IpQueryDTO extends BaseQueryDTO {
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
