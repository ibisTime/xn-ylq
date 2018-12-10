package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IApplyAO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.bo.INoticerBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.InfoAmount;
import com.cdkj.ylq.domain.Noticer;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.res.XN623020Res;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.ENoticerType;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.exception.BizException;

@Service
public class ApplyAOImpl implements IApplyAO {

    @Autowired
    private IApplyBO applyBO;

    @Autowired
    private INoticerBO noticerBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private ICertificationBO certificationBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public XN623020Res submitApply(String applyUser, String companyCode,
            String remark) {
        Date now = new Date();
        User user = userBO.getUser(applyUser);
        if (EBoolean.YES.getCode().equals(user.getIsBlackList())) {
            throw new BizException("xn000000", "由于您逾期未还款，已被平台拉入黑名单，请联系平台进行处理！");
        }
        XN623020Res res = new XN623020Res();
        String status = EApplyStatus.TO_CERTI.getCode();
        if (certificationBO.isCompleteCerti(applyUser)) {
            status = EApplyStatus.TO_APPROVE.getCode();
            // 通知信用分审核人
            List<Noticer> noticers = noticerBO.queryNoticersNow(
                ENoticerType.Credit.getCode(), companyCode);
            if (!noticers.isEmpty()) {
                for (Noticer noticer : noticers) {
                    smsOutBO.sendContent(noticer.getMobile(),
                        "有一个信用分申请单待审核，请尽快登陆管理端进行审核", companyCode,
                        ESystemCode.YLQ.getCode());
                }
            }
        }

        Apply apply = applyBO.getCurrentApply(applyUser);
        if (apply != null) {
            if (EApplyStatus.APPROVE_NO.getCode().equals(apply.getStatus())) {
                if (DateUtil.daysBetween(apply.getApplyDatetime(), now) < 7) {
                    throw new BizException("xn623020",
                        "您在一周内已经有一个申请被驳回，请在一周后重新尝试。");
                }
                apply.setApplyDatetime(now);
                apply.setStatus(status);
                apply.setUpdater(applyUser);
                apply.setUpdateDatetime(now);
                apply.setRemark("重新提交申请");
                applyBO.resubmit(apply);
                res.setCode(apply.getCode());
                res.setStatus(status);
            } else {
                throw new BizException("xn623020", "您已经有一个申请");
            }
        } else {
            Apply data = new Apply();
            String code = OrderNoGenerater.generateM(EGeneratePrefix.APPLY
                .getCode());
            data.setCode(code);
            data.setApplyUser(applyUser);
            data.setApplyDatetime(now);
            data.setStatus(status);
            data.setCreditScore(BigDecimal.ZERO);
            data.setUpdater(applyUser);
            data.setUpdateDatetime(now);
            data.setRemark("新申请");
            data.setCompanyCode(companyCode);
            applyBO.saveApply(data);
            res.setCode(code);
            res.setStatus(status);
        }
        return res;
    }

    @Override
    public void cancalApply(String applyUser) {
        Apply apply = applyBO.getCurrentApply(applyUser);
        if (!EApplyStatus.TO_CERTI.getCode().equals(apply.getStatus())
                && !EApplyStatus.TO_APPROVE.getCode().equals(apply.getStatus())
                && !EApplyStatus.APPROVE_YES.getCode()
                    .equals(apply.getStatus())) {
            throw new BizException("xn623021", "当前状态不能取消");
        }
        applyBO.cancel(apply);

    }

