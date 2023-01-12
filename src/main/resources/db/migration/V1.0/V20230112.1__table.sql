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
