package com.duzy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role")
@Schema(description = "角色")
public class SysRoleModel extends CustomerModel {

    @Schema(description = "角色名称")
    @TableField("role_name")
    private String roleName;


}
