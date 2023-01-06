package com.duzy.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhiyuandu
 * @since 2023/1/7 00:22
 * @description
 */

@Data
public class Geographic {
    @ApiModelProperty("省")
    private Province province;

    @ApiModelProperty("城市")
    private City city;
}
