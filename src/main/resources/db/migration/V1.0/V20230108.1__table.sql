CREATE TABLE nginx_log(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '主键' ,
    ip VARCHAR(255)    COMMENT 'ip地址' ,
    date_time VARCHAR(255)    COMMENT '请求时间' ,
    request_method VARCHAR(255)    COMMENT '请求方法' ,
    request_url VARCHAR(255)    COMMENT '请求URL' ,
    protocol VARCHAR(255)    COMMENT '请求协议' ,
    status VARCHAR(255)    COMMENT '响应状态' ,
    bytes VARCHAR(255)    COMMENT '响应大小' ,
    referer VARCHAR(255)    COMMENT 'refere' ,
    agent VARCHAR(255)    COMMENT 'agent' ,
    source VARCHAR(900)    COMMENT '原请求记录' ,
    created_time   datetime default CURRENT_TIMESTAMP not null,
    updated_time   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    created_by     varchar(32)                        null comment '创建人',
    updated_by     varchar(32)                        null comment '更新人',
    PRIMARY KEY (id)
)  COMMENT = 'nginx日志';
