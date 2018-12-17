package com.cdkj.ylq.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.StagingRule;

//CHECK ��鲢��ע�� 
public interface IStagingRuleBO extends IPaginableBO<StagingRule> {

    public boolean isStagingRuleExist(String code);

    public String saveStagingRule(Long count, Long cycle, BigDecimal rate,
            Long orderNo, String companyCode);

    public int removeStagingRule(String code);

    public int refreshStagingRule(StagingRule data, Long count, Long cycle,
            BigDecimal rate, Long orderNo, String updater, String remark);

    public List<StagingRule> queryStagingRuleList(StagingRule condition);

    public StagingRule getStagingRule(String code);

    public List<StagingRule> getModelRules();

}
