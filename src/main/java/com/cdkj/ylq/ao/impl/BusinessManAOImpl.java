package com.cdkj.ylq.ao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IBusinessManAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.IChargeBO;
import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.ISYSDictBO;
import com.cdkj.ylq.bo.ISYSMenuBO;
import com.cdkj.ylq.bo.ISYSMenuRoleBO;
import com.cdkj.ylq.bo.ISYSRoleBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.MD5Util;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dao.ISYSMenuDAO;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.domain.SYSConfig;
import com.cdkj.ylq.domain.SYSDict;
import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.domain.SYSMenuRole;
import com.cdkj.ylq.domain.SYSRole;
import com.cdkj.ylq.dto.req.XN630100Req;
import com.cdkj.ylq.dto.res.XN630101Res;
import com.cdkj.ylq.enums.EAccountType;
import com.cdkj.ylq.enums.ECurrency;
import com.cdkj.ylq.enums.EJourBizTypeBoss;
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

    @Autowired
    private ISYSMenuBO sysMenuBO;

    @Autowired
    private ISYSMenuDAO sysMenuDAO;

    @Autowired
    private ISYSMenuRoleBO sysMenuRoleBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private ISYSDictBO sysDictBO;

    @Autowired
    private IChargeBO chargeBO;

    @Override
    public Paginable<BusinessMan> queryBusinessManPage(int start, int limit,
            BusinessMan condition) {
        Paginable<BusinessMan> page = businessManBO.getPaginable(start, limit,
            condition);
        for (BusinessMan man : page.getList()) {
            man.setAccount(accountBO.getAccountByUser(man.getUserId(),
                ECurrency.CNY.getCode()));
        }
        return page;
    }

    @Override
    public List<BusinessMan> queryBusinessManList(BusinessMan condition) {
        List<BusinessMan> list = businessManBO.queryBusinessManList(condition);
        for (BusinessMan businessMan : list) {
            businessMan.setAccount(accountBO.getAccountByUser(
                businessMan.getUserId(), ECurrency.CNY.getCode()));
        }
        return list;
    }

    @Override
    public BusinessMan getBusinessMan(String userId) {
        BusinessMan man = businessManBO.getBusinessMan(userId);
        man.setAccount(accountBO.getAccountByUser(man.getUserId(),
            ECurrency.CNY.getCode()));
        return man;
    }

    @Override
    @Transactional
    public String addBusinessMan(XN630100Req req) {
        // 手机检验
        businessManBO.isMobileExist(req.getMobile());
        // 落地数据
        String userId = businessManBO.saveBusinessMan(req);
        // 开设公司
        String companyCode = companyBO.saveCompany(userId);
        // 分配账户
        Account account = accountBO.distributeAccount(userId,
            EAccountType.BUSINESS, ECurrency.CNY.getCode());
        // 分配菜单、角色、权限、数据字典、系统参数
        // 角色
        SYSRole role = new SYSRole();
        role.setName("借款商");
        role.setUpdater("UCOIN201700000000000001");
        role.setRemark("新增客户角色");
        role.setCompanyCode(companyCode);
        String roleCode = sysRoleBO.saveSYSRole(role);
        // 菜单
        List<SYSMenu> modelMenus = sysMenuBO.queryModelMenus();
        for (SYSMenu sysMenu : modelMenus) {
            StringBuilder menuCode = new StringBuilder(sysMenu.getCode())
                .append(companyCode);
            sysMenu.setCompanyCode(companyCode);
            sysMenu.setCode(menuCode.toString());
            if (sysMenu.getParentCode() != null) {
                StringBuilder parentCode = new StringBuilder(
                    sysMenu.getParentCode()).append(companyCode);
                sysMenu.setParentCode(parentCode.toString());
            }
            sysMenuDAO.insert(sysMenu);
            // 权限
            SYSMenuRole menuRole = new SYSMenuRole();
            menuRole.setRoleCode(roleCode);
            menuRole.setMenuCode(menuCode.toString());
            menuRole.setUpdater("admin");
            menuRole.setCompanyCode(companyCode);
            sysMenuRoleBO.saveSYSMenuRole(menuRole);
        }
        // 系统参数
        List<SYSConfig> configList = sysConfigBO.queryModelConfigs();
        for (SYSConfig sysConfig : configList) {
            sysConfigBO.saveConfig(sysConfig.getCkey(), sysConfig.getCvalue(),
                sysConfig.getUpdater(), sysConfig.getRemark(), companyCode);
        }
        // 数据字典
        List<SYSDict> dictList = sysDictBO.queryDictList();
        for (SYSDict sysDict : dictList) {
            sysDictBO.saveDict(sysDict.getType(), sysDict.getParentKey(),
                sysDict.getDkey(), sysDict.getDvalue(), sysDict.getUpdater(),
                sysDict.getRemark(), companyCode);
        }
        // 预充值
        chargeBO.applyOrderOffline(account, EJourBizTypeBoss.CHARGE.getCode(),
            StringValidater.toBigDecimal(req.getPrecharge()), null, null,
            userId, "B", "新建账户预充值");
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
