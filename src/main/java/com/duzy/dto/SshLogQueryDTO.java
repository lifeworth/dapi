package com.duzy.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author zhiyuandu
 * @since 2022/11/21 17:54
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ssh日志查询对象", description = "ssh日志查询对象")
public class SshLogQueryDTO extends BaseQueryDTO {


    private String ip;

    private Integer id;

    private String source;

}
