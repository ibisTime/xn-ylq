
CREATE TABLE `t_product` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `slogan` varchar(255) DEFAULT NULL COMMENT '广告语',
  `level` varchar(32) DEFAULT NULL COMMENT '等级',
  `amount` bigint(32) DEFAULT NULL COMMENT '借款金额',
  `duration` int(11) DEFAULT NULL COMMENT '借款时长',
  `rate1` decimal(18,8) DEFAULT NULL COMMENT '7天内逾期利率',
  `rate2` decimal(18,8) DEFAULT NULL COMMENT '7天外逾期利率',
  `lx_amount` bigint(32) DEFAULT NULL COMMENT '利息',
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

CREATE TABLE `t_apply` (
  `code` varchar(32) NOT NULL COMMENT '编号',
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
  `ref` varchar(45) DEFAULT NULL COMMENT '关联申请单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;


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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

