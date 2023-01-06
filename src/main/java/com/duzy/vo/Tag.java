package com.duzy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhiyuandu
 * @since 2023/1/7 00:22
 * @description
 */

@Data
@ApiModel(value = "标签VO", description = "标签VO")
public class Tag {
    @ApiModelProperty("key")
    private String key;
    @ApiModelProperty("label")
    private String label;
}
