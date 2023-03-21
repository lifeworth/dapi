package com.duzy.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.duzy.model.CustomerModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.v3.oas.annotations.media.Schema;
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
@TableName("sys_resource")
public class SysResourceModel extends CustomerModel {


    @Schema(description = "资源名称")
    @TableField("resource_name")
    private String resourceName;

    @Schema(description = "资源路径")
    @TableField("resource_url")
    private String resourceUrl;
}
