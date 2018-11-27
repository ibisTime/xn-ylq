package com.cdkj.ylq.bo.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.INoticerBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dao.INoticerDAO;
import com.cdkj.ylq.domain.Noticer;
import com.cdkj.ylq.dto.req.XN623160Req;
import com.cdkj.ylq.dto.req.XN623162Req;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.exception.BizException;

//CHECK ��鲢��ע�� 
@Component
public class NoticerBOImpl extends PaginableBOImpl<Noticer> implements
        INoticerBO {

    @Autowired
    private INoticerDAO noticerDAO;

    @Override
    public boolean isNoticerExist(String code) {
        Noticer condition = new Noticer();
        condition.setCode(code);
        if (noticerDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveNoticer(XN623160Req req) {
        Noticer data = new Noticer();
        String code = OrderNoGenerater.generateM(EGeneratePrefix.NOTICER
            .getCode());
        data.setCode(code);
        data.setName(req.getName());
        data.setMobile(req.getMobile());
        data.setType(req.getType());
        data.setStartTime(req.getStratTime());
        data.setEndTime(req.getEndTime());
        data.setCreateDatetime(new Date());
        data.setCompanyCode(req.getCompanyCode());
        noticerDAO.insert(data);
        return code;
    }

    @Override
    public int removeNoticer(String code) {
        int count = 0;
        if (StringUtils.isNotBlank(code)) {
            Noticer data = new Noticer();
            data.setCode(code);
            count = noticerDAO.delete(data);
        }
        return count;
    }

    @Override
    public int refreshNoticer(Noticer data, XN623162Req req) {
        int count = 0;
        if (StringUtils.isNotBlank(req.getCode())) {
            data.setName(req.getName());
            data.setMobile(req.getMobile());
            data.setStartTime(req.getStratTime());
            data.setEndTime(req.getEndTime());
            data.setUpdater(req.getUpdater());
            data.setUpdateDatetime(new Date());
            data.setRemark(req.getRemark());
            count = noticerDAO.update(data);
        }
        return count;
    }

    @Override
    public List<Noticer> queryNoticerList(Noticer condition) {
        return noticerDAO.selectList(condition);
    }

    @Override
    public Noticer getNoticer(String code) {
        Noticer data = null;
        if (StringUtils.isNotBlank(code)) {
            Noticer condition = new Noticer();
            condition.setCode(code);
            data = noticerDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该通知人不存在");
            }
        }
        return data;
    }

    @Override
    public List<Noticer> queryNoticersNow(String type, String companyCode) {
        // 获取当前小时数
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        Noticer condition = new Noticer();
        condition.setType(type);
        condition.setCompanyCode(companyCode);
        List<Noticer> noticers = noticerDAO.selectList(condition);
        List<Noticer> smsList = new ArrayList<Noticer>();
        if (!noticers.isEmpty()) {
            for (Noticer noticer : noticers) {
                if (hour >= StringValidater.toInteger(noticer.getStartTime())
                        && hour < StringValidater.toInteger(noticer
                            .getEndTime())) {
                    smsList.add(noticer);
                }
            }
        }
        return smsList;
    }
}
