package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IWayerBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.MD5Util;
import com.cdkj.ylq.common.PwdUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IWayerDAO;
import com.cdkj.ylq.domain.Wayer;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EUserStatus;
import com.cdkj.ylq.exception.BizException;

@Component
public class WayerBOImpl extends PaginableBOImpl<Wayer> implements IWayerBO {

    @Autowired
    private IWayerDAO wayerDAO;

    @Override
    public boolean isWayerExist(String userId) {
        Wayer condition = new Wayer();
        condition.setUserId(userId);
        if (wayerDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveWayer(String name, String loginName, String loginPwd,
            String updater, String remark, String companyCode) {
        Wayer data = new Wayer();
        String userId = OrderNoGenerater.generateM(EGeneratePrefix.Wayer
            .getCode());
        data.setUserId(userId);
        data.setCompanyCode(companyCode);
        data.setName(name);
        data.setLoginName(loginName);
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        data.setCreateDatetime(new Date());
        data.setUserCount(Long.valueOf(0));
        data.setUrlCount(Long.valueOf(0));
        data.setStatus(EUserStatus.NORMAL.getCode());
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        wayerDAO.insert(data);
        return userId;
    }

    @Override
    public int removeWayer(String userId) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            Wayer data = new Wayer();
            data.setUserId(userId);
            count = wayerDAO.delete(data);
        }
        return count;
    }

    @Override
    public List<Wayer> queryWayerList(Wayer condition) {
        return wayerDAO.selectList(condition);
    }

    @Override
    public Wayer getWayer(String userId) {
        Wayer data = null;
        if (StringUtils.isNotBlank(userId)) {
            Wayer condition = new Wayer();
            condition.setUserId(userId);
            data = wayerDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "该渠道商不存在");
            }
        }
        return data;
    }

    @Override
    public void refreshPwd(Wayer data, String pwd) {
        data.setLoginPwd(MD5Util.md5(pwd));
        wayerDAO.updatePwd(data);
    }

    @Override
    public void refreshStatus(Wayer data, String status, String updater,
            String remark) {
        data.setStatus(status);
        data.setRemark(remark);
        data.setUpdater(updater);
        wayerDAO.updateStatus(data);
    }

    @Override
    public boolean isNameExist(String name, String companyCode) {
        Wayer condition = new Wayer();
        condition.setName(name);
        condition.setCompanyCode(companyCode);
        if (wayerDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isLoginNameExist(String loginName, String companyCode) {
        Wayer condition = new Wayer();
        condition.setLoginName(loginName);
        condition.setCompanyCode(companyCode);
        if (wayerDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }
}
