/**
 * @Title XN623153.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月13日 下午2:01:42 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IWayAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623153Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 渠道点击量加一
 * @author: taojian 
 * @since: 2018年12月13日 下午2:01:42 
 * @history:
 */
public class XN623153 extends AProcessor {

    private IWayAO wayAO = SpringContextHolder.getBean(IWayAO.class);

    private XN623153Req req;

    @Override
    public Object doBusiness() throws BizException {
        wayAO.point(req.getCode());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623153Req.class);
        ObjValidater.validateReq(req);
    }

}
