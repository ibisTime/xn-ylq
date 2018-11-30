package com.cdkj.ylq.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.enums.ECertiKey;
import com.cdkj.ylq.enums.ECertificationStatus;

public interface ICertificationBO extends IPaginableBO<Certification> {

    public String saveCertification(Certification data);

    public int refreshCertification(Certification data);

    public int refreshFlag(Certification data, ECertificationStatus status);

    public Certification getCertification(Long id);

    // 根据认证标识和用户ID获取认证记录
    public Certification getCertification(String userId, ECertiKey certiKey);

    // 同盾运营商使用，认证中状态，根据taskid获取认证信息
    public Certification getCarrierCertificationByTaskId(String taskId);

    // 根据userId查询用户认证信息列表
    public List<Certification> queryCertificationList(String userId);

    public List<Certification> queryCertificationList(Certification condition);

    // 认证失效
    public void makeInvalid(Certification certification);

    // 额度重置为0
    public void resetSxAmount(String userId);

    // 额度加减
    public void refreshSxAmount(String userId, BigDecimal amount);

    // 是否完成必要认证
    public boolean isCompleteCerti(String userId);

    public List<Certification> queryCertiedList(String userId);

}
