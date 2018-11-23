/**
 * @Title IUserAO.java 
 * @Package com.cdkj.ylq.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月21日 下午9:20:22 
 * @version V1.0   
 */
package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.req.XN805042Req;
import com.cdkj.ylq.dto.req.XN805043Req;
import com.cdkj.ylq.dto.req.XN805081ZReq;
import com.cdkj.ylq.dto.req.XN805095Req;
import com.cdkj.ylq.dto.res.XN001400Res;
import com.cdkj.ylq.dto.res.XN805041Res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月21日 下午9:20:22 
 * @history:
 */
public interface IUserAO {

    String DEFAULT_ORDER_COLUMN = "user_id";

    // 检查手机号是否存在
    public void doCheckMobile(String mobile, String kind, String companyCode,
            String systemCode);

    // 注册前端用户
    public XN805041Res doRegister(String mobile, String loginPwd,
            String userReferee, String userRefereeKind, String smsCaptcha,
            String province, String city, String area, String address,
            String companyCode, String createClient);

    // 代注册
    public String doAddUser(XN805042Req req);

    // 申请注册
    public String doApplyRegUser(XN805043Req req);

    // 用户登录
    public String doLogin(String loginName, String loginPwd, String companyCode);

    // 验证码登录注册
    public String doCaptchaLoginReg(String mobile, String kind,
            String smsCaptcha, String companyCode, String systemCode);

    // 检查登录密码是否正确
    public void doCheckLoginPwd(String userId, String password);

    // 绑定手机号
    public void doBindMoblie(String userId, String mobile, String smsCaptcha,
            String isSendSms);

    // 更换手机号
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha);

    // 更换手机号_需支付密码
    public void doChangeMoblie(String userId, String newMobile,
            String smsCaptcha, String tradePwd);

    // 重置登录密码
    public void doResetLoginPwd(String mobile, String smsCaptcha,
            String newLoginPwd, String companyCode);

    // 修改登录密码
    public void doModifyLoginPwd(String userId, String oldLoginPwd,
            String newLoginPwd);

    // 修改昵称
    public void doModifyNickname(String userId, String nickname);

    // 管理员重置用户密码
    public void doResetLoginPwdByOss(String userId, String loginPwd,
            String adminUserId, String adminPwd);

    // 设置支付密码
    public void doSetTradePwd(String userId, String tradePwd, String smsCaptcha);

    // 重置支付密码
    public void doResetTradePwd(String userId, String newTradePwd,
            String smsCaptcha);

    // 重置支付密码(需实名认证)
    public void doResetTradePwd(String userId, String newTradePwd,
            String smsCaptcha, String idKind, String idNo);

    // 修改支付密码
    public void doModifyTradePwd(String userId, String oldTradePwd,
            String newTradePwd);

    // 本系统实名认证
    public void doIdentify(String userId, String idKind, String idNo,
            String realName);

    // 修改头像
    public void modifyPhoto(String userId, String photo);

    // 修改用户信息
    public void doModifyUserExt(XN805081ZReq req);

    // 完善手机号和身份信息
    public void doModfiyMobileAndIds(String userId, String mobile,
            String realName, String idKind, String idNo);

    // 修改经纬度
    public void doModifyLngLat(String userId, String longitude, String latitude);

    // 注销/激活用户
    public void doCloseOpen(String userId, String updater, String remark);

    // 设置角色
    public void doRoleUser(String userId, String roleCode, String updater,
            String remark);

    // 修改分成比例
    public void doModifyDivRate(String userId, Double divRate, String updater,
            String remark);

    // 修改用户等级
    public void doUpLevel(String userId, String level);

    // 修改用户信息
    public void doModifyUser(XN805095Req req);

    // 两要素实名认证
    public void doTwoIdentify(String userId, String idKind, String idNo,
            String realName);

    // 三四要素实名认证
    public void doFourIdentify(String userId, String idKind, String idNo,
            String realName, String cardNo, String bindMobile);

    // 芝麻认证
    public Object doZhimaIdentify(String userId, String idKind, String idNo,
            String realName);

    // 芝麻认证查询
    public Object doZhimaQuery(String userId, String bizNo);

    public Paginable<User> queryUserPage(int start, int limit, User condition);

    public List<User> queryUserList(User condition);

    public User doGetUser(String userId);

    public List<User> getUserRefereeList(String userId);

    // 查询用户详情(对外开放)
    public XN001400Res doGetDetailUser(String userId);

    // 根据手机号，种类，公司编号，系统编号获取用户编号
    public String doGetUserIdByCondition(String mobile, String kind,
            String companyCode, String systemCode);

    // 校验支付密码
    public void doCheckTradePwd(String userId, String tradePwd);

    public void blacklist(String userId);

    public void whitelist(String userId);
}
