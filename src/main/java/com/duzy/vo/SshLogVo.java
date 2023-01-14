package com.duzy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * ssh日志
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-11-20
 */
@Getter
@Setter
@Schema(description = "ssh日志")
public class SshLogVo {

    @Schema(description = "id")
    private Integer id;
    @Schema(description = "ip")
    private String ip;
    @Schema(description = "源")
    private String source;
    @Schema(description = "时间")
    private LocalDateTime triggerTime;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
