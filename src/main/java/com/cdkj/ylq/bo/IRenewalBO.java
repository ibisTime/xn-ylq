package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.Renewal;

public interface IRenewalBO extends IPaginableBO<Renewal> {

    public Renewal applyRenewal(Borrow borrow);

    public List<Renewal> queryRenewalList(Renewal condition);

    public List<Renewal> queryRenewalListByPayGroup(String payGroup);

    public int renewalSuccess(Renewal renewal, String payCode, String payType,
            Integer curNo);

    public Renewal getRenewal(String code);

    public int getTotalRenewalCount(String userId);

}
