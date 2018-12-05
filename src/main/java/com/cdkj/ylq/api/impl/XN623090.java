/**
 * @Title XN623090.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月4日 下午1:29:29 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBorrowOrderAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN623090Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 查询即将到期借款（非分期差两天到期）
 * @author: taojian 
 * @since: 2018年12月4日 下午1:29:29 
 * @history:
 */
public class XN623090 extends AProcessor {

    private IBorrowOrderAO borrowOrderAO = SpringContextHolder
        .getBean(IBorrowOrderAO.class);

    private XN623090Req req;

    @Override
    public Object doBusiness() throws BizException {
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return borrowOrderAO.queryNearlyOrder(start, limit,
            req.getCompanyCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623090Req.class);
        ObjValidater.validateReq(req);
    }

}
