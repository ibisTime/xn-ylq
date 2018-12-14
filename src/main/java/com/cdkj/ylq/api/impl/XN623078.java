/**
 * @Title XN623078.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月14日 下午4:57:34 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBorrowOrderAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623078Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 归档
 * @author: taojian 
 * @since: 2018年12月14日 下午4:57:34 
 * @history:
 */
public class XN623078 extends AProcessor {

    private IBorrowOrderAO borrowOrderAO = SpringContextHolder
        .getBean(IBorrowOrderAO.class);

    private XN623078Req req;

    @Override
    public Object doBusiness() throws BizException {
        borrowOrderAO.archive(req.getCode(), req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623078Req.class);
        ObjValidater.validateReq(req);
    }

}
