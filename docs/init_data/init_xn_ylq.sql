
/*
-- Query: SELECT * FROM xn_ylq.t_product
-- Date: 2017-08-24 19:24
*/
INSERT INTO `t_product` (`code`,`name`,`slogan`,`level`,`amount`,`duration`,`yq_rate1`,`yq_rate2`,`lx_rate`,`xs_rate`,`gl_rate`,`fw_rate`,`status`,`ui_location`,`ui_order`,`ui_color`,`updater`,`update_datetime`,`remark`) VALUES ('CP2017082321163077150710','借贷产品A','无条件申请','1',1000000,7,0.00100000,0.00200000,0.00100000,0.0010000,0.00100000,0.00100000,'1','0',1,'#0cb8ae','admin',now(),NULL);
INSERT INTO `t_product` (`code`,`name`,`slogan`,`level`,`amount`,`duration`,`yq_rate1`,`yq_rate2`,`lx_rate`,`xs_rate`,`gl_rate`,`fw_rate`,`status`,`ui_location`,`ui_order`,`ui_color`,`updater`,`update_datetime`,`remark`) VALUES ('CP201708232117435016838','借贷产品B','LV1成功借还2次以上方可申请','2',2000000,7,0.00100000,0.00100000,0.00100000,0.00100000,0.00100000,0.00100000,'1','0',2,'#fba72e','admin',now(),NULL);
INSERT INTO `t_product` (`code`,`name`,`slogan`,`level`,`amount`,`duration`,`yq_rate1`,`yq_rate2`,`lx_rate`,`xs_rate`,`gl_rate`,`fw_rate`,`status`,`ui_location`,`ui_order`,`ui_color`,`updater`,`update_datetime`,`remark`) VALUES ('CP2017082321185528683722','借贷产品C','LV2成功借还2次以上方可申请','3',4000000,7,0.00100000,0.00100000,0.00100000,0.00100000,0.00100000,0.00100000,'1','0',3,'#f16254','admin',now(),NULL);
INSERT INTO `t_product` (`code`,`name`,`slogan`,`level`,`amount`,`duration`,`yq_rate1`,`yq_rate2`,`lx_rate`,`xs_rate`,`gl_rate`,`fw_rate`,`status`,`ui_location`,`ui_order`,`ui_color`,`updater`,`update_datetime`,`remark`) VALUES ('CP2017082321213612259502','借贷产品D','LV3成功借还2次以上方可申请','4',5000000,7,0.00100000,0.00100000,0.00100000,0.00100000,0.00100000,0.00100000,'1','0',4,'#28a6e6','admin',now(),NULL);


INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','identifyValidDays','7','admin',now(),'身份认证有效天数','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','antifraudValidDays','7','admin',now(),'欺诈识别认证有效天数','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','zmScoreValidDays','7','admin',now(),'芝麻分认证有效天数','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','carrierValidDays','7','admin',now(),'运营商认证有效天数','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','addressBookValidDays','7','admin',now(),'通讯录认证有效天数','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','amountValidDays','7','admin',now(),'额度有效天数','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','sendSmsCount','5','admin',now(),'催收联系人数量','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','renewalStep','7','admin',now(),'续期步长','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','renewalLimit','5','admin',now(),'最大续期次数','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('2','smsCuishou','先生/女士，您好，请通知%s（%s），其在【九州宝】的欠款已严重逾期。若%s不能及时处理，产生的责任和影响由其本人承担！打扰之处敬请谅解。','admin',now(),'催收短信模板','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('2','mxUrl','https://api.51datakey.com/carrier/v3/mobiles/%s/mxreport?task_id=%s','admin',now(),'魔蝎URL','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('2','mxApiKey','96ee985a972a4685be2bb423588e008f','admin',now(),'魔蝎apiKey','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('2','mxToken','76216f728af3416083f20a13f9ddb2a9','admin',now(),'魔蝎token','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('2','tdSubmitUrl','https://apitest.tongdun.cn/preloan/apply/v5','admin',now(),'同盾贷前审核信息提交URL','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('2','tdQueryUrl','https://apitest.tongdun.cn/preloan/report/v9','admin',now(),'同盾贷前审核查询URL','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('2','tdPartnerCode','jiuzhou','admin',now(),'合作方标识','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('2','tdPartnerKey','af52d445b7344653b1bb8ca8b5ce3878','admin',now(),'合作方密钥','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('2','tdPartnerApp','jiuzhou_and','admin',now(),'应用名','CD-YLQ000014','CD-YLQ000014');

/*
-- Query: SELECT * FROM xn_ylq.t_coupon
-- Date: 2017-08-19 00:45
*/
INSERT INTO `t_coupon` (`code`,`type`,`get_condition`,`amount`,`valid_days`,`start_amount`,`status`,`updater`,`update_datetime`,`remark`) VALUES ('CD-YLQ000014001','0',1,50000,30,1000000,'1','admin',now(),'推荐成功送');
INSERT INTO `t_coupon` (`code`,`type`,`get_condition`,`amount`,`valid_days`,`start_amount`,`status`,`updater`,`update_datetime`,`remark`) VALUES ('CD-YLQ000014002','1',2,80000,30,1000000,'1','ylq',now(),'借还成功送');


INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','product_level','产品等级','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_level','1','LV.1','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_level','2','LV.2','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_level','3','LV.3','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_level','4','LV.4','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','product_status','产品状态','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_status','0','待上架','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_status','1','上架中','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_status','2','已下架','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','product_color','产品UI颜色','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_color','#0cb8ae','LV.1-浅蓝','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_color','#fba72e','LV.2-黄色','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_color','#f16254','LV.3-红色','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_color','#28a6e6','LV.4-蓝色','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','product_location','产品位置','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','product_location','0','普通列表','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','education','学历','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','education','0','小学','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','education','1','初中','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','education','2','高中/职高/技校','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','education','3','大专','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','education','4','本科','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','education','5','硕士','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','education','6','博士','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','marriage','婚姻','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','marriage','0','未婚','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','marriage','1','已婚','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','marriage','2','离异','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','marriage','3','丧偶','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','live_time','居住时长','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','live_time','0','三个月','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','live_time','1','六个月','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','live_time','2','一年','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','live_time','3','两年','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','live_time','4','两年以上','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','occupation','职业','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','0','程序员','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','1','设计师','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','2','教师','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','3','服务员','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','4','司机','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','5','厨师','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','6','理发师','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','7','教练','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','8','文员','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','9','销售经理','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','10','客服专员','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','11','采购员','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','12','营业员','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','13','网店店长','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','14','维修工','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','15','快递员','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','16','律师','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','17','翻译','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','18','编辑','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','19','会计','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','20','医生','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','21','工程师','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','22','公务员','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','occupation','23','其他','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','income','月收入','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','income','0','小于1000元','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','income','1','1000-2000元','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','income','2','2000-4000元','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','income','3','4000-6000元','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','income','4','6000-10000元','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','income','5','10000元以上','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','family_relation','亲属关系','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','family_relation','0','父母','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','family_relation','1','配偶','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','family_relation','2','兄弟姐妹','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','society_relation','社会关系','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','society_relation','0','同学','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','society_relation','1','同事','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','society_relation','2','朋友','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','bank','开户行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','ICBC','中国工商银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','ABC','中国农业银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','CCB','中国建设银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','BOC','中国银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','BCM','中国交通银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','CIB','兴业银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','CITIC','中信银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','CEB','中国光大银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','PAB','平安银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','PSBC','中国邮政储蓄银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','SHB','上海银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','bank','SPDB','浦东发展银行','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','apply_status','产品状态','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','apply_status','1','认证中','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','apply_status','2','待审核','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','apply_status','3','已驳回','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','apply_status','4','审核通过','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','apply_status','5','待放款','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','apply_status','6','生效中','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','apply_status','7','已逾期','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','apply_status','8','已还款','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','apply_status','9','已取消','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','apply_status','10','确认坏账','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','coupon_type','优惠券类型','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','coupon_type','0','获客优惠券','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','coupon_type','1','借还优惠券','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','coupon_status','优惠券状态','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','coupon_status','0','关闭','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','coupon_status','1','开启','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','user_coupon_status','优惠券类型','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','user_coupon_status','0','可使用','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','user_coupon_status','1','已使用','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','user_coupon_status','2','已过期','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','user_coupon_status','3','已回收','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','borrow_status','借款状态','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','borrow_status','0','待审核','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','borrow_status','1','待放款','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','borrow_status','2','审核不通过','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','borrow_status','3','已放款','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','borrow_status','4','已还款','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','borrow_status','5','已逾期','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','borrow_status','6','坏账','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','borrow_status','7','打款失败','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','borrow_status','8','代付转账中','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','pay_type','支付方式','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','pay_type','2','微信','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','pay_type','3','支付宝','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','pay_type','4','线下','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','repay_apply_status','线下打款申请状态','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','repay_apply_status','0','待审核','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','repay_apply_status','1','审核通过','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','repay_apply_status','2','审核不通过','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('0','','repay_apply_type','线下打款申请类型','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','repay_apply_type','0','线下还款','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('1','repay_apply_type','1','线下续期','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
