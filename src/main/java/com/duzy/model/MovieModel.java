package com.duzy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
@Schema(description = "影视播放源")
public class MovieModel {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "地址")
    @TableField("source")
    private String source;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "速度")
    @TableField("speed")
    private Integer speed;

    @Schema(description = "创建人")
    @TableField("created_by")
    private String createdBy;

    @Schema(description = "创建时间")
    @TableField("created_time")
    private LocalDateTime createdTime;

    @Schema(description = "更新人")
    @TableField("updated_by")
    private String updatedBy;

    @Schema(description = "更新时间")
    @TableField("updated_time")
    private LocalDateTime updatedTime;
}
