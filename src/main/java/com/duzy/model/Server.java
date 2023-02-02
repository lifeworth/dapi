package com.duzy.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 服务器列表
 * @TableName m_server
 */
@Data
@Accessors(chain = true)
@TableName(value ="server")
public class Server implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 服务器名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 地址
     */
    @TableField(value = "host")
    private String host;

    /**
     * 端口
     */
    @TableField(value = "port")
    private Integer port;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 备注
     */
    @TableField(value = "brief")
    private String brief;

    /**
     * 证书地址
     */
    @TableField(value = "certificate")
    private String certificate;

    /**
     * 
     */
    @TableField(value = "created_time")
    private LocalDateTime createdTime;

    /**
     * 
     */
    @TableField(value = "updated_time")
    private LocalDateTime updatedTime;

    /**
     * 
     */
    @TableField(value = "can_view")
    private Boolean canView;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
