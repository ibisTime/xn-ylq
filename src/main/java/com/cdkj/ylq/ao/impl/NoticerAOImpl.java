package com.cdkj.ylq.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.INoticerAO;
import com.cdkj.ylq.bo.INoticerBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.PhoneUtil;
import com.cdkj.ylq.domain.Noticer;
import com.cdkj.ylq.dto.req.XN623160Req;
import com.cdkj.ylq.dto.req.XN623162Req;
import com.cdkj.ylq.exception.BizException;

//CHECK ��鲢��ע�� 
@Service
public class NoticerAOImpl implements INoticerAO {

    @Autowired
    private INoticerBO noticerBO;

    @Override
    public String addNoticer(XN623160Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        return noticerBO.saveNoticer(req);
    }

    @Override
    public int editNoticer(XN623162Req req) {
        PhoneUtil.checkMobile(req.getMobile());
        Noticer data = noticerBO.getNoticer(req.getCode());
        return noticerBO.refreshNoticer(data, req);
    }

    @Override
    public int dropNoticer(String code) {
        if (!noticerBO.isNoticerExist(code)) {
            throw new BizException("xn0000", "通知人不存在");
        }
        return noticerBO.removeNoticer(code);
    }

    @Override
    public Paginable<Noticer> queryNoticerPage(int start, int limit,
            Noticer condition) {
        return noticerBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Noticer> queryNoticerList(Noticer condition) {
        return noticerBO.queryNoticerList(condition);
    }

    @Override
    public Noticer getNoticer(String code) {
        return noticerBO.getNoticer(code);
    }
}
