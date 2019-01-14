package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Way;

public interface IWayBO extends IPaginableBO<Way> {

    public boolean isWayExist(String code);

    public String saveWay(String name, String companyCode, String userId);

    public int removeWay(String code);

    public int refreshWay(Way data, String name, String updater, String remark);

    public int refreshProductPointCount(Way data, Long count);

    public int refreshRegPointCount(Way data, Long count);

    public int refreshUserCount(Way data, Long count);

    public List<Way> queryWayList(Way condition);

    public Way getWay(String code);

    public void refreshStatus(Way data);

}
