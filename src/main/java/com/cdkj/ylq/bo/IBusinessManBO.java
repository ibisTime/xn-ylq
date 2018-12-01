package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.dto.req.XN630100Req;

//CHECK ��鲢��ע�� 
public interface IBusinessManBO extends IPaginableBO<BusinessMan> {

    public boolean isBusinessManExist(String userId);

    public void isMobileExist(String mobile);

    public String saveBusinessMan(XN630100Req req);

    public int removeBusinessMan(String userId);

    public List<BusinessMan> queryBusinessManList(BusinessMan condition);

    public BusinessMan getBusinessMan(String userId);

    public BusinessMan getBusinessManByCompanyCode(String companyCode);

    public void refreshMobile(BusinessMan data, String mobile);

    public void refreshLoginPwd(BusinessMan data, String password);

    public void refreshStatus(BusinessMan data, String updater, String remark);

    public void refershRole(BusinessMan data, String roleCode, String updater,
            String remark);

    public void refershPhoto(BusinessMan data, String photo);

    public void refereshCompany(String userId, String comapnyCode);

    public BusinessMan getBusinessManByMobile(String mobile);

    public BusinessMan getBusinessBoss(String companyCode);

}
