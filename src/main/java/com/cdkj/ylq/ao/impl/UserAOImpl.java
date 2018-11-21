/**
 * @Title UserAO.java 
 * @Package com.cdkj.ylq.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月21日 下午9:22:32 
 * @version V1.0   
 */
package com.cdkj.ylq.ao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.MD5Util;
import com.cdkj.ylq.common.PhoneUtil;
import com.cdkj.ylq.common.RandomUtil;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.req.XN805042Req;
import com.cdkj.ylq.dto.req.XN805043Req;
import com.cdkj.ylq.dto.req.XN805081ZReq;
import com.cdkj.ylq.dto.req.XN805095Req;
import com.cdkj.ylq.dto.res.XN001400Res;
import com.cdkj.ylq.dto.res.XN805041Res;
import com.cdkj.ylq.enums.EBoolean;
import com.cdkj.ylq.enums.EIDKind;
import com.cdkj.ylq.enums.EUser;
import com.cdkj.ylq.enums.EUserKind;
import com.cdkj.ylq.enums.EUserStatus;
import com.cdkj.ylq.exception.BizException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月21日 下午9:22:32 
 * @history:
 */
@Service
public class UserAOImpl implements IUserAO {
    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICertificationAO certificationAO;

    @Autowired
    protected ISYSConfigBO sysConfigBO;

    @Autowired
    private ISmsOutBO smsOutBO;

    @Autowired
    private ICompanyBO companyBO;

