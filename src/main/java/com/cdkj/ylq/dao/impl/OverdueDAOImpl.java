package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IOverdueDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Overdue;

@Repository("overdueDAOImpl")
public class OverdueDAOImpl extends AMybatisTemplate implements IOverdueDAO {

    @Override
    public int insert(Overdue data) {
        return super.insert(NAMESPACE.concat("insert_overdue"), data);
    }

    @Override
    public int delete(Overdue data) {
        return 0;
    }

    @Override
    public Overdue select(Overdue condition) {
        return super.select(NAMESPACE.concat("select_overdue"), condition,
            Overdue.class);
    }

    @Override
    public Long selectTotalCount(Overdue condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_overdue_count"),
            condition);
    }

    @Override
    public List<Overdue> selectList(Overdue condition) {
        return super.selectList(NAMESPACE.concat("select_overdue"), condition,
            Overdue.class);
    }

    @Override
    public List<Overdue> selectList(Overdue condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_overdue"), start,
            count, condition, Overdue.class);
    }

}