    @Override
    @Transactional
    public void doApprove(String code, String approveResult,
            BigDecimal sxAmount, String approver, String approveNote) {
        Apply apply = applyBO.getApply(code);
        if (!EApplyStatus.TO_APPROVE.getCode().equals(apply.getStatus())) {
            throw new BizException("xn623021", "该申请记录不处于待审核状态");
        }
        String status = null;
        String content = null;
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EApplyStatus.APPROVE_YES.getCode();
            // 落地授信信息
            InfoAmount infoAmount = new InfoAmount();
            infoAmount.setSxAmount(sxAmount);
            Certification certification = certificationBO.getCertification(
                apply.getApplyUser(), ECertiKey.INFO_AMOUNT);
            Integer config = sysConfigBO.getIntegerValue(
                SysConstants.AMOUNT_VALID_DAYS, certification.getCompanyCode());
            if (certification != null) {
                certification.setFlag(ECertificationStatus.CERTI_YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoAmount));
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(), config));
                certification.setRef(apply.getCode());
                certificationBO.refreshCertification(certification);
            }
            content = "恭喜您，您的借款申请已经通过审核，请登录APP进行自助借款操作。";
        } else {
            sxAmount = BigDecimal.ZERO;
            status = EApplyStatus.APPROVE_NO.getCode();
            content = "很抱歉，您的借款申请未通过平台审核，失败原因为：" + approveNote
                    + "，请保证填写的资料为本人真实资料,如需再次申请，请7天后再登陆APP提交审核。";
        }
        applyBO.doApprove(apply, status, sxAmount, approver, approveNote);
        User user = userBO.getUser(apply.getApplyUser());
        smsOutBO.sendContent(user.getMobile(), content, apply.getCompanyCode(),
            ESystemCode.YLQ.getCode());
    }

    @Override
    public Paginable<Apply> queryApplyPage(int start, int limit, Apply condition) {
        Paginable<Apply> results = applyBO
            .getPaginable(start, limit, condition);
        List<Apply> applyList = results.getList();
        for (Apply apply : applyList) {
            apply.setUser(userBO.getUser(apply.getApplyUser()));
            List<Certification> certifications = certificationBO
                .queryCertiedList(apply.getApplyUser());
            apply.setCertifications(certifications);
        }
        return results;
    }

    @Override
    public Apply getApply(String code) {
        Apply apply = applyBO.getApply(code);
        apply.setUser(userBO.getUser(apply.getApplyUser()));
        List<Certification> certifications = certificationBO
            .queryCertiedList(apply.getApplyUser());
        apply.setCertifications(certifications);
        return apply;
    }

    @Override
    public Apply getCurrentApply(String userId) {
        return applyBO.getCurrentApply(userId);
    }

    @Override
    public XN623020Res getRes(String userId) {
        XN623020Res res = new XN623020Res();
        Apply condition = new Apply();
        condition.setApplyUser(userId);
        List<Apply> applies = applyBO.queryApplyList(condition);
        Certification certification = certificationBO.getCertification(userId,
            ECertiKey.INFO_AMOUNT);
        InfoAmount infoAmount = JsonUtil.json2Bean(certification.getResult(),
            InfoAmount.class);
        if (certification != null) {
            res.setCreditScore(infoAmount.getSxAmount());
            // 没有申请单，状态为0
            if (applies.isEmpty()) {
                res.setStatus("0");
            } else {
                // 获得最新申请单
                Apply data = null;
                for (Apply apply : applies) {
                    data = apply;
                    break;
                }
                for (Apply apply : applies) {
                    if (apply.getApplyDatetime().after(data.getApplyDatetime())) {
                        data = apply;
                    }
                }
                res.setApply(data);
                res.setStatus(data.getStatus());
                // 状态为123时返回一样状态（认证中，待审核，已驳回）
                if (!EApplyStatus.TO_APPROVE.getCode().equals(data.getStatus())
                        && !EApplyStatus.TO_CERTI.getCode().equals(
                            data.getStatus())
                        && !EApplyStatus.APPROVE_NO.getCode().equals(
                            data.getStatus())) {

                    if (infoAmount.getSxAmount().compareTo(BigDecimal.ZERO) == 0) {// 信用分使用完
                        res.setStatus("6");
                    } else if (ECertificationStatus.INVALID.getCode().equals(
                        certification.getFlag())) {// 信用分过期
                        res.setStatus("5");
                    } else if (ECertificationStatus.CERTI_YES.getCode().equals(
                        certification.getFlag())) {// 信用分未用且未过期
                        res.setStatus("4");
                        res.setValidDays(DateUtil.daysBetween(
                            DateUtil.getTodayStart(),
                            certification.getValidDatetime()));
                    }
                }

            }

        }
        return res;
    }

}
