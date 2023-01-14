package com.duzy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhiyuandu
 * @since 2023/1/7 00:22
 * @description
 */

@Data
@Schema(description = "标签VO")
public class Tag {
    @Schema(description = "key")
    private String key;
    @Schema(description = "label")
    private String label;
}
