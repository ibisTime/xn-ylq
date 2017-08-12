package com.cdkj.ylq.ao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IApplyAO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.exception.BizException;

@Service
public class ApplyAOImpl implements IApplyAO {

    @Autowired
    private IApplyBO applyBO;

    @Autowired
    private IUserBO userBO;

    @Override
    public String submitApply(String applyUser, String productCode) {
        userBO.getRemoteUser(applyUser);
        Apply apply = applyBO.getCurrentApply(applyUser, productCode);
        if (apply != null) {
            throw new BizException("xn623020", "您已经有贷款申请");
        }
        Apply data = new Apply();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.APPLY
            .getCode());
        data.setCode(code);
        data.setApplyUser(applyUser);
        data.setApplyDatetime(new Date());
        data.setProductCode(productCode);
        data.setStatus(EApplyStatus.TO_CERTI.getCode());
        data.setUpdater(applyUser);
        data.setUpdateDatetime(new Date());
        data.setRemark("新申请");
        applyBO.saveApply(data);
        return code;
    }

    @Override
    public void cancalApply(String applyUser, String productCode) {
        Apply apply = applyBO.getCurrentApply(applyUser, productCode);
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
    public Paginable<Apply> queryApplyPage(int start, int limit, Apply condition) {
        return applyBO.getPaginable(start, limit, condition);
    }

    @Override
    public Apply getApply(String code) {
        return applyBO.getApply(code);
    }

}
