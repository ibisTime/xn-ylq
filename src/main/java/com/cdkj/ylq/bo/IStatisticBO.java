package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Statistic;

public interface IStatisticBO extends IPaginableBO<Statistic> {

    public List<Statistic> queryStatisticList(Statistic condition);

    public void doInsertStatisticDaily();

}
