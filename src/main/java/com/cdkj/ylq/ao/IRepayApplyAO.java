package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.RepayApply;

public interface IRepayApplyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 管理端：审核
    public void doApprove(String code, String approveResult, String approver,
            String approveNote);

    public Paginable<RepayApply> queryRepayApplyPage(int start, int limit,
            RepayApply condition);

    public RepayApply getRepayApply(String code);

    public String repayStage(String code);

}
