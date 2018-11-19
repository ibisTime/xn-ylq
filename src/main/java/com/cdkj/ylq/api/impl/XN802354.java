package com.cdkj.ylq.api.impl;

import java.math.BigDecimal;

import com.cdkj.ylq.ao.IWithdrawAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN802354Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 取现回录
 * @author: xieyj 
 * @since: 2018年10月6日 下午1:32:35 
 * @history:
 */
public class XN802354 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802354Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        synchronized (XN802354.class) {
            BigDecimal amount = new BigDecimal(req.getAmount());
            withdrawAO.withdrawEnter(req.getAccountNumber(), amount,
                req.getWithDate(), req.getChannelOrder(), req.getWithNote(),
                req.getUpdater());
            return new BooleanRes(true);
        }
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802354Req.class);
        ObjValidater.validateReq(req);
    }

}
