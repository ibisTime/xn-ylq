package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Apply;

public interface IApplyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 提交申请
    public String submitApply(String applyUser, String productCode);

    // 取消申请
    public void cancalApply(String applyUser, String productCode);

    public Paginable<Apply> queryApplyPage(int start, int limit, Apply condition);

    public Apply getApply(String code);

}
