package com.duzy.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zhiyuandu
 * @since 2023/1/12 22:13
 * @description
 */
@Data
@ApiModel(value = "MovieVo对象", description = "电影源")
public class MovieVo {
    private Integer id;

    @ApiModelProperty("地址")
    @JsonProperty("value")
    private String source;

    @ApiModelProperty("名称")
    @JsonProperty("label")
    private String name;

    @ApiModelProperty("速度")
    private Integer speed;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新人")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;
}
