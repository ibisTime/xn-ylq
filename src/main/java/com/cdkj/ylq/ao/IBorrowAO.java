package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Borrow;

public interface IBorrowAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    // 使用额度借款
    public String borrow(String userId, Long couponId);

    public Paginable<Borrow> queryBorrowPage(int start, int limit,
            Borrow condition);

    public Paginable<Borrow> queryMyBorrowPage(int start, int limit,
            Borrow condition);

    public Borrow getBorrow(String code);

}
