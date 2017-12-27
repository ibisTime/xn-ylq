package com.cdkj.ylq.ao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IStatisticAO;
import com.cdkj.ylq.bo.IStatisticBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Statistic;

@Service
public class StatisticAOImpl implements IStatisticAO {

    static final Logger logger = LoggerFactory.getLogger(StatisticAOImpl.class);

    @Autowired
    private IStatisticBO statisticBO;

    @Override
    public Paginable<Statistic> queryStatisticPage(int start, int limit,
            Statistic condition) {
        return statisticBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Statistic> queryStatisticList(Statistic condition) {
        return statisticBO.queryStatisticList(condition);
    }

    @Override
    public void doInsertStatisticDaily() {
        logger.info("***************统计数据开始***************");
        statisticBO.doInsertStatisticDaily();
        logger.info("***************统计数据结束***************");
    }

}
