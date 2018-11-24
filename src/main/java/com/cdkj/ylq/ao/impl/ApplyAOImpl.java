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
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.res.XN623020Res;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.exception.BizException;

@Service
public class ApplyAOImpl implements IApplyAO {

    @Autowired
    private IApplyBO applyBO;

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
        User user = userBO.getUser(applyUser);
        if (EBoolean.YES.getCode().equals(user.getIsBlackList())) {
            throw new BizException("xn000000", "由于您逾期未还款，已被平台拉入黑名单，请联系平台进行处理！");
        }
        XN623020Res res = new XN623020Res();
        String status = EApplyStatus.TO_CERTI.getCode();
        if (certificationBO.isCompleteCerti(applyUser)) {
            status = EApplyStatus.TO_APPROVE.getCode();
        }

        Apply apply = applyBO.getCurrentApply(applyUser);
        if (apply != null) {
            if (EApplyStatus.APPROVE_NO.getCode().equals(apply.getStatus())) {
                if (DateUtil.daysBetween(apply.getApplyDatetime(), new Date()) < 7) {
                    throw new BizException("xn623020",
                        "您在一周内已经有一个申请被驳回，请在一周后重新尝试。");
                }
                apply.setStatus(status);
                apply.setUpdater(applyUser);
                apply.setUpdateDatetime(new Date());
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
            data.setApplyDatetime(new Date());
            data.setStatus(status);
            data.setUpdater(applyUser);
            data.setUpdateDatetime(new Date());
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
            Integer config = sysConfigBO
                .getIntegerValue(SysConstants.AMOUNT_VALID_DAYS);
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
        smsOutBO.sentContent(apply.getApplyUser(), content);
    }

    @Override
    public Paginable<Apply> queryApplyPage(int start, int limit, Apply condition) {
        Paginable<Apply> results = applyBO
            .getPaginable(start, limit, condition);
        List<Apply> applyList = results.getList();
        for (Apply apply : applyList) {
            apply.setUser(userBO.getUser(apply.getApplyUser()));
        }
        return results;
    }

    @Override
    public Apply getApply(String code) {
        Apply apply = applyBO.getApply(code);
        apply.setUser(userBO.getUser(apply.getApplyUser()));
        return apply;
    }

    @Override
    public Apply getCurrentApply(String userId) {
        return applyBO.getCurrentApply(userId);
    }

}
