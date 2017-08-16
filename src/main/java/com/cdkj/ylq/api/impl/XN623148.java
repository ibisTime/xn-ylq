/**
 * @Title XN623130.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 下午2:03:38 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IUserCouponAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.UserCoupon;
import com.cdkj.ylq.dto.req.XN623148Req;
import com.cdkj.ylq.enums.EUserCouponStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 列表查询可使用的优惠券
 * @author: haiqingzheng 
 * @since: 2017年8月16日 下午2:03:38 
 * @history:
 */
public class XN623148 extends AProcessor {
    private IUserCouponAO userCouponAO = SpringContextHolder
        .getBean(IUserCouponAO.class);

    private XN623148Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        UserCoupon condition = new UserCoupon();
        condition.setUserId(req.getUserId());
        condition.setStatus(EUserCouponStatus.TO_USE.getCode());
        String orderColumn = req.getOrderColumn();
        if (StringUtils.isBlank(orderColumn)) {
            orderColumn = IUserCouponAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(orderColumn, req.getOrderDir());
        return userCouponAO.queryCouponList(condition, req.getProductCode());
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623148Req.class);
        StringValidater.validateBlank(req.getUserId(), req.getProductCode());
    }

}
