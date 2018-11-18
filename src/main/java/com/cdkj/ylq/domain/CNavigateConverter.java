package com.cdkj.ylq.domain;

import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dto.req.XN805800Req;
import com.cdkj.ylq.dto.req.XN805802Req;
import com.cdkj.ylq.dto.req.XN805805Req;

public class CNavigateConverter {

    // 新增导航
    public static CNavigate converter(XN805800Req req) {
        CNavigate result = new CNavigate();
        result.setName(req.getName());
        result.setType(req.getType());
        result.setUrl(req.getUrl());
        result.setPic(req.getPic());
        result.setStatus(req.getStatus());
        result.setLocation(req.getLocation());
        result.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        result.setBelong(req.getBelong());
        result.setParentCode(req.getParentCode());
        result.setRemark(req.getRemark());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        result.setSystemCode(req.getSystemCode());
        return result;
    }

    // 修改导航
    public static CNavigate converter(XN805802Req req) {
        CNavigate result = new CNavigate();
        result.setCode(req.getCode());
        result.setName(req.getName());
        result.setType(req.getType());
        result.setUrl(req.getUrl());
        result.setPic(req.getPic());
        result.setStatus(req.getStatus());
        result.setLocation(req.getLocation());
        result.setOrderNo(StringValidater.toInteger(req.getOrderNo()));
        result.setBelong(req.getBelong());
        result.setParentCode(req.getParentCode());
        result.setRemark(req.getRemark());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        return result;
    }

    // 分页查询导航
    public static CNavigate converter(XN805805Req req) {
        CNavigate result = new CNavigate();
        result.setName(req.getName());
        result.setType(req.getType());
        result.setStatus(req.getStatus());
        result.setLocation(req.getLocation());
        result.setBelong(req.getBelong());
        result.setParentCode(req.getParentCode());
        result.setContentType(req.getContentType());
        result.setCompanyCode(req.getCompanyCode());
        result.setSystemCode(req.getSystemCode());
        return result;
    }

}
