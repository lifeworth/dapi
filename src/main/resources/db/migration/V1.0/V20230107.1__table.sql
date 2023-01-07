alter table dapi.ip_location add created_time datetime null comment '创建时间';
alter table dapi.ip_location add updated_time datetime null comment '更新时间';
alter table dapi.ip_location add created_by varchar(32)  null comment '创建人';
alter table dapi.ip_location add updated_by varchar(32) null comment '更新人';

alter table dapi.ssh_log add created_time datetime null comment '创建时间';
alter table dapi.ssh_log add updated_time datetime null comment '更新时间';
alter table dapi.ssh_log add created_by varchar(32)  null comment '创建人';
alter table dapi.ssh_log add updated_by varchar(32) null comment '更新人';


ALTER TABLE ip_location MODIFY COLUMN created_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE ip_location MODIFY COLUMN updated_time datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL;

ALTER TABLE ssh_log MODIFY COLUMN created_time datetime DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE ssh_log MODIFY COLUMN updated_time datetime DEFAULT CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP NOT NULL;
