package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Apply;

public interface IApplyDAO extends IBaseDAO<Apply> {
    String NAMESPACE = IApplyDAO.class.getName().concat(".");

    public int updateCancel(Apply data);

    public int updateToDoApprove(Apply data);

    public int updateApprove(Apply data);

    public int updateStatus(Apply data);
}
