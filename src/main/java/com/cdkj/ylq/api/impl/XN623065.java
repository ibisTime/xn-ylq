/**
 * @Title XN623065.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月13日 下午5:32:07 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN623065Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 添加通讯录备注
 * @author: taojian 
 * @since: 2018年12月13日 下午5:32:07 
 * @history:
 */
public class XN623065 extends AProcessor {

    private ICertificationAO certificationAO = SpringContextHolder
        .getBean(ICertificationAO.class);

    private XN623065Req req;

    @Override
    public Object doBusiness() throws BizException {
        certificationAO.addRemark(req.getUserId(),
            StringValidater.toInteger(req.getId()), req.getRemark());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623065Req.class);
        ObjValidater.validateReq(req);
    }

}
