/**
 * @Title XN630054.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author dl  
 * @date 2018年8月23日 下午4:00:04 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630054Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 根据旧密码改新密码
 * @author: dl 
 * @since: 2018年8月23日 下午4:00:04 
 * @history:
 */
public class XN630054 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN630054Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.editPwd(req.getUserId(), req.getOldLoginPwd(),
            req.getNewLoginPwd());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630054Req.class);
        ObjValidater.validateReq(req);
    }

}
