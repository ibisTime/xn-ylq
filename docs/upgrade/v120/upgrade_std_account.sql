CREATE TABLE `tbf_pay_order` (
  `code` varchar(32) NOT NULL COMMENT '宝付订单号',
  `trans_orderid` varchar(64) DEFAULT NULL COMMENT '宝付订单号',
  `trans_batchid` varchar(64) DEFAULT NULL COMMENT '宝付批次号',
  `trans_no` varchar(32) DEFAULT NULL COMMENT '商户订单号',
  `trans_money` bigint(32) DEFAULT NULL COMMENT '转账金额',
  `to_acc_name` varchar(64) DEFAULT NULL COMMENT '收款人姓名',
  `to_acc_no` varchar(64) DEFAULT NULL COMMENT '收款人银行帐号',
  `to_acc_dept` varchar(128) DEFAULT NULL COMMENT '收款人开户行机构名',
  `trans_summary` varchar(255) DEFAULT NULL COMMENT '摘要',
  `trans_fee` bigint(32) DEFAULT NULL COMMENT '交易手续费',
  `state` varchar(32) DEFAULT NULL COMMENT '状态（0-转账中 1-转账成功 -1-转账失败 2-转账退款）',
  `trans_remark` varchar(255) DEFAULT NULL COMMENT '备注（错误信息）',
  `trans_starttime` datetime DEFAULT NULL COMMENT '交易申请时间',
  `trans_endtime` datetime DEFAULT NULL COMMENT '交易完成时间',
  `back_url` varchar(255) DEFAULT NULL COMMENT '回调地址',
  `company_code` varchar(32) DEFAULT NULL COMMENT '公司编号',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编号',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `tstd_company_channel` (`company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code`) VALUES ('CD-YLQ000014','九州宝','40','1','1192914','cer/baofoo_pay/ylq/product/baofu_private.pfx','cer/baofoo_pay/ylq/product/baofu_public.cer','36733','349494',NULL,'https://public.baofoo.com/baofoo-fopay/pay',NULL,NULL,NULL,NULL,'CD-YLQ000014');
INSERT INTO `tstd_company_channel` (`company_code`,`company_name`,`channel_type`,`status`,`channel_company`,`private_key1`,`private_key2`,`private_key3`,`private_key4`,`private_key5`,`page_url`,`error_url`,`back_url`,`fee`,`remark`,`system_code`) VALUES ('CD-YLQ000014','九州宝','41','1','1192914','cer/baofoo_pay/ylq/product/baofu_private.pfx','cer/baofoo_pay/ylq/product/baofu_public.cer','36733','349494',NULL,'https://public.baofoo.com/cutpayment/api/backTransRequest',NULL,NULL,NULL,NULL,'CD-YLQ000014');