    /** 
     * @see com.std.user.ao.IUserAO#doCheckMobile(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doCheckMobile(String mobile, String kind, String companyCode,
            String systemCode) {
        userBO.isMobileExist(mobile, companyCode);
    }

    @Override
    @Transactional
    public XN805041Res doRegister(String mobile, String loginPwd,
            String userReferee, String userRefereeKind, String smsCaptcha,
            String province, String city, String area, String address,
            String companyCode, String createClient) {
        // 1、参数校验
        // 验证手机号是否存在
        userBO.isMobileExist(mobile, companyCode);
        // 公司验证
        companyBO.getCompany(companyCode);
        // 验证推荐人是否存在,并将手机号转化为用户编号
        String userRefereeId = userBO.getUserId(userReferee, companyCode);
        // 验证短信验证码
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805041", companyCode);
        // 2、注册用户
        String userId = userBO.doRegister(mobile, loginPwd, userRefereeId,
            province, city, area, address, companyCode, createClient);
        // 优惠券
        // TODO
        // 分配认证信息
        certificationAO.initialCertification(userId);

        return new XN805041Res(userId, userRefereeId);
    }

    @Override
    public void doCheckLoginPwd(String userId, String loginPwd) {
        User condition = new User();
        condition.setUserId(userId);
        List<User> userList1 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn702002", "用户不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<User> userList2 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn702002", "登录密码错误");
        }

    }

    @Override
    @Transactional
    public String doAddUser(XN805042Req req) {
        String userId = null;
        userId = doAddUserYLQ(req);
        return userId;
    }

    private String doAddUserYLQ(XN805042Req req) {
        String userId = null;
        if (EUserKind.Customer.getCode().equals(req.getKind())) {
            // 验证手机号
            userBO.isMobileExist(req.getMobile(), req.getCompanyCode());
            // 判断登录密码是否为空
            if (StringUtils.isBlank(req.getLoginPwd())) {
                req.setLoginPwd(RandomUtil.generate6());
            }
            userId = userBO.doAddUser(req);

            // 发送短信
            smsOutBO
                .sendSmsOut(
                    req.getMobile(),
                    "尊敬的"
                            + PhoneUtil.hideMobile(req.getMobile())
                            + "用户，您已成功注册。初始化登录密码为"
                            + req.getLoginPwd()
                            + "，请及时登录网站更改密码。APP下载地址：http://m.yiliangqian.com/share/share-qrcord.html",
                    "805042", req.getCompanyCode());
        } else if (EUserKind.Plat.getCode().equals(req.getKind())) {
            // 验证登录名
            userBO.isLoginNameExist(req.getLoginName(), req.getKind(),
                req.getCompanyCode());

            userId = userBO.doAddUser(req);
        } else {
            throw new BizException("xn805042", "用户类型" + req.getKind() + "未能识别");
        }
        return userId;
    }

    @Override
    @Transactional
    public String doApplyRegUser(XN805043Req req) {
        String userId = null;
        userId = doApplyRegUserCaigo(req);
        return userId;
    }

    private String doApplyRegUserCaigo(XN805043Req req) {
        String userId = null;
        if (EUserKind.Customer.getCode().equals(req.getKind())) {
            // 验证手机号
            userBO.isMobileExist(req.getMobile(), req.getCompanyCode());
            // 判断登录密码是否为空
            if (StringUtils.isBlank(req.getLoginPwd())) {
                req.setLoginPwd(RandomUtil.generate6());
            }
            userId = userBO.doApplyRegUser(req, null);

            // 发送短信
            smsOutBO.sendSmsOut(req.getMobile(),
                "尊敬的" + PhoneUtil.hideMobile(req.getMobile())
                        + "用户，您已成功注册。初始化登录密码为" + req.getLoginPwd()
                        + "，请及时登录网站更改密码。", "805043", req.getCompanyCode());
        } else if (EUserKind.Merchant.getCode().equals(req.getKind())) {
            // 验证手机号
            userBO.isMobileExist(req.getMobile(), req.getCompanyCode());
            // 判断登录密码是否为空
            if (StringUtils.isBlank(req.getLoginPwd())) {
                req.setLoginPwd(RandomUtil.generate6());
            }
            userId = userBO.doApplyRegUser(req, null);

            // 发送短信
            smsOutBO.sendSmsOut(req.getMobile(),
                "尊敬的" + PhoneUtil.hideMobile(req.getMobile())
                        + "用户，您已成功注册。初始化登录密码为" + req.getLoginPwd()
                        + "，请及时登录网站更改密码。", "805043", req.getCompanyCode());
        } else if (EUserKind.Plat.getCode().equals(req.getKind())) {
            // 验证登录名
            userBO.isLoginNameExist(req.getLoginName(), req.getKind(),
                req.getCompanyCode());
            userId = userBO.doApplyRegUser(req, null);
        }
        return userId;
    }

    @Override
    public String doLogin(String loginName, String loginPwd, String companyCode) {
        User condition = new User();
        condition.setLoginName(loginName);
        condition.setCompanyCode(companyCode);
        List<User> userList1 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList1)) {
            throw new BizException("xn805050", "登录名不存在");
        }
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<User> userList2 = userBO.queryUserList(condition);
        if (CollectionUtils.isEmpty(userList2)) {
            throw new BizException("xn805050", "登录密码错误");
        }
        User user = userList2.get(0);
        if (!EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
            throw new BizException("xn805050", "该账号"
                    + EUserStatus.getMap().get(user.getStatus()).getValue()
                    + "，请联系工作人员");
        }
        return user.getUserId();
    }

    @Override
    public String doCaptchaLoginReg(String mobile, String kind,
            String smsCaptcha, String companyCode, String systemCode) {
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805051", companyCode);
        String userId = userBO.getUserId(mobile, companyCode);
        if (StringUtils.isNotBlank(userId)) {
            userId = userBO.saveUser(mobile, kind, companyCode, systemCode);
        }
        return userId;
    }

    @Override
    public void doBindMoblie(String userId, String mobile, String smsCaptcha,
            String isSendSms) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        if (StringUtils.isNotBlank(user.getMobile())) {
            throw new BizException("li01004", "手机号已经绑定，无需再次操作");
        }
        // 验证手机号
        userBO.isMobileExist(mobile, user.getCompanyCode());
        // 短信验证码是否正确（往手机号发送）
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805060",
            user.getCompanyCode());
        // 插入用户信息
        String loginPwd = RandomUtil.generate6();
        userBO.refreshBindMobile(userId, mobile, mobile, loginPwd, "1");
        // 发送短信
        if (EBoolean.YES.getCode().equals(isSendSms)) {
            smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                    + "用户，您的登录密码为" + loginPwd + "，请及时登录网站更改密码。", "805060",
                user.getCompanyCode());
        }
    }

    @Override
    @Transactional
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn000000", "用户不存在");
        }
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        // 验证手机号
        userBO.isMobileExist(newMobile, user.getCompanyCode());
        // 短信验证码是否正确（往新手机号发送）
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "805061",
            user.getCompanyCode());
        userBO.refreshMobile(userId, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(
            oldMobile,
            "尊敬的"
                    + PhoneUtil.hideMobile(oldMobile)
                    + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "提交的更改绑定手机号码服务已审核通过，现绑定手机号码为" + newMobile
                    + "，请妥善保管您的账户相关信息。", "805061", user.getCompanyCode());
    }

    @Override
    @Transactional
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha, String tradePwd) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn000000", "用户不存在");
        }
        String oldMobile = user.getMobile();
        if (newMobile.equals(oldMobile)) {
            throw new BizException("xn000000", "新手机与原手机一致");
        }
        userBO.isMobileExist(newMobile, user.getCompanyCode());
        // 验证支付密码
        if (StringUtils.isNotBlank(tradePwd)) {
            userBO.checkTradePwd(userId, tradePwd);
        }
        // 短信验证码是否正确（往新手机号发送）
        smsOutBO.checkCaptcha(newMobile, smsCaptcha, "805062",
            user.getCompanyCode());
        userBO.refreshMobile(userId, newMobile);
        // 发送短信
        smsOutBO.sendSmsOut(
            oldMobile,
            "尊敬的"
                    + PhoneUtil.hideMobile(oldMobile)
                    + "用户，您于"
                    + DateUtil.dateToStr(new Date(),
                        DateUtil.DATA_TIME_PATTERN_1)
                    + "提交的更改绑定手机号码服务已审核通过，现绑定手机号码为" + newMobile
                    + "，请妥善保管您的账户相关信息。", "805062", user.getCompanyCode());
    }

    @Override
    @Transactional
    public void doResetLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd, String companyCode) {
        String userId = userBO.getUserId(mobile, companyCode);
        if (StringUtils.isBlank(userId)) {
            throw new BizException("li01004", "用户不存在,请先注册");
        }
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805063", companyCode);
        userBO.refreshLoginPwd(userId, newLoginPwd);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的登录密码重置成功。请妥善保管您的账户相关信息。", "805063", companyCode);
    }

    @Override
    @Transactional
    public void doModifyLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd) {
        if (oldLoginPwd.equals(newLoginPwd)) {
            throw new BizException("li01006", "新登录密码不能与原有密码重复");
        }
        // 验证当前登录密码是否正确
        userBO.checkLoginPwd(userId, oldLoginPwd);
        // 重置
        userBO.refreshLoginPwd(userId, newLoginPwd);
        // 发送短信
        User user = userBO.getUser(userId);
        smsOutBO.sendSmsOut(user.getMobile(),
            "尊敬的" + PhoneUtil.hideMobile(user.getMobile())
                    + "用户，您的登录密码修改成功。请妥善保管您的账户相关信息。", "805064",
            user.getCompanyCode());
    }

    @Override
    @Transactional
    public void doResetLoginPwdByOss(String userId, String loginPwd,
            String adminUserId, String adminPwd) {
        // 验证当前登录密码是否正确
        userBO.checkLoginPwd(adminUserId, adminPwd, "管理员密码");
        userBO.refreshLoginPwd(userId, loginPwd);
    }

    @Override
    @Transactional
    public void doSetTradePwd(String userId, String tradePwd, String smsCaptcha) {
        User user = this.doGetUser(userId);
        // 短信验证码是否正确
        smsOutBO.checkCaptcha(user.getMobile(), smsCaptcha, "805066",
            user.getCompanyCode());
        // 修改支付密码
        userBO.refreshTradePwd(userId, tradePwd);
        // 发送短信
        String mobile = user.getMobile();
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的支付密码设置成功。请妥善保管您的账户相关信息。", "805066",
            user.getCompanyCode());
    }

    @Override
    public void doResetTradePwd(String userId, String newTradePwd,
            String smsCaptcha) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li010004", "用户名不存在");
        }
        // 短信验证码是否正确
        String mobile = user.getMobile();
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805067",
            user.getCompanyCode());
        userBO.refreshTradePwd(userId, newTradePwd);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的支付密码重置成功。请妥善保管您的账户相关信息。", "805067",
            user.getCompanyCode());
    }

    @Override
    @Transactional
    public void doResetTradePwd(String userId, String newTradePwd,
            String smsCaptcha, String idKind, String idNo) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户名不存在");
        }
        if (user.getIdKind() == null || user.getIdNo() == null) {
            throw new BizException("li01004", "请先实名认证");
        }
        // 证件是否正确
        if (!(user.getIdKind().equalsIgnoreCase(idKind) && user.getIdNo()
            .equalsIgnoreCase(idNo))) {
            throw new BizException("li01009", "身份证不符合");
        }
        // 短信验证码是否正确
        String mobile = user.getMobile();
        smsOutBO.checkCaptcha(mobile, smsCaptcha, "805068",
            user.getCompanyCode());
        userBO.refreshTradePwd(userId, newTradePwd);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的支付密码重置成功。请妥善保管您的账户相关信息。", "805068",
            user.getCompanyCode());
    }

    @Override
    @Transactional
    public void doModifyTradePwd(String userId, String oldTradePwd,
            String newTradePwd) {
        if (oldTradePwd.equals(newTradePwd)) {
            throw new BizException("li01008", "新支付密码与原有支付密码重复");
        }
        User conditon = new User();
        conditon.setUserId(userId);
        conditon.setTradePwd(MD5Util.md5(oldTradePwd));
        List<User> list = userBO.queryUserList(conditon);
        User user = null;
        if (CollectionUtils.isNotEmpty(list)) {
            user = list.get(0);
        } else {
            throw new BizException("li01008", "旧支付密码不正确");
        }
        userBO.refreshTradePwd(userId, newTradePwd);
        String mobile = user.getMobile();
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + "用户，您的支付密码修改成功。请妥善保管您的账户相关信息。", "805069",
            user.getCompanyCode());
    }

    @Override
    public void modifyPhoto(String userId, String photo) {
        userBO.refreshPhoto(userId, photo);
    }

    // 修改用户信息
    public void doModifyUserExt(XN805081ZReq req) {
    }

    // 完善手机号和身份信息
    public void doModfiyMobileAndIds(String userId, String mobile,
            String realName, String idKind, String idNo) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn0110", "用户不存在");
        }
        if (StringUtils.isBlank(user.getMobile())) {
            User data = new User();
            data.setUserId(userId);
            data.setMobile(mobile);
            data.setRealName(realName);
            data.setIdKind(idKind);
            data.setIdNo(idNo);
            userBO.refreshUserSupple(data);
        }
    }

    // 修改经纬度
    public void doModifyLngLat(String userId, String longitude, String latitude) {
    }

    @Override
    public void doCloseOpen(String userId, String updater, String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        // admin 不注销
        if (EUser.ADMIN.getCode().equals(user.getLoginName())) {
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
        userBO.refreshStatus(userId, userStatus, updater, remark);
        // 发送短信
        smsOutBO.sendSmsOut(mobile, "尊敬的" + PhoneUtil.hideMobile(mobile)
                + smsContent, "805091", user.getCompanyCode());
    }

    @Override
    public void doModifyNickname(String userId, String nickname) {
        userBO.refreshNickname(userId, nickname);
    }

    @Override
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", "用户不存在");
        }
        // SYSRole role = sysRoleBO.getSYSRole(roleCode);
        // if (role == null) {
        // throw new BizException("li01004", "角色不存在");
        // }
        // if (!user.getSystemCode().equals(role.getSystemCode())) {
        // throw new BizException("li01004", "用户和角色系统不对应");
        // }
        userBO.refreshRole(userId, roleCode, updater, remark);
    }

    @Override
    public void doApproveUser(String userId, String approver,
            String approveResult, String divRate, String remark) {
        User user = userBO.getUser(userId);
        Double divRateD = null;
        if (!EUserStatus.TO_APPROVE.getCode().equals(user.getStatus())
                && !EUserStatus.APPROVE_NO.getCode().equals(user.getStatus())) {
            throw new BizException("xn000000", "用户不处于待审核状态");
        }
        String userStatus = EUserStatus.APPROVE_NO.getCode();
        if (EBoolean.YES.getCode().equals(approveResult)) {
            userStatus = EUserStatus.NORMAL.getCode();
            divRateD = StringValidater.toDouble(divRate);
        }
        userBO.approveUser(userId, approver, userStatus, divRateD, remark);
    }

    @Override
    public void doModifyDivRate(String userId, Double divRate, String updater,
            String remark) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("xn000000", "用户不存在");
        }
        userBO.refreshDivRate(userId, divRate);
    }

    @Override
    public void doIdentify(String userId, String idKind, String idNo,
            String realName) {
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);
        // // 回写Account表realName;
        // accountBO.refreshRealName(user.getUserId(), realName,
        // user.getSystemCode());
    }

    @Override
    public void doTwoIdentify(String userId, String idKind, String idNo,
            String realName) {
        // User user = userBO.getUser(userId);
        // dentifyBO.doTwoIdentify(user.getSystemCode(), user.getCompanyCode(),
        // userId, realName, idKind, idNo);
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);
        // // 回写Account表realName;
        // accountBO.refreshRealName(user.getUserId(), realName,
        // user.getSystemCode());
    }

    @Override
    public void doFourIdentify(String userId, String idKind, String idNo,
            String realName, String cardNo, String bindMobile) {
        // 三方认证
        // dentifyBO.doFourIdentify(userId, realName, idKind, idNo, cardNo,
        // bindMobile);
        // 更新用户表
        userBO
            .refreshIdentity(userId, realName, EIDKind.IDCard.getCode(), idNo);
        // // 回写Account表realName;
        // accountBO.refreshRealName(user.getUserId(), realName,
        // user.getSystemCode());
    }

    @Override
    public Object doZhimaIdentify(String userId, String idKind, String idNo,
            String realName) {
        // User user = userBO.getUser(userId);
        // // 判断库中是否有该记录
        // userBO.checkIdentify(user.getKind(), idKind, idNo, realName);
        // // 芝麻认证 有两种结果：如果本地有记录，返回成功；如果本地无记录，返货芝麻认证所需信息
        // XN798011Res res = dentifyBO.doZhimaVerify(user.getSystemCode(),
        // user.getSystemCode(), userId, idKind, idNo, realName);
        // // 如果直接返回成功
        // if (res.isSuccess()) {
        // // 更新用户表
        // userBO.refreshIdentity(userId, realName, EIDKind.IDCard.getCode(),
        // idNo);
        // // 回写Account表realName;
        // accountBO.refreshRealName(user.getUserId(), realName,
        // user.getSystemCode());
        // }
        // return res;
        return null;
    }

    @Override
    public Object doZhimaQuery(String userId, String bizNo) {
        User user = userBO.getUser(userId);
        // XN798012Res res = dentifyBO.doZhimaQuery(user.getSystemCode(),
        // user.getSystemCode(), bizNo);
        // if (res.isSuccess()) {
        // // 更新用户表
        // userBO.refreshIdentity(userId, res.getRealName(), res.getIdKind(),
        // res.getIdNo());
        // // 回写Account表realName;
        // accountBO.refreshRealName(user.getUserId(), res.getRealName(),
        // user.getSystemCode());
        // }
        // return res;
        return user;
    }

    @Override
    @Transactional
    public void doModifyUser(XN805095Req req) {
        User dbUser = userBO.getUser(req.getUserId());
        if (dbUser == null) {
            throw new BizException("xn000000", "该用户编号不存在！");
        }
        User condition = new User();
        condition.setProvince(req.getProvince());
        condition.setCity(req.getCity());
        condition.setArea(req.getArea());
        List<User> list = userBO.queryUserList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            User user = list.get(0);
            if (!user.getUserId().equals(req.getUserId())) {
                throw new BizException("xn000000", "该辖区已存在合伙人！");
            }
        }

        User data = new User();
        data.setUserId(req.getUserId());
        data.setMobile(req.getMobile());
        data.setIdKind(req.getIdKind());
        data.setIdNo(req.getIdNo());

        data.setRealName(req.getRealName());
        data.setUpdater(req.getUpdater());
        data.setUpdateDatetime(new Date());
        data.setRemark(req.getRemark());
        data.setStatus(dbUser.getStatus());

        data.setProvince(req.getProvince());
        data.setCity(req.getCity());
        data.setArea(req.getArea());

        userBO.refreshUser(data);
    }

    @Override
    public Paginable<User> queryUserPage(int start, int limit, User condition) {
        Paginable<User> page = userBO.getPaginable(start, limit, condition);
        List<User> list = page.getList();
        for (User user : list) {
            // 推荐人转义
            User userReferee = userBO.getUser(user.getUserReferee());
            if (userReferee != null) {
                user.setRefereeUser(userReferee);
            }
        }
        return page;
    }

    @Override
    public List<User> queryUserList(User condition) {
        return userBO.queryUserList(condition);
    }

    @Override
    public List<User> getUserRefereeList(String userId) {
        List<User> list = new ArrayList<User>();
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", userId + "用户不存在");
        }
        String refeere = user.getUserReferee();
        // 获取上级
        User userRefeereTop1 = getTopUserRefeere(refeere, -1);
        if (userRefeereTop1 != null) {
            list.add(userRefeereTop1);
        }

        // 获取下级，下下级，下下下级
        List<User> refeeresNext1 = getNextUserRefeere(userId, 1);
        if (CollectionUtils.isNotEmpty(refeeresNext1)) {
            list.addAll(refeeresNext1);
            for (User userNext2 : refeeresNext1) {
                List<User> refeeresNext2 = getNextUserRefeere(
                    userNext2.getUserId(), 2);
                if (CollectionUtils.isNotEmpty(refeeresNext2)) {
                    list.addAll(refeeresNext2);
                }
                for (User userNext3 : refeeresNext2) {
                    List<User> refeeresNext3 = getNextUserRefeere(
                        userNext3.getUserId(), 3);
                    if (CollectionUtils.isNotEmpty(refeeresNext3)) {
                        list.addAll(refeeresNext3);
                    }
                }
            }
        }
        return list;
    }

    private User getTopUserRefeere(String userId, int refeereLevel) {
        User userRefeere = userBO.getUser(userId);
        return userRefeere;
    }

    private List<User> getNextUserRefeere(String userId, int refeereLevel) {
        List<User> userList = userBO.getUsersByUserReferee(userId);
        return userList;
    }

    @Override
    public User doGetUser(String userId) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", userId + "用户不存在");
        } else {
            // 拉取推荐人信息
            User refereeUser = userBO.getUser(user.getUserReferee());
            user.setRefereeUser(refereeUser);

        }
        return user;
    }

    /** 
     * @see com.std.user.ao.IUserAO#doGetUserIdByCondition(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public String doGetUserIdByCondition(String mobile, String kind,
            String companyCode, String systemCode) {
        String userId = null;
        User condition = new User();
        condition.setMobile(mobile);
        condition.setCompanyCode(companyCode);
        List<User> userList = userBO.queryUserList(condition);
        if (CollectionUtils.isNotEmpty(userList)) {
            User user = userList.get(0);
            if (EUserStatus.NORMAL.getCode().equals(user.getStatus())) {
                userId = user.getUserId();
            }
        }
        return userId;
    }

    @Override
    public void doCheckTradePwd(String userId, String tradePwd) {
        userBO.checkTradePwd(userId, tradePwd);
    }

    // @Override
    // @Transactional
    // public XN805170Res doLoginWeChat(XN805170Req req) {
    // String companyCode = req.getCompanyCode();
    // String systemCode = ;
    // // Step1：获取密码参数信息
    // Map<String, String> configPwd = sysConfigBO.getConfigsMap(
    // EConfigType.WEIXIN_H5.getCode(), companyCode, systemCode);
    // String appId = null;
    // String appSecret = null;
    // if (EConfigType.WEIXIN_APP.getCode().equals(req.getType())) {
    // appId = configPwd.get(SysConstant.WX_APP_ACCESS_KEY);
    // appSecret = configPwd.get(SysConstant.WX_APP_SECRET_KEY);
    // } else if (EConfigType.WEIXIN_H5.getCode().equals(req.getType())) {
    // appId = configPwd.get(SysConstant.WX_H5_ACCESS_KEY);
    // appSecret = configPwd.get(SysConstant.WX_H5_SECRET_KEY);
    // } else {
    // throw new BizException("XN000000", "登录类型不支持");
    // }
    // if (StringUtils.isBlank(appId)) {
    // throw new BizException("XN000000", "参数appId配置获取失败，请检查配置");
    // }
    // if (StringUtils.isBlank(appSecret)) {
    // throw new BizException("XN000000", "参数appSecret配置获取失败，请检查配置");
    // }
    //
    // // Step2：通过Authorization Code获取Access Token
    // String accessToken = "";
    // Map<String, String> res = new HashMap<>();
    // Properties fromProperties = new Properties();
    // fromProperties.put("grant_type", "authorization_code");
    // fromProperties.put("appid", appId);
    // fromProperties.put("secret", appSecret);
    // fromProperties.put("code", req.getCode());
    // logger.info("appId:" + appId + ",appSecret:" + appSecret + ",code:"
    // + req.getCode());
    // XN805170Res result = null;
    // try {
    // String response = PostSimulater.requestPostForm(
    // WechatConstant.WX_TOKEN_URL, fromProperties);
    // res = getMapFromResponse(response);
    // accessToken = (String) res.get("access_token");
    // if (res.get("error") != null) {
    // throw new BizException("XN000000", "微信登录失败原因："
    // + res.get("error"));
    // }
    // if (StringUtils.isBlank(accessToken)) {
    // throw new BizException("XN000000", "accessToken不能为空");
    // }
    // // Step3：使用Access Token来获取用户的OpenID
    // String openId = (String) res.get("openid");
    // // 获取unionid
    // Map<String, String> wxRes = new HashMap<>();
    // Properties queryParas = new Properties();
    // queryParas.put("access_token", accessToken);
    // queryParas.put("openid", openId);
    // queryParas.put("lang", "zh_CN");
    // wxRes = getMapFromResponse(PostSimulater.requestPostForm(
    // WechatConstant.WX_USER_INFO_URL, queryParas));
    // String unionId = (String) wxRes.get("unionid");
    // String appOpenId = null;
    // String h5OpenId = null;
    // if (EConfigType.WEIXIN_APP.getCode().equals(req.getType())) {
    // appOpenId = (String) wxRes.get("openid");
    // } else if (EConfigType.WEIXIN_H5.getCode().equals(req.getType())) {
    // h5OpenId = (String) wxRes.get("openid");
    // }
    // // Step4：根据openId，unionId从数据库中查询用户信息
    // User dbUser = userBO.doGetUserByOpenId(appOpenId, h5OpenId,
    // companyCode, systemCode);
    // if (null != dbUser) {// 如果user存在，说明用户授权登录过，直接登录
    // addLoginAmount(dbUser);// 每天登录送积分
    // result = new XN805170Res(dbUser.getUserId());
    // } else {
    // String nickname = (String) wxRes.get("nickname");
    // String photo = (String) wxRes.get("headimgurl");
    // String gender = ESex.UNKNOWN.getCode();
    // if (String.valueOf(wxRes.get("sex")).equals("1.0")) {
    // gender = ESex.MEN.getCode();
    // } else if (String.valueOf(wxRes.get("sex")).equals("2.0")) {
    // gender = ESex.WOMEN.getCode();
    // }
    // // Step5：判断注册是否传手机号，有则注册，无则反馈
    // if (EBoolean.YES.getCode().equals(req.getIsNeedMobile())) {
    // result = doWxLoginRegMobile(req, companyCode, systemCode,
    // unionId, appOpenId, h5OpenId, nickname, photo, gender);
    // } else {
    // result = doWxLoginReg(req, companyCode, systemCode,
    // unionId, appOpenId, h5OpenId, nickname, photo, gender);
    // }
    // }
    // } catch (Exception e) {
    // throw new BizException("xn000000", e.getMessage());
    // }
    // return result;
    // }

    /** 
     * @param req
     * @param companyCode
     * @param systemCode
     * @param unionId
     * @param appOpenId
     * @param h5OpenId
     * @param nickname
     * @param photo
     * @param gender
     * @return 
     * @create: 2017年7月14日 下午9:58:06 xieyj
     * @history: 
     */
    // private XN805170Res doWxLoginReg(XN805170Req req, String companyCode,
    // String systemCode, String unionId, String appOpenId,
    // String h5OpenId, String nickname, String photo, String gender) {
    // XN805170Res result;
    // // 验证推荐人,将userReferee手机号转为用户编号
    // String userRefereeId = userBO.getUserId(req.getUserReferee(),
    // req.getUserRefereeKind(), companyCode, systemCode);
    // userBO.doCheckOpenId(unionId, h5OpenId, appOpenId, companyCode,
    // systemCode);
    // // 插入用户信息
    // String userId = userBO.doRegister(unionId, h5OpenId, appOpenId, null,
    // req.getKind(), EUserPwd.InitPwd.getCode(), nickname, photo, gender,
    // userRefereeId, companyCode, systemCode);
    // distributeAccount(userId, nickname, req.getKind(), companyCode,
    // systemCode);
    // result = new XN805170Res(userId, EBoolean.NO.getCode());
    // return result;
    // }
    //
    // private XN805170Res doWxLoginRegMobile(XN805170Req req, String
    // companyCode,
    // String systemCode, String unionId, String appOpenId,
    // String h5OpenId, String nickname, String photo, String gender) {
    // XN805170Res result;
    // if (StringUtils.isNotBlank(req.getMobile())) {
    // // 判断是否需要验证码验证码,登录前一定要验证
    // if (!EBoolean.YES.getCode().equals(req.getIsLoginCaptcha())) {
    // if (StringUtils.isBlank(req.getSmsCaptcha())) {
    // throw new BizException("xn702002", "请输入短信验证码");
    // }
    // // 短信验证码是否正确
    // smsOutBO.checkCaptcha(req.getMobile(), req.getSmsCaptcha(),
    // "805151", companyCode, systemCode);
    // }
    // String mobileUserId = userBO.getUserId(req.getMobile(),
    // req.getKind(), companyCode, systemCode);
    // if (StringUtils.isBlank(mobileUserId)) {
    // // 验证推荐人,将userReferee手机号转为用户编号
    // String userRefereeId = userBO.getUserId(req.getUserReferee(),
    // req.getUserRefereeKind(), companyCode, systemCode);
    // userBO.doCheckOpenId(unionId, h5OpenId, appOpenId, companyCode,
    // systemCode);
    // // 插入用户信息
    // String userId = userBO.doRegister(unionId, h5OpenId, appOpenId,
    // req.getMobile(), req.getKind(), EUserPwd.InitPwd.getCode(),
    // nickname, photo, gender, userRefereeId, companyCode,
    // systemCode);
    // distributeAccount(userId, req.getMobile(), req.getKind(),
    // companyCode, systemCode);
    // result = new XN805170Res(userId);
    // } else {
    // userBO.refreshWxInfo(mobileUserId, unionId, h5OpenId,
    // appOpenId, nickname, photo, gender);
    // result = new XN805170Res(mobileUserId);
    // }
    // } else {
    // result = new XN805170Res(null, EBoolean.YES.getCode());
    // }
    // return result;
    // }

