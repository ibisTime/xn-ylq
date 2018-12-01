/**
 * @Title XN623033.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月1日 下午12:21:02 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IApplyAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623033Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 获取当前信用分状态
 * @author: taojian 
 * @since: 2018年12月1日 下午12:21:02 
 * @history:
 */
public class XN623033 extends AProcessor {

    private IApplyAO applyAO = SpringContextHolder.getBean(IApplyAO.class);

    private XN623033Req req;

    @Override
    public Object doBusiness() throws BizException {
        return applyAO.getRes(req.getUserId());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623033Req.class);
        ObjValidater.validateReq(req);
    }

}
