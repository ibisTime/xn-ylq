package com.cdkj.ylq.bo;

import com.cdkj.ylq.domain.User;
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
}
