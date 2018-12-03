package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.MD5Util;
import com.cdkj.ylq.common.PhoneUtil;
import com.cdkj.ylq.common.PwdUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IBusinessManDAO;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.dto.req.XN630100Req;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EBusinessManStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.exception.BizException;

//CHECK ��鲢��ע�� 
@Component
public class BusinessManBOImpl extends PaginableBOImpl<BusinessMan> implements
        IBusinessManBO {

    @Autowired
    private IBusinessManDAO businessManDAO;

    @Override
    public boolean isBusinessManExist(String userId) {
        BusinessMan condition = new BusinessMan();
        condition.setUserId(userId);
        if (businessManDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String saveBusinessMan(XN630100Req req) {
        BusinessMan data = new BusinessMan();
        String userId = OrderNoGenerater.generateM(EGeneratePrefix.BUSINESS
            .getCode());
        data.setUserId(userId);
        data.setRoleCode(req.getRoleCode());
        data.setRealName(req.getRealName());
        data.setPhoto(req.getPhoto());
        data.setMobile(req.getMobile());
        data.setLoginName(req.getLoginName());
        data.setLoginPwd(MD5Util.md5(req.getLoginPwd()));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(req
            .getLoginPwd()));
        data.setCreateDatetime(new Date());
        data.setStatus(EBusinessManStatus.NORMAL.getCode());
        data.setIsJt(req.getIsJt());
        data.setIsFk(req.getIsFk());
        data.setIsDl(req.getIsDl());
        data.setIsAdmin(req.getIsAdmin());
        businessManDAO.insert(data);
        return userId;
    }

    @Override
    public int removeBusinessMan(String userId) {
        int count = 0;
        if (StringUtils.isNotBlank(userId)) {
            BusinessMan data = new BusinessMan();
            data.setUserId(userId);
            count = businessManDAO.delete(data);
        }
        return count;
    }

    @Override
    public List<BusinessMan> queryBusinessManList(BusinessMan condition) {
        return businessManDAO.selectList(condition);
    }

    @Override
    public BusinessMan getBusinessMan(String userId) {
        BusinessMan data = null;
        if (StringUtils.isNotBlank(userId)) {
            BusinessMan condition = new BusinessMan();
            condition.setUserId(userId);
            data = businessManDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "不存在该借款商");
            }
        }
        return data;
    }

    @Override
    public void refreshMobile(BusinessMan data, String mobile) {
        data.setMobile(mobile);
        businessManDAO.updateMobile(data);
    }

    @Override
    public void refreshLoginPwd(BusinessMan data, String password) {
        data.setLoginPwd(MD5Util.md5(password));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(password));
        businessManDAO.updatePwd(data);
    }

    @Override
    public void refreshStatus(BusinessMan data, String updater, String remark) {
        if (EBusinessManStatus.Locked.getCode().equals(data.getStatus())) {
            data.setStatus(EBusinessManStatus.NORMAL.getCode());
        } else {
            data.setStatus(EBusinessManStatus.Locked.getCode());
        }
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        businessManDAO.updateStatus(data);
    }

    @Override
    public void refershRole(BusinessMan data, String roleCode, String updater,
            String remark) {
        data.setRoleCode(roleCode);
        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        businessManDAO.updateRole(data);
    }

    @Override
    public void refershPhoto(BusinessMan data, String photo) {
        data.setPhoto(photo);
        businessManDAO.updatePhoto(data);
    }

    @Override
    public void refereshCompany(String userId, String comapnyCode) {
        BusinessMan data = new BusinessMan();
        data.setUserId(userId);
        data.setCompanyCode(comapnyCode);
        businessManDAO.updateCompany(data);
    }

    @Override
    public void isMobileExist(String mobile) {
        PhoneUtil.checkMobile(mobile);
        BusinessMan condition = new BusinessMan();
        condition.setMobile(mobile);
        if (getTotalCount(condition) > 0) {
            throw new BizException("xn0000", "该手机已存在");
        }
    }

    @Override
    public BusinessMan getBusinessManByMobile(String mobile) {
        PhoneUtil.checkMobile(mobile);
        BusinessMan condition = new BusinessMan();
        condition.setMobile(mobile);
        BusinessMan data = businessManDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "手机号不存在");
        }
        return data;
    }

    @Override
    public BusinessMan getBusinessManByCompanyCode(String companyCode) {
        BusinessMan condition = new BusinessMan();
        condition.setCompanyCode(companyCode);
        condition.setIsAdmin(EBoolean.YES.getCode());
        BusinessMan data = businessManDAO.select(condition);
        if (null == data) {
            throw new BizException("xn0000", "公司编号不存在");
        }
        return data;
    }

    @Override
    public BusinessMan getBusinessBoss(String companyCode) {
        BusinessMan condition = new BusinessMan();
        condition.setCompanyCode(companyCode);
        condition.setIsAdmin(EBoolean.YES.getCode());
        BusinessMan data = businessManDAO.select(condition);
        return data;
    }

    @Override
    public boolean isLoginNameExist(String loginName) {
        BusinessMan condition = new BusinessMan();
        condition.setLoginName(loginName);
        if (getTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }
}
