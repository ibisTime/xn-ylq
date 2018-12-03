package com.cdkj.ylq.api.impl;

import org.apache.commons.lang3.StringUtils;

import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.api.AProcessor;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.req.XN805120Req;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.ParaException;
import com.cdkj.ylq.spring.SpringContextHolder;

/**
 * 分页查询用户列表
 * @author: xieyj 
 * @since: 2017年7月16日 下午4:38:56 
 * @history:
 */
public class XN805120 extends AProcessor {
    private IUserAO userAO = SpringContextHolder.getBean(IUserAO.class);

    private XN805120Req req = null;

    @Override
    public Object doBusiness() throws BizException {
        User condition = new User();
        condition.setLoginName(req.getLoginName());
        condition.setMobileForQuery(req.getMobile());
        condition.setNickname(req.getNickname());
        condition.setUserReferee(req.getUserReferee());
        condition.setIdKind(req.getIdKind());
        condition.setIdNo(req.getIdNo());
        condition.setRealName(req.getRealName());
        condition.setStatus(req.getStatus());
        condition.setEmail(req.getEmail());
        condition.setProvince(req.getProvince());
        condition.setCity(req.getCity());
        condition.setArea(req.getArea());
        condition.setLongitude(req.getLongitude());
        condition.setLatitude(req.getLatitude());
        condition.setIsBlackList(req.getIsBlackList());
        condition.setIsWhiteList(req.getIsWhiteList());
        condition.setCreateDatetimeStart(DateUtil.getFrontDate(
            req.getCreateDatetimeStart(), false));
        condition.setCreateDatetimeEnd(DateUtil.getFrontDate(
            req.getCreateDatetimeEnd(), true));

        condition.setUpdater(req.getUpdater());
        condition.setCompanyCode(req.getCompanyCode());
        String column = req.getOrderColumn();
        if (StringUtils.isBlank(column)) {
            column = IUserAO.DEFAULT_ORDER_COLUMN;
        }
        condition.setOrder(column, req.getOrderDir());
        int start = Integer.valueOf(req.getStart());
        int limit = Integer.valueOf(req.getLimit());
        return userAO.queryUserPage(start, limit, condition);
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        req = JsonUtil.json2Bean(inputparams, XN805120Req.class);
        StringValidater.validateNumber(req.getStart(), req.getLimit());
        StringValidater.validateBlank(req.getSystemCode());
    }
}
