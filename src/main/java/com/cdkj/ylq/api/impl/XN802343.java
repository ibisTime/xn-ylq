package com.cdkj.ylq.api.impl;

import java.math.BigDecimal;

import com.cdkj.ylq.ao.IChargeAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN802343Req;
import com.cdkj.ylq.dto.res.PayOrderRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 线上充值申请
 * @author: myb858 
 * @since: 2017年5月3日 上午9:23:51 
 * @history:
 */
public class XN802343 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802343Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public synchronized Object doBusiness() throws BizException {
        BigDecimal amount = StringValidater.toBigDecimal(req.getAmount());
        String signOrder = chargeAO.applyOrderOnline(req.getUserId(),
            req.getPayType(), amount);
        return new PayOrderRes(signOrder);
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802343Req.class);
        ObjValidater.validateReq(req);
    }
}
