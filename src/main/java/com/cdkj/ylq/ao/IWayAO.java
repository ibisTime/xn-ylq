package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Way;

public interface IWayAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addWay(String name, String companyCode, String userId,
            String remark);

    public int dropWay(String code);

    public int editWay(String code, String name, String updater, String remark);

    public Paginable<Way> queryWayPage(int start, int limit, Way condition);

    public List<Way> queryWayList(Way condition);

    public Way getWay(String code);

    public void point(String code, String type);

    public void pointOff(String code);

    public void loginOff(String code);

}
