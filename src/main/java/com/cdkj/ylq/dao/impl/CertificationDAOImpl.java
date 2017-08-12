package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.ICertificationDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Certification;

@Repository("certificationDAOImpl")
public class CertificationDAOImpl extends AMybatisTemplate implements
        ICertificationDAO {

    @Override
    public int insert(Certification data) {
        return super.insert(NAMESPACE.concat("insert_certification"), data);
    }

    @Override
    public int delete(Certification data) {
        return super.delete(NAMESPACE.concat("delete_certification"), data);
    }

    @Override
    public Certification select(Certification condition) {
        return super.select(NAMESPACE.concat("select_certification"),
            condition, Certification.class);
    }

    @Override
    public Long selectTotalCount(Certification condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_certification_count"), condition);
    }

    @Override
    public List<Certification> selectList(Certification condition) {
        return super.selectList(NAMESPACE.concat("select_certification"),
            condition, Certification.class);
    }

    @Override
    public List<Certification> selectList(Certification condition, int start,
            int count) {
        return super.selectList(NAMESPACE.concat("select_certification"),
            start, count, condition, Certification.class);
    }

    @Override
    public int updateCertification(Certification data) {
        return super.update(NAMESPACE.concat("update_certification"), data);
    }

}
