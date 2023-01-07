package com.duzy.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhiyuandu
 * @since 2022/11/26 02:03
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NginxLogModel {
    //基础变量
    //  访问次数
    private Integer visit_times;
    //  ip:120.92.182.49
    private String remote_addr;
    //  [11/Jun/2020:00:00:51 +0800]
    private String time_local;
    //  GET POST
    private String requestMethod;
    //  /aggr/robot.php
    private String requestUrl;
    //  HTTP/1.0
    private String protocol;
    //  200
    private Integer status;
    //  1550
    private Integer bytes;
    //  请求源url
    private String http_referer;
    //  处理请求的总时间 0.340 (秒)
    private Double request_time;
    //  服务端的响应时间 0.340 (秒)
    private Double response_time;
    //  0.000 (秒)
    private Double connect_time;
    //  0.340 (秒)
    private Double header_time;
    //  日志日期 20200101
    private Double log_date;

    //聚合变量
    // 最大处理请求时间：状态码为200的请求的最大客户端请求时间
    private Double max_request_time;
    // 最大服务端响应时间：状态码为200的请求的最大服务端响应时间
    private Double max_response_time;
    // 最小处理请求时间：状态码为200的请求的最小客户端请求时间
    private Double min_request_time;
    // 最小服务端响应时间：状态码为200的请求的最小服务端响应时间
    private Double min_response_time;
    // 平均处理请求时间：状态码为200的请求的平均客户端请求时间
    private Double average_request_time;
    // 平均服务端响应时间：状态码为200的请求的平均服务端响应时间
    private Double average_response_time;
    // 状态码为200 的次数
    private Integer succeed_visit_times;
}
