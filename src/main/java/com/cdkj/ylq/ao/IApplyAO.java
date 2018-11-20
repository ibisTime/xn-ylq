package com.cdkj.ylq.ao;

import java.math.BigDecimal;

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
    public void doApprove(String code, String approveResult,
            BigDecimal sxAmount, String approver, String approveNote);

    // 获取用户当前正在进行中的申请记录
    public Apply getCurrentApply(String userId);

    public Paginable<Apply> queryApplyPage(int start, int limit, Apply condition);

    public Apply getApply(String code);

}
