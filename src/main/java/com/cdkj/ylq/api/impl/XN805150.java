/**
 * @Title XN805150.java 
 * @Package com.std.user.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月19日 下午9:08:23 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN805151Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 拉白/移除
 * @author: taojian 
 * @since: 2018年11月19日 下午9:08:23 
 * @history:
 */
public class XN805150 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805151Req req;

    @Override
    public Object doBusiness() throws BizException {
        userAO.whitelist(req.getUserId());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805151Req.class);
        ObjValidater.validateReq(req);
    }

}
