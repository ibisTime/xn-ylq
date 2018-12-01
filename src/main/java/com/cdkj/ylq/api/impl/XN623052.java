/**
 * @Title XN623052.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月1日 下午6:52:53 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623052Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 提交个人信息
 * @author: taojian 
 * @since: 2018年12月1日 下午6:52:53 
 * @history:
 */
public class XN623052 extends AProcessor {

    private ICertificationAO certificationAO = SpringContextHolder
        .getBean(ICertificationAO.class);

    private XN623052Req req;

    @Override
    public Object doBusiness() throws BizException {
        certificationAO.submitInfoPersonal(req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623052Req.class);
        ObjValidater.validateReq(req);
    }

}
