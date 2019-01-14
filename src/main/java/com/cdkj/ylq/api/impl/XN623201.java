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
import com.cdkj.ylq.dto.req.XN623201Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 渠道商登陆
 * @author: taojian 
 * @since: 2019年1月14日 上午11:41:05 
 * @history:
 */
public class XN623201 extends AProcessor {

    private IWayerAO wayerAO = SpringContextHolder.getBean(IWayerAO.class);

    private XN623201Req req;

    @Override
    public Object doBusiness() throws BizException {
        return wayerAO.doLogin(req.getLoginName(), req.getLoginPwd());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623201Req.class);
        ObjValidater.validateReq(req);
    }

}
