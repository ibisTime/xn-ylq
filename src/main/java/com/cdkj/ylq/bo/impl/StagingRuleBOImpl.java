package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IStagingRuleBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IStagingRuleDAO;
import com.cdkj.ylq.domain.StagingRule;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.exception.BizException;

//CHECK ��鲢��ע�� 
@Component
public class StagingRuleBOImpl extends PaginableBOImpl<StagingRule> implements
        IStagingRuleBO {

    @Autowired
    private IStagingRuleDAO stagingRuleDAO;

    @Override
    public boolean isStagingRuleExist(String code) {
        StagingRule condition = new StagingRule();
        condition.setCode(code);
        if (stagingRuleDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveStagingRule(Long count, Long cycle, BigDecimal rate,
            Long orderNo, String companyCode) {
        StagingRule data = new StagingRule();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.StagingRule
            .getCode());
        data.setCode(code);
        data.setCount(count);
        data.setCycle(cycle);
        data.setRate(rate);
        data.setOrderNo(orderNo);
        data.setCompanyCode(companyCode);
        stagingRuleDAO.insert(data);
        return code;
    }

    @Override
    public int removeStagingRule(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            StagingRule data = new StagingRule();
            data.setCode(code);
            count = stagingRuleDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshStagingRule(StagingRule data, Long count, Long cycle,
            BigDecimal rate, Long orderNo, String updater, String remark) {
        int flag = 0;
        if (null != data) {
            data.setCount(count);
            data.setCycle(cycle);
            data.setRate(rate);
            data.setOrderNo(orderNo);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            flag = stagingRuleDAO.update(data);
        }
        return flag;
    }

    @Override
    public List<StagingRule> queryStagingRuleList(StagingRule condition) {
        return stagingRuleDAO.selectList(condition);
    }

    @Override
    public StagingRule getStagingRule(String code) {
        StagingRule data = null;
        if (StringUtils.isNotBlank(code)) {
            StagingRule condition = new StagingRule();
            condition.setCode(code);
            data = stagingRuleDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该规则不存在");
            }
        }
        return data;
    }
}
