package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IAccountAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN802302Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 根据用户编号，币种获取账户列表
 * @author: xieyj 
 * @since: 2016年12月24日 下午1:05:33 
 * @history:
 */
public class XN802302 extends AProcessor {

    private IAccountAO accountAO = SpringContextHolder
        .getBean(IAccountAO.class);

    private XN802302Req req = null;

    /** 
    * @see com.xnjr.base.api.IProcessor#doBusiness()
    */
    @Override
    public Object doBusiness() throws BizException {
        return accountAO.getAccountAmountSumList(req.getCurrency(),
            req.getStatus(), req.getType());
    }

    /** 
    * @see com.xnjr.base.api.IProcessor#doCheck(java.lang.String)
    */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802302Req.class);
        ObjValidater.validateReq(req);
    }
}
