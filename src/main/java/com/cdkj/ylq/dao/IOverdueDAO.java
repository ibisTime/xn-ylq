package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Overdue;

public interface IOverdueDAO extends IBaseDAO<Overdue> {
	String NAMESPACE = IOverdueDAO.class.getName().concat(".");
}