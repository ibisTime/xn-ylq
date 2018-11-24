package com.cdkj.ylq.ao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.ISYSUserAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.IJourBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.ISYSRoleBO;
import com.cdkj.ylq.bo.ISYSUserBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.MD5Util;
import com.cdkj.ylq.common.PhoneUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.domain.SYSRole;
import com.cdkj.ylq.domain.SYSUser;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.req.XN630050Req;
import com.cdkj.ylq.dto.res.XN629901Res;
import com.cdkj.ylq.enums.EAccountType;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.enums.EUserStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

@Service
public class SYSUserAOImpl implements ISYSUserAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private ISYSRoleBO sysRoleBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ICompanyBO companyBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IProductBO productBO;

    @Autowired
    private IUserBO userBO;

    // 新增用户（平台）
    @Override
    @Transactional
    public String addSYSUser(XN630050Req req) {
        sysUserBO.isMobileExist(req.getMobile());
        String userId = sysUserBO.doSaveSYSuser(req);
        return userId;
    }

    // 代申请

    // 用户登录
    @Override
    public String doLogin(String loginName, String loginPwd) {
        SYSUser condition = new SYSUser();
        condition.setLoginName(loginName);
        List<SYSUser> userList1 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<SYSUser> userList2 = sysUserBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        SYSUser user = userList2.get(0);

        if (EUserStatus.Li_Locked.getCode().equals(user.getStatus())
                || EUserStatus.Ren_Locked.getCode().equals(user.getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "当前用户已被锁定，请联系管理员");
        }
        return user.getUserId();
    }

    // 注销，激活其他管理员
    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        SYSUser user = sysUserBO.getSYSUserUnCheck(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        // admin 不注销
        if (SysConstants.ADMIN.equals(user.getLoginName())) {
            throw new BizException("li01004", "管理员无法注销");
        }
        String mobile = user.getMobile();
        String smsContent = "";
        EUserStatus userStatus = null;
        if (EUserStatus.NORMAL.getCode().equalsIgnoreCase(user.getStatus())) {
            smsContent = "您的账号已被管理员封禁";
            userStatus = EUserStatus.Ren_Locked;
        } else {
            smsContent = "您的账号已被管理员解封,请遵守平台相关规则";
            userStatus = EUserStatus.NORMAL;
        }
        sysUserBO.refreshStatus(userId, userStatus, updater, remark);
        if (PhoneUtil.isMobile(mobile)) {
            // 发送短信
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + smsContent, "805091", ESystemCode.YLQ.getCode());
        }

    }

    // 设置角色
    @Override
    public void doRoleSYSUser(String userId, String roleCode, String updater,
            String remark) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        SYSRole role = sysRoleBO.getSYSRole(roleCode);
        if (role == null) {
            throw new BizException("li01004", "角色不存在");
        }
        sysUserBO.refreshRole(userId, roleCode, updater, remark);
    }

    // 重置其他管理员密码
    @Override
    public void resetAdminLoginPwd(String userId, String newLoginPwd) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        sysUserBO.resetAdminLoginPwd(user, newLoginPwd);
    }

    // 重置登录密码
    @Override
    public void resetSelfPwd(String mobile, String smsCaptcha,
            String newLoginPwd) {
        // 判断手机号是否存在

        SYSUser user = sysUserBO.getUserByMobile(mobile);
        String oldPwd = user.getLoginPwd();
        if (newLoginPwd.equals(oldPwd)) {
            throw new BizException("xn000000", "新密码与原密码一致");
        }

        // 新密码验证
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "630053", null);
        sysUserBO.resetSelfPwd(user, newLoginPwd);
        // 发送短信
        smsOutBO.sendSmsOut(
            mobile,
            "尊敬的"
                    + PhoneUtil.hideMobile(mobile)
                    + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1) + "已更改登录密码"
                    + "，请妥善保管您的账户相关信息。", "631072", ESystemCode.YLQ.getCode());
    }

    @Override
    public void editPwd(String userId, String oldPwd, String newPwd) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        if (oldPwd.equals(newPwd)) {
            throw new BizException("li01006", "新登录密码不能与原有密码重复");
        }

        sysUserBO.checkLoginPwd(userId, oldPwd);

        sysUserBO.resetSelfPwd(user, newPwd);
    }

    // 修改照片
    @Override
    public void doModifyPhoto(String userId, String photo) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        sysUserBO.refreshPhoto(user, photo);
    }

    // 更换绑定手机号
    @Override
    public void doResetMoblie(String userId, String remark, String newMobile,
            String smsCaptcha) {
        SYSUser user = sysUserBO.getSYSUser(userId);
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }

        // 判断手机号是否存在
        sysUserBO.isMobileExist(newMobile);

        // 新手机号验证
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "630052", null);
        sysUserBO.resetBindMobile(user, newMobile);

        // 发送短信
        smsOutBO.sendSmsOut(
            oldMobile,
            "尊敬的"
                    + PhoneUtil.hideMobile(oldMobile)
                    + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1) + "已将手机号码改为" + newMobile
                    + "，您的登录名更改为" + newMobile + "，请妥善保管您的账户相关信息。", "631072",
            ESystemCode.YLQ.getCode());

    }

    // 分页查询
    @Override
    public Paginable<SYSUser> querySYSUserPage(int start, int limit,
            SYSUser condition) {
        if (condition.getCreateDatetimeStart() != null
                && condition.getCreateDatetimeEnd() != null
                && condition.getCreateDatetimeStart().after(
                    condition.getCreateDatetimeEnd())) {
            throw new BizException("xn00000", "开始时间不能大于结束时间");
        }
        Paginable<SYSUser> page = sysUserBO.getPaginable(start, limit,
            condition);
        return page;
    }

    // 列表查询
    public List<SYSUser> querySYSUserList(SYSUser condition) {
        List<SYSUser> list = sysUserBO.queryUserList(condition);
        for (SYSUser sysUser : list) {
            init(sysUser);
        }
        return list;
    }

    // 详细查询
    public SYSUser getSYSUser(String code) {
        SYSUser sysUser = sysUserBO.getSYSUser(code);
        init(sysUser);
        return sysUser;
    }

    public void init(SYSUser data) {
        // if (ESYSUserKind.OWNER.getCode().equals(data.getKind())) {
        //
        // // 产权方
        // long count = treeBO.getTotalCountByOwnerId(data.getUserId());
        // data.setTreeQuantity(String.valueOf(count));
        //
        // // 古树市值
        // XN630065PriceRes priceRes = productBO.getOwnerProductPrice(data
        // .getUserId());
        // data.setMaxPrice(priceRes.getMaxPrice());
        // data.setMinPrice(priceRes.getMinPrice());
        //
        // } else if (ESYSUserKind.MAINTAIN.getCode().equals(data.getKind())) {
        //
        // // 养护方
        // ApplyBindMaintain abmCondition = new ApplyBindMaintain();
        // abmCondition.setStatus(EApplyBindMaintainStatus.BIND.getCode());
        // abmCondition.setMaintainId(data.getUserId());
        // List<ApplyBindMaintain> abmList = applyBindMaintainBO
        // .queryApplyBindMaintainList(abmCondition);
        // if (CollectionUtils.isNotEmpty(abmList)) {
        // SYSUser sysUser2 = sysUserBO.getSYSUser(abmList.get(0)
        // .getOwnerId());
        // data.setOwner(sysUser2.getRealName());
        // }
        //
        // // 总收入
        // Account cnyAccount = accountBO.getAccountByUser(data.getUserId(),
        // ECurrency.CNY.getCode());
        // BigDecimal totalIncome = jourBO.getTotalAmount(
        // EJourBizTypeMaintain.MAINTAIN_DEDECT.getCode(),
        // EChannelType.NBZ.getCode(), cnyAccount.getAccountNumber());
        // data.setTotalIncome(totalIncome);
        //
        // } else if (ESYSUserKind.BUSINESS.getCode().equals(data.getKind())) {
        //
        // // 店铺信息
        // Company company = companyBO.getCompany(data.getCompanyCode());
        // data.setCompany(company);
        //
        // }
    }

    @Override
    public XN629901Res getTotalCreateCount(String userId, String type,
            Date createDatetimeStart, Date createDatetimeEnd) {

        long userTotalCount = 0L;// 用户总数
        XN629901Res res = null;

        // 平台端查询全部用户
        if (EAccountType.PLAT.getCode().equals(type)) {
            long userCount = 0L;// C端用户
            long agentUserCount = 0L;// 代理用户
            long sysUserCount = 0L;// 系统用户

            User condition = new User();
            condition.setCreateDatetimeStart(createDatetimeStart);
            condition.setCreateDatetimeEnd(createDatetimeEnd);
            userCount = userBO.getTotalCount(condition);

            // agentUserCount = agentUserBO.getTotalCount(null,
            // createDatetimeStart, createDatetimeEnd);

            sysUserCount = sysUserBO.getTotalCount(createDatetimeStart,
                createDatetimeEnd);

            userTotalCount = userCount + agentUserCount + sysUserCount;

            res = new XN629901Res(userTotalCount);
        }

        // 产权端查询新增的认养用户
        // if (EAccountType.OWNER.getCode().equals(type)) {
        // userTotalCount = adoptOrderTreeBO.getCountByOwner(userId,
        // createDatetimeStart, createDatetimeEnd);
        //
        // res = new XN629901Res(userTotalCount);
        // }

        // 养护端查询绑定的产权方
        // if (EAccountType.MAINTAIN.getCode().equals(type)) {
        // userTotalCount = applyBindMaintainBO.getOwnerCountByMaintain(
        // userId, createDatetimeStart, createDatetimeEnd);
        //
        // res = new XN629901Res(userTotalCount);
        // }

        // 代理商查询新增的业务员和用户
        // if (EAccountType.AGENT.getCode().equals(type)) {
        // long userCount = 0L;// C端用户
        // long agentUserCount = 0L;// 代理用户
        //
        // userCount = userBO.getTotalCount(userId, createDatetimeStart,
        // createDatetimeEnd);
        //
        // agentUserCount = agentUserBO.getTotalCount(userId,
        // createDatetimeStart, createDatetimeEnd);
        //
        // userTotalCount = userCount + agentUserCount;
        //
        // res = new XN629901Res(userTotalCount, userCount, agentUserCount);
        // }

        return res;
    }

}
