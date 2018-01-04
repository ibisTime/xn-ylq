delete from tsys_config where ckey='sendSmsCount';

CREATE TABLE `t_statistic` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `today` bigint(32) DEFAULT NULL COMMENT '日期',
  `reg_num` bigint(32) DEFAULT NULL COMMENT '注册人数',
  `certi_num` bigint(32) DEFAULT NULL COMMENT '认证人数',
  `zmxy_num` bigint(32) DEFAULT NULL COMMENT '芝麻认证人数',
  `carrier_num` bigint(32) DEFAULT NULL COMMENT '运营商认证人数',
  `jdt_num` bigint(32) DEFAULT NULL COMMENT '借贷通导流人数',
  `apply_num` bigint(32) DEFAULT NULL COMMENT '申请人数',
  `fk_num` bigint(32) DEFAULT NULL COMMENT '放款单量',
  `fk_amount` bigint(32) DEFAULT NULL COMMENT '放款金额',
  `order_amount` bigint(32) DEFAULT NULL COMMENT '订单金额',
  `yhk_num` bigint(32) DEFAULT NULL COMMENT '当日应还款单量',
  `yhk_amount` bigint(32) DEFAULT NULL COMMENT '当日应还款金额',
  `sjhk_num` bigint(32) DEFAULT NULL COMMENT '实际还款总单量',
  `bfhk_num` bigint(32) DEFAULT NULL COMMENT '宝付代扣还款单量',
  `bfhk_amount` bigint(32) DEFAULT NULL COMMENT '宝付代扣还款金额',
  `zfbhk_num` bigint(32) DEFAULT NULL COMMENT '支付宝还款单量',
  `zfbhk_amount` bigint(32) DEFAULT NULL COMMENT '支付宝还款金额',
  `hk_amount` bigint(32) DEFAULT NULL COMMENT '实际还款总金额',
  `xq_num` bigint(32) DEFAULT NULL COMMENT '续期总单量',
  `bfdk_num` bigint(32) DEFAULT NULL COMMENT '宝付代扣续期单量',
  `bfdk_amount` bigint(32) DEFAULT NULL COMMENT '宝付代扣续期金额',
  `zfbdk_num` bigint(32) DEFAULT NULL COMMENT '支付宝代扣续期单量',
  `zfbdk_amount` bigint(32) DEFAULT NULL COMMENT '支付宝代扣续期金额',
  `xq_total_amount` bigint(32) DEFAULT NULL COMMENT '续期总金额',
  `yqhk_num` bigint(32) DEFAULT NULL COMMENT '逾期还款单量',
  `yqhk_amount` bigint(32) DEFAULT NULL COMMENT '逾期还款金额',
  `bfyq_num` bigint(32) DEFAULT NULL COMMENT '宝付代扣逾期还款单量',
  `bfyq_total_amount` bigint(32) DEFAULT NULL COMMENT '宝付代扣逾期还款金额',
  `zfbyq_num` bigint(32) DEFAULT NULL COMMENT '支付宝逾期还款单量',
  `zfbyq_total_amount` bigint(32) DEFAULT NULL COMMENT '支付宝逾期还款金额',
  `ys_num` bigint(32) DEFAULT NULL COMMENT '当前应收单量',
  `ys_total_amount` bigint(32) DEFAULT NULL COMMENT '当前应收金额',
  `ysbj_total_amount` bigint(32) DEFAULT NULL COMMENT '当前应收本金',
  `ysfy_total_amount` bigint(32) DEFAULT NULL COMMENT '当前应收费用',
  `yq_count` bigint(32) DEFAULT NULL COMMENT '当前逾期单量',
  `yq_total_amount` bigint(32) DEFAULT NULL COMMENT '当前逾期金额',
  `yqbj_total_amount` bigint(32) DEFAULT NULL COMMENT '当前逾期本金',
  `yqfy_total_amount` bigint(32) DEFAULT NULL COMMENT '当前逾期费用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

update `tsys_dict` set `dvalue`='宝付代扣(自动)' where `parent_key`='pay_type' and `dkey`='5';
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','pay_type','6','宝付代扣(客户)','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','pay_type','7','宝付代扣(平台)','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

