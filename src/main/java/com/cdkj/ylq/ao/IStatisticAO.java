package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Statistic;

public interface IStatisticAO {

    static final String DEFAULT_ORDER_COLUMN = "id";

    public Paginable<Statistic> queryStatisticPage(int start, int limit,
            Statistic condition);

    public List<Statistic> queryStatisticList(Statistic condition);

    public void doInsertStatisticDaily();

}
