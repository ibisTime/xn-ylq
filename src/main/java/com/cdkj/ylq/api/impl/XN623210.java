/**
 * @Title XN623210.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2019年1月15日 上午11:39:58 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IRepayCardAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.dto.req.XN623210Req;
import com.cdkj.ylq.dto.res.PKCodeRes;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 新增收款账号
 * @author: taojian 
 * @since: 2019年1月15日 上午11:39:58 
 * @history:
 */
public class XN623210 extends AProcessor {

    private IRepayCardAO repayCardAO = SpringContextHolder
        .getBean(IRepayCardAO.class);

    private XN623210Req req;

    @Override
    public Object doBusiness() throws BizException {
        return new PKCodeRes(repayCardAO.addRepayCard(req.getPict(),
            req.getBankcardNumber(), req.getBankCode(), req.getBankName(),
            req.getSubbranch(), req.getOwnerName(), req.getRemark(),
            req.getCompanyCode()));
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623210Req.class);
        ObjValidater.validateReq(req);
    }

}
