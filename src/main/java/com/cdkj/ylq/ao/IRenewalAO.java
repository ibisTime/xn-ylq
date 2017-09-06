package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Renewal;

public interface IRenewalAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public Paginable<Renewal> queryRenewalPage(int start, int limit,
            Renewal condition);

    public Renewal getRenewal(String code);

}
