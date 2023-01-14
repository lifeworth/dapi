package com.duzy.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhiyuandu
 * @since 2023/1/12 22:13
 * @description
 */
@Data
@Schema(description = "电影源")
public class MovieVo {
    private Integer id;

    @Schema(description = "地址")
    @JsonProperty("value")
    private String source;

    @Schema(description = "名称")
    @JsonProperty("label")
    private String name;

    @Schema(description = "速度")
    private Integer speed;

    @Schema(description = "创建人")
    private String createdBy;

    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新人")
    private String updatedBy;

    @Schema(description = "更新时间")
    private LocalDateTime updatedTime;
}
