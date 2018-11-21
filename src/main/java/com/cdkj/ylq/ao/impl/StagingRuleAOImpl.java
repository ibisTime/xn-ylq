package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IStagingRuleAO;
import com.cdkj.ylq.bo.IStagingRuleBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.StagingRule;
import com.cdkj.ylq.exception.BizException;

//CHECK ��鲢��ע�� 
@Service
public class StagingRuleAOImpl implements IStagingRuleAO {

    @Autowired
    private IStagingRuleBO stagingRuleBO;

    @Override
    public String addStagingRule(Long count, Long cycle, BigDecimal rate,
            Long orderNo, String companyCode) {

        return stagingRuleBO.saveStagingRule(count, cycle, rate, orderNo,
            companyCode);
    }

    @Override
    public int editStagingRule(String code, Long count, Long cycle,
            BigDecimal rate, Long orderNo, String updater, String remark) {
        StagingRule data = stagingRuleBO.getStagingRule(code);
        return stagingRuleBO.refreshStagingRule(data, count, cycle, rate,
            orderNo, updater, remark);
    }

    @Override
    public int dropStagingRule(String code) {
        if (!stagingRuleBO.isStagingRuleExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return stagingRuleBO.removeStagingRule(code);
    }

    @Override
    public Paginable<StagingRule> queryStagingRulePage(int start, int limit,
            StagingRule condition) {
        return stagingRuleBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<StagingRule> queryStagingRuleList(StagingRule condition) {
        return stagingRuleBO.queryStagingRuleList(condition);
    }

    @Override
    public StagingRule getStagingRule(String code) {
        return stagingRuleBO.getStagingRule(code);
    }
}
