package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.StagingRule;

//dao层 
public interface IStagingRuleDAO extends IBaseDAO<StagingRule> {
    String NAMESPACE = IStagingRuleDAO.class.getName().concat(".");

    public int update(StagingRule data);
}
