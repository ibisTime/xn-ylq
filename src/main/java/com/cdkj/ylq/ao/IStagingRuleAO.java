package com.cdkj.ylq.ao;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.StagingRule;

public interface IStagingRuleAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addStagingRule(Long count, Long cycle, BigDecimal rate,
            Long orderNo, String companyCode);

    public int dropStagingRule(String code);

    public int editStagingRule(String code, Long count, Long cycle,
            BigDecimal rate, Long orderNo, String updater, String remark);

    public Paginable<StagingRule> queryStagingRulePage(int start, int limit,
            StagingRule condition);

    public List<StagingRule> queryStagingRuleList(StagingRule condition);

    public StagingRule getStagingRule(String code);

}
