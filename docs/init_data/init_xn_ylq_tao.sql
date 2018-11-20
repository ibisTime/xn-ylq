
INSERT INTO `tsys_role` (`code`,`name`,`type`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','超级管理员','P','UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_role` (`code`,`name`,`type`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('JS201810041749297484833','管理员','P','UCOIN201700000000000001',now(),'','CD-YLQ000014');


/*
-- Query: SELECT * FROM tsys_menu where code='COINSM201612071021105964'
union
select * from tsys_menu where code ='COINSM201612071021105964' or parent_code='COINSM201612071021105964'
union
select * from tsys_menu where parent_code in (select code from tsys_menu where parent_code='COINSM201612071021105964')
union
select * from tsys_menu where parent_code in (select code from tsys_menu where parent_code in (select code from tsys_menu where parent_code='COINSM201612071021105964'))
-- Date: 2018-11-20 14:54
*/
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM201612071021105964','COINSM201700000000000000','财务管理','1','#',4,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281458013408308','COINSM201612071021105964','平台账户','1','#',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281458246867562','COINSM201612071021105964','充值管理','1','#',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281458530008193','COINSM201612071021105964','线下取现','1','#',3,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281500541305442','CD201809281458013408308','分销规则设置','1','/platform/distributionRules.htm',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281501543134548','CD201809281458013408308','级差设置','1','/platform/gradationRules.htm',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281503133311331','CD201809281458013408308','碳泡泡规则','1','/platform/tppRules.htm',4,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281504013431237','CD201809281458013408308','账户查询','1','/platform/account.htm',5,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281504368347161','CD201809281458013408308','流水查询','1','/platform/flows.htm',6,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281507086522935','CD201809281458246867562','线下充值','1','/recharge/recharges.htm',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281507479361586','CD201809281458246867562','充值查询','1','/recharge/records.htm',3,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281509063426888','CD201809281458530008193','取现规则','1','/withdraw/rules.htm',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281509450622929','CD201809281458530008193','线下取现','1','/withdraw/withdraw.htm',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281510256651980','CD201809281458530008193','取现查询','1','/withdraw/records.htm',3,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810042352064116135','CD201809281458013408308','积分规则','1','/platform/integralRules.htm',3,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD2018103121070311232752','CD201809281458013408308','支付规则','1','/platform/payRules.htm',7,'UCOIN201700000000000001','2018-10-31 21:07:03','','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD2018111420442435330453','CD201809281458013408308','会员等级设置','1','/platform/memberLevel.htm',8,'UCOIN201700000000000001','2018-11-14 20:44:24','','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD2018111615311589529770','CD201809281458013408308','邀请好友设置','1','/platform/invitation.htm',9,'UCOIN201700000000000001','2018-11-16 15:39:19','','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810042325430956759','CD201809281500541305442','修改','2','/edit',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810042326100774694','CD201809281501543134548','修改','2','/edit',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810042327114493874','CD201809281503133311331','修改','2','/edit',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810042352267473712','CD201810042352064116135','修改','2','/edit',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810050104422483297','CD201809281507086522935','待申请','2','/add',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810050105195526885','CD201809281507086522935','详情','2','/detail',3,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810050105538468486','CD201809281507086522935','审核','2','/check',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810050106121877523','CD201809281507086522935','导出','2','/export',4,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810050154234414919','CD201809281507479361586','详情','2','/detail',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810050154373965588','CD201809281507479361586','导出','2','/export',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810051837019382814','CD201809281509063426888','修改','2','/edit',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810061158329263282','CD201809281509450622929','审核','2','/check',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810061200553476264','CD201809281509450622929','打款回录','2','/enter',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810061201298954721','CD201809281510256651980','详情','2','/detail',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201810061201456666951','CD201809281510256651980','导出','2','/export',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD2018103121130942498032','CD2018103121070311232752','修改','2','/edit',1,'UCOIN201700000000000001','2018-10-31 21:13:09','','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD2018111420444714344443','CD2018111420442435330453','修改','2','/edit',1,'UCOIN201700000000000001','2018-11-14 20:44:47','','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD2018111615313723721071','CD2018111615311589529770','修改','2','/edit',1,'UCOIN201700000000000001','2018-11-16 15:31:37','','CD-YLQ000014');
/*
-- Query: SELECT * FROM tsys_menu where code='COINSM201700001000000001'
union
select * from tsys_menu where code ='COINSM201700001000000001' or parent_code='COINSM201700001000000001'
union
select * from tsys_menu where parent_code in (select code from tsys_menu where parent_code='COINSM201700001000000001')
union
select * from tsys_menu where parent_code in (select code from tsys_menu where parent_code in (select code from tsys_menu where parent_code='COINSM201700001000000001'))
-- Date: 2018-11-20 14:51
*/
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM201700001000000001','COINSM201700000000000000','系统管理','1','#',1,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809292005595194892','COINSM201700001000000001','消息推送','1','#',3,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM201700001000000002','COINSM201700001000000001','运维管理','1','#',2,'admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM201707251006045006005','COINSM201700001000000001','广告位管理','1','#',5,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101716241339082','COINSM201700001000000001','运营管理','1','#',1,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017121215543215610','COINSM201700001000000001','文章管理','1','#',4,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281556563159269','COINSM201700001000000002','数据字典管理','1','/system/dataDict.htm',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809292006538897361','CD201809292005595194892','公告管理','1','/public/notice.htm',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM201700001000000003','COINSM201700001000000002','菜单管理','1','/system/menu.htm',1,'admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017033020005366333','COINSM201707251006045006005','banner管理','1','/public/banner.htm',1,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101716253866426','COINSM2017101716241339082','角色管理','1','/system/role.htm',1,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101716261754674','COINSM2017101716241339082','会员查询','1','/system/user.htm',2,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201711181220013316605','COINSM2017121215543215610','注册协议','1','/public/registrationAgreement.htm',1,'UCOIN201700000000000001','2018-10-08 23:02:45','','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('SM201711272034567989636','COINSM2017121215543215610','攻略','1','/public/strategy.htm',2,'UCOIN201700000000000001','2018-10-08 23:03:05','','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281557164456982','CD201809281556563159269','新增','2','/add',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809281557350347620','CD201809281556563159269','修改','2','/edit',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809292102317412562','CD201809292006538897361','新增','2','/add',1,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809292103266638972','CD201809292006538897361','发布','2','/edit',2,'UCOIN201700000000000001',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD201809292104078866023','CD201809292006538897361','撤回','2','/back',3,'UCOIN201700000000000001','2018-10-15 17:41:04','','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('CD2018101517393978183610','CD201809292006538897361','详情','2','/detail',4,'UCOIN201700000000000001','2018-10-15 17:39:39','','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM201700001000000004','COINSM201700001000000003','新增','2','/add',1,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017032911200961325','COINSM201700001000000003','修改','2','/edit',2,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017033020015631166','COINSM2017033020005366333','新增','2','/add',1,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017033020021094115','COINSM2017033020005366333','修改','2','/edit',2,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017033020022649991','COINSM2017033020005366333','删除','2','/delete',3,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017033020024827112','COINSM2017033020005366333','详情','2','/detail',4,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM201708241024194086655','COINSM201700001000000003','删除','2','/delete',3,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101716450533995','COINSM2017101716253866426','分配菜单','2','/change',4,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101717551955993','COINSM2017101716253866426','新增','2','/add',1,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101717560118734','COINSM2017101716253866426','修改','2','/edit',2,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101717563661357','COINSM2017101716253866426','删除','2','/delete',3,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101719082391126','COINSM2017101716261754674','新增','2','/add',1,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101719094151894','COINSM2017101716261754674','重置密码','2','/reset',2,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101719100760088','COINSM2017101716261754674','注销','2','/rock',4,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017101719110981215','COINSM2017101716261754674','设置角色','2','/assign',5,'admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`parent_code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSM2017120610552303416','COINSM2017101716261754674','激活','2','/active',3,'admin',now(),'','CD-YLQ000014');

/*
-- Query: SELECT code from tsys_menu_role
LIMIT 0, 5000

-- Date: 2018-11-20 15:28
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281458013408308','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281458246867562','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281458530008193','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281500541305442','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281501543134548','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281503133311331','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281504013431237','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281504368347161','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281507086522935','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281507479361586','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281509063426888','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281509450622929','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281510256651980','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281556563159269','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281557164456982','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809281557350347620','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809292005595194892','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809292006538897361','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809292102317412562','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809292103266638972','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201809292104078866023','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810042325430956759','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810042326100774694','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810042327114493874','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810042352064116135','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810042352267473712','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810050104422483297','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810050105195526885','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810050105538468486','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810050106121877523','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810050154234414919','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810050154373965588','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810051837019382814','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810061158329263282','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810061200553476264','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810061201298954721','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD201810061201456666951','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD2018101517393978183610','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD2018103121070311232752','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD2018103121130942498032','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD2018111420442435330453','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD2018111420444714344443','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD2018111615311589529770','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','CD2018111615313723721071','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM201612071021105964','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000001','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000002','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000003','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM201700001000000004','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017032911200961325','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017033020005366333','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017033020015631166','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017033020021094115','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017033020022649991','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017033020024827112','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM201707251006045006005','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM201708241024194086655','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101716241339082','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101716253866426','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101716261754674','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101716450533995','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101717551955993','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101717560118734','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101717563661357','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101719082391126','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101719094151894','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101719100760088','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017101719110981215','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017120610552303416','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','COINSM2017121215543215610','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','SM201711181220013316605','admin',now(),'','CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('COINSR201700000000000000','SM201711272034567989636','admin',now(),'','CD-YLQ000014');


/*
-- Query: SELECT `user_id`,`role_code`,`real_name`,`photo`,`mobile`,`login_name`,`login_pwd`,`login_pwd_strength`,`create_datetime`,`status`,`updater`, now() `update_datetime`,`remark`,`system_code` FROM tsys_user where user_id='UCOIN201700000000000001'
LIMIT 0, 500

-- Date: 2018-10-04 14:26
*/
INSERT INTO `tsys_user` (`user_id`,`role_code`,`real_name`,`photo`,`mobile`,`login_name`,`login_pwd`,`login_pwd_strength`,`create_datetime`,`status`,`updater`,`update_datetime`,`remark`,`company_code`) VALUES ('UCOIN201700000000000001','COINSR201700000000000000','系统管理员',NULL,NULL,'admin','21218cca77804d2ba1922c33e0151105','1',now(),'2','UCOIN201700000000000001',now(),'管理端系统方','CD-YLQ000014');

