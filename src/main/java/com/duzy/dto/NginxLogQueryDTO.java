package com.duzy.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhiyuandu
 * @since 2022/11/21 17:54
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "nginx日志查询对象", description = "nginx日志查询对象")
public class NginxLogQueryDTO extends BaseQueryDTO {


    private String ip;

    private Integer id;

    private String source;

}
