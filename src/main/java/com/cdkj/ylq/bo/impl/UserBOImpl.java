package com.cdkj.ylq.bo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.core.StringValidater;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.req.XN001100Req;
import com.cdkj.ylq.dto.req.XN001102Req;
import com.cdkj.ylq.dto.req.XN001350Req;
import com.cdkj.ylq.dto.req.XN001400Req;
import com.cdkj.ylq.dto.res.XN001102Res;
import com.cdkj.ylq.dto.res.XN001400Res;
import com.cdkj.ylq.dto.res.XNUserRes;
import com.cdkj.ylq.enums.ESysUser;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.enums.EUserKind;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.http.BizConnecter;
import com.cdkj.ylq.http.JsonUtils;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:30 
 * @history:
 */
@Component
public class UserBOImpl implements IUserBO {

    @Override
    public void checkTradePwd(String userId, String tradePwd) {
        if (StringUtils.isBlank(tradePwd)) {
            throw new BizException("XN000000", "请输入支付密码");
        }
        XN001100Req req = new XN001100Req();
        req.setTokenId(userId);
        req.setUserId(userId);
        req.setTradePwd(tradePwd);
        BizConnecter.getBizData("001100", JsonUtils.object2Json(req),
            Object.class);
    }

    @Override
    public User getRemoteUser(String userId) {
        User user = null;
        if (StringUtils.isNotBlank(userId)) {
            XN001400Req req = new XN001400Req();
            req.setTokenId(userId);
            req.setUserId(userId);
            XN001400Res res = BizConnecter.getBizData("001400",
                JsonUtils.object2Json(req), XN001400Res.class);
            if (res == null) {
                throw new BizException("XN000000", "编号为" + userId + "的用户不存在");
            }
            user = new User();
            user.setUserId(res.getUserId());
            user.setOpenId(res.getOpenId());
            user.setKind(res.getKind());
            user.setLoginName(res.getLoginName());
            user.setNickname(res.getNickname());
            user.setPhoto(res.getPhoto());
            user.setMobile(res.getMobile());
            user.setIdentityFlag(res.getIdentityFlag());
            user.setUserReferee(res.getUserReferee());
            user.setDivRate(StringValidater.toDouble(res.getDivRate()));
            user.setCompanyCode(res.getCompanyCode());
            user.setSystemCode(res.getSystemCode());
        }
        return user;
    }

    @Override
    public String isUserExist(String mobile, EUserKind kind, String systemCode) {
        String userId = null;
        XN001102Req req = new XN001102Req();
        req.setMobile(mobile);
        req.setKind(kind.getCode());
        req.setSystemCode(systemCode);
        XN001102Res res = BizConnecter.getBizData("001102",
            JsonUtils.object2Json(req), XN001102Res.class);
        if (res != null) {
            userId = res.getUserId();
        }
        return userId;
    }

    @Override
    public String doSaveBUser(String mobile, String userReferee,
            String updater, String systemCode, String companyCode) {
        XN001350Req req = new XN001350Req();
        req.setLoginName(mobile);
        req.setMobile(mobile);
        req.setUserReferee(userReferee);
        req.setUpdater(updater);
        req.setRemark("代注册商家");

        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        XNUserRes res = BizConnecter.getBizData("001350",
            JsonUtils.object2Json(req), XNUserRes.class);
        return res.getUserId();
    }

    @Override
    public String doSavePartnerUser(String mobile, String userReferee,
            String updater, String systemCode, String companyCode) {
        XN001350Req req = new XN001350Req();
        req.setLoginName(mobile);
        req.setMobile(mobile);
        req.setUserReferee(userReferee);
        req.setUpdater(updater);
        req.setRemark("代注册名宿主");

        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        XNUserRes res = BizConnecter.getBizData("001351",
            JsonUtils.object2Json(req), XNUserRes.class);
        return res.getUserId();
    }

    @Override
    public String getSystemUser(String systemCode) {
        if (ESystemCode.YLQ.getCode().equals(systemCode)) {
            return ESysUser.SYS_USER_YLQ.getCode();
        }
        return null;
    }
}
