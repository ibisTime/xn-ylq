package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.RepayApply;

public interface IRepayApplyBO extends IPaginableBO<RepayApply> {

    public int saveRepayApply(RepayApply data);

    public void doApprove(RepayApply data, String status, String approver,
            String approveNote);

    public RepayApply getRepayApply(String code);

    public List<RepayApply> queryCurrentRepayApplyList(String applyUser);

}
