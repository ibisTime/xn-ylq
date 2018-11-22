/**
 * @Title XN623180.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月22日 下午10:04:08 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBorrowOrderAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623180Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * @author: taojian 
 * @since: 2018年11月22日 下午10:04:08 
 * @history:
 */
public class XN623180 extends AProcessor {

    private IBorrowOrderAO borrowOrderAO = SpringContextHolder
        .getBean(IBorrowOrderAO.class);

    private XN623180Req req;

    @Override
    public Object doBusiness() throws BizException {

        return borrowOrderAO.repay(req.getOrderCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623180Req.class);
        ObjValidater.validateReq(req);
    }

}
