package com.cdkj.ylq.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Overdue;

public interface IOverdueBO extends IPaginableBO<Overdue> {

    public void saveOverdue(String userId, String borrowCode, Integer days,
            BigDecimal amount, String result);

    public List<Overdue> queryOverdueList(Overdue condition);

    public Overdue getOverdue(Long id);

    public String getOverdueCode(String userId);

}
