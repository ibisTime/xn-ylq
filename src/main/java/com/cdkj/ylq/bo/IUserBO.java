package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.enums.EUserStatus;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:13 
 * @history:
 */
public interface IUserBO extends IPaginableBO<User> {

    public String getSystemUser(String systemCode);

    public void doIdentify(String userId, String idKind, String idNo,
            String realName);

    // 将用户拉入黑名单
    public void addBlacklist(String userId, String type, String updater,
            String remark);

    //
    public User doGetUserByOpenId(String appOpenId, String h5OpenId,
            String companyCode, String systemCode);

    // 根据手机号和类型判断手机号是否存在
    public void isMobileExist(String mobile, String companyCode);

    // 判断登录名是否存在
    public void isLoginNameExist(String loginName, String kind,
            String companyCode);

    public String getUserId(String mobile, String companyCode);

    // 查询openId
    public void doCheckOpenId(String unionId, String h5OpenId,
            String appOpenId, String companyCode, String systemCode);

    // 前端用户注册
    public String doRegister(String mobile, String loginPwd,
            String userReferee, String province, String city, String area,
            String address, String companyCode, String createClient,
            String isCoupon);

    public String doRegister(String unionId, String h5OpenId, String appOpenId,
            String mobile, String kind, String loginPwd, String nickname,
            String photo, String gender, String userReferee,
            String companyCode, String systemCode);

    public void refreshWxInfo(String userId, String type, String unionId,
            String openId, String nickname, String photo, String gender);

    public String saveUser(String mobile, String kind, String companyCode,
            String systemCode);

    // 判断用户编号是否存在
    public boolean isUserExist(String userId, String systemCode);

    // 验证支付密码:拿tradePwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkTradePwd(String userId, String tradePwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd);

    // 验证登录密码:拿loginPwd进行MD5后与数据库中userId得数据库支付密码比对
    public void checkLoginPwd(String userId, String loginPwd, String alertStr);

    // 校验是否已经有人实名认证
    public void checkIdentify(String kind, String idKind, String idNo,
            String realName);

    // 判断推荐人是否存在(手机号)
    public void checkUserReferee(String userReferee, String systemCode);

    public int refreshIdentity(String userId, String realName, String idKind,
            String idNo);

    public int refreshRealName(String userId, String realName);

    public int refreshLoginPwd(String userId, String loginPwd);

    public int refreshTradePwd(String userId, String tradePwd);

    public int refreshMobile(String userId, String mobile);

    public int refreshBindMobile(String userId, String loginName,
            String mobile, String loginPwd, String loginPwdStrength);

    public User getUser(String userId);

    public List<User> getUsersByUserReferee(String userReferee);

    public User getUserByLoginName(String loginName, String systemCode);

    public List<User> queryUserList(User condition);

    public void refreshStatus(String userId, EUserStatus normal,
            String updater, String remark);

    public void refreshRole(String userId, String roleCode, String updater,
            String remark);

    public void refreshPdf(String userId, String pdf, String updater,
            String remark);

    public void refreshLoginName(String userId, String loginName);

    public void refreshNickname(String userId, String nickname);

    public void refreshPhoto(String userId, String photo);

    public void refreshCompany(String userId, String companyCode);

    public void refreshUser(User data);

    public void refreshUserSupple(User data);

    public void refreshLevel(User data);

    public List<User> queryUserList(String mobile, String kind,
            String systemCode);

    public void approveUser(String userId, String approver, String status,
            Double divRate, String remark);

    public int refreshDivRate(String userId, Double divRate);

    public User getUserUnCheck(String userId);

    public Long totalUser(User condition);

    public void refereshBlack(User data);

    public void refereshWhite(User data);

    public void refreshIsCoupon(User data);

    public List<User> getNoCouponList(String referee, String companyCode);
}
