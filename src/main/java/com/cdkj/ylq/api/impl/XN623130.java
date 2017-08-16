/**
 * @Title XN623130.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 下午2:03:38 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IUserCouponAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN623130Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 优惠券发放
 * @author: haiqingzheng 
 * @since: 2017年8月16日 下午2:03:38 
 * @history:
 */
public class XN623130 extends AProcessor {
    private IUserCouponAO userCouponAO = SpringContextHolder
        .getBean(IUserCouponAO.class);

    private XN623130Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        userCouponAO.grant(req.getUserId(), req.getCouponCode(),
            req.getUpdater(), req.getRemark());
        return new BooleanRes(true);
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623130Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getCouponCode(),
            req.getUpdater());
    }

}
