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
import com.cdkj.ylq.dto.req.XN623171Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 删除分期规则
 * @author: taojian 
 * @since: 2018年11月21日 下午12:18:53 
 * @history:
 */
public class XN623171 extends AProcessor {

    private IStagingRuleAO stagingRuleAO = SpringContextHolder
        .getBean(IStagingRuleAO.class);

    private XN623171Req req;

    @Override
    public Object doBusiness() throws BizException {
        stagingRuleAO.dropStagingRule(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623171Req.class);
        ObjValidater.validateReq(req);
    }

}
