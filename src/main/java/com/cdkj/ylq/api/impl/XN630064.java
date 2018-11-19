package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICompanyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630064Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 完善公司信息
 * @author: xieyj 
 * @since: 2018年10月4日 下午1:47:59 
 * @history:
 */
public class XN630064 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN630064Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        companyAO.completeCompanyOwner(req.getUserId(),
            req.getBussinessLicense(), req.getCertificateTemplate(),
            req.getContractTemplate());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630064Req.class);
        ObjValidater.validateReq(req);
    }
}
