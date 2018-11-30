/**
 * @Title XN630305.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午7:35:12 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICompanyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Company;
import com.cdkj.ylq.dto.req.XN630305Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 公司分页查询
 * @author: tao 
 * @since: 2018年8月17日 下午7:35:12 
 * @history:
 */
public class XN630305 extends AProcessor {

    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN630305Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        Company condition = new Company();
        condition.setName(req.getName());
        condition.setUpdater(req.getUpdater());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return companyAO.queryCompanyPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {

        req = JsonUtil.json2Bean(inputparams, XN630305Req.class);
        ObjValidater.validateReq(req);
    }

}
