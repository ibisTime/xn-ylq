package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBankcardAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN802027Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 详情查询银行卡
 * @author: lei 
 * @since: 2018年9月11日 下午5:39:49 
 * @history:
 */
public class XN802027 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802027Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return bankCardAO.getBankcard(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802027Req.class);
        StringValidater.validateBlank(req.getCode());
    }

}
