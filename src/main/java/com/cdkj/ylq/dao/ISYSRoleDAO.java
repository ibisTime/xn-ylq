package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.SYSRole;

public interface ISYSRoleDAO extends IBaseDAO<SYSRole> {
    String NAMESPACE = ISYSRoleDAO.class.getName().concat(".");

    public int update(SYSRole data);
}
