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
import com.cdkj.ylq.domain.RepayCard;
import com.cdkj.ylq.dto.req.XN623217Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * @author: taojian 
 * @since: 2019年1月15日 上午11:39:58 
 * @history:
 */
public class XN623217 extends AProcessor {

    private IRepayCardAO repayCardAO = SpringContextHolder
        .getBean(IRepayCardAO.class);

    private XN623217Req req;

    @Override
    public Object doBusiness() throws BizException {
        RepayCard condition = new RepayCard();
        condition.setBankCode(req.getBankCode());
        condition.setSubbranch(req.getSubbranch());
        condition.setOwnerName(req.getOwnerName());
        condition.setStatus(req.getStatus());
        condition.setCompanyCode(req.getCompanyCode());
        return repayCardAO.queryRepayCardList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623217Req.class);
        ObjValidater.validateReq(req);
    }

}
