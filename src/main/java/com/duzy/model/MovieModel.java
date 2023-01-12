package com.duzy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 影视播放源
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-01-12
 */
@Getter
@Setter
@TableName("movie")
@ApiModel(value = "MovieModel对象", description = "影视播放源")
public class MovieModel {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("地址")
    @TableField("source")
    private String source;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("速度")
    @TableField("speed")
    private Integer speed;

    @ApiModelProperty("创建人")
    @TableField("created_by")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @ApiModelProperty("更新人")
    @TableField("updated_by")
    private String updatedBy;

    @ApiModelProperty("更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
