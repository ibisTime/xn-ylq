package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.dto.res.XN623020Res;

public interface IApplyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 提交申请
    public XN623020Res submitApply(String applyUser, String productCode);

    // 取消申请
    public void cancalApply(String applyUser);

    // 审核
    public void doApprove(String code, String approveResult, Long sxAmount,
            String approver, String approveNote);

    public Paginable<Apply> queryApplyPage(int start, int limit, Apply condition);

    public Apply getApply(String code);

}
