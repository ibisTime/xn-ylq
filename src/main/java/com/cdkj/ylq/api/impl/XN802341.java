package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IChargeAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN802341Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 批量审批线下充值订单
 * @author: myb858 
 * @since: 2017年5月3日 上午9:24:44 
 * @history:
 */
public class XN802341 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802341Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public Object doBusiness() throws BizException {
        synchronized (XN802341.class) {
            for (String code : req.getCodeList()) {
                chargeAO.payOrder(code, req.getPayUser(), req.getPayResult(),
                    req.getPayNote());
            }
            return new BooleanRes(true);
        }
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802341Req.class);
        ObjValidater.validateReq(req);
    }
}
