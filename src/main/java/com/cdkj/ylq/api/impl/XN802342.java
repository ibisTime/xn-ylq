package com.cdkj.ylq.api.impl;

import java.math.BigDecimal;

import com.cdkj.ylq.ao.IChargeAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN802342Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 积分池手动加积分
 * @author: xieyj 
 * @since: 2018年10月6日 下午2:07:59 
 * @history:
 */
public class XN802342 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802342Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public Object doBusiness() throws BizException {
        synchronized (XN802342.class) {
            BigDecimal amount = new BigDecimal(req.getAmount());
            chargeAO.addSysAccount(amount, req.getCurrency(), req.getBizNote(),
                req.getUpdater());
            return new BooleanRes(true);
        }
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802342Req.class);
        ObjValidater.validateReq(req);
    }
}
