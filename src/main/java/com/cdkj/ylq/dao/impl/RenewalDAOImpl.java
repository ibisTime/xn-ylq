package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IRenewalDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Renewal;

@Repository("renewalDAOImpl")
public class RenewalDAOImpl extends AMybatisTemplate implements IRenewalDAO {

    @Override
    public int insert(Renewal data) {
        return super.insert(NAMESPACE.concat("insert_renewal"), data);
    }

    @Override
    public int delete(Renewal data) {
        return 0;
    }

    @Override
    public Renewal select(Renewal condition) {
        return super.select(NAMESPACE.concat("select_renewal"), condition,
            Renewal.class);
    }

    @Override
    public Long selectTotalCount(Renewal condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_renewal_count"),
            condition);
    }

    @Override
    public List<Renewal> selectList(Renewal condition) {
        return super.selectList(NAMESPACE.concat("select_renewal"), condition,
            Renewal.class);
    }

    @Override
    public List<Renewal> selectList(Renewal condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_renewal"), start,
            count, condition, Renewal.class);
    }

    @Override
    public int updateRenewalSuccess(Renewal data) {
        return super.update(NAMESPACE.concat("update_renewalSuccess"), data);
    }

}
