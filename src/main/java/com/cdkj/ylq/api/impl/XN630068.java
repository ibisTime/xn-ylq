package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICompanyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN630068Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 查询用户公司信息
 * @author: silver 
 * @since: Oct 8, 2018 9:28:57 PM 
 * @history:
 */
public class XN630068 extends AProcessor {

    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN630068Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return companyAO.getCompanyByUser(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630068Req.class);
        StringValidater.validateBlank(req.getUserId());
    }

}
