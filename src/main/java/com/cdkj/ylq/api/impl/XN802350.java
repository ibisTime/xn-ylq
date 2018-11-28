package com.cdkj.ylq.api.impl;

import java.math.BigDecimal;

import com.cdkj.ylq.ao.IWithdrawAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN802350Req;
import com.cdkj.ylq.dto.res.PKCodeRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 线下取现申请
 * @author: myb858 
 * @since: 2017年4月24日 下午8:00:31 
 * @history:
 */
public class XN802350 extends AProcessor {

    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802350Req req = null;

    @Override
    public synchronized Object doBusiness() throws BizException {
        synchronized (XN802350.class) {
            BigDecimal amount = StringValidater.toBigDecimal(req.getAmount());
            String code = withdrawAO.applyOrder(req.getAccountNumber(), amount,
                req.getPayCardInfo(), req.getPayCardNo(), req.getTradePwd(),
                req.getApplyUser(), req.getApplyUserType(), req.getApplyNote());
            return new PKCodeRes(code);
        }

    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802350Req.class);
        ObjValidater.validateReq(req);
    }
}
