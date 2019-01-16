package com.cdkj.ylq.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IWayAO;
import com.cdkj.ylq.bo.IWayBO;
import com.cdkj.ylq.bo.IWayerBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Way;
import com.cdkj.ylq.domain.Wayer;
import com.cdkj.ylq.enums.EUrlType;
import com.cdkj.ylq.exception.BizException;

@Service
public class WayAOImpl implements IWayAO {

    @Autowired
    private IWayBO wayBO;

    @Autowired
    private IWayerBO wayerBO;

    @Override
    @Transactional
    public String addWay(String name, String companyCode, String userId) {
        Wayer wayer = wayerBO.getWayer(userId);
        String code = wayBO.saveWay(name, companyCode, userId);
        // 渠道商链接数加一
        wayerBO.refreshUrlCount(wayer, 1L);
        return code;
    }

    @Override
    public int editWay(String code, String name, String updater, String remark) {
        Way data = wayBO.getWay(code);
        wayBO.refreshWay(data, name, updater, remark);
        return 0;
    }

    @Override
    public int dropWay(String code) {
        if (!wayBO.isWayExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return wayBO.removeWay(code);
    }

    @Override
    public Paginable<Way> queryWayPage(int start, int limit, Way condition) {
        Paginable<Way> page = wayBO.getPaginable(start, limit, condition);
        for (Way way : page.getList()) {
            Wayer wayer = wayerBO.getWayer(way.getUserId());
            way.setWayer(wayer);
        }
        return page;
    }

    @Override
    public List<Way> queryWayList(Way condition) {
        return wayBO.queryWayList(condition);
    }

    @Override
    public Way getWay(String code) {
        return wayBO.getWay(code);
    }

    @Override
    public void point(String code, String type) {
        Way way = wayBO.getWay(code);
        if (EUrlType.PRODUCT.getCode().equals(type)) {
            wayBO.refreshProductPointCount(way, Long.valueOf(1));
        } else if (EUrlType.REG.getCode().equals(type)) {
            wayBO.refreshRegPointCount(way, Long.valueOf(1));
        }

    }

    @Override
    public void loginOff(String code) {
        Way way = wayBO.getWay(code);
        wayBO.refreshStatus(way);
    }

    @Override
    public void pointOff(String code) {
        Way way = wayBO.getWay(code);
        wayBO.refreshRegPointCount(way, Long.valueOf(-1));
    }

}
