/**
 * @Title XN623200.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2019年1月14日 上午11:41:05 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IWayerAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623200Req;
import com.cdkj.ylq.dto.res.PKCodeRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 新增渠道商
 * @author: taojian 
 * @since: 2019年1月14日 上午11:41:05 
 * @history:
 */
public class XN623200 extends AProcessor {

    private IWayerAO wayerAO = SpringContextHolder.getBean(IWayerAO.class);

    private XN623200Req req;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(wayerAO.addWayer(req.getName(),
            req.getLoginName(), req.getLoginPwd(), req.getUpdater(),
            req.getRemark(), req.getCompanyCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623200Req.class);
        ObjValidater.validateReq(req);
    }

}
