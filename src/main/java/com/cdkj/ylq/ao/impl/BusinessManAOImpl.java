package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IBusinessManAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.IChargeBO;
import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.ICouponBO;
import com.cdkj.ylq.bo.IJourBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.ISYSDictBO;
import com.cdkj.ylq.bo.ISYSMenuBO;
import com.cdkj.ylq.bo.ISYSMenuRoleBO;
import com.cdkj.ylq.bo.ISYSRoleBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IStagingRuleBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.MD5Util;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.dao.ISYSMenuDAO;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.domain.Company;
import com.cdkj.ylq.domain.Coupon;
import com.cdkj.ylq.domain.Jour;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.SYSConfig;
import com.cdkj.ylq.domain.SYSDict;
import com.cdkj.ylq.domain.SYSMenu;
import com.cdkj.ylq.domain.SYSMenuRole;
import com.cdkj.ylq.domain.SYSRole;
import com.cdkj.ylq.domain.StagingRule;
import com.cdkj.ylq.dto.req.XN630100Req;
import com.cdkj.ylq.dto.res.XN630101Res;
import com.cdkj.ylq.enums.EAccountType;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ECurrency;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EJourBizTypeBoss;
import com.cdkj.ylq.enums.EMenuCode;
import com.cdkj.ylq.enums.EUserStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

//CHECK ��鲢��ע�� 
@Service
public class BusinessManAOImpl implements IBusinessManAO {

    @Autowired
    private IBusinessManBO businessManBO;

    @Autowired
    private ICouponBO couponBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IProductBO productBO;

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
    private IStagingRuleBO stagingRuleBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IChargeBO chargeBO;

    @Override
    public Paginable<BusinessMan> queryBusinessManPage(int start, int limit,
            BusinessMan condition) {
        Paginable<BusinessMan> page = businessManBO.getPaginable(start, limit,
            condition);
        for (BusinessMan man : page.getList()) {
            BusinessMan boss = businessManBO.getBusinessManByCompanyCode(man
                .getCompanyCode());
            man.setAccount(accountBO.getAccountByUser(boss.getUserId(),
                ECurrency.CNY.getCode()));
            Company company = companyBO.getCompany(man.getCompanyCode());
            man.setCompany(company);
            man.setOutAmount(outAmount(man));
        }
        return page;
    }

    private BigDecimal outAmount(BusinessMan man) {
        Jour condition = new Jour();
        condition.setUserId(man.getUserId());
        condition.setBizType(EJourBizTypeBoss.API.getCode());

        return jourBO.getTotalAmount(condition).negate();
    }

    @Override
    public List<BusinessMan> queryBusinessManList(BusinessMan condition) {
        List<BusinessMan> list = businessManBO.queryBusinessManList(condition);
        for (BusinessMan businessMan : list) {
            BusinessMan boss = businessManBO
                .getBusinessManByCompanyCode(businessMan.getCompanyCode());
            businessMan.setAccount(accountBO.getAccountByUser(boss.getUserId(),
                ECurrency.CNY.getCode()));
        }
        return list;
    }

    @Override
    public BusinessMan getBusinessMan(String userId) {
        BusinessMan man = businessManBO.getBusinessMan(userId);
        BusinessMan boss = businessManBO.getBusinessManByCompanyCode(man
            .getCompanyCode());
        man.setAccount(accountBO.getAccountByUser(boss.getUserId(),
            ECurrency.CNY.getCode()));
        man.setOutAmount(outAmount(man));
        return man;
    }

    @Override
    @Transactional
    public String addBusinessMan(XN630100Req req) {
        // 公司编号
        String companyCode = OrderNoGenerater.generateM(EGeneratePrefix.GS
            .getCode());
        // 分配菜单、角色、权限、数据字典、系统参数
        // 角色
        SYSRole role = new SYSRole();
        role.setName("借款商");
        role.setUpdater("admin");
        role.setRemark("新增客户角色");
        role.setCompanyCode(companyCode);
        String roleCode = sysRoleBO.saveSYSRole(role);
        // 手机检验
        businessManBO.isMobileExist(req.getMobile());
        // 登录名检验
        if (businessManBO.isLoginNameExist(req.getLoginName())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "登录名已存在");
        }
        // 落地数据
        req.setIsAdmin(EBoolean.YES.getCode());
        req.setRoleCode(roleCode);
        String userId = businessManBO.saveBusinessMan(req);
        // 开设公司
        companyBO.saveCompany(userId, companyCode, req.getAppName(),
            req.getLogo());
        // 分配账户
        Account account = accountBO.distributeAccount(userId,
            EAccountType.BUSINESS, ECurrency.CNY.getCode());

