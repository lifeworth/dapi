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
    role_type int null comment '角色类型',
    can_view tinyint null comment '可以预览',
    PRIMARY KEY (id)
)  COMMENT = '用户';

CREATE TABLE ip_location(
    id INT NOT NULL AUTO_INCREMENT  COMMENT 'id' ,
    country VARCHAR(90) NOT NULL   ,
    status VARCHAR(90) NOT NULL    ,
    message VARCHAR(90) NOT NULL    ,
    currency VARCHAR(255) NOT NULL    ,
    district VARCHAR(255)     ,
    continent VARCHAR(255)     ,
    offset VARCHAR(255)     ,
    continent_code VARCHAR(255)     ,
    country_code VARCHAR(255)     ,
    region VARCHAR(255)     ,
    region_name VARCHAR(32)     ,
    city VARCHAR(90)     ,
    zip VARCHAR(32)     ,
    lat VARCHAR(90)     ,
    lon int null ,
    timezone VARCHAR(90) null ,
    isp VARCHAR(90) null ,
    org VARCHAR(90) null ,
    as_name VARCHAR(90) null,
    query VARCHAR(90) null ,
    mobile VARCHAR(90) null,
    proxy VARCHAR(90) null ,
    hosting VARCHAR(90) null,
    ip VARCHAR(90) null ,
    PRIMARY KEY (id)
)  COMMENT = 'ip地理位置信息';

CREATE TABLE ssh_log(
    id INT NOT NULL AUTO_INCREMENT  COMMENT 'id' ,
    ip VARCHAR(90) NOT NULL   ,
    source VARCHAR(90) NOT NULL    ,
    trigger_time VARCHAR(90) NOT NULL,
    PRIMARY KEY (id)
)  COMMENT = 'ssh日志';

