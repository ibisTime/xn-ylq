/**
 * @Title XN630100.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月20日 下午9:38:06 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBusinessManAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630109Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 修改头像
 * @author: taojian 
 * @since: 2018年11月20日 下午9:38:06 
 * @history:
 */
public class XN630109 extends AProcessor {

    private IBusinessManAO businessManAO = SpringContextHolder
        .getBean(IBusinessManAO.class);

    private XN630109Req req;

    @Override
    public Object doBusiness() throws BizException {
        businessManAO.editPhoto(req.getUserId(), req.getPhoto());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630109Req.class);
        ObjValidater.validateReq(req);
    }

}