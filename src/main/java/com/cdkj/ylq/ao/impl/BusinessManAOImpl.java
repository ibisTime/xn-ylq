package com.cdkj.ylq.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IBusinessManAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.ISYSRoleBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.MD5Util;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.dto.req.XN630100Req;
import com.cdkj.ylq.dto.res.XN630101Res;
import com.cdkj.ylq.enums.EAccountType;
import com.cdkj.ylq.enums.ECurrency;
import com.cdkj.ylq.exception.BizException;

//CHECK ��鲢��ע�� 
@Service
public class BusinessManAOImpl implements IBusinessManAO {

    @Autowired
    private IBusinessManBO businessManBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ISYSRoleBO sysRoleBO;

    @Override
    public Paginable<BusinessMan> queryBusinessManPage(int start, int limit,
            BusinessMan condition) {
        return businessManBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<BusinessMan> queryBusinessManList(BusinessMan condition) {
        return businessManBO.queryBusinessManList(condition);
    }

    @Override
    public BusinessMan getBusinessMan(String userId) {
        return businessManBO.getBusinessMan(userId);
    }

    @Override
    @Transactional
    public String addBusinessMan(XN630100Req req) {
        // 手机检验
        businessManBO.isMobileExist(req.getMobile());
        // 落地数据
        String userId = businessManBO.saveBusinessMan(req);
        // 开设公司
        companyBO.saveCompany(userId);
        // 分配账户
        accountBO.distributeAccount(userId, EAccountType.BUSINESS,
            ECurrency.CNY.getCode());
        // TODO
        // 分配菜单
        return userId;
    }

    @Override
    public XN630101Res doLogin(String loginName, String loginPwd) {
        BusinessMan condition = new BusinessMan();
        condition.setLoginName(loginName);
        if (businessManBO.getTotalCount(condition) < 1) {
            throw new BizException("xn0000", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<BusinessMan> dataList = businessManBO
            .queryBusinessManList(condition);
        if (dataList.isEmpty()) {
            throw new BizException("xn0000", "密码不正确");
        }

        String userId = dataList.get(0).getUserId();
        String companyCode = dataList.get(0).getCompanyCode();
        return new XN630101Res(userId, companyCode);
    }

    @Override
    public void editMobile(String userId, String newMobile, String smsCaptcha,
            String remark) {
        BusinessMan data = businessManBO.getBusinessMan(userId);
        // 验证码检验
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "630102");
        businessManBO.refreshMobile(data, newMobile);
    }

    @Override
    public void editLoginPwd(String mobile, String newLoginPwd,
            String smsCaptcha) {
        BusinessMan data = businessManBO.getBusinessManByMobile(mobile);
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "630102");
        businessManBO.refreshLoginPwd(data, newLoginPwd);
    }

    @Override
    public void resetLoginPwd(String userId, String newLoginPwd) {
        BusinessMan data = businessManBO.getBusinessMan(userId);
        businessManBO.refreshLoginPwd(data, newLoginPwd);
    }

    @Override
    public void editStatus(String userId, String updater, String remark) {
        BusinessMan data = businessManBO.getBusinessMan(userId);
        businessManBO.refreshStatus(data, updater, remark);
    }

    @Override
    public void editRole(String userId, String roleCode, String updater,
            String remark) {
        BusinessMan data = businessManBO.getBusinessMan(userId);
        if (null == sysRoleBO.getSYSRole(roleCode)) {
            throw new BizException("xn0000", "角色不存在");
        }
        businessManBO.refershRole(data, roleCode, updater, remark);

    }

    @Override
    public void editPhoto(String userId, String photo) {
        BusinessMan data = businessManBO.getBusinessMan(userId);
        businessManBO.refershPhoto(data, photo);
    }
}
