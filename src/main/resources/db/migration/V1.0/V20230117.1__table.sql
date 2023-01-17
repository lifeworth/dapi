alter table dapi.user
    add role_type int null comment '角色类型';

alter table dapi.user
    add can_view tinyint null comment '可以预览';
