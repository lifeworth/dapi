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

CREATE TABLE `ip_location` (
  `id` int NOT NULL AUTO_INCREMENT,
  `country` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `currency` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `district` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `continent` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `offset` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `continent_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `country_code` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `region` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `region_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `city` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `zip` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lat` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lon` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `timezone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `isp` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `org` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `as` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `as_name` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `query` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `mobile` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `proxy` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `hosting` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ip` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `ssh_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `source` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `trigger_time` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

