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
