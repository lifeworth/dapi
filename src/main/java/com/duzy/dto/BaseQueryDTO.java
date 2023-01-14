package com.duzy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhiyuandu
 * @date 2021/12/21-13:15
 * @description 查询DTO 基础类
 **/
@Schema(description = "查询DTO 基础类")
@Data
public class BaseQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "当前页", defaultValue = "1")
    private Integer pageIndex;

    @Schema(description = "每页数量", defaultValue = "1")
    private Integer pageSize;

    @Schema(description = "排序字段")
    private String orderBy;

    @Schema(description = "默认正叙")
    private Boolean asc;

    @Schema(description = "创建时间开始")
    private LocalDateTime createTimeStart;

    @Schema(description = "创建时间结束")
    private LocalDateTime createTimeEnd;

    @Schema(description = "更新时间开始")
    private LocalDateTime updateTimeStart;

    @Schema(description = "更新时间结束")
    private LocalDateTime updateTimeEnd;

}
