package com.cdkj.ylq.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.enums.EApplyStatus;

public interface IApplyBO extends IPaginableBO<Apply> {

    public String saveApply(Apply data);

    public void cancel(Apply data);

    public void toDoApprove(Apply data);

    public void doApprove(Apply data, String status, BigDecimal sxAmount,
            String approver, String remark);

    public Apply getApply(String code);

    public Apply getCurrentApply(String userId);

    public Apply getInCertApply(String userId);

    public void refreshCurNode(Apply apply, String curNode);

    public List<Apply> queryApplyList(Apply condition);

    public void refreshStatus(Apply data);

    public void resubmit(Apply data);

    public void refreshCurrentApplyStatus(String userId, EApplyStatus status);

}
