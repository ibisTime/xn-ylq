package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

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
import com.cdkj.ylq.domain.SYSUser;
import com.cdkj.ylq.dto.req.XN630050Req;
import com.cdkj.ylq.enums.EUserStatus;
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
        data.setCreateDatetime(new Date());
        data.setStatus(EUserStatus.NORMAL.getCode());
        data.setRemark(req.getRemark());
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
    public void resetSelfPwd(SYSUser sysUser, String newLoginPwd) {
        sysUser.setLoginPwd(MD5Util.md5(newLoginPwd));
        sysUserDAO.updateLoginPwd(sysUser);

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
            if (EUserStatus.Li_Locked.getCode().equals(data.getStatus())
                    || EUserStatus.Ren_Locked.getCode()
                        .equals(data.getStatus())) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                    "用户已被锁定，请联系管理员");
            }

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
    public void refreshStatus(String userId, EUserStatus status,
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
    public long getTotalCount(Date createDatetimeStart, Date createDatetimeEnd) {
        SYSUser condition = new SYSUser();
        condition.setCreateDatetimeStart(createDatetimeStart);
        condition.setCreateDatetimeEnd(createDatetimeEnd);
        return sysUserDAO.selectTotalCount(condition);
    }

}
