/**
 * @Title XN623060.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月7日 下午4:10:13 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623060Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 个人认证查询
 * @author: taojian 
 * @since: 2018年12月7日 下午4:10:13 
 * @history:
 */
public class XN623060 extends AProcessor {

    private ICertificationAO certificationAO = SpringContextHolder
        .getBean(ICertificationAO.class);

    private XN623060Req req;

    @Override
    public Object doBusiness() throws BizException {
        return certificationAO.getCertification(req.getUserId(), req.getKey());
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623060Req.class);
        ObjValidater.validateReq(req);
    }

}
