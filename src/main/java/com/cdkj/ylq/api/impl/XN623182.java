/**
 * @Title XN623182.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月26日 下午10:27:57 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IRepayApplyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623182Req;
import com.cdkj.ylq.dto.res.PKCodeRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 线下还款（分期）申请
 * @author: taojian 
 * @since: 2018年11月26日 下午10:27:57 
 * @history:
 */
public class XN623182 extends AProcessor {

    private IRepayApplyAO repayApplyAO = SpringContextHolder
        .getBean(IRepayApplyAO.class);

    private XN623182Req req;

    @Override
    public Object doBusiness() throws BizException {

        return new PKCodeRes(repayApplyAO.repayStage(req.getStagingCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623182Req.class);
        ObjValidater.validateReq(req);
    }

}
