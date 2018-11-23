package com.cdkj.ylq.bo;

import java.util.Date;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.SYSUser;
import com.cdkj.ylq.dto.req.XN630050Req;
import com.cdkj.ylq.enums.EUserStatus;

public interface ISYSUserBO extends IPaginableBO<SYSUser> {

    // 新增系统用户
    public String doSaveSYSuser(XN630050Req req);

    // 分配角色
    public void refreshRole(String userId, String roleCode, String updater,
            String remark);

    // 重置密码
    public void resetAdminLoginPwd(SYSUser user, String loginPwd);

    // 修改头像
    public void refreshPhoto(SYSUser user, String photo);

    // 判断手机号是否存在
    public void isMobileExist(String mobile);

    // 重置代理密码
    public void resetSelfPwd(SYSUser user, String newLoginPwd);

    // 修改绑定手机号
    public void resetBindMobile(SYSUser user, String newMobile);

    // 列表查询
    public List<SYSUser> queryUserList(SYSUser condition);

    // 登录判断
    public SYSUser getUserByMobile(String mobile);

    public void checkLoginPwd(String userId, String loginPwd);

    // 判断用户编号是否存在
    public boolean isUserExist(String userId);

    public void refreshStatus(String userId, EUserStatus status,
            String updater, String remark);

    // 查询详情
    public SYSUser getSYSUser(String userId);

    public SYSUser getSYSUserUnCheck(String userId);

    // 统计创建时间区间的总数
    public long getTotalCount(Date createDatetimeStart, Date createDatetimeEnd);

}
