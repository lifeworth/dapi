package com.duzy.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author zhiyuandu
 * @since 2022/11/21 17:54
 * @description
 */
@Data
@ApiModel(value = "ssh日志查询对象", description = "ssh日志查询对象")
public class SshLogQueryDTO extends BaseQueryDTO {

    private String ip;
}
