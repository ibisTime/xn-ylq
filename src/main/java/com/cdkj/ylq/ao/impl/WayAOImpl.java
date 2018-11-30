package com.cdkj.ylq.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IWayAO;
import com.cdkj.ylq.bo.IWayBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Way;
import com.cdkj.ylq.exception.BizException;

@Service
public class WayAOImpl implements IWayAO {

    @Autowired
    private IWayBO wayBO;

    @Override
    public String addWay(String name, String companyCode) {
        String code = wayBO.saveWay(name, companyCode);
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
        return wayBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Way> queryWayList(Way condition) {
        return wayBO.queryWayList(condition);
    }

    @Override
    public Way getWay(String code) {
        return wayBO.getWay(code);
    }

}
