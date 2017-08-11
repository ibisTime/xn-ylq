
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
