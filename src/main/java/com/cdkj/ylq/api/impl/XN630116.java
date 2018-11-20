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
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.dto.req.XN630116Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 列表查
 * @author: taojian 
 * @since: 2018年11月20日 下午9:38:06 
 * @history:
 */
public class XN630116 extends AProcessor {

    private IBusinessManAO businessManAO = SpringContextHolder
        .getBean(IBusinessManAO.class);

    private XN630116Req req;

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
        return businessManAO.queryBusinessManList(condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630116Req.class);
        ObjValidater.validateReq(req);
    }

}
