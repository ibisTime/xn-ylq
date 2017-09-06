package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Renewal;

public interface IRenewalDAO extends IBaseDAO<Renewal> {
    String NAMESPACE = IRenewalDAO.class.getName().concat(".");

    public int updateRenewalSuccess(Renewal data);
}
