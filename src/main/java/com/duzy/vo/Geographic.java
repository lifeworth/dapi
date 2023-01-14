package com.duzy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhiyuandu
 * @since 2023/1/7 00:22
 * @description
 */

@Data
public class Geographic {
    @Schema(description = "省")
    private Province province;

    @Schema(description = "城市")
    private City city;
}
