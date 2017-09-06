/**
 * @Title XN623070.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 下午4:31:31 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IRenewalAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Renewal;
import com.cdkj.ylq.dto.req.XN623090Req;
import com.cdkj.ylq.enums.ERenewalStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 根据借款订单号查询续期记录
 * @author: haiqingzheng 
 * @since: 2017年8月16日 下午4:31:31 
 * @history:
 */
public class XN623090 extends AProcessor {

    private IRenewalAO renewalAO = SpringContextHolder
        .getBean(IRenewalAO.class);

    private XN623090Req req = null;

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doBusiness()
     */
    @Override
    public Object doBusiness() throws BizException {
        Renewal condition = new Renewal();
        condition.setBorrowCode(req.getBorrowCode());
        condition.setStatus(ERenewalStatus.PAY_YES.getCode());
        condition.setOrder("cur_no", "asc");
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return renewalAO.queryRenewalPage(start, limit, condition);
    }

    /** 
     * @see com.cdkj.ylq.api.IProcessor#doCheck(java.lang.String)
     */
    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623090Req.class);
        StringValidater.validateBlank(req.getBorrowCode(), req.getStart(),
            req.getLimit());
    }

}
