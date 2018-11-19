package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBankcardAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN802020Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 绑定银行卡
 * @author: lei 
 * @since: 2018年9月11日 下午5:36:09 
 * @history:
 */
public class XN802020 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802020Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardAO.addBankcard(req);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802020Req.class);
        StringValidater.validateBlank(req.getSystemCode(),
            req.getBankcardNumber(), req.getBankCode(), req.getBankName(),
            req.getUserId(), req.getRealName(), req.getType());

    }
}
