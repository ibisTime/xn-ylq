package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.RepayApply;

public interface IRepayApplyDAO extends IBaseDAO<RepayApply> {
    String NAMESPACE = IRepayApplyDAO.class.getName().concat(".");

    public int updateApprove(RepayApply data);
}
