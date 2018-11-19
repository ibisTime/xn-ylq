package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.ISYSUserBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.MD5Util;
import com.cdkj.ylq.common.PhoneUtil;
import com.cdkj.ylq.common.PwdUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.ISYSUserDAO;
import com.cdkj.ylq.domain.Company;
import com.cdkj.ylq.domain.SYSUser;
import com.cdkj.ylq.dto.req.XN630050Req;
import com.cdkj.ylq.dto.req.XN630063Req;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.ERoleCode;
import com.cdkj.ylq.enums.ESYSUserKind;
import com.cdkj.ylq.enums.ESYSUserStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

@Component
public class SYSUserBOImpl extends PaginableBOImpl<SYSUser> implements
        ISYSUserBO {

    @Autowired
    private ISYSUserDAO sysUserDAO;

    @Autowired
    private ICompanyBO companyBO;

    @Override
    public List<SYSUser> queryUserList(SYSUser data) {
        List<SYSUser> list = sysUserDAO.selectList(data);

        if (CollectionUtils.isNotEmpty(list)) {
            for (SYSUser sysUser : list) {
                initUser(sysUser);
            }
        }

        return list;
    }

    @Override
    public String doSaveSYSuser(XN630050Req req) {
        SYSUser data = new SYSUser();
        String userId = OrderNoGenerater.generateM("U");
        data.setUserId(userId);
        data.setRoleCode(req.getRoleCode());
        data.setRealName(req.getRealName());
        data.setPhoto(req.getPhoto());
        data.setMobile(req.getMobile());
        data.setLoginName(req.getLoginName());
        data.setLoginPwd(req.getLoginPwd());
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(req
            .getLoginPwd()));
        data.setIsJt(req.getIsJt());
        data.setIsFk(req.getIsFk());
        data.setIsDl(req.getIsDl());
        data.setCreateDatetime(new Date());
        data.setStatus(ESYSUserStatus.NORMAL.getCode());
        data.setRemark(req.getRemark());
        sysUserDAO.insert(data);
        return userId;
    }

    @Override
    public String doSaveSYSuser(XN630063Req req, String loginPwd) {
        SYSUser data = new SYSUser();
        String userId = OrderNoGenerater.generateM("U");
        data.setUserId(userId);
        data.setKind(req.getKind());
        // data.setRealName(req.getRealName());
        data.setMobile(req.getMobile());
        data.setLoginName(req.getMobile());
        if (ESYSUserKind.OWNER.getCode().equals(req.getKind())) {
            data.setRoleCode(ERoleCode.OWNER.getCode());
        } else if (ESYSUserKind.MAINTAIN.getCode().equals(req.getKind())) {
            data.setRoleCode(ERoleCode.MAINTAIN.getCode());
        } else if (ESYSUserKind.BUSINESS.getCode().equals(req.getKind())) {
            data.setRoleCode(ERoleCode.BUSINESS.getCode());
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "用户类型不支持");
        }
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        data.setCreateDatetime(new Date());
        data.setStatus(ESYSUserStatus.NORMAL.getCode());
        data.setRemark(req.getRemark());
        sysUserDAO.insert(data);

        return userId;
    }

    @Override
    public String doSaveSYSUser(String kind, String mobile, String loginPwd) {
        SYSUser data = new SYSUser();
        String userId = OrderNoGenerater.generateM("U");
        data.setUserId(userId);
        data.setKind(kind);
        data.setMobile(mobile);
        data.setLoginName(mobile);
        if (ESYSUserKind.OWNER.getCode().equals(kind)) {
            data.setRoleCode(ERoleCode.OWNER.getCode());
        } else if (ESYSUserKind.MAINTAIN.getCode().equals(kind)) {
            data.setRoleCode(ERoleCode.MAINTAIN.getCode());
        } else if (ESYSUserKind.BUSINESS.getCode().equals(kind)) {
            data.setRoleCode(ERoleCode.BUSINESS.getCode());
        } else {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "用户类型不支持");
        }
        data.setLoginPwd(MD5Util.md5(loginPwd));
        data.setLoginPwdStrength(PwdUtil.calculateSecurityLevel(loginPwd));
        data.setCreateDatetime(new Date());
        data.setStatus(ESYSUserStatus.TO_FILL.getCode());// 待填公司资料
        sysUserDAO.insert(data);
        return userId;
    }

    @Override
    public void isMobileExist(String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            SYSUser condition = new SYSUser();
            condition.setMobile(mobile);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    @Override
    public void isMobileExist(String kind, String mobile) {
        if (StringUtils.isNotBlank(mobile)) {
            // 判断格式
            PhoneUtil.checkMobile(mobile);
            SYSUser condition = new SYSUser();
            condition.setKind(kind);
            condition.setMobile(mobile);
            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "手机号已经存在");
            }
        }
    }

    // 登录
    @Override
    public SYSUser getUserByLoginName(String loginName, String systemCode) {
        SYSUser data = null;
        if (StringUtils.isNotBlank(loginName)) {
            SYSUser condition = new SYSUser();
            condition.setLoginName(loginName);
            List<SYSUser> list = sysUserDAO.selectList(condition);
            if (list != null && list.size() > 1) {
                throw new BizException("li01006", "登录名重复");
            }
            if (CollectionUtils.isNotEmpty(list)) {
                data = list.get(0);
            }
        }
        return data;
    }

    @Override
    public SYSUser getUserByMobile(String mobile) {
        SYSUser data = null;
        if (StringUtils.isNotBlank(mobile)) {
            SYSUser condition = new SYSUser();
            condition.setMobile(mobile);
            data = sysUserDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "用户不存在");
            }
        }
        return data;

    }

    @Override
    public SYSUser getSYSUserUnCheck(String userId) {
        SYSUser data = null;
        if (StringUtils.isNotBlank(userId)) {
            SYSUser condition = new SYSUser();
            condition.setUserId(userId);
            data = sysUserDAO.select(condition);
        }
        return data;
    }

    @Override
    public SYSUser getSYSUser(String userId) {
        SYSUser data = null;
        if (StringUtils.isNotBlank(userId)) {
            SYSUser condition = new SYSUser();
            condition.setUserId(userId);
            data = sysUserDAO.select(condition);
            if (data == null) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "系统用户不存在");
            }
            if (ESYSUserStatus.Li_Locked.getCode().equals(data.getStatus())
                    || ESYSUserStatus.Ren_Locked.getCode().equals(
                        data.getStatus())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "用户已被锁定，请联系管理员");
            }

            initUser(data);
        }
        return data;
    }

    @Override
    public boolean isUserExist(String userId) {
        SYSUser condition = new SYSUser();
        condition.setUserId(userId);
        if (sysUserDAO.selectTotalCount(condition) > 0) {
            return true;
        }
        return false;
    }

    // 密码检查
    @Override
    public void checkLoginPwd(String userId, String loginPwd) {
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(loginPwd)) {
            SYSUser condition = new SYSUser();
            condition.setUserId(userId);
            condition.setLoginPwd(MD5Util.md5(loginPwd));
            long count = this.getTotalCount(condition);
            if (count != 1) {
                throw new BizException("jd00001", "原登录密码错误");
            }
        } else {
            throw new BizException("jd00001", "原登录密码错误");
        }
    }

    // 重置密码
    @Override
    public void resetAdminLoginPwd(SYSUser sysUser, String loginPwd) {
        sysUser.setLoginPwd(MD5Util.md5(loginPwd));
        sysUserDAO.updateLoginPwd(sysUser);
    }

    @Override
    public void resetSelfPwd(SYSUser sysUser, String newLoginPwd) {
        sysUser.setLoginPwd(MD5Util.md5(newLoginPwd));
        sysUserDAO.updateLoginPwd(sysUser);

    }

    // 重置登录名
    @Override
    public void refreshLoginName(String userId, String loginName) {
        if (StringUtils.isNotBlank(userId)) {
            SYSUser data = new SYSUser();
            data.setUserId(userId);
            data.setLoginName(loginName);
            sysUserDAO.updateLoginName(data);
        }
    }

    @Override
    public void isLoginNameExist(String loginName) {
        if (StringUtils.isNotBlank(loginName)) {
            // 判断格式
            SYSUser condition = new SYSUser();
            condition.setLoginName(loginName);

            long count = getTotalCount(condition);
            if (count > 0) {
                throw new BizException("li01003", "登录名已经存在");
            }
        }
    }

    //
    @Override
    public void resetBindMobile(SYSUser user, String newMobile) {
        user.setMobile(newMobile);
        user.setLoginName(newMobile);
        sysUserDAO.resetBindMobile(user);
    }

    @Override
    public void refreshPhoto(SYSUser user, String photo) {
        user.setPhoto(photo);
        sysUserDAO.updatePhoto(user);

    }

    @Override
    public void refreshRole(String userId, String roleCode, String updater,
            String remark) {
        if (StringUtils.isNotBlank(userId)) {
            SYSUser data = new SYSUser();
            data.setUserId(userId);
            data.setRoleCode(roleCode);
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            sysUserDAO.updateRole(data);
        }
    }

    @Override
    public void approveSYSUser(SYSUser data, String approveResult,
            String updater, String remark) {
        String status = ESYSUserStatus.APPROVE_NO.getCode();
        if (EBoolean.YES.getCode().equals(approveResult)) {
            status = ESYSUserStatus.NORMAL.getCode();
        }
        data.setStatus(status);
        data.setUpdater(updater);

        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        sysUserDAO.updateApproveSYSUser(data);
    }

    @Override
    public void refreshStatus(String userId, ESYSUserStatus status,
            String updater, String remark) {
        if (StringUtils.isNotBlank(userId)) {
            SYSUser data = new SYSUser();
            data.setUserId(userId);
            data.setStatus(status.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(new Date());
            data.setRemark(remark);
            sysUserDAO.updateStatus(data); // change to updateStatus
        }
    }

    @Override
    public void refreshCompany(String userId, String companyCode) {
        if (StringUtils.isNotBlank(userId)) {
            SYSUser data = new SYSUser();
            data.setUserId(userId);
            data.setCompanyCode(companyCode);
            sysUserDAO.updateCompany(data);
        }
    }

    private void initUser(SYSUser data) {
        if (ESYSUserKind.OWNER.getCode().equals(data.getKind())
                || ESYSUserKind.MAINTAIN.getCode().equals(data.getKind())) {
            Company company = companyBO.getCompanyByUserId(data.getUserId());
            data.setCompany(company);
        }
    }

    @Override
    public long getTotalCount(Date createDatetimeStart, Date createDatetimeEnd) {
        SYSUser condition = new SYSUser();
        condition.setCreateDatetimeStart(createDatetimeStart);
        condition.setCreateDatetimeEnd(createDatetimeEnd);
        return sysUserDAO.selectTotalCount(condition);
    }

}
