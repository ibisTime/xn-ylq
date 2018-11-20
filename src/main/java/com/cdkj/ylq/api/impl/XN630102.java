/**
 * @Title XN630100.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月20日 下午9:38:06 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBusinessManAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630102Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 自主修改手机号
 * @author: taojian 
 * @since: 2018年11月20日 下午9:38:06 
 * @history:
 */
public class XN630102 extends AProcessor {

    private IBusinessManAO businessManAO = SpringContextHolder
        .getBean(IBusinessManAO.class);

    private XN630102Req req;

    @Override
    public Object doBusiness() throws BizException {
        businessManAO.editMobile(req.getUserId(), req.getNewMobile(),
            req.getSmsCaptcha(), null);
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630102Req.class);
        ObjValidater.validateReq(req);
    }

}
