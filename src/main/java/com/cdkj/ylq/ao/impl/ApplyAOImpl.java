package com.cdkj.ylq.ao.impl;

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
import com.cdkj.ylq.domain.SYSConfig;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.res.XN623020Res;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.ESystemCode;
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
    public XN623020Res submitApply(String applyUser, String productCode) {
        User user = userBO.getRemoteUser(applyUser);
        if (EBoolean.YES.getCode().equals(user.getBlacklistFlag())) {
            throw new BizException("xn000000", "由于您逾期未还款，已被平台拉入黑名单，请联系平台进行处理！");
        }
        XN623020Res res = new XN623020Res();
        String status = EApplyStatus.TO_CERTI.getCode();
        Certification identify = certificationBO.getCertification(applyUser,
            ECertiKey.INFO_IDENTIFY);
        Certification antifraud = certificationBO.getCertification(applyUser,
            ECertiKey.INFO_ANTIFRAUD);
        Certification zmcredit = certificationBO.getCertification(applyUser,
            ECertiKey.INFO_ZMCREDIT);
        Certification carrier = certificationBO.getCertification(applyUser,
            ECertiKey.INFO_CARRIER);
        if (identify != null && antifraud != null && zmcredit != null
                && carrier != null) {
            if (EBoolean.YES.getCode().equals(identify.getFlag())
                    && EBoolean.YES.getCode().equals(antifraud.getFlag())
                    && EBoolean.YES.getCode().equals(zmcredit.getFlag())
                    && EBoolean.YES.getCode().equals(carrier.getFlag())) {
                status = EApplyStatus.TO_APPROVE.getCode();
            }
        }

        Apply apply = applyBO.getCurrentApply(applyUser);
        if (apply != null) {
            if (EApplyStatus.APPROVE_NO.getCode().equals(apply.getStatus())) {
                apply.setStatus(status);
                applyBO.refreshStatus(apply);
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
            data.setProductCode(productCode);
            data.setStatus(status);
            data.setUpdater(applyUser);
            data.setUpdateDatetime(new Date());
            data.setRemark("新申请");
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
                && !EApplyStatus.APPROVE_NO.getCode().equals(apply.getStatus())
                && !EApplyStatus.APPROVE_YES.getCode()
                    .equals(apply.getStatus())) {
            throw new BizException("xn623021", "当前状态不能取消");
        }
        applyBO.cancel(apply);

    }

    @Override
    @Transactional
    public void doApprove(String code, String approveResult, Long sxAmount,
            String approver, String approveNote) {
        Apply apply = applyBO.getApply(code);
        if (!EApplyStatus.TO_APPROVE.getCode().equals(apply.getStatus())) {
            throw new BizException("xn623021", "该申请记录不处于待审核状态");
        }
        String status = EApplyStatus.APPROVE_NO.getCode();
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = EApplyStatus.APPROVE_YES.getCode();
            // 落地授信信息
            InfoAmount infoAmount = new InfoAmount();
            infoAmount.setSxAmount(sxAmount);
            Certification certification = certificationBO.getCertification(
                apply.getApplyUser(), ECertiKey.INFO_AMOUNT);
            SYSConfig config = sysConfigBO.getSYSConfig(
                SysConstants.AMOUNT_VALID_DAYS, ESystemCode.YLQ.getCode(),
                ESystemCode.YLQ.getCode());
            if (certification != null) {
                certification.setFlag(EBoolean.YES.getCode());
                certification.setResult(JsonUtil.Object2Json(infoAmount));
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(),
                    Integer.valueOf(config.getCvalue())));
                certification.setRef(apply.getProductCode());
                certificationBO.refreshCertification(certification);
            } else {
                certification = new Certification();
                certification.setUserId(apply.getApplyUser());
                certification.setCertiKey(ECertiKey.INFO_AMOUNT.getCode());
                certification.setFlag(EBoolean.YES.getCode());
                certification.setResult(JsonUtil.Object2Json(sxAmount));
                certification.setCerDatetime(new Date());
                certification.setValidDatetime(DateUtil.getRelativeDateOfDays(
                    DateUtil.getTodayStart(),
                    Integer.valueOf(config.getCvalue())));
                certification.setRef(apply.getProductCode());
                certificationBO.saveCertification(certification);
            }
        } else {
            sxAmount = 0L;
        }
        applyBO.doApprove(apply, status, sxAmount, approver, approveNote);

        String content = "恭喜您，您的借款申请已经通过审核，请登录APP进行自助借款操作。";
        if (EBoolean.NO.getCode().equals(approveResult)) {
            content = "很抱歉，您的借款申请未通过平台审核，失败原因为：" + approveNote + "，请登录APP查看详情。";
        }
        smsOutBO.sentContent(apply.getApplyUser(), content);
    }

    @Override
    public Paginable<Apply> queryApplyPage(int start, int limit, Apply condition) {
        Paginable<Apply> results = applyBO
            .getPaginable(start, limit, condition);
        List<Apply> applyList = results.getList();
        for (Apply apply : applyList) {
            apply.setUser(userBO.getRemoteUser(apply.getApplyUser()));
            apply.setProduct(productBO.getProduct(apply.getProductCode()));
        }
        return results;
    }

    @Override
    public Apply getApply(String code) {
        Apply apply = applyBO.getApply(code);
        apply.setUser(userBO.getRemoteUser(apply.getApplyUser()));
        apply.setProduct(productBO.getProduct(apply.getProductCode()));
        return apply;
    }

}
