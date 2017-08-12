package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Apply;

public interface IApplyAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String submitApply(Apply data);

    public Paginable<Apply> queryApplyPage(int start, int limit, Apply condition);

    public Apply getApply(String code);

}
