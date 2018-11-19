package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IWithdrawAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN802356Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 详情查询取现订单
 * @author: xieyj 
 * @since: 2017年5月17日 下午5:17:47 
 * @history:
 */
public class XN802356 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802356Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return withdrawAO.getWithdraw(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802356Req.class);
        ObjValidater.validateReq(req);

    }

}
