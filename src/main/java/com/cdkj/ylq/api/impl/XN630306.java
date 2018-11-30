package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICompanyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.domain.Company;
import com.cdkj.ylq.dto.req.XN630306Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 列表查询公司
 * @author: tao 
 * @since: 2018年8月17日 下午7:43:31 
 * @history:
 */
public class XN630306 extends AProcessor {

    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN630306Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Company condition = new Company();
        condition.setName(req.getName());
        condition.setUpdater(req.getUpdater());
        return companyAO.queryCompanyList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630306Req.class);
        ObjValidater.validateReq(req);

    }

}
