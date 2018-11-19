package com.cdkj.ylq.dao;

import java.math.BigDecimal;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Withdraw;

public interface IWithdrawDAO extends IBaseDAO<Withdraw> {
    String NAMESPACE = IWithdrawDAO.class.getName().concat(".");

    void approveOrder(Withdraw data);

    void payOrder(Withdraw data);

    void broadcastOrder(Withdraw data);

    public BigDecimal selectTotalAmount(Withdraw data);

}
