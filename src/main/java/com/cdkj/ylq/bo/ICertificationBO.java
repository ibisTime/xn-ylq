package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Certification;

public interface ICertificationBO extends IPaginableBO<Certification> {

    public String saveCertification(Certification data);

    public int refreshCertification(Certification data);

    public Certification getCertification(Long id);

    // 根据认证标识和用户ID获取认证记录
    public Certification getCertification(String userId, String certiKey);

    // 根据userId查询用户认证信息列表
    public List<Certification> queryCertificationList(String userId);

}
