package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN630052Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 *  重置手机 （系统用户）
 * @author: clockorange 
 * @since: Jul 17, 2018 11:54:25 AM 
 * @history:
 */

public class XN630052 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN630052Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        userAO.doResetMoblie(req.getUserId(), req.getRemark(),
            req.getNewMobile(), req.getSmsCaptcha());
        return new BooleanRes(true);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630052Req.class);
        ObjValidater.validateReq(req);
    }

}
