/**
 * @Title XN623150.java 
 * @Package com.cdkj.ylq.api.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月20日 下午3:54:52 
 * @version V1.0   
 */
package com.cdkj.ylq.api.impl;

import com.cdkj.ylq.ao.IWayAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.Way;
import com.cdkj.ylq.dto.req.XN623155Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/** 
 * 列表查渠道
 * @author: taojian 
 * @since: 2018年11月20日 下午3:54:52 
 * @history:
 */
public class XN623155 extends AProcessor {

    private IWayAO wayAO = SpringContextHolder.getBean(IWayAO.class);

    private XN623155Req req;

    @Override
    public Object doBusiness() throws BizException {
        Way condition = new Way();
        condition.setName(req.getName());
        condition.setCompanyCode(req.getCompanyCode());
        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());
        return wayAO.queryWayPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN623155Req.class);
        ObjValidater.validateReq(req);
    }

}
