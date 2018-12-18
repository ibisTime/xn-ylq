/**
 * @Title XN630104.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年12月18日 下午12:42:23 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBusinessManAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630104Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 修改密码
 * @author: taojian 
 * @since: 2018年12月18日 下午12:42:23 
 * @history:
 */
public class XN630104 extends AProcessor {

    private IBusinessManAO businessManAO = SpringContextHolder
        .getBean(IBusinessManAO.class);

    private XN630104Req req;

    @Override
    public Object doBusiness() throws BizException {
        businessManAO.editPwdByOld(req.getUserId(), req.getOldPwd(),
            req.getNewPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630104Req.class);
        ObjValidater.validateReq(req);
    }

}
