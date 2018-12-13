/**
 * @Title XN623083.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月12日 下午1:20:15 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBorrowOrderAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623083Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * @author: taojian 
 * @since: 2018年12月12日 下午1:20:15 
 * @history:
 */
public class XN623083 extends AProcessor {

    private IBorrowOrderAO borrowOrderAO = SpringContextHolder
        .getBean(IBorrowOrderAO.class);

    private XN623083Req req;

    @Override
    public Object doBusiness() throws BizException {
        borrowOrderAO.repayWarning(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623083Req.class);
        ObjValidater.validateReq(req);
    }

}
