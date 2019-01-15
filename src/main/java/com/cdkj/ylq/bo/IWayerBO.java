package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Wayer;

public interface IWayerBO extends IPaginableBO<Wayer> {

    public boolean isWayerExist(String userId);

    public boolean isNameExist(String name, String companyCode);

    public boolean isLoginNameExist(String loginName, String companyCode);

    public String saveWayer(String name, String loginName, String loginPwd,
            String updater, String remark, String companyCode);

    public int removeWayer(String userId);

    public void refreshPwd(Wayer data, String pwd);

    public void refreshStatus(Wayer data, String status, String updater,
            String remark);

    public List<Wayer> queryWayerList(Wayer condition);

    public Wayer getWayer(String userId);

    public void refreshUrlCount(Wayer data, Long count);

    public void refreshUserCount(Wayer data, Long count);

}
