package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBankcardAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.dto.req.XN802026Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 列表查询银行卡
 * @author: lei 
 * @since: 2018年9月11日 下午5:38:36 
 * @history:
 */
public class XN802026 extends AProcessor {
    private IBankcardAO bankCardAO = SpringContextHolder
        .getBean(IBankcardAO.class);

    private XN802026Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Bankcard condition = new Bankcard();
        condition.setSystemCode(req.getSystemCode());
        condition.setUserId(req.getUserId());
        condition.setBankName(req.getBankName());
        condition.setBankcardNumber(req.getBankcardNumber());
        condition.setType(req.getType());
        condition.setStatus(req.getStatus());
        return bankCardAO.queryBankcardList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN802026Req.class);
        StringValidater.validateBlank(req.getSystemCode());
    }

}
