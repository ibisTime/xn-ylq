package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.CertRecord;

//dao层 
public interface ICertRecordDAO extends IBaseDAO<CertRecord> {
	String NAMESPACE = ICertRecordDAO.class.getName().concat(".");
}