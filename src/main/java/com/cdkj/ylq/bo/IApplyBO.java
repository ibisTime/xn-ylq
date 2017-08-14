package com.cdkj.ylq.bo;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Apply;

public interface IApplyBO extends IPaginableBO<Apply> {

    public String saveApply(Apply data);

    public void cancel(Apply data);

    public void toDoApprove(Apply data);

    public void doApprove(Apply data, String status, Long sxAmount,
            String approver, String remark);

    public Apply getApply(String code);

    public Apply getCurrentApply(String userId, String productCode);

}
