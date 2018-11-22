/**
 * @Title XN623181.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月22日 下午10:10:56 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IRepayApplyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623181Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * @author: taojian 
 * @since: 2018年11月22日 下午10:10:56 
 * @history:
 */
public class XN623181 extends AProcessor {

    private IRepayApplyAO repayApplyAO = SpringContextHolder
        .getBean(IRepayApplyAO.class);

    private XN623181Req req;

    @Override
    public Object doBusiness() throws BizException {
        repayApplyAO.doApprove(req.getCode(), req.getApproveResult(),
            req.getApprover(), req.getApproveNote());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623181Req.class);
        ObjValidater.validateReq(req);
    }

}
