/*
-- Query: SELECT * FROM dev_xn_ylq.tstd_account
LIMIT 0, 5000

-- Date: 2018-12-01 22:39
*/
INSERT INTO `tstd_account` (`account_number`,`user_id`,`currency`,`type`,`status`,`amount`,`total_amount`,`frozen_amount`,`md5`,`in_amount`,`out_amount`,`create_datetime`,`last_order`,`company_code`) VALUES ('A2018112119133811539698','JDS2018112119133807240821','CNY','B','0',0,0,0,'1e72d8e8b2005419537f5f6b733e7180',0,0,now(),'',NULL);

/*
-- Query: SELECT * FROM dev_xn_ylq.tjd_business_man
LIMIT 0, 5000

-- Date: 2018-12-01 22:36
*/
INSERT INTO `tjd_business_man` (`user_id`,`role_code`,`company_code`,`real_name`,`photo`,`mobile`,`login_name`,`login_pwd`,`login_pwd_strength`,`create_datetime`,`status`,`is_jt`,`is_fk`,`is_dl`,`is_admin`,`updater`,`update_datetime`,`remark`) VALUES ('JDS2018112119133807240821','JS201811081749297484833','GSModelCode','模版公司','','13858092437','test','96e79218965eb72c92a549dd5a330112','1',now(),'0','1','1','1','1','admin',now(),NULL);

