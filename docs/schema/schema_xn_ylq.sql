CREATE TABLE `t_apply` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) NOT NULL COMMENT '类型',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `product_code` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `sx_amount` bigint(32) DEFAULT NULL COMMENT '授信额度',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `jdt_report` longtext DEFAULT NULL COMMENT '借贷通资信报告',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_borrow` (
  `code` varchar(32) NOT NULL COMMENT '编号（合同编号）',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `sign_datetime` datetime DEFAULT NULL COMMENT '签约时间',
  `amount` bigint(32) DEFAULT NULL COMMENT '借款金额',
  `level` varchar(32) DEFAULT NULL COMMENT '借款等级',
  `duration` int(11) DEFAULT NULL COMMENT '借款时长',
  `fk_datetime` datetime DEFAULT NULL COMMENT '放款时间',
  `jx_datetime` datetime DEFAULT NULL COMMENT '计息时间',
  `hk_datetime` datetime DEFAULT NULL COMMENT '约定还款时间',
  `lx_rate` decimal(18,8) DEFAULT NULL COMMENT '正常利息利率',
  `lx_amount` bigint(32) DEFAULT NULL COMMENT '正常应付利息',
  `xs_amount` bigint(32) DEFAULT NULL COMMENT '快速信审费',
  `gl_amount` bigint(32) DEFAULT NULL COMMENT '账户管理费',
  `fw_amount` bigint(32) DEFAULT NULL COMMENT '服务费',
  `yh_amount` bigint(32) DEFAULT NULL COMMENT '优惠金额',
  `rate1` decimal(18,8) DEFAULT NULL COMMENT '7天内逾期利率',
  `rate2` decimal(18,8) DEFAULT NULL COMMENT '7天外逾期利率',
  `yqlx_amount` bigint(32) DEFAULT NULL COMMENT '逾期利息',
  `yq_days` int(11) DEFAULT NULL COMMENT '逾期天数',
  `total_amount` bigint(32) DEFAULT NULL COMMENT '总共应还金额',
  `real_hk_datetime` datetime DEFAULT NULL COMMENT '实际还款时间',
  `real_hk_amount` bigint(32) DEFAULT NULL COMMENT '实际还款金额',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_type` varchar(32) DEFAULT NULL COMMENT '还款方式',
  `loan_type` varchar(32) DEFAULT NULL COMMENT '放款方式',
  `renewal_count` int(11) DEFAULT NULL COMMENT '续期次数',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `is_archive` varchar(32) DEFAULT '0' COMMENT '是否归档',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_certification` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `certi_key` varchar(32) DEFAULT NULL COMMENT '键',
  `flag` varchar(32) DEFAULT NULL COMMENT '标识',
  `result` longtext COMMENT '认证结果',
  `cer_datetime` datetime DEFAULT NULL COMMENT '认证时间',
  `valid_datetime` datetime DEFAULT NULL COMMENT '有效时间',
  `ref` longtext DEFAULT NULL COMMENT '关联申请单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8;

CREATE TABLE `t_coupon` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `get_condition` int(11) DEFAULT NULL COMMENT '获得条件',
  `amount` bigint(32) DEFAULT NULL COMMENT '金额',
  `valid_days` int(11) DEFAULT NULL COMMENT '有效天数',
  `start_amount` bigint(32) DEFAULT NULL COMMENT '起用金额',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_product` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `slogan` varchar(255) DEFAULT NULL COMMENT '广告语',
  `level` varchar(32) DEFAULT NULL COMMENT '等级',
  `amount` bigint(32) DEFAULT NULL COMMENT '借款金额',
  `duration` int(11) DEFAULT NULL COMMENT '借款时长',
  `yq_rate1` decimal(18,8) DEFAULT NULL COMMENT '7天内逾期利率',
  `yq_rate2` decimal(18,8) DEFAULT NULL COMMENT '7天外逾期利率',
  `lx_rate` decimal(18,8) DEFAULT NULL COMMENT '利息',
  `xs_amount` bigint(32) DEFAULT NULL COMMENT '快速信审费',
  `gl_amount` bigint(32) DEFAULT NULL COMMENT '账户管理费',
  `fw_amount` bigint(32) DEFAULT NULL COMMENT '服务费',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `ui_location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `ui_order` int(11) DEFAULT NULL COMMENT 'UI顺序',
  `ui_color` varchar(32) DEFAULT NULL COMMENT 'UI颜色',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(32) DEFAULT NULL COMMENT 'key值',
  `cvalue` text COMMENT '值',
  `updater` varchar(32) NOT NULL COMMENT '更新人',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`) COMMENT '系统参数'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `type` char(1) NOT NULL COMMENT '类型（0=下拉框意义 1=下拉框选项）',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父key',
  `dkey` varchar(32) NOT NULL COMMENT 'key',
  `dvalue` varchar(64) NOT NULL COMMENT '值',
  `updater` varchar(32) NOT NULL COMMENT '更新人',
  `update_datetime` datetime NOT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`) COMMENT '数据字典'
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_coupon` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `get_datetime` datetime DEFAULT NULL COMMENT '获得时间',
  `type` varchar(32) DEFAULT NULL COMMENT '优惠券类型',
  `amount` bigint(32) DEFAULT NULL COMMENT '优惠券金额',
  `start_amount` bigint(32) DEFAULT NULL COMMENT '起用金额',
  `valid_days` int(11) DEFAULT NULL COMMENT '有效天数',
  `invalid_datetime` datetime DEFAULT NULL COMMENT '失效时间',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `borrow_code` varchar(32) DEFAULT NULL COMMENT '关联借款编号',
  `updater` varchar(32) DEFAULT NULL COMMENT '最后更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最后更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE `t_coupon_condition` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `coupon_type` varchar(32) DEFAULT NULL COMMENT '获取优惠券类型',
  `count` int(11) DEFAULT NULL COMMENT '已有次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `t_repay_apply` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `ref_no` VARCHAR(32) NULL COMMENT '关联订单号',
  `type` VARCHAR(32) NULL COMMENT '打款类型',
  `amount` BIGINT(32) NULL COMMENT '打款金额',
  `apply_user` VARCHAR(32) NULL COMMENT '申请人',
  `apply_note` VARCHAR(255) NULL COMMENT '申请备注',
  `apply_datetime` DATETIME NULL COMMENT '申请时间',
  `approver` VARCHAR(32) NULL COMMENT '审核人',
  `approve_note` VARCHAR(255) NULL COMMENT '审核说明',
  `approve_datetime` DATETIME NULL COMMENT '审核时间',
  `status` VARCHAR(32) NULL COMMENT '状态',
  PRIMARY KEY (`code`)  COMMENT '')
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_renewal` (
  `code` VARCHAR(32) NOT NULL COMMENT '编号',
  `apply_user` VARCHAR(32) NULL COMMENT '申请人',
  `borrow_code` VARCHAR(32) NULL COMMENT '借款编号',
  `step` INT(11) NULL COMMENT '续期步长',
  `cycle` INT(11) NULL COMMENT '续期周期',
  `start_date` DATETIME NULL COMMENT '开始时间',
  `end_date` DATETIME NULL COMMENT '结束时间',
  `yq_amount` BIGINT(32) NULL COMMENT '续期前逾期费',
  `xs_amount` BIGINT(32) NULL COMMENT '快速信审费',
  `gl_amount` BIGINT(32) NULL COMMENT '账户管理费',
  `fw_amount` BIGINT(32) NULL COMMENT '服务费',
  `lx_amount` BIGINT(32) NULL COMMENT '利息',
  `total_amount` BIGINT(32) NULL COMMENT '续费总费用',
  `pay_datetime` DATETIME NULL COMMENT '支付时间',
  `pay_type` VARCHAR(32)  NULL COMMENT '支付方式',
  `pay_code` VARCHAR(32)  NULL COMMENT '三方支付编号',
  `pay_group` VARCHAR(32)  NULL COMMENT '支付组号',
  `create_datetime` DATETIME NULL COMMENT '创建时间',
  `status` VARCHAR(32)  NULL COMMENT '状态',
  `cur_no` INT(11) NULL COMMENT '第几次续期',
  PRIMARY KEY (`code`)  COMMENT '')
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_overdue` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` VARCHAR(32) NULL COMMENT '用户编号',
  `borrow_code` VARCHAR(32) NULL COMMENT '关联借款订单号',
  `days` INT(11) NULL COMMENT '逾期天数',
  `amount` BIGINT(32) NULL COMMENT '逾期金额',
  `result` VARCHAR(32) NULL COMMENT '逾期后处理',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_contract` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `borrow_code` varchar(32) NOT NULL COMMENT '借款编号',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` text NOT NULL COMMENT '内容',
  `type` char(1) NOT NULL COMMENT '类型',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` varchar(2) NOT NULL COMMENT '状态',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电子合同表';
