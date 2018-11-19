package com.cdkj.ylq.api.impl;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IWithdrawAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN802353Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 提现支付回录
 * @author: xieyj 
 * @since: 2018年10月5日 下午9:00:50 
 * @history:
 */
public class XN802353 extends AProcessor {
    private IWithdrawAO withdrawAO = SpringContextHolder
        .getBean(IWithdrawAO.class);

    private XN802353Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        synchronized (XN802353.class) {
            for (String code : req.getCodeList()) {
                BigDecimal payFee = BigDecimal.ZERO;
                if (StringUtils.isNotBlank(req.getPayFee())) {
                    payFee = new BigDecimal(req.getPayFee());
                }

                withdrawAO.payOrder(code, req.getPayUser(), req.getPayResult(),
                    req.getPayNote(), req.getChannelOrder(), payFee);
            }
            return new BooleanRes(true);
        }
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802353Req.class);
        ObjValidater.validateReq(req);
    }

}
