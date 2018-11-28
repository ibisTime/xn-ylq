package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.ICertRecordDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.CertRecord;



//CHECK 。。。 
@Repository("certRecordDAOImpl")
public class CertRecordDAOImpl extends AMybatisTemplate implements ICertRecordDAO {


	@Override
	public int insert(CertRecord data) {
		return super.insert(NAMESPACE.concat("insert_certRecord"), data);
	}


	@Override
	public int delete(CertRecord data) {
		return super.delete(NAMESPACE.concat("delete_certRecord"), data);
	}


	@Override
	public CertRecord select(CertRecord condition) {
		return super.select(NAMESPACE.concat("select_certRecord"), condition,CertRecord.class);
	}


	@Override
	public Long selectTotalCount(CertRecord condition) {
		return super.selectTotalCount(NAMESPACE.concat("select_certRecord_count"),condition);
	}


	@Override
	public List<CertRecord> selectList(CertRecord condition) {
		return super.selectList(NAMESPACE.concat("select_certRecord"), condition,CertRecord.class);
	}


	@Override
	public List<CertRecord> selectList(CertRecord condition, int start, int count) {
		return super.selectList(NAMESPACE.concat("select_certRecord"), start, count,condition, CertRecord.class);
	}


}