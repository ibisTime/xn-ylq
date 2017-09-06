package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAddressBook;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.InfoZMCredit;
import com.cdkj.ylq.domain.MxReportData;
import com.cdkj.ylq.dto.req.XN623040Req;
import com.cdkj.ylq.dto.req.XN623041Req;
import com.cdkj.ylq.dto.req.XN623042Req;
import com.cdkj.ylq.dto.res.XN623050Res;
import com.cdkj.ylq.dto.res.XN798013Res;
import com.cdkj.ylq.dto.res.XN798014Res;
import com.cdkj.ylq.enums.ECertiKey;

public interface ICertificationAO {
    static final String DEFAULT_ORDER_COLUMN = "apply_datetime";

    // 提交身份证照片
    public void submitIdentifyPic(String userId, String identifyPic,
            String identifyPicReverse, String identifyPicHand);

    // 芝麻认证第一步，返回bizNo
    public XN798013Res doZhimaVerify(String userId, String idKind, String idNo,
            String realName, String returnUrl, String localCheck, String remark);

    // 芝麻认证查询
    public XN798014Res doZhimaQuery(String userId, String bizNo);

    // 提交身份认证
    public void submitIdentifyInfo(String userId);

    // 提交个人基本信息
    public void submitInfoBasic(XN623040Req req);

    // 提交职业信息
    public void submitInfoOccupation(XN623041Req req);

    // 提交紧急联系人
    public void submitInfoContact(XN623042Req req);

    // 提交银行卡信息
    // public void submitInfoBankcard(XN623043Req req);

    // 提交个人信息，开始欺诈认证
    public void submitPersonalInfo(String userId, String ip, String mac,
            String wifiMac, String imei);

    // 芝麻信用分查询（同时查询行业关注名单）
    public InfoZMCredit doZhimaCreditScoreGet(String userId);

    // 魔蝎运营商认证
    public MxReportData doCarrierVerify(String userId, String taskId);

    // 通讯录认证
    public void doAddressBookVerify(String userId,
            List<InfoAddressBook> addressBookList);

    // 查询个人超详细的认证信息（可形成用户报告）
    public XN623050Res getCertiInfo(String userId);

    // 根据认证类型获取认证信息
    public Object getCertiInfo(String userId, ECertiKey certiKey);

    // 查询我的授信额度
    public InfoAmount getMyCreditAmount(String userId);

    public Paginable<Certification> queryCertificationPage(int start,
            int limit, Certification condition);

    public Certification getCertification(Long id);

    // 定时器：每日检查认证是否已过期
    public void doCheckValidDaily();

}
