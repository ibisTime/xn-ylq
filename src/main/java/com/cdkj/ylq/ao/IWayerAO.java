package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Page;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Wayer;
import com.cdkj.ylq.dto.res.XN623208Res;

public interface IWayerAO {
    static final String DEFAULT_ORDER_COLUMN = "userId";

    public String addWayer(String name, String loginName, String loginPwd,
            String updater, String remark, String companyCode);

    public int dropWayer(String code);

    public Paginable<Wayer> queryWayerPage(int start, int limit, Wayer condition);

    public List<Wayer> queryWayerList(Wayer condition);

    public Wayer getWayer(String code);

    public Wayer doLogin(String loginName, String loginPwd);

    public void editPwd(String userId, String oldPwd, String newPwd);

    public void editStatus(String userId, String updater);

    public Page<XN623208Res> getUsersOfWayer(String userId, int start,
            int limit, String dateStart, String dateEnd);

}
