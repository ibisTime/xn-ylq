package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IRepayApplyBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.IRepayApplyDAO;
import com.cdkj.ylq.domain.RepayApply;
import com.cdkj.ylq.enums.ERepayApplyStatus;
import com.cdkj.ylq.exception.BizException;

@Component
public class RepayApplyBOImpl extends PaginableBOImpl<RepayApply> implements
        IRepayApplyBO {

    @Autowired
    private IRepayApplyDAO repayApplyDAO;

    @Override
    public int saveRepayApply(RepayApply data) {
        int count = 0;
        if (data != null) {
            count = repayApplyDAO.insert(data);
        }
        return count;
    }

    @Override
    public void doApprove(RepayApply data, String status, String approver,
            String approveNote) {
        Date now = new Date();
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(now);
        data.setApproveNote(approveNote);
        repayApplyDAO.updateApprove(data);
    }

    @Override
    public RepayApply getRepayApply(String code) {
        RepayApply data = null;
        if (StringUtils.isNotBlank(code)) {
            RepayApply condition = new RepayApply();
            condition.setCode(code);
            data = repayApplyDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "打款申请不存在");
            }
        }
        return data;
    }

    @Override
    public List<RepayApply> queryCurrentRepayApplyList(String applyUser) {
        RepayApply condition = new RepayApply();
        condition.setApplyUser(applyUser);
        condition.setStatus(ERepayApplyStatus.TO_APPROVE.getCode());
        return repayApplyDAO.selectList(condition);
    }

}
