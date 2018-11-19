package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBankcardAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN802022Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 修改银行卡
 * @author: lei 
 * @since: 2018年9月11日 下午5:36:23 
 * @history:
 */
public class XN802022 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802022Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        bankCardAO.editBankcard(req);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802022Req.class);
        StringValidater.validateBlank(req.getCode(), req.getBankcardNumber(),
            req.getBankCode(), req.getBankName());

    }
}
