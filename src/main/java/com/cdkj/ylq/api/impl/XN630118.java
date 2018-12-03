/**
 * @Title XN630118.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月3日 下午3:57:20 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBusinessManAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630118Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 详情查用户
 * @author: taojian 
 * @since: 2018年12月3日 下午3:57:20 
 * @history:
 */
public class XN630118 extends AProcessor {

    private IBusinessManAO businessManAO = SpringContextHolder
        .getBean(IBusinessManAO.class);

    private XN630118Req req;

    @Override
    public Object doBusiness() throws BizException {
        return businessManAO.getBusinessManByCompanyCode(req.getCompanyCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630118Req.class);
        ObjValidater.validateReq(req);
    }

}
