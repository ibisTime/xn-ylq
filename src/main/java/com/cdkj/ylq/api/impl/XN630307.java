/**
 * @Title XN630307.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月17日 下午7:46:04 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICompanyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630307Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 详情查询
 * @author: tao 
 * @since: 2018年8月17日 下午7:46:04 
 * @history:
 */
public class XN630307 extends AProcessor {
    private ICompanyAO companyAO = SpringContextHolder
        .getBean(ICompanyAO.class);

    private XN630307Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        return companyAO.getCompany(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630307Req.class);
        ObjValidater.validateReq(req);

    }

}
