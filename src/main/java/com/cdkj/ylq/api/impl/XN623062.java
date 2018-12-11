/**
 * @Title XN623062.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月11日 上午11:10:16 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623062Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 定位认证
 * @author: taojian 
 * @since: 2018年12月11日 上午11:10:16 
 * @history:
 */
public class XN623062 extends AProcessor {

    private ICertificationAO certificationAO = SpringContextHolder
        .getBean(ICertificationAO.class);

    private XN623062Req req;

    @Override
    public Object doBusiness() throws BizException {
        certificationAO.submitLocation(req.getUserId(), req.getProvince(),
            req.getCity(), req.getArea(), req.getAddress());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623062Req.class);
        ObjValidater.validateReq(req);
    }

}
