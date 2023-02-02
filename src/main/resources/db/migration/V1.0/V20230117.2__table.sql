CREATE TABLE server(
    id INT NOT NULL AUTO_INCREMENT  COMMENT 'id' ,
    title VARCHAR(255)    COMMENT '服务器名称' ,
    host VARCHAR(255)    COMMENT '地址' ,
    port VARCHAR(255)    COMMENT '端口' ,
    username VARCHAR(255)    COMMENT '用户名' ,
    password VARCHAR(255)    COMMENT '密码' ,
    brief VARCHAR(255)    COMMENT '备注' ,
    certificate VARCHAR(255)    COMMENT '证书地址' ,
    can_view VARCHAR(1)    COMMENT '' ,
    created_by VARCHAR(32)    COMMENT '创建人' ,
    created_time DATETIME    COMMENT '创建时间' ,
    updated_by VARCHAR(32)    COMMENT '更新人' ,
    updated_time DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id)
)  COMMENT = '服务器';
