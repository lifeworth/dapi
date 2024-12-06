DROP TABLE IF EXISTS user;
CREATE TABLE user(
    id INT NOT NULL AUTO_INCREMENT  COMMENT 'id' ,
    username VARCHAR(90) NOT NULL   COMMENT '登录名' ,
    nick VARCHAR(90) NOT NULL   COMMENT '昵称' ,
    password VARCHAR(90) NOT NULL   COMMENT '密码' ,
    phone VARCHAR(255) NOT NULL   COMMENT '手机号' ,
    avatar VARCHAR(255)    COMMENT '头像' ,
    email VARCHAR(255)    COMMENT '邮箱地址' ,
    signature VARCHAR(255)    COMMENT '签名' ,
    title VARCHAR(255)    COMMENT '头衔' ,
    country VARCHAR(255)    COMMENT '国家' ,
    address VARCHAR(255)    COMMENT '地址' ,
    created_by VARCHAR(32)    COMMENT '创建人' ,
    created_time DATETIME    COMMENT '创建时间' ,
    updated_by VARCHAR(32)    COMMENT '更新人' ,
    updated_time DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id)
)  COMMENT = '用户';


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

CREATE TABLE movie(
    id INT NOT NULL AUTO_INCREMENT  COMMENT '' ,
    source VARCHAR(255)    COMMENT '地址' ,
    name VARCHAR(255)    COMMENT '名称' ,
    speed INT    COMMENT '速度' ,
    created_by VARCHAR(32)    COMMENT '创建人' ,
    created_time DATETIME    COMMENT '创建时间' ,
    updated_by VARCHAR(32)    COMMENT '更新人' ,
    updated_time DATETIME    COMMENT '更新时间' ,
    PRIMARY KEY (id)
)  COMMENT = '影视播放源';

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