    /**
     * @param response  可能是Json & Jsonp字符串 & urlParas
     *          eg：urlParas：access_token=xxx&expires_in=7776000&refresh_token=xxx
     * @return
     */
    public Map<String, String> getMapFromResponse(String response) {
        if (StringUtils.isBlank(response)) {
            return new HashMap<>();
        }

        Map<String, String> result = new HashMap<>();
        int begin = response.indexOf("{");
        int end = response.lastIndexOf("}") + 1;

        if (begin >= 0 && end > 0) {
            result = new Gson().fromJson(response.substring(begin, end),
                new TypeToken<Map<String, Object>>() {
                }.getType());
        } else {
            String[] paras = response.split("&");
            for (String para : paras) {
                result.put(para.split("=")[0], para.split("=")[1]);
            }
        }

        return result;
    }

    @Override
    public XN001400Res doGetDetailUser(String userId) {
        User user = userBO.getUser(userId);
        if (user == null) {
            throw new BizException("li01004", userId + "用户不存在");
        }
        XN001400Res res = new XN001400Res();

        res.setUserId(userId);
        res.setLoginName(user.getLoginName());
        res.setNickname(user.getNickname());
        res.setMobile(user.getMobile());

        res.setStatus(user.getStatus());
        res.setRealName(user.getRealName());

        res.setUserReferee(user.getUserReferee());
        res.setIdKind(user.getIdKind());
        res.setIdNo(user.getIdNo());

        if (StringUtils.isNotBlank(user.getIdNo())) {
            res.setIdentityFlag(EBoolean.YES.getCode());
        } else {
            res.setIdentityFlag(EBoolean.NO.getCode());
        }
        if (StringUtils.isNotBlank(user.getTradePwdStrength())) {
            res.setTradepwdFlag(EBoolean.YES.getCode());
        } else {
            res.setTradepwdFlag(EBoolean.NO.getCode());
        }
        res.setCompanyCode(user.getCompanyCode());
        // 获取用户扩展信息
        res.setProvince(user.getProvince());
        res.setCity(user.getCity());
        res.setArea(user.getArea());
        res.setAddress(user.getAddress());
        res.setPhoto(user.getPhoto());
        res.setLatitude(user.getLatitude());
        res.setLongitude(user.getLongitude());

        return res;
    }

    /** 
     * @see com.std.user.ao.IUserAO#doUpLevel(java.lang.String, java.lang.String)
     */
    @Override
    public void doUpLevel(String userId, String level) {
        User data = new User();
        data.setUserId(userId);
        userBO.refreshLevel(data);
    }

    @Override
    public void blacklist(String userId) {
        User data = userBO.getUser(userId);
        userBO.refereshBlack(data);
    }

    @Override
    public void whitelist(String userId) {
        User data = userBO.getUser(userId);
        userBO.refereshWhite(data);
    }
}
