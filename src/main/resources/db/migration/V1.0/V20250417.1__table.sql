CREATE TABLE `bd_customer` (
  `id` bigint NOT NULL COMMENT '主键',
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `project_id` bigint NOT NULL COMMENT '项目ID',
  `org_id` bigint DEFAULT NULL COMMENT '所属组织机构ID',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'CUSTOMER' COMMENT '客商类型(业户/开发商/供应商)',
  `customer_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'PERSONAL' COMMENT '业户类型',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户名称',
  `phone_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码区域/类型',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '电子邮箱',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别',
  `nation` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '民族',
  `certificate_type_id` bigint DEFAULT NULL COMMENT '证件类型ID',
  `certificate_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '证件类型',
  `certificateno` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '证件号码',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `country` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '国籍',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系地址',
  `customer_grade_id` bigint DEFAULT NULL COMMENT '客户等级ID',
  `customer_grade` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户等级',
  `industry_category_id` bigint DEFAULT NULL COMMENT '行业分类ID',
  `industry_category` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '行业分类',
  `tax_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '纳税人识别号',
  `contact_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司联系人姓名',
  `deposit_bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '待删除',
  `company_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司账号',
  `postal_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮政编号',
  `job_type_Id` bigint DEFAULT NULL COMMENT '行业分类id',
  `post` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '职务',
  `job_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '行业分类',
  `china_customer` tinyint(1) DEFAULT NULL COMMENT '是否大陆企业',
  `unit_internal` tinyint(1) DEFAULT NULL COMMENT '是否内部单位',
  `belong_company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属公司名称',
  `sealed` tinyint(1) DEFAULT NULL COMMENT '封存',
  `class_id` bigint DEFAULT NULL COMMENT '品类id（字典值）',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '品类',
  `performance_enable` tinyint(1) DEFAULT NULL COMMENT '是否提报业绩',
  `bank_acount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '待删除',
  `invoice_tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发票电话',
  `invoice_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发票地址',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '编号',
  `invoice_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发票名称(收款方户名)',
  `invoice_account_bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发票开户行(开户行名称)',
  `invoice_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发票开户行账号(收款银行账号)',
  `regist_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '户籍所在地',
  `union_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '微信unionId',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint DEFAULT NULL COMMENT '创建人id',
  `create_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人姓名',
  `modifier_time` datetime DEFAULT NULL COMMENT '修改时间',
  `modifier_id` bigint DEFAULT NULL COMMENT '修改人id',
  `modifier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人姓名',
  `lease_manager_id` bigint DEFAULT NULL COMMENT '招商经理id',
  `lease_manager_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '招商经理名称',
  `extsource` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '外部来源',
  `ext_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '外部ID',
  `ext_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '外部code',
  `ext_memo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '外部备注',
  `from_org_customer` tinyint(1) DEFAULT NULL COMMENT '是否来源于集团客商',
  `org_customer_id` bigint DEFAULT NULL COMMENT '集团客商id',
  `invoice_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发票手机号',
  `invoice_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发票邮箱',
  `good_customer` tinyint(1) DEFAULT NULL COMMENT '优质客户',
  `advance_payment_supervision` tinyint(1) DEFAULT NULL COMMENT '预付款监管',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `enterprise_properties_id` bigint DEFAULT NULL COMMENT '企业性质id',
  `enterprise_properties_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '企业性质名称',
  `year_tax_amount` decimal(18,6) DEFAULT NULL COMMENT '年纳税金额(万元)',
  `credit_degree` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '信用度',
  `credit_degree_id` bigint DEFAULT NULL COMMENT '信用度Id',
  `arrears_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '欠费类别',
  `arrears_category_id` bigint DEFAULT NULL COMMENT '欠费类别Id',
  `hobby` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '兴趣爱好',
  `additional_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '附加说明',
  `profession_id` bigint DEFAULT NULL COMMENT '职业id',
  `profession` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '职业',
  `work_unit` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '工作单位',
  `if_internal` tinyint(1) DEFAULT NULL COMMENT '是否内部客户',
  `total_staff` int DEFAULT NULL COMMENT '人员办公数量',
  `for_short` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '公司简称',
  `enterprise_contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '企业联系人',
  `contact_certificateno` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '企业联系人证件号码',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系人电话',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `customer_tid` (`tenant_id`) USING BTREE,
  KEY `customer_orgid` (`org_id`) USING BTREE,
  KEY `customer_projectid` (`project_id`) USING BTREE,
  KEY `customer_pphone` (`tenant_id`,`project_id`,`phone`) USING BTREE,
  KEY `bd_customer_ext_id_index` (`ext_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='业户表，业主租户';

CREATE TABLE `bd_car_customer_parking` (
  `id` bigint NOT NULL COMMENT '主键',
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `car_id` bigint NOT NULL COMMENT '车辆ID',
  `car_license` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车牌号',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `customer` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '客户',
  `house_id` bigint DEFAULT NULL COMMENT '车位id',
  `house` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '车位名称',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '附加说明',
  `operator_id` bigint DEFAULT NULL COMMENT '经手人ID',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '经手人',
  `operator_time` datetime DEFAULT NULL COMMENT '经手时间',
  `lease_start_date` date DEFAULT NULL COMMENT '租赁开始日期',
  `lease_end_date` date DEFAULT NULL COMMENT '租赁结束日期',
  `lease_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '租赁类型（新租，续租）',
  `belong_house_id` bigint DEFAULT NULL COMMENT '所属房屋id',
  `belong_house_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属房屋name',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '来源',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `house_customer_tid` (`tenant_id`) USING BTREE,
  KEY `house_customer_hid` (`car_id`) USING BTREE,
  KEY `house_customer_cid` (`customer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='车辆人员车位关系';

CREATE TABLE `bd_car_change_record` (
  `id` bigint NOT NULL COMMENT '主键',
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `before_car_id` bigint DEFAULT NULL COMMENT '变更前车辆id',
  `before_car_license` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '变更前车辆车牌号',
  `after_car_id` bigint DEFAULT NULL COMMENT '变更后车辆ID',
  `after_car_license` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '变更后车牌号',
  `operator_id` bigint DEFAULT NULL COMMENT '操作人ID',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作人',
  `operator_time` datetime DEFAULT NULL COMMENT '操作时间',
  `change_date` date DEFAULT NULL COMMENT '变更日期',
  `source` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '来源',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '附加说明',
  `house_id` bigint DEFAULT NULL COMMENT '车位id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `house_customer_tid` (`tenant_id`) USING BTREE,
  KEY `house_customer_hid` (`after_car_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='车位变更记录';

CREATE TABLE `bd_car_customer_history` (
  `id` bigint NOT NULL COMMENT '主键',
  `tenant_id` bigint NOT NULL COMMENT '租户ID',
  `project_id` bigint DEFAULT NULL COMMENT '项目id',
  `car_id` bigint NOT NULL COMMENT '车辆ID',
  `car_license` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '车牌号',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `customer` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户',
  `unbind_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '解除绑定原因',
  `operator_id` bigint DEFAULT NULL COMMENT '经手人ID',
  `operator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '经手人',
  `operator_time` datetime DEFAULT NULL COMMENT '经手时间',
  `belong_house_id` bigint DEFAULT NULL COMMENT '所属房屋id',
  `belong_house_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属房屋name',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `house_customer_tid` (`tenant_id`) USING BTREE,
  KEY `house_customer_hid` (`car_id`) USING BTREE,
  KEY `house_customer_cid` (`customer_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='车位历史客户';


alter table bd_customer add created_time datetime null comment '创建时间';
alter table bd_customer add updated_time datetime null comment '更新时间';
alter table bd_customer add created_by varchar(32)  null comment '创建人';
alter table bd_customer add updated_by varchar(32) null comment '更新人';
alter table bd_customer add is_delete tinyint(1) default 0 comment '是否删除';


alter table bd_car_customer_parking add created_time datetime null comment '创建时间';
alter table bd_car_customer_parking add updated_time datetime null comment '更新时间';
alter table bd_car_customer_parking add created_by varchar(32)  null comment '创建人';
alter table bd_car_customer_parking add updated_by varchar(32) null comment '更新人';
alter table bd_car_customer_parking add is_delete tinyint(1) default 0 comment '是否删除';

alter table bd_car_change_record add created_time datetime null comment '创建时间';
alter table bd_car_change_record add updated_time datetime null comment '更新时间';
alter table bd_car_change_record add created_by varchar(32)  null comment '创建人';
alter table bd_car_change_record add updated_by varchar(32) null comment '更新人';
alter table bd_car_change_record add is_delete tinyint(1) default 0 comment '是否删除';


alter table bd_car_customer_history add created_time datetime null comment '创建时间';
alter table bd_car_customer_history add updated_time datetime null comment '更新时间';
alter table bd_car_customer_history add created_by varchar(32)  null comment '创建人';
alter table bd_car_customer_history add updated_by varchar(32) null comment '更新人';
alter table bd_car_customer_history add is_delete tinyint(1) default 0 comment '更新人';