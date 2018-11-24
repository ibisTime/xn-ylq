/**
 * @Title XN623150.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月20日 下午3:54:52 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IWayAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623156Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 详情查渠道
 * @author: taojian 
 * @since: 2018年11月20日 下午3:54:52 
 * @history:
 */
public class XN623156 extends AProcessor {

    private IWayAO wayAO = SpringContextHolder.getBean(IWayAO.class);

    private XN623156Req req;

    @Override
    public Object doBusiness() throws BizException {
        return wayAO.getWay(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623156Req.class);
        ObjValidater.validateReq(req);
    }

}