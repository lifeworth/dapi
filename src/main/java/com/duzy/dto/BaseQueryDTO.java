package com.duzy.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhiyuandu
 * @date 2021/12/21-13:15
 * @description 查询DTO 基础类
 **/
@ApiModel("查询DTO 基础类")
@Data
public class BaseQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("当前页")
    private Integer pageIndex;

    @ApiModelProperty("每页数量")
    private Integer pageSize;

    @ApiModelProperty("排序字段")
    private String orderBy;

    @ApiModelProperty("默认正叙")
    private Boolean asc;

    @ApiModelProperty("创建时间开始")
    private LocalDateTime createTimeStart;

    @ApiModelProperty("创建时间结束")
    private LocalDateTime createTimeEnd;

    @ApiModelProperty("更新时间开始")
    private LocalDateTime updateTimeStart;

    @ApiModelProperty("更新时间结束")
    private LocalDateTime updateTimeEnd;

}