/*
-- Query: SELECT * FROM dev_xn_ylq.tsys_channel_bank
LIMIT 0, 5000

-- Date: 2018-12-01 22:41
*/
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (1,'ICBC','中国工商银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (2,'ABC','中国农业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (3,'CCB','中国建设银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (4,'BOC','中国银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (5,'BCOM','中国交通银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (6,'CIB','兴业银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (7,'CITIC','中信银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (8,'CEB','中国光大银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (9,'PAB','平安银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (10,'PSBC','中国邮政储蓄银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (11,'SHB','上海银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (12,'SPDB','浦东发展银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (13,'CMBC','民生银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (14,'CMB','招商银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (15,'GDB','广发银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (16,'HXB','华夏银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (17,'HZB','杭州银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (18,'BOB','北京银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (19,'NBCB','宁波银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (20,'JSB','江苏银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tsys_channel_bank` (`id`,`bank_code`,`bank_name`,`channel_type`,`status`,`channel_bank`,`max_order`,`order_amount`,`day_amount`,`month_amount`,`remark`) VALUES (21,'ZSB','浙商银行','40','1',NULL,NULL,NULL,NULL,NULL,NULL);

/*
-- Query: SELECT * FROM dev_xn_ylq.tsys_company
LIMIT 0, 5000

-- Date: 2018-12-01 22:41
*/
INSERT INTO `tsys_company` (`code`,`user_id`,`name`,`logo`,`charger`,`charge_mobile`,`province`,`city`,`area`,`address`,`description`,`bussiness_license`,`organization_code`,`certificate_template`,`contract_template`,`create_datetime`,`updater`,`update_datetime`,`remark`) VALUES ('GSModelCode','JDS2018112119133807240821','橙小贷','Fvn5aof2QJ2U8RRLl7LN9E2iRDcq',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,now(),NULL,NULL,NULL);


/*
-- Query: SELECT type,ckey,cvalue,updater,update_datetime,remark,company_code FROM dev_xn_ylq.tsys_config where company_code="GSModelCode"
LIMIT 0, 5000

-- Date: 2018-12-01 22:33
*/
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('richText','aboutUs','待配置','admin',now(),'关于我们','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('richText','regProtocol','待配置','admin',now(),'借款服务与隐私协议','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('richText','couponExplain','待配置','admin',now(),'优惠券说明','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('richText','borrowProtocol','借款协议','admin',now(),'借款协议','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('richText','addressBookProtocol','通讯录授权协议','admin',now(),'通讯录授权协议','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('richText','helpCenter','待配置','admin',now(),'帮助中心','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('richText','customerService','待配置','admin',now(),'联系客服','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('richText','repayOfflineAccount','待配置','admin',now(),'线下打款账号','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('text','couponRule','待配置','admin',now(),'优惠券规则','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('text','activityRule','待配置','admin',now(),'邀请好友规则','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('text','time','待配置','admin',now(),'服务时间','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('text','telephone','待配置','test',now(),'服务电话','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('text','creditScore','待配置','test',now(),'信用分说明','GSModelCode');

INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('config','domainUrl','http://m.sl.hichengdai.com','admin',now(),'推荐注册链接','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('config','androidShowFlag','1','admin',now(),'安卓展示标识','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('config','iosShowFlag','1','admin',now(),'苹果展示标识','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('android-c','version',NULL,'admin',now(),'最新版本号','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('android-c','forceUpdate',NULL,'admin',now(),'是否强制更新','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('android-c','note',NULL,'admin',now(),'更新说明','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('android-c','downloadUrl',NULL,'admin',now(),'Android下载地址','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('ios-c','version',NULL,'admin',now(),'最新版本号','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('ios-c','forceUpdate',NULL,'admin',now(),'是否强制更新','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('ios-c','note',NULL,'admin',now(),'更新说明','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('ios-c','downloadUrl',NULL,'admin',now(),'iOS下载地址','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('ios-c','copyright',NULL,'admin',now(),'版权所属','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','identifyValidDays','180','admin',now(),'身份认证有效天数','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','personalValidDays','180','admin',now(),'个人信息认证有效天数','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','alipayValidDays','180','admin',now(),'支付宝认证有效天数','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','carrierValidDays','30','admin',now(),'运营商认证有效天数','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','addressBookValidDays','7','admin',now(),'通讯录认证有效天数','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','tongdunPreLoanValidDays','10','admin',now(),'同盾贷前审核报告有效天数','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','amountValidDays','15','admin',now(),'额度有效天数','GSModelCode');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','yqlxFdRate','0.5','admin',now(),'逾期利息封顶（本金乘以该数值）','GSModelCode');
/*
-- Query: SELECT * FROM dev_xn_ylq.tjd_coupon_rule
LIMIT 0, 5000

-- Date: 2018-12-01 22:37
*/
INSERT INTO `tjd_coupon_rule` (`code`,`type`,`get_condition`,`amount`,`valid_days`,`start_amount`,`status`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('GSModelCode001','0',1,50000,30,1000000,'1','admin',now(),'推荐成功送优惠券','GSModelCode');
INSERT INTO `tjd_coupon_rule` (`code`,`type`,`get_condition`,`amount`,`valid_days`,`start_amount`,`status`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('GSModelCode002','1',2,80000,30,1000000,'1','admin',now(),'借还成功送优惠券','GSModelCode');
INSERT INTO `tjd_coupon_rule` (`code`,`type`,`get_condition`,`amount`,`valid_days`,`start_amount`,`status`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('GSModelCode003','2',0,80000,30,1000000,'1','admin',now(),'直接赠送','GSModelCode');

/*
-- Query: SELECT type,parent_key,dkey,dvalue,updater,update_datetime,remark,company_code FROM dev_xn_ylq.tsys_dict where company_code="GSModelCode"
LIMIT 0, 5000

-- Date: 2018-12-01 22:34
*/

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'withdraw_status','取现订单状态','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','withdraw_status','1','待审批','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','withdraw_status','2','审批不通过','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','withdraw_status','3','审批通过待支付','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','withdraw_status','4','支付失败','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','withdraw_status','5','支付成功','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'user_status','用户状态','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','user_status','0','正常','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','user_status','1','程序锁定','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','user_status','2','人工锁定','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'mobile_modify_status','手机修改申请状态','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','mobile_modify_status','0','待审核','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','mobile_modify_status','1','审核通过','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','mobile_modify_status','2','审核不通过','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'blacklist_status','黑名单状态','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','blacklist_status','0','已移除','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','blacklist_status','1','生效中','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'id_kind','证件类型','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','id_kind','1','身份证','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'role_level','角色等级','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','role_level','1','运维','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','role_level','2','运营','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','role_level','3','客户','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'res_type','资源类型','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','res_type','1','菜单','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','res_type','2','按钮','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'lock_direction','锁定方向','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','lock_direction','1','锁定','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','lock_direction','2','解锁','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'news_status','信息状态','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','news_status','0','待发送','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','news_status','1','已发送','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','news_status','2','发送失败','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'user_kind','针对人群','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','user_kind','1','C端用户','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'notice_status','公告状态','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','notice_status','0','未发布','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','notice_status','1','已发布','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','notice_status','2','已下架','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','product_status','产品状态','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_status','0','关闭','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_status','1','开启','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','product_level','产品等级','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_level','1','LV.1','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_level','2','LV.2','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_level','3','LV.3','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_level','4','LV.4','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','product_color','产品UI颜色','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_color','#0cb8ae','LV.1-浅蓝','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_color','#fba72e','LV.2-黄色','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_color','#f16254','LV.3-红色','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_color','#28a6e6','LV.4-蓝色','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','borrow_status','借款状态','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','borrow_status','0','待审核','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','borrow_status','1','待放款','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','borrow_status','2','审核不通过','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','borrow_status','3','已放款','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','borrow_status','4','已还款','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','borrow_status','5','已逾期','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','borrow_status','6','坏账','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','borrow_status','7','打款失败','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','coupon_type','优惠券类型','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','coupon_type','0','获客优惠券','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','coupon_type','1','借还优惠券','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','coupon_status','优惠券状态','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','coupon_status','0','关闭','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','coupon_status','1','开启','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','user_coupon_status','优惠券类型','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','user_coupon_status','0','可使用','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','user_coupon_status','1','已使用','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','user_coupon_status','2','已过期','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','user_coupon_status','3','已回收','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','product_location','产品位置','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','product_location','0','普通列表','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','education','学历','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','education','0','小学','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','education','1','初中','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','education','2','高中/职高/技校','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','education','3','大专','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','education','4','本科','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','education','5','硕士','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','education','6','博士','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','marriage','婚姻','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','marriage','0','未婚','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','marriage','1','已婚','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','marriage','2','离异','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','marriage','3','丧偶','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','live_time','居住时长','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','live_time','0','三个月','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','live_time','1','六个月','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','live_time','2','一年','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','live_time','3','两年','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','live_time','4','两年以上','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','occupation','职业','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','0','程序员','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','1','设计师','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','2','教师','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','3','服务员','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','4','司机','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','5','厨师','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','6','理发师','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','7','教练','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','8','文员','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','9','销售经理','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','10','客服专员','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','11','采购员','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','12','营业员','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','13','网店店长','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','14','维修工','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','15','快递员','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','16','律师','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','17','翻译','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','18','编辑','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','19','会计','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','20','医生','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','21','工程师','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','22','公务员','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','occupation','23','其他','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','income','月收入','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','income','0','小于1000元','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','income','1','1000-2000元','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','income','2','2000-4000元','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','income','3','4000-6000元','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','income','4','6000-10000元','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','income','5','10000元以上','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','family_relation','亲属关系','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','family_relation','0','父母','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','family_relation','1','配偶','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','family_relation','2','兄弟姐妹','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','society_relation','社会关系','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','society_relation','0','同学','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','society_relation','1','同事','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','society_relation','2','朋友','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','pay_type','付款类型','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','pay_type','4','线下支付','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','loan_type','放款方式','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','loan_type','2','人工打款','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','repay_apply_status','还款申请状态','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','repay_apply_status','0','待审核','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','repay_apply_status','1','审核通过','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','repay_apply_status','2','审核不通过','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','repay_apply_type','还款类型','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','repay_apply_type','0','正常还款','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','repay_apply_type','1','分期还款','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'noticer_type','通知人类型','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','noticer_type','0','借款审批通知人','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','noticer_type','1','放款通知人','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','noticer_type','2','信用分审批通知人','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','coupon_type','2','直送优惠券','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'channel_type','渠道类型','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','channel_type','0','内部账','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','channel_type','90','线下','admin',now(),'','GSModelCode');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'biz_type','业务类型','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','biz_type','CHARGE','充值','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','biz_type','API_OUT','风控认证接口支出','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','biz_type','WITHDRAW_FROZEN','取现冻结','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','biz_type','WITHDRAW_UNFROZEN','取现解冻','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','biz_type','WITHDRAW','取现','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','biz_type','WITHDRAW_FEE','取现手续费','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'jour_status','流水状态','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','jour_status','1','待对账','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','jour_status','3','已对账且账已平','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','jour_status','4','帐不平待调账审批','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','jour_status','5','已对账且账不平','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','jour_status','6','无需对账','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0',NULL,'charge_status','充值订单状态','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','charge_status','1','待支付','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','charge_status','2','支付失败','admin',now(),'','GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','charge_status','3','支付成功','admin',now(),'','GSModelCode');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('0','','way_status','渠道状态','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','way_status','0','正常','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('1','way_status','1','注销','admin',now(),NULL,'GSModelCode');

/*
-- Query: SELECT * FROM dev_xn_ylq.tsys_menu where company_code="GSModelCode"
LIMIT 0, 5000

-- Date: 2018-12-17 21:17
*/
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201707261741263791893','YLQSM201600000000000000','借款管理','1','#',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201707261747250339518','SM2018120604520596132802','信用分','1','#',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201707261747450824730','SM201707261741263791893','借款审批','1','#',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708050938371312906','SM201708232117575498751','详情','2','/detail',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071146484282769','SM201708071354294834594','修改','2','/edit',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071354294834594','SM201708091124302986006','文本管理','1','/general/textParam.htm',1,'test','2018-12-13 21:17:15','','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071357128989378','SM201708071501160616280','认证信息报表','2','/report',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071412042227977','SM2018120605573219335713','归档订单','1','/borrowManage/backFiled.htm',9,'test','2019-02-05 17:36:00','','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071412251077022','SM201708071412042227977','详情','2','/detail',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071415131876278','SM201708071412042227977','导出','2','/export',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071450049267108','SM201708121352107968730','放款成功','2','/check',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071450464839683','SM201708121352107968730','导出','2','/export',4,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170807145206101437','SM201707261741263791893','优惠券管理','1','#',7,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071452439275974','SM201707261741263791893','会员管理','1','#',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071501160616280','SM201708071452439275974','会员查询','1','/members/members.htm',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071507011747619','SM201708232117575498751','审核','2','/check',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071507268774905','SM201707261747250339518','认证中订单','1','/oansBefore/audit.htm',1,'test','2018-12-06 10:11:09','','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071508058991967','SM201709061600347699783','产品管理','1','/borrowManage/product.htm',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071508282156023','SM201707261747450824730','打款审批','1','/borrowManage/moneyCheck.htm',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071508534869053','SM2018120605573219335713','还款中订单（还款提醒）','1','/borrowManage/moneyBack.htm',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071512012672115','SM2018120605573219335713','确认坏账','1','/afetrLoan/badDebat.htm',8,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071512256685382','SM20170807145206101437','优惠券管理','1','/coupons/manage.htm',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708071512479798921','SM20170807145206101437','优惠券发放','1','/coupons/provide.htm',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708091124302986006','YLQSM201600001000000001','系统参数管理','1','#',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708111748512971755','SM201708071508058991967','新增','2','/add',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170811174916362472','SM201708071508058991967','修改','2','/edit',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708111749554168986','SM201708071508058991967','开启','2','/up2',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708111750176783455','SM201708071508058991967','关闭','2','/down',4,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708111750409784736','SM201708071508058991967','详情','2','/detail',5,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708121352107968730','SM201707261747450824730','待放款订单','1','/borrowManage/moneyWait.htm',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708122156387433834','SM201708231950576596223','批复','2','/check',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708122157282325744','SM201708121352107968730','详情','2','/detail',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708161114137125273','SM201708071512256685382','修改','2','/edit',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708161114401741957','SM201708071512256685382','详情','2','/detail',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170816111540982883','SM201708071512479798921','人工发放','2','/add',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708161116139277868','SM201708071512479798921','回收','2','/edit',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708161116396921918','SM201708071512479798921','详情','2','/detail',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708161343379834734','SM201708071512256685382','开启/关闭','2','/opean',0,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708171117018936918','SM201708071508282156023','审核','2','/check',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170817111734833646','SM201708071508282156023','详情','2','/detail',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708171435014109013','SM201708071508282156023','导出','2','/export',4,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170817143532126439','SM201708071508534869053','导出','2','/export',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708171436208007438','SM201708071512012672115','导出','2','/export',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708171436540626776','SM201708071508534869053','详情','2','/detail',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708171437432547268','SM201708071512012672115','详情','2','/detail',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170817154845179227','SM201708071501160616280','借款记录','2','/borrow',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708171550048941018','SM201708071501160616280','优惠券情况','2','/discount',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708171702274727044','SM20170906163351853866','确认坏账','2','/confirm',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170817190200294878','SM2018120605573219335713','已还款订单','1','/borrowManage/backAlready.htm',4,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170817190225126189','SM20170817190200294878','详情','2','/detail',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708171902547796589','SM20170817190200294878','导出','2','/export',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708221731395824621','SM20170817190200294878','归档','2','/filed',0,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170822180050178936','SM201708071501160616280','注销/激活','2','/active',4,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708231648307619561','SM201708071452439275974','黑名单查询','1','/members/blackList.htm',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708231649104958224','SM201708231648307619561','移出黑名单','2','/remove',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708231950576596223','SM201707261747250339518','待批复订单','1','/oansBefore/query.htm',2,'test','2018-12-06 10:11:28','','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170823195259044857','SM201708231950576596223','详情','2','/detail',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708232117575498751','SM2018120605573219335713','线下还款审批','1','/borrowManage/offlinePayment.htm',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708241453322228980','SM201707261747450824730','审核不通过订单','1','/borrowManage/moneyCancel.htm',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708241454242452171','SM201708241453322228980','详情','2','/detail',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201708241454472641153','SM201708241453322228980','导出','2','/export',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709051456374016074','SM20170906163351853866','认证信息报告','2','/report',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709061516339571200','SM201708121352107968730','放款失败','2','/failed',12,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709061600347699783','SM201707261741263791893','产品管理','1','#',6,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709061610053357582','SM201707261747450824730','放款失败订单','1','/borrowManage/moneyfailed.htm',4,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170906163351853866','SM2018120605573219335713','逾期催收（逾期提醒）','1','/afetrLoan/outTimeBacking.htm',5,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709061643437993325','SM201709061610053357582','详情','2','/detail',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709061644123706857','SM201709061610053357582','导出','2','/export',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709061645007907662','SM20170906163351853866','详情','2','/detail',5,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709061645248609679','SM20170906163351853866','导出','2','/export',6,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709071533572994472','SM201708071507268774905','详情','2','/detail',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709071534339413916','SM201708071507268774905','导出','2','/export',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170907153458964880','SM201708071501160616280','导出','2','/export',5,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709071659128803343','SM201707261747250339518','已批订单','1','/oansBefore/alreadyQuery.htm',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709071700146864602','SM201709071659128803343','详情','2','/detail',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709072048471569273','SM20170906163351853866','催收','2','/press',0,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709081146091358854','SM201708231950576596223','导出','2','/export',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709081146366074462','SM201709071659128803343','导出','2','/export',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201709091105488557747','SM201708232117575498751','导出','2','/export',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM20170912190345302315','SM201708071512012672115','添加备注','2','/addRemark',0,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201801021903114864244','SM201709071659128803343','认证信息报告','2','/report',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201801021909044884586','SM201708071507268774905','认证信息报告','2','/report',4,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018010219135355513','SM201708231950576596223','认证信息报告','2','/report',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201801021915464867297','SM201708071508282156023','认证信息报告','2','/report',4,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201801021916214861423','SM201708241453322228980','认证信息报告','2','/report',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201801021917064882086','SM201708121352107968730','认证信息报告','2','/report',8,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201801021917334857105','SM201709061610053357582','认证信息报告','2','/report',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201801021918164858919','SM201708071508534869053','认证信息报告','2','/report',5,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201801021918434859397','SM20170817190200294878','认证信息报告','2','/report',4,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201801021926045039345','SM201708071512012672115','认证信息报告','2','/report',4,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018112217325547237538','SM201708071501160616280','信用分设置','2','/setCreditScore',4,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018112616431630810736','SM201708091124302986006','Android版本管理','1','/general/andPublish.htm',3,'test',now(),'系统参数管理','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018112616435328522069','SM201708091124302986006','IOS版本管理','1','/general/iosPublish.htm',4,'test',now(),'系统参数管理','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018112617542033922080','YLQSM201600001000000003','删除','2','/delete',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018113011153452340044','SM201709061600347699783','分期规则','1','/borrowManage/productByStages.htm',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018113011155691915432','SM2018113011153452340044','新增','2','/add',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201811301116215068916','SM2018113011153452340044','修改','2','/edit',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018113011163833636406','SM2018113011153452340044','删除','2','/delete',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018113011165860585983','SM2018113011153452340044','详情','2','/detail',4,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018113014493533939922','SM201708071508534869053','还款提醒','2','/repaymentReminder',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018113014504837339979','SM201708071508534869053','正常分期','2','/normalStaging',4,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018113022054238138702','SM2018120610123534452114','重新批复','2','/check',0,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018113022060698273196','SM2018120610123534452114','详情','2','/detail',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018113022062681658347','SM2018120610123534452114','导出','2','/export',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018113022065164452947','SM2018120610123534452114','认证信息报告','2','/report',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117265880380149','SM2018121319024036412067','渠道引流','1','#',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117274264430580','SM2018120117265880380149','渠道管理','1','/channel/channel.htm',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117281901912815','SM2018120117274264430580','新增','2','/add',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117284232829895','SM2018120117274264430580','修改','2','/edit',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117285830034202','SM2018120117274264430580','注销/激活','2','/delete',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117291031594163','SM2018120117274264430580','导出','2','/export',9,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117304503023288','SM2018120117265880380149','渠道用户','1','/channel/channelUser.htm',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117314345921703','SM2018120117304503023288','详情','2','/detail',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117315441191410','SM2018120117304503023288','导出','2','/export',9,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117321274827716','SM2018120604520596132802','我的账户','1','#',4,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117325922077446','SM2018120117321274827716','账户查询','1','/myAccount/accountQuery.htm',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117340144467013','SM2018120117321274827716','账户流水','1','/myAccount/accountFlow.htm',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117342145233185','SM2018120117340144467013','详情','2','/detail',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117382256745550','SM2018120117321274827716','充值管理','1','/myAccount/recharge.htm',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117385831961507','SM2018120117382256745550','预充','2','/recharge',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120117391984998817','SM2018120117382256745550','详情','2','/detail',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120604520596132802','YLQSM201600000000000000','风控管理','1','#',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120605573219335713','SM201707261741263791893','借款管理','1','#',32,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120606103156159250','SM201707261747450824730','审批通知人','1','/borrowManage/approveNotifier.htm',8,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120606122525577341','SM201707261747450824730','放款通知人','1','/borrowManage/loanNotifier.htm',9,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120609594310943778','SM2018120605573219335713','已放款借款中订单','1','/borrowManage/moneyBorrowing.htm',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610000903122973','SM2018120609594310943778','详情','2','/detail',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610002432353300','SM2018120609594310943778','导出','2','/export',9,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610123534452114','SM201707261747250339518','驳回订单','1','/oansBefore/reject.htm',4,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610145170464706','SM201707261747250339518','审批通知人','1','/oansBefore/approveNotifier.htm',5,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610160151811390','SM2018120610145170464706','新增','2','/add',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610161795721635','SM2018120610145170464706','修改','2','/edit',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201812061016317978846','SM2018120610145170464706','删除','2','/delete',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201812061016459499965','SM2018120606103156159250','新增','2','/add',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610170163288564','SM2018120606103156159250','修改','2','/edit',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610171576486702','SM2018120606103156159250','删除','2','/delete',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610184437137301','SM2018120606122525577341','新增','2','/add',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610185917038010','SM2018120606122525577341','修改','2','/edit',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018120610192167057387','SM2018120606122525577341','删除','2','/detele',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018121123325198343915','SM201708071452439275974','白名单查询','1','/members/whiteList.htm',4,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018121123332658819368','SM2018121123325198343915','移出白名单','2','/remove',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018121319024036412067','YLQSM201600000000000000','导流管理','1','#',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018121319405520459220','SM201708091124302986006','文章管理','1','/general/articleParam.htm',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018121319411224670523','SM2018121319405520459220','修改','2','/edit',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018121321512102055022','SM2018120117321274827716','取现管理','1','/myAccount/withdraw.htm',4,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018121321520388171119','SM2018121321512102055022','取现','2','/withdraw',1,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018121321535080844010','SM2018121321512102055022','详情','2','/detail',2,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM2018123000043345085473','SM20170906163351853866','逾期分期','2','/overdueStaging',3,'test',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM201600000000000000','','根目录','1','#',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM201600001000000001','YLQSM201600000000000000','系统管理','1','#',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM201600001000000002','YLQSM201600001000000001','运维管理','1','#',2,'admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM201600001000000003','YLQSM201600001000000002','菜单管理','1','/security/menu.htm',1,'admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM201600001000000004','YLQSM201600001000000003','新增','2','/add',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101716241339082','YLQSM201600001000000001','运营管理','1','#',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101716253866426','YLQSM2016101716241339082','角色管理','1','/security/role.htm',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101716261754674','YLQSM2016101716241339082','用户管理','1','/security/user.htm',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101716450533995','YLQSM2016101716253866426','分配菜单','2','/change',4,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101717551955993','YLQSM2016101716253866426','新增','2','/add',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101717560118734','YLQSM2016101716253866426','修改','2','/edit',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101717563661357','YLQSM2016101716253866426','删除','2','/delete',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101719082391126','YLQSM2016101716261754674','新增','2','/add',1,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101719094151894','YLQSM2016101716261754674','重置密码','2','/reset',2,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101719100760088','YLQSM2016101716261754674','注销','2','/rock',4,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016101719110981215','YLQSM2016101716261754674','设置角色','2','/assign',5,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2016120610552303416','YLQSM2016101716261754674','激活','2','/active',3,'admin',now(),'','GSModelCode');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('YLQSM2017032911200961325','YLQSM201600001000000003','修改','2','/edit',2,'admin',now(),'','GSModelCode');



/*
-- Query: SELECT role_code,menu_code,'admin' updater,now() as update_datetime ,remark ,company_code FROM dev_xn_ylq.tsys_menu_role where company_code="GSModelCode"
LIMIT 0, 5000

-- Date: 2018-12-17 22:44
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM201600000000000000','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM201600001000000001','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101716241339082','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101716253866426','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101717551955993','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101717560118734','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101717563661357','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101716450533995','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101716261754674','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101719082391126','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101719094151894','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016120610552303416','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101719100760088','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101719110981215','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM201600001000000002','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM201600001000000003','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM201600001000000004','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2017032911200961325','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101716295904680','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101719143965297','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708091124302986006','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709301726521467138','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709301727035194747','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101716290657836','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','YLQSM2016101719140087629','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071354294834594','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071146484282769','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071355289904791','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708182237428263004','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071356054479020','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170818223805070243','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071146218919795','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170807135329320738','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071405342791952','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071406010883237','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071407469348684','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707261741263791893','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707261747250339518','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709072056238373820','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709072056522372406','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071507268774905','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708122156387433834','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709071533572994472','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709071534339413916','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801021909044884586','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708231950576596223','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170823195259044857','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709081146091358854','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM2018010219135355513','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709071659128803343','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709071700146864602','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709081146366074462','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801021903114864244','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071452439275974','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071501160616280','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170817154845179227','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708171550048941018','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071357128989378','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170822180050178936','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170907153458964880','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM2018112217325547237538','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708231044579116110','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708231045516438211','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708231131548957945','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708231648307619561','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708231649104958224','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707261747450824730','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071508282156023','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708171117018936918','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170817111734833646','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708171435014109013','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801021915464867297','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708241453322228980','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708241454242452171','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708241454472641153','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801021916214861423','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708121352107968730','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071450049267108','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709200658553691986','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708122157282325744','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071450464839683','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801021917064882086','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709061516339571200','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709200705597256865','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709061610053357582','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709061643437993325','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709061644123706857','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801021917334857105','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071508534869053','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709072209524842524','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708171436540626776','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170817143532126439','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201712251443470935439','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801021918164858919','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708232117575498751','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071507011747619','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708050938371312906','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709091105488557747','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708232117356419291','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071445429053500','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170807114715900667','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709091105194285399','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170817190200294878','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708221731395824621','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170817190225126189','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708171902547796589','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801021918434859397','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071412042227977','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071412251077022','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071415131876278','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071451454165989','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071511351107502','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM2017081714371781556','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708171435571187682','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170906163351853866','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709072048471569273','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708171702274727044','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709051456374016074','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709072238341143858','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709061645007907662','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709061645248609679','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201712251444306946400','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709061634387129503','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170906164554638487','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709061647112423950','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709061647339684423','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801021925364883315','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071512012672115','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170912190345302315','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708171437432547268','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708171436208007438','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801021926045039345','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801022350454967336','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801022354044987642','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801030034014992202','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801030034324987794','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201709061600347699783','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071508058991967','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708111748512971755','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170811174916362472','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708111749554168986','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708111750176783455','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708111750409784736','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170807145206101437','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071512256685382','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708161343379834734','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708161114137125273','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708161114401741957','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708071512479798921','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170816111540982883','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708161116139277868','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201708161116396921918','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707261745530413766','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707261817561904796','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707281704301515120','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707281705081256008','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM20170726182557630657','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707271735196294785','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707271437582732708','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707271705134394086','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201707271704363792795','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801030420155017667','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801030422005009669','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801030423565042024','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112217361154717967','SM201801030520575038530','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM201600000000000000','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM201600001000000001','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101716241339082','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101716253866426','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101717551955993','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101717560118734','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101717563661357','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101716450533995','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101716261754674','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101719082391126','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101719094151894','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016120610552303416','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101719100760088','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101719110981215','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM201600001000000002','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM201600001000000003','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM201600001000000004','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2017032911200961325','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101716295904680','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101719143965297','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201708091124302986006','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201709301726521467138','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201709301727035194747','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101716290657836','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','YLQSM2016101719140087629','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201708071354294834594','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201708071146484282769','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201708071355289904791','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201708182237428263004','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201708071356054479020','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM20170818223805070243','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201708071146218919795','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM20170807135329320738','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201708071405342791952','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201708071406010883237','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2018112610253289865710','SM201708071407469348684','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM201600000000000000','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM201600001000000001','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101716241339082','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101716253866426','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101717551955993','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101717560118734','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101717563661357','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101716450533995','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101716261754674','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101719082391126','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101719094151894','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016120610552303416','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101719100760088','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2016101719110981215','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM201600001000000002','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM201600001000000003','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM201600001000000004','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','YLQSM2017032911200961325','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018112617542033922080','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708091124302986006','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071354294834594','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071146484282769','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018121319405520459220','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018121319411224670523','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018112616431630810736','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201812132116111113665','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018112616435328522069','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018121321162942125593','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201707261741263791893','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071452439275974','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071501160616280','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170817154845179227','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708171550048941018','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071357128989378','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170822180050178936','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018112217325547237538','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170907153458964880','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708231648307619561','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708231649104958224','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018121123325198343915','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018121123332658819368','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201707261747450824730','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071508282156023','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708171117018936918','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170817111734833646','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708171435014109013','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201801021915464867297','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708241453322228980','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708241454242452171','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708241454472641153','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201801021916214861423','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708121352107968730','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071450049267108','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708122157282325744','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071450464839683','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201801021917064882086','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709061516339571200','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709061610053357582','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709061643437993325','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709061644123706857','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201801021917334857105','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708232117575498751','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071507011747619','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708050938371312906','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709091105488557747','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120606103156159250','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201812061016459499965','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610170163288564','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610171576486702','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120606122525577341','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610184437137301','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610185917038010','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610192167057387','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709061600347699783','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071508058991967','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708111748512971755','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170811174916362472','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708111749554168986','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708111750176783455','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708111750409784736','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018113011153452340044','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018113011155691915432','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201811301116215068916','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018113011163833636406','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018113011165860585983','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170807145206101437','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071512256685382','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708161343379834734','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708161114137125273','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708161114401741957','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071512479798921','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170816111540982883','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708161116139277868','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708161116396921918','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120605573219335713','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120609594310943778','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610000903122973','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610002432353300','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071508534869053','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708171436540626776','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170817143532126439','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018113014493533939922','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018113014504837339979','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201801021918164858919','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170817190200294878','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708221731395824621','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170817190225126189','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708171902547796589','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201801021918434859397','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170906163351853866','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709072048471569273','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708171702274727044','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709051456374016074','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018123000043345085473','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709061645007907662','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709061645248609679','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071512012672115','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170912190345302315','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708171437432547268','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708171436208007438','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201801021926045039345','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071412042227977','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071412251077022','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071415131876278','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120604520596132802','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201707261747250339518','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708071507268774905','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709071533572994472','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709071534339413916','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201801021909044884586','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708231950576596223','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM20170823195259044857','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201708122156387433834','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709081146091358854','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018010219135355513','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709071659128803343','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709071700146864602','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201709081146366074462','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201801021903114864244','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610123534452114','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018113022054238138702','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018113022060698273196','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018113022062681658347','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018113022065164452947','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610145170464706','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610160151811390','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120610161795721635','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM201812061016317978846','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117321274827716','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117325922077446','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117340144467013','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117342145233185','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117382256745550','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117385831961507','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117391984998817','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018121321512102055022','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018121321520388171119','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018121321535080844010','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018121319024036412067','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117265880380149','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117274264430580','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117281901912815','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117284232829895','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117285830034202','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117291031594163','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117304503023288','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117314345921703','admin',now(),NULL,'GSModelCode');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','SM2018120117315441191410','admin',now(),NULL,'GSModelCode');



/*
-- Query: SELECT * FROM dev_xn_ylq.tsys_role where company_code="GSModelCode"
LIMIT 0, 5000

-- Date: 2018-12-01 22:31
*/
INSERT INTO `tsys_role` (`code`,`name`,`type`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201811081749297484833','admin','B','admin',now(),'不能删','GSModelCode');

INSERT INTO `tsys_user` (`user_id`,`role_code`,`real_name`,`photo`,`mobile`,`login_name`,`login_pwd`,`login_pwd_strength`,`create_datetime`,`status`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('UCOIN201700000000000001','COINSR201700000000000000','系统管理员',NULL,NULL,'admin','21218cca77804d2ba1922c33e0151105','1',now(),'0','admin',now(),'管理端系统方','GSModelCode');

/*
-- Query: SELECT * FROM test_xn_ylq.tjd_product
LIMIT 0, 5000

-- Date: 2018-12-03 11:48
*/
INSERT INTO `tjd_product` (`code`,`name`,`slogan`,`level`,`amount`,`duration`,`yq_rate1`,`yq_rate2`,`lx_rate`,`xs_amount`,`gl_amount`,`fw_amount`,`status`,`location`,`order_no`,`color`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CP2018DEFAULT1','默认产品1','极速放款',1,1000000,'7',0.05000000,0.05000000,0.03000000,35000,45000,55000,'0','0',0,'#0cb8ae','admin',now(),'','GSModelCode');


/*
-- Query: SELECT * FROM dev_xn_ylq.tjd_stage_rule
LIMIT 0, 5000

-- Date: 2018-12-17 15:33
*/
INSERT INTO `tjd_stage_rule` (`code`,`count`,`cycle`,`rate`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SR2019012700020830833566',2,3,0.02000000,1,NULL,NULL,NULL,'GSModelCode');
