package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IChargeAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN802346Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 详情查询充值订单
 * @author: xieyj 
 * @since: 2017年5月13日 下午7:58:10 
 * @history:
 */
public class XN802346 extends AProcessor {
    private IChargeAO chargeAO = SpringContextHolder.getBean(IChargeAO.class);

    private XN802346Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return chargeAO.getCharge(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802346Req.class);
        ObjValidater.validateReq(req);

    }

}
