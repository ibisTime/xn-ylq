/**
 * @Title XN623800.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月21日 下午9:19:46 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.ICouponConditionAO;
import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.PhoneUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805041Req;
import com.cdkj.ylq.dto.res.XN805041Res;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月21日 下午9:19:46 
 * @history:
 */
public class XN623800 extends AProcessor {

    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private ICouponConditionAO couponConditionAO = SpringContextHolder
        .getBean(ICouponConditionAO.class);

    private XN805041Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        XN805041Res res = userAO.doRegister(req.getMobile(), req.getLoginPwd(),
            req.getUserReferee(), req.getUserRefereeKind(),
            req.getSmsCaptcha(), req.getKind(), req.getIsRegHx(),
            req.getProvince(), req.getCity(), req.getArea(), req.getAddress(),
            req.getCompanyCode(), req.getSystemCode());
        return res;
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805041Req.class);
        StringValidater.validateBlank(req.getMobile(), req.getLoginPwd(),
            req.getSmsCaptcha(), req.getKind(), req.getCompanyCode(),
            req.getSystemCode());
        PhoneUtil.checkMobile(req.getMobile());// 判断格式
    }

}
