package com.duzy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.duzy.vo.CustomerVo;
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
@Schema(name = "SysResourceVo")
@EqualsAndHashCode(callSuper = false)
public class SysResourceVo extends CustomerVo{

    @Schema(description = "资源名称")
    private String resourceName;
    @Schema(description = "资源路径")
    private String resourceUrl;
}
