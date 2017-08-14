package com.cdkj.ylq.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.IApplyDAO;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.exception.BizException;

@Component
public class ApplyBOImpl extends PaginableBOImpl<Apply> implements IApplyBO {

    @Autowired
    private IApplyDAO applyDAO;

    @Override
    public String saveApply(Apply data) {
        String code = null;
        if (data != null) {
            applyDAO.insert(data);
        }
        return code;
    }

    @Override
    public void cancel(Apply data) {
        if (data != null) {
            data.setStatus(EApplyStatus.CANCEL.getCode());
            data.setUpdateDatetime(new Date());
            data.setUpdater(data.getApplyUser());
            data.setRemark("用户自主取消");
            applyDAO.updateCancel(data);
        }

    }

    @Override
    public void doApprove(Apply data, String status, Long sxAmount,
            String approver, String remark) {
        data.setStatus(status);
        data.setSxAmount(sxAmount);
        data.setApprover(approver);
        data.setApproveDatetime(new Date());
        data.setApproveNote(remark);
        applyDAO.updateApprove(data);
    }

    @Override
    public Apply getApply(String code) {
        Apply data = null;
        if (StringUtils.isNotBlank(code)) {
            Apply condition = new Apply();
            condition.setCode(code);
            data = applyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "额度申请记录不存在");
            }
        }
        return data;
    }

    @Override
    public Apply getCurrentApply(String userId, String productCode) {
        Apply condition = new Apply();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EApplyStatus.TO_CERTI.getCode());
        statusList.add(EApplyStatus.TO_APPROVE.getCode());
        statusList.add(EApplyStatus.APPROVE_NO.getCode());
        statusList.add(EApplyStatus.APPROVE_YES.getCode());
        statusList.add(EApplyStatus.TO_LOAN.getCode());
        statusList.add(EApplyStatus.LOANING.getCode());
        statusList.add(EApplyStatus.OVERDUE.getCode());
        condition.setApplyUser(userId);
        condition.setProductCode(productCode);
        condition.setStatusList(statusList);
        return applyDAO.select(condition);
    }

    @Override
    public void toDoApprove(Apply data) {
        data.setStatus(EApplyStatus.TO_APPROVE.getCode());
        applyDAO.updateToDoApprove(data);
    }

}
