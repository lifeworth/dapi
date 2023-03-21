package com.duzy.dto.query;

import io.swagger.v3.oas.annotations.media.Schema;
import com.duzy.dto.BaseQueryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 权限/资源
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "SysResourceQueryDto")
public class SysResourceQueryDto extends BaseQueryDTO{

    @Schema(description = "资源名称")
    private String resourceName;
    @Schema(description = "资源路径")
    private String resourceUrl;
}
