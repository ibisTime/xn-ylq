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
import com.cdkj.ylq.dto.req.XN623150Req;
import com.cdkj.ylq.dto.res.PKCodeRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 新增渠道
 * @author: taojian 
 * @since: 2018年11月20日 下午3:54:52 
 * @history:
 */
public class XN623150 extends AProcessor {

    private IWayAO wayAO = SpringContextHolder.getBean(IWayAO.class);

    private XN623150Req req;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(wayAO.addWay(req.getName(), req.getCompanyCode(),
            req.getUserId(), req.getRemark()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623150Req.class);
        ObjValidater.validateReq(req);
    }

}
