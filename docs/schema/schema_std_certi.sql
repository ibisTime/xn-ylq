CREATE TABLE `tstd_cpassword` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类别',
  `account` varchar(64) DEFAULT NULL COMMENT 'key',
  `password` text COMMENT 'value',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置表';

CREATE TABLE `tstd_gateway_idauth_log` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键 PK',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `id_kind` char(4) NOT NULL COMMENT '证件类型 1:身份证 ',
  `id_no` varchar(32) NOT NULL COMMENT '证件号码',
  `real_name` varchar(16) NOT NULL COMMENT '真实姓名',
  `card_no` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '绑定手机号',
  `zhima_biz_no` varchar(32) DEFAULT NULL COMMENT '芝麻认证唯一标识',
  `zhima_url` text COMMENT '芝麻认证url',
  `error_code` varchar(16) DEFAULT NULL COMMENT '三方验证结果',
  `error_msg` varchar(255) DEFAULT NULL COMMENT '三方验证信息',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31941 DEFAULT CHARSET=utf8 COMMENT='三方认证日志表';

CREATE TABLE `tstd_idauth` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `id_kind` char(4) NOT NULL COMMENT '证件类型 1:身份证 ',
  `id_no` varchar(32) NOT NULL COMMENT '证件号码',
  `real_name` varchar(16) NOT NULL COMMENT '真实姓名',
  `card_no` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '绑定手机号',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='身份认证结果表';

CREATE TABLE `tstd_user_picture` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键 PK',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `id_kind` char(4) NOT NULL COMMENT '证件类型 1:身份证 ',
  `id_no` varchar(32) NOT NULL COMMENT '证件号码',
  `real_name` varchar(16) NOT NULL COMMENT '真实姓名',
  `id_pic1` varchar(255) NOT NULL COMMENT '正面照',
  `id_pic2` varchar(255) NOT NULL COMMENT '反面照',
  `id_user_pic` varchar(255) NOT NULL COMMENT '手持照',
  `create_datetime` datetime NOT NULL COMMENT '创建时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `verify_user` varchar(32) DEFAULT NULL COMMENT '认证人',
  `verify_datetime` datetime DEFAULT NULL COMMENT '认证时间',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人工认证日志表';

CREATE TABLE `tstd_zm_antifraud_score` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `type` varchar(32) DEFAULT NULL COMMENT '查询类型 0-欺诈评分 1-欺诈验证 2-欺诈关注清单',
  `biz_no` varchar(64) DEFAULT NULL COMMENT '芝麻认证标识',
  `id_no` varchar(32) DEFAULT NULL COMMENT '身份证号',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `bank_card` varchar(64) DEFAULT NULL COMMENT '银行卡号',
  `address` varchar(255) DEFAULT NULL COMMENT '地址信息',
  `ip` varchar(32) DEFAULT NULL COMMENT 'ip地址',
  `mac` varchar(128) DEFAULT NULL COMMENT '物理地址',
  `wifimac` varchar(128) DEFAULT NULL COMMENT 'wifi的物理地址',
  `imei` varchar(32) DEFAULT NULL COMMENT '国际移动设备标志',
  `score` bigint(32) DEFAULT NULL COMMENT '欺诈分',
  `verify_code` text COMMENT '欺诈信息验证，输出验证码verifyCode列表',
  `risk_code` text COMMENT '欺诈关注清单的RiskCode列表',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8;

CREATE TABLE `tstd_zm_credit_score` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `id_no` varchar(64) DEFAULT NULL COMMENT '身份证号',
  `open_id` varchar(32) DEFAULT NULL COMMENT '芝麻开放平台唯一标识',
  `biz_no` varchar(32) DEFAULT NULL COMMENT '芝麻信用对于每一次请求返回的业务号',
  `score` varchar(45) DEFAULT NULL COMMENT '芝麻信用分',
  `is_matched` varchar(4) DEFAULT NULL COMMENT '是否在行业关注名单',
  `details` text COMMENT ' 行业关注名单信息列表',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
