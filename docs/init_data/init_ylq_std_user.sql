
INSERT INTO `tstd_user` (`user_id`,`login_name`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`role_code`,`status`,`create_datetime`,`remark`,`company_code`,`system_code`) VALUES ('CYLQ201700000000000001','admin','21218cca77804d2ba1922c33e0151105','1','P','0','sysAdmin','0',now(),'管理端系统方','CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tstd_user` (`user_id`,`login_name`,`login_pwd`,`login_pwd_strength`,`kind`,`level`,`role_code`,`status`,`create_datetime`,`remark`,`company_code`,`system_code`) VALUES ('CYLQ201700000000000002','ylq','21218cca77804d2ba1922c33e0151105','1','P','0','superAdmin','0',now(),'管理端系统方','CD-YLQ000014','CD-YLQ000014');

INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('sysAdmin','系统管理员','1','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_role` (`code`,`name`,`level`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','超级管理员','1','admin',now(),NULL,'CD-YLQ000014');

INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('QINIU','QINIU_ACCESS_KEY','ulfN2j4k6vHEsmKw-4EKZEGKL9qVkI7UJgrAHr4s','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('QINIU','QINIU_SECRET_KEY','aCdHlg4ct83YENkupTWLovs2-ISxKfS1U2FoHyZb','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');
INSERT INTO `tsys_config` (`type`,`ckey`,`cvalue`,`updater`,`update_datetime`,`remark`,`company_code`,`system_code`) VALUES ('QINIU','QINIU_BUCKET','ylq-pic','admin',now(),NULL,'CD-YLQ000014','CD-YLQ000014');

/*
-- Query: SELECT code,name,type,url,order_no,updater, now() as update_datetime,remark,parent_code,system_code FROM tsys_menu where system_code = 'CD-YLQ000014'
-- Date: 2017-08-07 17:16
*/
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM201600000000000000','根目录','1','#','1','admin','2017-08-07 17:14:19','','','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM201600001000000001','系统管理','1','#','1','admin','2017-08-07 17:14:19','','YLQSM201600000000000000','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM201600001000000002','运维管理','1','#','2','admin','2017-08-07 17:14:19',NULL,'YLQSM201600001000000001','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM201600001000000003','菜单管理','1','/security/menu.htm','1','admin','2017-08-07 17:14:19',NULL,'YLQSM201600001000000002','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM201600001000000004','新增','2','/add','1','admin','2017-08-07 17:14:19','','YLQSM201600001000000003','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101716241339082','运营管理','1','#','1','admin','2017-08-07 17:14:19','','YLQSM201600001000000001','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101716253866426','角色管理','1','/security/role.htm','1','admin','2017-08-07 17:14:19','','YLQSM2016101716241339082','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101716261754674','用户管理','1','/security/user.htm','2','admin','2017-08-07 17:14:19','','YLQSM2016101716241339082','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101716290657836','系统参数管理','1','/general/param.htm','2','admin','2017-08-07 17:14:19','','YLQSM201600001000000002','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101716295904680','数据字典管理','1','/general/dict.htm','3','admin','2017-08-07 17:14:19','','YLQSM201600001000000002','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101716450533995','分配菜单','2','/change','4','admin','2017-08-07 17:14:19','','YLQSM2016101716253866426','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101717551955993','新增','2','/add','1','admin','2017-08-07 17:14:19','','YLQSM2016101716253866426','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101717560118734','修改','2','/edit','2','admin','2017-08-07 17:14:19','','YLQSM2016101716253866426','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101717563661357','删除','2','/delete','3','admin','2017-08-07 17:14:19','','YLQSM2016101716253866426','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101719082391126','新增','2','/add','1','admin','2017-08-07 17:14:19','','YLQSM2016101716261754674','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101719094151894','重置密码','2','/reset','2','admin','2017-08-07 17:14:19','','YLQSM2016101716261754674','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101719100760088','注销','2','/rock','4','admin','2017-08-07 17:14:19','','YLQSM2016101716261754674','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101719110981215','设置角色','2','/assign','5','admin','2017-08-07 17:14:19','','YLQSM2016101716261754674','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101719140087629','修改','2','/edit','1','admin','2017-08-07 17:14:19','','YLQSM2016101716290657836','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016101719143965297','修改','2','/edit','1','admin','2017-08-07 17:14:19','','YLQSM2016101716295904680','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2016120610552303416','激活','2','/active','3','admin','2017-08-07 17:14:19','','YLQSM2016101716261754674','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('YLQSM2017032911200961325','修改','2','/edit','2','admin','2017-08-07 17:14:19','','YLQSM201600001000000003','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707261741263791893','业务管理','1','#','2','admin','2017-08-07 17:14:19','','YLQSM201600000000000000','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707261745530413766','认证管理','1','#','1','admin','2017-08-07 17:14:19','','SM201707261741263791893','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707261747250339518','贷前审批','1','#','2','admin','2017-08-07 17:14:19','','SM201707261741263791893','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707261747450824730','借款管理','1','#','3','admin','2017-08-07 17:14:19','','SM201707261741263791893','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707261817561904796','实名认证','1','/certificat/realName.htm','1','admin','2017-08-07 17:14:19','','SM201707261745530413766','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM20170726182557630657','芝麻信用分','1','/certificat/sesameCreditScore.htm','2','admin','2017-08-07 17:14:19','','SM201707261745530413766','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707271437582732708','欺诈评分记录','1','/certificat/fraudScoreApply.htm','3','admin','2017-08-07 17:14:19','','SM201707261745530413766','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707271704363792795','欺诈关注清单','1','/certificat/fraudFocusList.htm','4','admin','2017-08-07 17:14:19','','SM201707261745530413766','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707271705134394086','欺诈信息验证','1','/certificat/fraudInfoApprove.htm','3','admin','2017-08-07 17:14:19','','SM201707261745530413766','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707271735196294785','行业关注清单','1','/certificat/businessFocusList.htm','2','admin','2017-08-07 17:14:19','','SM201707261745530413766','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707281704301515120','运营商认证','1','/certificat/operator.htm','1','admin','2017-08-07 17:14:19','','SM201707261745530413766','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201707281705081256008','报告详情','2','/detail','1','admin','2017-08-07 17:14:19','','SM201707281704301515120','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708050938371312906','修改','2','/edit','1','admin','2017-08-07 17:14:19','','SM201708071445429053500','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071146218919795','通知管理','1','#','3','admin','2017-08-07 17:14:19','','YLQSM201600001000000001','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071146484282769','文章管理','1','#','4','admin','2017-08-07 17:14:19','','YLQSM201600001000000001','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM20170807114715900667','广告段位管理','1','#','5','admin','2017-08-07 17:14:19','','YLQSM201600001000000001','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM20170807135329320738','公告管理','1','/public/announce.htm','1','admin','2017-08-07 17:14:19','','SM201708071146218919795','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071354294834594','关于我们','1','/public/aboutus.htm','1','admin','2017-08-07 17:14:19','','SM201708071146484282769','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071355289904791','服务热线','1','/public/serviceTel.htm','2','admin','2017-08-07 17:14:19','','SM201708071146484282769','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071356054479020','服务时间','1','/public/serviceTime.htm','3','admin','2017-08-07 17:14:19','','SM201708071146484282769','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071357128989378','banner管理','1','/public/banner.htm','1','admin','2017-08-07 17:14:19','','SM20170807114715900667','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071405342791952','新增','2','/add','1','admin','2017-08-07 17:14:19','','SM20170807135329320738','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071406010883237','修改','2','/edit','2','admin','2017-08-07 17:14:19','','SM20170807135329320738','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071407469348684','发布/撤下','2','/push','3','admin','2017-08-07 17:14:19','','SM20170807135329320738','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071412042227977','新增','2','/add','1','admin','2017-08-07 17:14:19','','SM201708071357128989378','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071412251077022','修改','2','/edit','2','admin','2017-08-07 17:14:19','','SM201708071357128989378','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071415131876278','删除','2','/delete','3','admin','2017-08-07 17:14:19','','SM201708071357128989378','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071445429053500','程筛规则','1','/loansBefore/csRule.htm','1','admin','2017-08-07 17:14:19','','SM201707261747250339518','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071450049267108','统计分析','1','#','3','admin','2017-08-07 17:14:19','','YLQSM201600000000000000','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071450464839683','报表分析','1','#','1','admin','2017-08-07 17:14:19','','SM201708071450049267108','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071451454165989','贷后管理','1','#','4','admin','2017-08-07 17:14:19','','SM201707261741263791893','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM20170807145206101437','优惠券管理','1','#','5','admin','2017-08-07 17:14:19','','SM201707261741263791893','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071452439275974','会员查询','1','#','6','admin','2017-08-07 17:14:19','','SM201707261741263791893','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071501160616280','会员查询','1','/members/members.htm','1','admin','2017-08-07 17:14:19','','SM201708071452439275974','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071507011747619','程筛不通过','1','#','2','admin','2017-08-07 17:14:19','','SM201707261747250339518','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071507268774905','综合审批','1','#','3','admin','2017-08-07 17:14:19','','SM201707261747250339518','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071508058991967','产品管理','1','#','1','admin','2017-08-07 17:14:19','','SM201707261747450824730','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071508282156023','打款审批','1','#','2','admin','2017-08-07 17:14:19','','SM201707261747450824730','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071508534869053','还款中订单','1','#','3','admin','2017-08-07 17:14:19','','SM201707261747450824730','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071511351107502','逾期还款订单','1','#','1','admin','2017-08-07 17:14:19','','SM201708071451454165989','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071512012672115','确认坏账订单','1','#','2','admin','2017-08-07 17:14:19','','SM201708071451454165989','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071512256685382','优惠券管理','1','#','1','admin','2017-08-07 17:14:19','','SM20170807145206101437','CD-YLQ000014');
INSERT INTO `tsys_menu` (`code`,`name`,`type`,`url`,`order_no`,`updater`,`update_datetime`,`remark`,`parent_code`,`system_code`) VALUES ('SM201708071512479798921','优惠券发放','1','#','2','admin','2017-08-07 17:14:19','','SM20170807145206101437','CD-YLQ000014');

/*
-- Query: SELECT `role_code`,`menu_code`,`updater`,now() as `update_datetime`,`remark`,`system_code` FROM tsys_menu_role where system_code = 'CD-YLQ000014'
-- Date: 2017-08-07 17:19
*/
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM201600000000000000','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM201600001000000001','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM201600001000000002','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM201600001000000003','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM201600001000000004','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2017032911200961325','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101716290657836','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101719140087629','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101716295904680','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101719143965297','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101716241339082','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101716253866426','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101716450533995','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101717551955993','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101717560118734','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101717563661357','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101716261754674','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101719082391126','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101719094151894','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101719100760088','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016101719110981215','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','YLQSM2016120610552303416','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071146218919795','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM20170807135329320738','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071405342791952','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071406010883237','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071407469348684','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071146484282769','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071354294834594','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071355289904791','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071356054479020','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM20170807114715900667','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071357128989378','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071412042227977','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071412251077022','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071415131876278','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707261741263791893','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707261745530413766','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707261817561904796','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM20170726182557630657','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707271437582732708','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707271704363792795','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707271705134394086','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707271735196294785','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707281704301515120','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707281705081256008','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707261747250339518','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071445429053500','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708050938371312906','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071507011747619','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071507268774905','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201707261747450824730','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071508058991967','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071508282156023','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071508534869053','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071451454165989','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071511351107502','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071512012672115','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM20170807145206101437','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071512256685382','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071512479798921','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071452439275974','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071501160616280','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071450049267108','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');
INSERT INTO `tsys_menu_role` (`role_code`,`menu_code`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('superAdmin','SM201708071450464839683','admin','2017-08-07 17:18:46',NULL,'CD-YLQ000014');

/*
-- Query: select `system_code`, `type`, `parent_key`, `dkey`, `dvalue`, `updater`,now() as `update_datetime`, `remark` from tsys_dict
LIMIT 0, 10000

-- Date: 2017-03-09 16:16
*/

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_status','用户状态','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','0','正常','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','1','程序锁定','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_status','2','人工锁定','admin',now(),NULL,'CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'id_kind','证件类型','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','id_kind','1','身份证','admin',now(),NULL,'CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'role_level','角色等级','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','1','运维','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','2','运营','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','role_level','3','客户','admin',now(),NULL,'CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'res_type','资源类型','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','1','菜单','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','res_type','2','按钮','admin',now(),NULL,'CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'lock_direction','锁定方向','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','1','锁定','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','lock_direction','2','解锁','admin',now(),NULL,'CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'news_status','信息状态','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','news_status','0','待发送','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','news_status','1','已发送','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','news_status','2','发送失败','admin',now(),NULL,'CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'user_kind','针对人群','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','1','C端用户','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','2','B端用户','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','user_kind','3','平台用户','admin',now(),NULL,'CD-YLQ000014');

INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('0',NULL,'notice_status','公告状态','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','notice_status','0','未发布','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','notice_status','1','已发布','admin',now(),NULL,'CD-YLQ000014');
INSERT INTO `tsys_dict` (`type`,`parent_key`,`dkey`,`dvalue`,`updater`,`update_datetime`,`remark`,`system_code`) VALUES ('1','notice_status','2','已下架','admin',now(),NULL,'CD-YLQ000014');