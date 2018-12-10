package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
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
    public void doApprove(Apply data, String status, BigDecimal sxAmount,
            String approver, String remark) {
        Date now = new Date();
        data.setStatus(status);
        data.setCreditScore(sxAmount);
        data.setApprover(approver);
        data.setApproveDatetime(now);
        data.setApproveNote(remark);
        data.setUpdater(approver);
        data.setUpdateDatetime(now);
        data.setRemark("已完成人工审核");
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
    public Apply getCurrentApply(String userId) {
        Apply condition = new Apply();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EApplyStatus.TO_CERTI.getCode());
        statusList.add(EApplyStatus.TO_APPROVE.getCode());
        statusList.add(EApplyStatus.APPROVE_NO.getCode());
        condition.setApplyUser(userId);
        condition.setStatusList(statusList);
        return applyDAO.select(condition);
    }

    @Override
    public void toDoApprove(Apply data) {
        data.setStatus(EApplyStatus.TO_APPROVE.getCode());
        applyDAO.updateToDoApprove(data);
    }

    @Override
    public void refreshStatus(Apply data) {
        applyDAO.updateStatus(data);
    }

    @Override
    public void refreshCurrentApplyStatus(String userId, EApplyStatus status) {
        Apply apply = getCurrentApply(userId);
        apply.setStatus(status.getCode());
        refreshStatus(apply);
    }

    @Override
    public void resubmit(Apply data) {
        applyDAO.updateResubmit(data);
    }

    @Override
    public Apply getInCertApply(String userId) {
        Apply condition = new Apply();
        condition.setApplyUser(userId);
        condition.setStatus(EApplyStatus.TO_CERTI.getCode());
        Apply apply = applyDAO.select(condition);
        return apply;
    }

    @Override
    public void refreshCurNode(Apply apply, String curNode) {

    }

    @Override
    public List<Apply> queryApplyList(Apply condition) {

        return applyDAO.selectList(condition);
    }

    @Override
    public void refreshCreditScore(String userId, BigDecimal amount) {
        Apply condition = new Apply();
        condition.setStatus(EApplyStatus.APPROVE_YES.getCode());
        condition.setApplyUser(userId);
        Apply apply = applyDAO.select(condition);
        if (apply != null) {
            apply.setCreditScore(apply.getCreditScore().subtract(amount));
        }
        applyDAO.updateApprove(apply);
    }

}
