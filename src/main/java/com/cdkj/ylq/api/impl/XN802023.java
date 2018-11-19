package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBankcardAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN802023Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 修改银行卡(验证交易密码)
 * @author: xieyj 
 * @since: 2017年1月18日 上午11:08:40 
 * @history:
 */
public class XN802023 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802023Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bankCardAO.editBankcard(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802023Req.class);
        StringValidater.validateBlank(req.getCode(), req.getBankcardNumber(),
            req.getBankCode(), req.getBankName(), req.getTradePwd());

    }
}
