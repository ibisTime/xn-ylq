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
import com.cdkj.ylq.dto.req.XN623176Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 详情查分期规则
 * @author: taojian 
 * @since: 2018年11月21日 下午12:18:53 
 * @history:
 */
public class XN623176 extends AProcessor {

    private IStagingRuleAO stagingRuleAO = SpringContextHolder
        .getBean(IStagingRuleAO.class);

    private XN623176Req req;

    @Override
    public Object doBusiness() throws BizException {
        return stagingRuleAO.getStagingRule(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623176Req.class);
        ObjValidater.validateReq(req);
    }

}
