package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IOverdueBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.IOverdueDAO;
import com.cdkj.ylq.domain.Overdue;
import com.cdkj.ylq.exception.BizException;

@Component
public class OverdueBOImpl extends PaginableBOImpl<Overdue> implements
        IOverdueBO {

    @Autowired
    private IOverdueDAO overdueDAO;

    @Override
    public void saveOverdue(String userId, String borrowCode, Integer days,
            BigDecimal amount, String result) {
        Overdue data = new Overdue();
        data.setUserId(userId);
        data.setBorrowCode(borrowCode);
        data.setDays(days);
        data.setAmount(amount);
        data.setResult(result);
        overdueDAO.insert(data);
    }

    @Override
    public List<Overdue> queryOverdueList(Overdue condition) {
        return overdueDAO.selectList(condition);
    }

    @Override
    public Overdue getOverdue(Long id) {
        Overdue data = null;
        if (id != null) {
            Overdue condition = new Overdue();
            condition.setId(id);
            data = overdueDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "逾期记录不存在");
            }
        }
        return data;
    }

    @Override
    public String getOverdueCode(String userId) {
        String overdueCode = "";
        Overdue condition = new Overdue();
        condition.setUserId(userId);
        List<Overdue> results = overdueDAO.selectList(condition);
        for (Overdue overdue : results) {
            if (overdue.getDays() >= 1 && overdue.getDays() <= 3) {
                overdueCode += "1";
            } else if (overdue.getDays() >= 4 && overdue.getDays() <= 7) {
                overdueCode += "2";
            } else if (overdue.getDays() >= 8 && overdue.getDays() <= 30) {
                overdueCode += "3";
            } else if (overdue.getDays() > 30) {
                overdueCode += "4";
            }
        }
        if (StringUtils.isBlank(overdueCode)) {
            overdueCode = "无逾期";
        }
        return overdueCode;
    }
}
