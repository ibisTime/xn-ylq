package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IStagingDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Staging;

//CHECK 。。。 
@Repository("stagingDAOImpl")
public class StagingDAOImpl extends AMybatisTemplate implements IStagingDAO {

    @Override
    public int insert(Staging data) {
        return super.insert(NAMESPACE.concat("insert_staging"), data);
    }

    @Override
    public int delete(Staging data) {
        return super.delete(NAMESPACE.concat("delete_staging"), data);
    }

    @Override
    public Staging select(Staging condition) {
        return super.select(NAMESPACE.concat("select_staging"), condition,
            Staging.class);
    }

    @Override
    public Long selectTotalCount(Staging condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_staging_count"),
            condition);
    }

    @Override
    public List<Staging> selectList(Staging condition) {
        return super.selectList(NAMESPACE.concat("select_staging"), condition,
            Staging.class);
    }

    @Override
    public List<Staging> selectList(Staging condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_staging"), start,
            count, condition, Staging.class);
    }

    @Override
    public int updateRepay(Staging data) {
        return super.update(NAMESPACE.concat("update_repay"), data);
    }

}
