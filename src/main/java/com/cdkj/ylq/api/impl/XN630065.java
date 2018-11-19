package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.ObjValidater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.SYSUser;
import com.cdkj.ylq.dto.req.XN630065Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 分页查询系统用户
 * @author: clockorange 
 * @since: Aug 7, 2018 10:05:42 AM 
 * @history:
 */
public class XN630065 extends AProcessor {

    private ISYSUserAO userAO = SpringContextHolder.getBean(ISYSUserAO.class);

    private XN630065Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        SYSUser condition = new SYSUser();
        condition.setCompanyCode(req.getCompanyCode());
        condition.setMobileForQuery(req.getKeyword());
        condition.setRoleCode(req.getRoleCode());
        condition.setStatus(req.getStatus());
        condition.setUpdater(req.getUpdater());
        condition.setKind(req.getKind());
        String column = req.getOrderColumn();
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getDateStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(req.getDateEnd(),
            true));
        if (StringUtils.isBlank(column)) {
            column = ISYSUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());

        int start = StringValidater.toInteger(req.getStart());
        int limit = StringValidater.toInteger(req.getLimit());

        return userAO.querySYSUserPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN630065Req.class);
        ObjValidater.validateReq(req);
    }

}