        // 菜单
        List<SYSMenu> modelMenus = sysMenuBO.queryModelMenus();
        List<SYSMenu> toRemoveList = new ArrayList<SYSMenu>();
        // 借条模块权限
        if (EBoolean.NO.getCode().equals(req.getIsJt())) {
            toRemoveList.addAll(sysMenuBO.queryMenuList(EMenuCode.borrow
                .getCode()));
        }
        // 风控模块权限
        if (EBoolean.NO.getCode().equals(req.getIsFk())) {
            toRemoveList.addAll(sysMenuBO.queryMenuList(EMenuCode.risk
                .getCode()));
        }
        // 导流模块权限
        if (EBoolean.NO.getCode().equals(req.getIsDl())) {
            toRemoveList
                .addAll(sysMenuBO.queryMenuList(EMenuCode.way.getCode()));
        }
        if (!toRemoveList.isEmpty()) {
            modelMenus.removeAll(toRemoveList);
        }
        for (SYSMenu sysMenu : modelMenus) {
            StringBuilder menuCode = new StringBuilder(sysMenu.getCode())
                .append(companyCode);
            sysMenu.setCompanyCode(companyCode);
            sysMenu.setCode(menuCode.toString());
            StringBuilder parentCode = new StringBuilder(
                sysMenu.getParentCode()).append(companyCode);
            sysMenu.setParentCode(parentCode.toString());
            sysMenuDAO.insert(sysMenu);
            // 权限
            SYSMenuRole menuRole = new SYSMenuRole();
            menuRole.setRoleCode(roleCode);
            menuRole.setMenuCode(menuCode.toString());
            menuRole.setUpdater(req.getLoginName());
            menuRole.setCompanyCode(companyCode);
            sysMenuRoleBO.saveSYSMenuRole(menuRole);
        }
        // 系统参数
        List<SYSConfig> configList = sysConfigBO.queryModelConfigs();
        for (SYSConfig sysConfig : configList) {
            sysConfigBO.saveConfig(sysConfig.getType(), sysConfig.getCkey(),
                sysConfig.getCvalue(), sysConfig.getUpdater(),
                sysConfig.getRemark(), companyCode);
        }
        // 数据字典
        List<SYSDict> dictList = sysDictBO.queryDictList();
        for (SYSDict sysDict : dictList) {
            sysDictBO.saveDict(sysDict.getType(), sysDict.getParentKey(),
                sysDict.getDkey(), sysDict.getDvalue(), sysDict.getUpdater(),
                sysDict.getRemark(), companyCode);
        }
        // 优惠券规则分配
        List<Coupon> coupons = couponBO.queryModelCoupons();
        for (Coupon coupon : coupons) {
            coupon.setCompanyCode(companyCode);
            couponBO.saveCoupon(coupon);
        }
        // 分期规则
        List<StagingRule> rules = stagingRuleBO.getModelRules();
        for (StagingRule stagingRule : rules) {
            stagingRuleBO.saveStagingRule(stagingRule.getCount(),
                stagingRule.getCycle(), stagingRule.getRate(),
                stagingRule.getOrderNo(), companyCode);
        }
        // 产品
        List<Product> products = productBO.getModelProducts();
        for (Product product : products) {
            String code = OrderNoGenerater.generateM("CP");
            product.setCompanyCode(companyCode);
            product.setCode(code);
            productBO.saveProduct(product);
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
        BusinessMan businessMan = dataList.get(0);
        BusinessMan boss = businessManBO.getBusinessBoss(businessMan
            .getCompanyCode());
        if (!EUserStatus.NORMAL.getCode().equals(boss.getStatus())) {
            throw new BizException("xn805050", "该公司"
                    + EUserStatus.getMap().get(businessMan.getStatus())
                        .getValue() + "，请联系工作人员");
        }
        if (!EUserStatus.NORMAL.getCode().equals(businessMan.getStatus())) {
            throw new BizException("xn805050", "该账号"
                    + EUserStatus.getMap().get(businessMan.getStatus())
                        .getValue() + "，请联系工作人员");
        }
        String userId = businessMan.getUserId();
        String companyCode = businessMan.getCompanyCode();
        String rootMenuCode = sysMenuBO.getRootMenu(companyCode).getCode();
        Company company = companyBO.getCompany(companyCode);
        // 组装出参
        return new XN630101Res(userId, companyCode, rootMenuCode,
            boss.getIsJt(), boss.getIsFk(), boss.getIsDl(), company.getName(),
            company.getLogo());
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

    @Override
    public BusinessMan getBusinessManByCompanyCode(String companyCode) {
        BusinessMan man = businessManBO
            .getBusinessManByCompanyCode(companyCode);
        man.setCompany(companyBO.getCompany(companyCode));
        BusinessMan boss = businessManBO.getBusinessManByCompanyCode(man
            .getCompanyCode());
        man.setAccount(accountBO.getAccountByUser(boss.getUserId(),
            ECurrency.CNY.getCode()));
        man.setOutAmount(outAmount(man));
        return man;
    }
}
