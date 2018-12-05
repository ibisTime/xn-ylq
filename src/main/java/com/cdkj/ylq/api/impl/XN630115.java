/**
 * @Title XN630100.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月20日 下午9:38:06 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IBusinessManAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.dto.req.XN630115Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 分页查
 * @author: taojian 
 * @since: 2018年11月20日 下午9:38:06 
 * @history:
 */
public class XN630115 extends AProcessor {

    private IBusinessManAO businessManAO = SpringContextHolder
        .getBean(IBusinessManAO.class);

    private XN630115Req req;

    @Override
    public Object doBusiness() throws BizException {
        BusinessMan condition = new BusinessMan();
        condition.setCompanyCode(req.getCompanyCode());
        condition.setRoleCode(req.getRoleCode());
        condition.setUpdater(req.getUpdater());
        condition.setMobileForQuery(req.getKeyword());
        condition.setRealNameForQuery(req.getKeyword());
        condition.setStatus(req.getStatus());
        condition.setIsJt(req.getIsJt());
        condition.setIsFk(req.getIsFk());
        condition.setIsDl(req.getIsDl());
        condition.setIsAdmin(req.getIsAdmin());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return businessManAO.queryBusinessManPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630115Req.class);
        ObjValidater.validateReq(req);
    }

}
