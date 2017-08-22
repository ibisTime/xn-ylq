package com.cdkj.ylq.bo;

import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.dto.res.XN805041Res;
import com.cdkj.ylq.enums.EUserKind;

/**
 * @author: xieyj 
 * @since: 2016年5月30日 上午9:28:13 
 * @history:
 */
public interface IUserBO {

    public void checkTradePwd(String userId, String tradePwd);

    public User getRemoteUser(String userId);

    public String isUserExist(String mobile, EUserKind kind, String systemCode);

    public String getSystemUser(String systemCode);

    public void doIdentify(String userId, String idKind, String idNo,
            String realName);

    // 前端用户注册
    public XN805041Res doRegister(String mobile, String loginPwd,
            String userReferee, String userRefereeKind, String smsCaptcha,
            String kind, String isRegHx, String province, String city,
            String area, String companyCode, String systemCode);
}
