DROP TABLE IF EXISTS `tstd_user`;
CREATE TABLE `tstd_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户编号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登陆名',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登陆密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登陆密码强度',
  `referee_type` varchar(32) DEFAULT NULL COMMENT '推荐人类型',
  `user_referee` varchar(32) DEFAULT NULL COMMENT '推荐人',
  `id_kind` char(1) DEFAULT NULL COMMENT '证件类型',
  `id_no` varchar(32) DEFAULT NULL COMMENT '证件号码',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL COMMENT '安全密码强度',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) DEFAULT NULL COMMENT '维度',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `create_ip` varchar(32) DEFAULT NULL COMMENT '注册ip',
  `create_client` varchar(45) DEFAULT NULL COMMENT '注册端',
  `updater` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_blacklist` varchar(4) DEFAULT NULL COMMENT '是否黑名单',
  `is_whitelist` varchar(4) DEFAULT NULL COMMENT '是否白名单',
  `is_coupon` varchar(4) DEFAULT NULL COMMENT '是否领优惠券',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='C端用户';

DROP TABLE IF EXISTS `tsys_user`;
CREATE TABLE `tsys_user` (
  `user_id` varchar(32) NOT NULL,
  `kind` varchar(4) DEFAULT NULL COMMENT '类型',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `real_name` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号',
  `login_name` varchar(64) DEFAULT NULL COMMENT '登录名',
  `login_pwd` varchar(32) DEFAULT NULL COMMENT '登录密码',
  `login_pwd_strength` char(1) DEFAULT NULL COMMENT '登录密码强度',
  `trade_pwd` varchar(32) DEFAULT NULL COMMENT '安全密码',
  `trade_pwd_strength` char(1) DEFAULT NULL COMMENT '安全密码强度',
  `create_datetime` datetime DEFAULT NULL COMMENT '注册时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（1待审核2合伙中3已解除合伙4已注销）',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` text COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

DROP TABLE IF EXISTS `tsys_channel_bank`;
CREATE TABLE `tsys_channel_bank` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '编号（自增长）',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行编号',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `channel_type` varchar(4) DEFAULT NULL COMMENT '渠道类型',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（启用/不启用）',
  `channel_bank` varchar(32) DEFAULT NULL COMMENT '渠道给银行的代号',
  `max_order` bigint(32) DEFAULT NULL COMMENT '笔数限制',
  `order_amount` bigint(32) DEFAULT NULL COMMENT '单笔限额',
  `day_amount` bigint(32) DEFAULT NULL COMMENT '每日限额',
  `month_amount` bigint(32) DEFAULT NULL COMMENT '每月限额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_cnavigate`;
CREATE TABLE `tsys_cnavigate` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `url` varchar(255) DEFAULT NULL COMMENT '访问Url',
  `pic` varchar(255) DEFAULT NULL COMMENT '图片',
  `status` varchar(4) DEFAULT NULL COMMENT '状态(1 显示 0 不显示)',
  `location` varchar(32) DEFAULT NULL COMMENT '位置',
  `order_no` int(11) DEFAULT NULL COMMENT '相对位置编号',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父编号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_company`;
CREATE TABLE `tsys_company` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `charger` varchar(255) DEFAULT NULL COMMENT '负责人',
  `charge_mobile` varchar(32) DEFAULT NULL COMMENT '负责人联系方式',
  `province` varchar(255) DEFAULT NULL COMMENT '省',
  `city` varchar(255) DEFAULT NULL COMMENT '市',
  `area` varchar(255) DEFAULT NULL COMMENT '区',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `bussiness_license` text COMMENT '营业执照',
  `organization_code` varchar(255) DEFAULT NULL COMMENT '组织机构代码',
  `certificate_template` text COMMENT '证书模板',
  `contract_template` text COMMENT '合同模板',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_config`;
CREATE TABLE `tsys_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `ckey` varchar(255) DEFAULT NULL COMMENT 'key',
  `cvalue` text COMMENT 'value',
  `updater` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_dict`;
CREATE TABLE `tsys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `parent_key` varchar(32) DEFAULT NULL COMMENT '父亲key',
  `dkey` varchar(32) DEFAULT NULL COMMENT 'key',
  `dvalue` varchar(255) DEFAULT NULL COMMENT 'value',
  `updater` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=324 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_menu`;
CREATE TABLE `tsys_menu` (
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `parent_code` varchar(32) DEFAULT NULL COMMENT '父亲key',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(2) DEFAULT NULL COMMENT '类型',
  `url` varchar(255) DEFAULT NULL COMMENT 'url',
  `order_no` int(11) DEFAULT NULL COMMENT '次序',
  `updater` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_menu_role`;
CREATE TABLE `tsys_menu_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色编号',
  `menu_code` varchar(32) DEFAULT NULL COMMENT '菜单编号',
  `updater` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5730 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tsys_role`;
CREATE TABLE `tsys_role` (
  `code` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `type` varchar(4) DEFAULT NULL,
  `updater` varchar(32) DEFAULT NULL COMMENT '最近修改人',
  `update_datetime` datetime DEFAULT NULL COMMENT '最近修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tstd_account`;
CREATE TABLE `tstd_account` (
  `account_number` varchar(32) NOT NULL COMMENT '账户编号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `currency` varchar(32) DEFAULT NULL COMMENT '币种',
  `type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `status` varchar(2) DEFAULT NULL COMMENT '状态（正常/程序冻结/人工冻结）',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '余额',
  `total_amount` decimal(64,0) DEFAULT NULL COMMENT '累计加的金额',
  `frozen_amount` decimal(64,0) DEFAULT NULL COMMENT '冻结金额',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5',
  `in_amount` decimal(64,0) DEFAULT '0' COMMENT '总充值金额（入金）',
  `out_amount` decimal(64,0) DEFAULT '0' COMMENT '总取现金额（出金）',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户';

DROP TABLE IF EXISTS `tstd_bankcard`;
CREATE TABLE `tstd_bankcard` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `bankcard_number` varchar(64) DEFAULT NULL COMMENT '银行卡编号',
  `bank_code` varchar(32) DEFAULT NULL COMMENT '银行行别',
  `bank_name` varchar(32) DEFAULT NULL COMMENT '银行名称',
  `subbranch` varchar(255) DEFAULT NULL COMMENT '开户支行',
  `bind_mobile` varchar(32) DEFAULT NULL COMMENT '银行卡绑定手机号',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `amount` bigint(32) DEFAULT NULL COMMENT '余额',
  `frozen_amount` bigint(32) DEFAULT NULL COMMENT '冻结金额',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `last_order` varchar(32) DEFAULT NULL COMMENT '最近一次变动对应的流水编号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `tstd_charge`;
CREATE TABLE `tstd_charge` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账户编号',
  `account_type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '充值金额',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '关联业务类型',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '关联业务备注',
  `biz_no` varchar(255) DEFAULT NULL COMMENT '关联订单号',
  `pay_card_info` varchar(255) DEFAULT NULL COMMENT '支付渠道账号信息',
  `pay_card_no` varchar(255) DEFAULT NULL COMMENT '支付渠道账号',
  `status` varchar(4) NOT NULL COMMENT '状态 1待支付 2支付失败 3支付成功',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_user_type` varchar(32) DEFAULT NULL COMMENT '申请人类型',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `pay_user` varchar(32) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付渠道说明',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道单号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值订单';

DROP TABLE IF EXISTS `tstd_jour`;
CREATE TABLE `tstd_jour` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(32) DEFAULT NULL COMMENT '流水类型（余额流水、冻结流水）',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账户编号',
  `account_type` varchar(4) DEFAULT NULL COMMENT '账户类型',
  `currency` varchar(8) DEFAULT NULL COMMENT '币种',
  `biz_type` varchar(32) DEFAULT NULL COMMENT '业务类型',
  `biz_note` varchar(255) DEFAULT NULL COMMENT '业务说明',
  `trans_amount` decimal(64,0) DEFAULT NULL COMMENT '变动金额',
  `pre_amount` decimal(64,0) DEFAULT NULL COMMENT '变动前金额',
  `post_amount` decimal(64,0) DEFAULT NULL COMMENT '变动后金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道类型',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道单号',
  `ref_no` varchar(255) DEFAULT NULL COMMENT '系统内部参考订单号',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_datetime` datetime DEFAULT NULL COMMENT '创建时间',
  `work_date` varchar(8) DEFAULT NULL COMMENT '拟对账时间',
  `check_user` varchar(32) DEFAULT NULL COMMENT '对账人',
  `check_note` varchar(255) DEFAULT NULL COMMENT '对账说明',
  `check_datetime` datetime DEFAULT NULL COMMENT '对账时间',
  `adjust_user` varchar(32) DEFAULT NULL COMMENT '调账人',
  `adjust_note` varchar(255) DEFAULT NULL COMMENT '调账说明',
  `adjust_datetime` datetime DEFAULT NULL COMMENT '调账时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户流水';

DROP TABLE IF EXISTS `tstd_withdraw`;
CREATE TABLE `tstd_withdraw` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `account_number` varchar(32) DEFAULT NULL COMMENT '账户编号',
  `account_type` varchar(4) DEFAULT NULL COMMENT '类别（B端账号，C端账号，平台账号）',
  `currency` varchar(32) DEFAULT NULL COMMENT '币种',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '取现金额',
  `fee` decimal(64,0) DEFAULT NULL COMMENT '手续费',
  `actual_amount` decimal(64,0) DEFAULT NULL COMMENT '实际到账金额',
  `channel_type` varchar(32) DEFAULT NULL COMMENT '支付渠道',
  `channel_bank` varchar(32) DEFAULT NULL COMMENT '渠道银行代号',
  `channel_order` varchar(255) DEFAULT NULL COMMENT '支付渠道编号',
  `pay_card_info` varchar(255) DEFAULT NULL COMMENT '支付渠道账号信息',
  `pay_card_no` varchar(255) DEFAULT NULL COMMENT '支付渠道账号',
  `status` varchar(4) NOT NULL COMMENT '状态',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_user_type` varchar(32) DEFAULT NULL COMMENT '申请人类型',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `approve_user` varchar(32) DEFAULT NULL COMMENT '审批人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审批说明',
  `approve_datetime` varchar(32) DEFAULT NULL COMMENT '审批时间',
  `pay_user` varchar(255) DEFAULT NULL COMMENT '支付回录人',
  `pay_note` varchar(255) DEFAULT NULL COMMENT '支付回录说明',
  `pay_fee` decimal(64,0) DEFAULT NULL COMMENT '支付渠道手续费（矿工费）',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付回录时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='取现订单';

DROP TABLE IF EXISTS `tsys_channel`;
CREATE TABLE `tsys_channel` (
  `code` varchar(32) NOT NULL,
  `name` varchar(64) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '链接',
  `point_count` bigint(20) DEFAULT NULL COMMENT '渠道点击数',
  `user_count` bigint(20) DEFAULT NULL COMMENT '用户人数',
  `start_datetime` datetime DEFAULT NULL COMMENT '开始时间',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='渠道';

DROP TABLE IF EXISTS `tsys_noticer`;
CREATE TABLE `tsys_noticer` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `mobile` varchar(64) DEFAULT NULL COMMENT '手机号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `strat_time` varchar(4) DEFAULT NULL COMMENT '开始时间点',
  `end_time` varchar(4) DEFAULT NULL COMMENT '结束时间点',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) NOT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='通知人';

DROP TABLE IF EXISTS `tjd_product`;
CREATE TABLE `tjd_product` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(64) DEFAULT NULL COMMENT '名字',
  `slogan` varchar(255) DEFAULT NULL COMMENT '广告语',
  `level` int(11) DEFAULT NULL COMMENT '产品等级',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '借贷金额',
  `duration` varchar(32) DEFAULT NULL COMMENT '借贷时长',
  `yq_rate1` decimal(18,8) DEFAULT NULL COMMENT '7天内逾期利率',
  `yq_rate2` decimal(18,8) DEFAULT NULL COMMENT '7天后逾期利率',
  `lx_rate` decimal(18,8) DEFAULT NULL COMMENT '利息',
  `xs_amount` decimal(64,0) DEFAULT NULL COMMENT '快速信审费',
  `gl_amount` decimal(64,0) DEFAULT NULL COMMENT '账户管理费',
  `fw_amount` decimal(64,0) DEFAULT NULL COMMENT '服务费',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0 关闭，1 开启）',
  `location` varchar(32) DEFAULT NULL COMMENT 'UI位置',
  `order_no` bigint(20) DEFAULT NULL COMMENT 'UI次序',
  `color` varchar(32) DEFAULT NULL COMMENT 'UI颜色',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品';

DROP TABLE IF EXISTS `tjd_borrow_order`;
CREATE TABLE `tjd_borrow_order` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `sign_datetime` datetime DEFAULT NULL COMMENT '签约时间',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '借贷金额',
  `level` varchar(4) DEFAULT NULL COMMENT '借款等级',
  `duration` varchar(32) DEFAULT NULL COMMENT '借贷时长',
  `fk_datetime` datetime DEFAULT NULL COMMENT '放款时间',
  `jx_datetime` datetime DEFAULT NULL COMMENT '计息时间',
  `hk_datetime` datetime DEFAULT NULL COMMENT '约定还款时间',
  `yqlx_amount` decimal(18,8) DEFAULT NULL COMMENT '逾期金额',
  `yq_days` int(11) DEFAULT NULL COMMENT '逾期天数',
  `total_amount` decimal(64,0) DEFAULT NULL COMMENT '总共应还金额',
  `real_hk_datetime` datetime DEFAULT NULL COMMENT '实际还款时间',
  `real_hk_amount` decimal(64,0) DEFAULT NULL COMMENT '实际还款金额',
  `lx_rate` decimal(18,8) DEFAULT NULL COMMENT '正常利率',
  `lx_amount` decimal(18,8) DEFAULT NULL COMMENT '应付利息金额',
  `xs_amount` decimal(64,0) DEFAULT NULL COMMENT '快速信审费',
  `gl_amount` decimal(64,0) DEFAULT NULL COMMENT '账户管理费',
  `fw_amount` decimal(64,0) DEFAULT NULL COMMENT '服务费',
  `yh_amount` decimal(18,8) DEFAULT NULL COMMENT '优惠金额',
  `rate1` decimal(18,8) DEFAULT NULL COMMENT '7天内逾期利率',
  `rate2` decimal(18,8) DEFAULT NULL COMMENT '7天后逾期利率',
  `is_archive` varchar(4) DEFAULT NULL COMMENT '是否归档',
  `is_coupon` varchar(4) DEFAULT NULL COMMENT '是否领优惠券',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_group` varchar(45) DEFAULT NULL COMMENT '支付组号',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '还款方式',
  `loan_type` varchar(4) DEFAULT NULL COMMENT '放款方式',
  `stage_rule_code` varchar(45) DEFAULT NULL COMMENT '分期规则',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0 待审核，1 审核不通过，2 待放款，3 付款失败，4 已放款，5 逾期，6 已还款，7 坏账）',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='借款订单';

DROP TABLE IF EXISTS `tjd_cert_apply`;
CREATE TABLE `tjd_cert_apply` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0 认证中，1 待审核，2 审核通过，3 审核不通过）',
  `credit_score` decimal(64,0) DEFAULT NULL COMMENT '信用分',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_datetime` datetime DEFAULT NULL COMMENT '审核时间',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `cur_node` varchar(4) DEFAULT NULL COMMENT '当前节点',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `report` varchar(255) DEFAULT NULL COMMENT '报告',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='认证申请';

DROP TABLE IF EXISTS `tjd_certification`;
CREATE TABLE `tjd_certification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `certi_key` varchar(32) DEFAULT NULL COMMENT '键',
  `flag` varchar(4) DEFAULT NULL COMMENT '标示',
  `result` longtext COMMENT '结果',
  `cer_datetime` datetime DEFAULT NULL COMMENT '认证时间',
  `valid_datetime` datetime DEFAULT NULL COMMENT '有效时间',
  `ref` varchar(32) DEFAULT NULL COMMENT '关联订单号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='认证结果';

DROP TABLE IF EXISTS `tjd_coupon_rule`;
CREATE TABLE `tjd_coupon_rule` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `get_condition` int(11) DEFAULT NULL COMMENT '获得条件（数量）',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '优惠金额',
  `valid_days` int(11) DEFAULT NULL COMMENT '有效天数',
  `start_amount` decimal(64,0) DEFAULT NULL COMMENT '启用金额',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0 关闭，1 开启）',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券规则';

DROP TABLE IF EXISTS `tjd_coupon`;
CREATE TABLE `tjd_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `get_datetime` datetime DEFAULT NULL COMMENT '获得时间',
  `type` varchar(4) DEFAULT NULL COMMENT '优惠券类型',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '优惠金额',
  `start_amount` decimal(64,0) DEFAULT NULL COMMENT '启用金额',
  `valid_days` int(11) DEFAULT NULL COMMENT '有效天数',
  `invalid_datetime` datetime DEFAULT NULL COMMENT '失效时间',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0 可使用，1 已使用，2 已过期，3 已回收）',
  `order_code` varchar(32) DEFAULT NULL COMMENT '关联借款订单',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券';

DROP TABLE IF EXISTS `tjd_repay_apply`;
CREATE TABLE `tjd_repay_apply` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `type` varchar(4) DEFAULT NULL COMMENT '类型',
  `order_code` varchar(32) DEFAULT NULL COMMENT '借款订单号',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '还款金额',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `apply_datetime` datetime DEFAULT NULL COMMENT '申请时间',
  `apply_note` varchar(255) DEFAULT NULL COMMENT '申请说明',
  `approver` varchar(32) DEFAULT NULL COMMENT '审核人',
  `approve_note` varchar(255) DEFAULT NULL COMMENT '审核说明',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0 待审批，1 审批通过，2 审批不通过）',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='线下还款申请';

DROP TABLE IF EXISTS `tjd_overdue`;
CREATE TABLE `tjd_overdue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户编号',
  `order_code` varchar(32) DEFAULT NULL COMMENT '借款订单号',
  `days` int(11) DEFAULT NULL COMMENT '逾期天数',
  `amount` decimal(64,0) DEFAULT NULL COMMENT '逾期金额',
  `result` varchar(255) DEFAULT NULL COMMENT '逾期后处理',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='逾期记录';

DROP TABLE IF EXISTS `tjd_staging`;
CREATE TABLE `tjd_staging` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `apply_user` varchar(32) DEFAULT NULL COMMENT '申请人',
  `order_code` varchar(32) DEFAULT NULL COMMENT '借款订单号',
  `pay_amount` decimal(64,0) DEFAULT NULL COMMENT '还款金额',
  `last_pay_date` datetime DEFAULT NULL COMMENT '最晚支付日期',
  `pay_datetime` datetime DEFAULT NULL COMMENT '支付时间',
  `pay_type` varchar(4) DEFAULT NULL COMMENT '支付类型',
  `pay_code` varchar(32) DEFAULT NULL COMMENT '支付编号',
  `pay_group` varchar(32) DEFAULT NULL COMMENT '支付组号',
  `status` varchar(4) DEFAULT NULL COMMENT '状态（0 待支付，1 已支付，2 逾期）',
  `count` int(11) DEFAULT NULL COMMENT '第几期',
  `batch` int(11) DEFAULT NULL COMMENT '批次号',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分期记录';

DROP TABLE IF EXISTS `tjd_stage_rule`;
CREATE TABLE `tjd_stage_rule` (
  `code` varchar(32) NOT NULL COMMENT '编号',
  `count` int(11) DEFAULT NULL COMMENT '分期期数',
  `cycle` int(11) DEFAULT NULL COMMENT '分期周期',
  `rate` decimal(18,8) DEFAULT NULL COMMENT '日利率',
  `order_no` bigint(20) DEFAULT NULL COMMENT '次序',
  `updater` varchar(32) DEFAULT NULL COMMENT '更新人',
  `update_datetime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分期规则';

DROP TABLE IF EXISTS `tjd_stage_rule`;