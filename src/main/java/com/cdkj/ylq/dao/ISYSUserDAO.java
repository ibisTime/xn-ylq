package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.SYSUser;

public interface ISYSUserDAO extends IBaseDAO<SYSUser> {

    String NAMESPACE = ISYSUserDAO.class.getName().concat(".");

    // 设置登录密码
    public int updateLoginPwd(SYSUser data);

    // 更新状态
    public void updateStatus(SYSUser data);

    // 更新角色
    public int updateRole(SYSUser data);

    // 更新用户名
    public int updateLoginName(SYSUser data);

    // 更新昵称
    public int updateNickname(SYSUser data);

    // 更新用户手机号和真实信息
    public int update(SYSUser data);

    public void resetBindMobile(SYSUser user);

    // 更新头像
    public int updatePhoto(SYSUser data);

    // 审核注册用户
    public void updateApproveSYSUser(SYSUser data);

    // 更新交易密码
    public int updateTradePwd(SYSUser data);

    // 更新公司
    public int updateCompany(SYSUser data);

}
