package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.dto.req.XN630100Req;
import com.cdkj.ylq.dto.res.XN630101Res;

public interface IBusinessManAO {
    static final String DEFAULT_ORDER_COLUMN = "code";

    public String addBusinessMan(XN630100Req req);

    public Paginable<BusinessMan> queryBusinessManPage(int start, int limit,
            BusinessMan condition);

    public List<BusinessMan> queryBusinessManList(BusinessMan condition);

    public BusinessMan getBusinessMan(String userId);

    public XN630101Res doLogin(String loginName, String loginPwd);

    public void editMobile(String userId, String newMobile, String smsCaptcha,
            String remark);

    public void editLoginPwd(String mobile, String newLoginPwd,
            String smsCaptcha);

    public void resetLoginPwd(String userId, String newLoginPwd);

    public void editStatus(String userId, String updater, String remark);

    public void editRole(String userId, String roleCode, String updater,
            String remark);

    public void editPhoto(String userId, String photo);

    public BusinessMan getBusinessManByCompanyCode(String companyCode);

    public void editPwdByOld(String userId, String oldPwd, String newPwd);

    public void editPwdByAdmin(String userId, String adminPwd, String newPwd);

}
