package com.cdkj.ylq.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAddressBook;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.InfoZqzn;
import com.cdkj.ylq.domain.MxAlipayNotification;
import com.cdkj.ylq.domain.MxCarrierNofification;
import com.cdkj.ylq.domain.MxReportData;
import com.cdkj.ylq.dto.req.XN623040Req;
import com.cdkj.ylq.dto.req.XN623041Req;
import com.cdkj.ylq.dto.req.XN623042Req;
import com.cdkj.ylq.dto.res.XN623050Res;
import com.cdkj.ylq.enums.ECertiKey;

public interface ICertificationAO {
    static final String DEFAULT_ORDER_COLUMN = "apply_datetime";

    public InfoZqzn doZqznVerify(String userId, String frontImage,
            String backImage, String faceImage);

    // 支付宝账号密码
    public void submitZfb(String userId, String accountNumber, String password);

    // 提交个人基本信息
    public void submitInfoBasic(XN623040Req req);

    // 提交职业信息
    public void submitInfoOccupation(XN623041Req req);

    // 提交紧急联系人
    public void submitInfoContact(XN623042Req req);

    // 魔蝎运营商认证，主动查询
    public MxReportData doCarrierVerify(String userId, String taskId);

    // 魔蝎运营商任务提交通知回调处理
    public void doMxCarrierTaskSubmitCallback(MxCarrierNofification notification);

    // 魔蝎运营商登录完成通知回调处理
    public void doMxCarrierTaskCallback(MxCarrierNofification notification);

    // 魔蝎运营商采集任务失败通知回调处理
    public void doMxCarrierTaskFailCallback(MxCarrierNofification notification);

    // 魔蝎运营商资信报告通知回调处理
    public void doMxCarrierReportCallback(MxCarrierNofification notification);

    // 魔蝎支付宝登录完成回掉处理
    public void doMxAlipayTaskCallback(MxAlipayNotification notification);

    // 魔蝎支付宝采集任务失败通知回调处理
    public void doMxAlipayTaskFailCallback(MxAlipayNotification notification);

    // 魔蝎支付宝资信报告通知回掉处理
    public void doMxAlipayReportCallback(MxAlipayNotification notification);

    // 数聚魔盒运营商任务提交通知
    public void doTdCarrierTaskSubmitCallback(String userId, String taskId);

    // 数聚魔盒运营商任务完成通知
    public void doTdCarrierTaskCompleteCallback(boolean isSuccess,
            String userId, String taskId);

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

    public List<Certification> initialCertification(String userId);

    public void setCreditScore(String userId, BigDecimal creditScore);

    public void submitInfoPersonal(String userId);

    public Certification getCertification(String userId, String key);

    public void checkAmount(String key, String userId);

    public void submitLocation(String userId, String province, String city,
            String area, String address);

    public String duotouReport(String userId);

    public String updateDuotou(String userId);

    public void addRemark(String userId, int id, String remark);

}
