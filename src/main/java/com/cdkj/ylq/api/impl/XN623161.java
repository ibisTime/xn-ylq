/**
 * @Title XN623160.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月20日 下午10:46:27 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.INoticerAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623161Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * @author: taojian 
 * @since: 2018年11月20日 下午10:46:27 
 * @history:
 */
public class XN623161 extends AProcessor {

    private INoticerAO noticerAO = SpringContextHolder
        .getBean(INoticerAO.class);

    private XN623161Req req;

    @Override
    public Object doBusiness() throws BizException {
        return noticerAO.dropNoticer(req.getCode());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623161Req.class);
        ObjValidater.validateReq(req);
    }

}
