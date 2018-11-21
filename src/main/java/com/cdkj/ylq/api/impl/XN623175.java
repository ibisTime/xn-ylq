/**
 * @Title XN623170.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月21日 下午12:18:53 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IStagingRuleAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.StagingRule;
import com.cdkj.ylq.dto.req.XN623175Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 分页查分期规则
 * @author: taojian 
 * @since: 2018年11月21日 下午12:18:53 
 * @history:
 */
public class XN623175 extends AProcessor {

    private IStagingRuleAO stagingRuleAO = SpringContextHolder
        .getBean(IStagingRuleAO.class);

    private XN623175Req req;

    @Override
    public Object doBusiness() throws BizException {
        StagingRule condition = new StagingRule();
        condition.setCompanyCode(req.getCompanyCode());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return stagingRuleAO.queryStagingRulePage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623175Req.class);
        ObjValidater.validateReq(req);
    }

}
