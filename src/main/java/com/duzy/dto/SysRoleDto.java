package com.duzy.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhiyuandu
 * @since 2023/3/18 17:37
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRoleDto extends CustomerDto {

    @Schema(description = "角色名称")
    private String roleName;
}
