package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IStatisticDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Statistic;

@Repository("statisticDAOImpl")
public class StatisticDAOImpl extends AMybatisTemplate implements IStatisticDAO {

    @Override
    public int insert(Statistic data) {
        return super.insert(NAMESPACE.concat("insert_statistic"), data);
    }

    @Override
    public int delete(Statistic data) {
        return 0;
    }

    @Override
    public Statistic select(Statistic condition) {
        return super.select(NAMESPACE.concat("select_statistic"), condition,
            Statistic.class);
    }

    @Override
    public Long selectTotalCount(Statistic condition) {
        return super.selectTotalCount(
            NAMESPACE.concat("select_statistic_count"), condition);
    }

    @Override
    public List<Statistic> selectList(Statistic condition) {
        return super.selectList(NAMESPACE.concat("select_statistic"),
            condition, Statistic.class);
    }

    @Override
    public List<Statistic> selectList(Statistic condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_statistic"), start,
            count, condition, Statistic.class);
    }

    @Override
    public int doInsertStatisticDaily(Statistic condition) {
        return super.insert(NAMESPACE.concat("do_insert_statistic_daily"),
            condition);
    }

}
