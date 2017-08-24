package com.cdkj.ylq.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IBorrowDAO;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EProductLevel;
import com.cdkj.ylq.exception.BizException;

@Component
public class BorrowBOImpl extends PaginableBOImpl<Borrow> implements IBorrowBO {

    @Autowired
    private IBorrowDAO borrowDAO;

    @Override
    public String saveBorrow(Borrow data) {
        String code = null;
        if (data != null) {
            code = OrderNoGenerater.generateM(EGeneratePrefix.BORROW.getCode());
            data.setCode(code);
            borrowDAO.insert(data);
        }
        return code;
    }

    @Override
    public List<Borrow> queryBorrowList(Borrow condition) {
        return borrowDAO.selectList(condition);
    }

    @Override
    public Borrow getBorrow(String code) {
        Borrow data = null;
        if (StringUtils.isNotBlank(code)) {
            Borrow condition = new Borrow();
            condition.setCode(code);
            data = borrowDAO.select(condition);
            if (data == null) {
                throw new BizException("xn0000", "借款编号不存在");
            }
        }
        return data;
    }

    @Override
    public String addPayGroup(String code) {
        String payGroup = null;
        if (StringUtils.isNotBlank(code)) {
            Borrow data = new Borrow();
            data.setCode(code);
            payGroup = OrderNoGenerater.generateM(EGeneratePrefix.PAY_GROUP
                .getCode());
            data.setPayGroup(payGroup);
            borrowDAO.updatePayGroup(data);
        }
        return payGroup;
    }

    @Override
    public void doApprove(Borrow data, String status, String approver,
            String approveNote) {
        Date now = new Date();
        data.setStatus(status);
        data.setApprover(approver);
        data.setApproveDatetime(now);
        data.setApproveNote(approveNote);
        data.setUpdater(approver);
        data.setUpdateDatetime(now);
        data.setRemark("已完成审核");
        borrowDAO.updateApprove(data);
    }

    @Override
    public int loan(Borrow data, String updater, String remark) {
        int count = 0;
        if (data != null) {
            Date now = new Date();
            Date fkDatetime = now;
            Date jxDatetime = DateUtil.getTomorrowStart(fkDatetime);
            Date hkDatetime = DateUtil.getRelativeDate(jxDatetime,
                data.getDuration() * 24 * 3600 - 1);
            data.setFkDatetime(fkDatetime);
            data.setJxDatetime(jxDatetime);
            data.setHkDatetime(hkDatetime);
            data.setStatus(EBorrowStatus.LOANING.getCode());
            data.setUpdater(updater);
            data.setUpdateDatetime(now);
            data.setRemark(remark);
            count = borrowDAO.updateLoan(data);
        }
        return count;
    }

    @Override
    public List<Borrow> queryBorrowListByPayGroup(String payGroup) {
        Borrow condition = new Borrow();
        condition.setPayGroup(payGroup);
        return borrowDAO.selectList(condition);
    }

    @Override
    public int repaySuccess(Borrow borrow, Long payAmount, String payCode,
            String payType) {
        int count = 0;
        if (borrow != null && StringUtils.isNotBlank(borrow.getCode())) {
            Date now = new Date();
            borrow.setRealHkDatetime(now);
            borrow.setRealHkAmount(payAmount);
            borrow.setPayCode(payCode);
            borrow.setPayType(payType);
            borrow.setStatus(EBorrowStatus.REPAY.getCode());
            borrow.setRemark("已还款");
            count = borrowDAO.updateRepaySuccess(borrow);
        }
        return count;
    }

    @Override
    public int confirmBad(Borrow data) {
        int count = 0;
        if (data != null) {
            count = borrowDAO.updateConfirmBad(data);
        }
        return count;
    }

    @Override
    public int overdue(Borrow data) {
        int count = 0;
        if (data != null) {
            count = borrowDAO.updateOverdue(data);
        }
        return count;
    }

    @Override
    public Borrow getCurrentBorrow(String userId) {
        Borrow condition = new Borrow();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.TO_APPROVE.getCode());
        statusList.add(EBorrowStatus.APPROVE_YES.getCode());
        statusList.add(EBorrowStatus.LOANING.getCode());
        statusList.add(EBorrowStatus.OVERDUE.getCode());
        condition.setApplyUser(userId);
        condition.setStatusList(statusList);
        return borrowDAO.select(condition);
    }

    @Override
    public EProductLevel getUserBorrowLevel(String userId) {
        EProductLevel level = EProductLevel.ONE;
        Borrow condition = new Borrow();
        condition.setApplyUser(userId);
        condition.setStatus(EBorrowStatus.REPAY.getCode());
        condition.setLevel(EProductLevel.THREE.getCode());
        if (borrowDAO.selectTotalCount(condition) >= 2) {
            level = EProductLevel.FOUR;
        } else {
            condition.setLevel(EProductLevel.TWO.getCode());
            if (borrowDAO.selectTotalCount(condition) >= 2) {
                level = EProductLevel.THREE;
            } else {
                condition.setLevel(EProductLevel.ONE.getCode());
                if (borrowDAO.selectTotalCount(condition) >= 2) {
                    level = EProductLevel.TWO;
                }
            }
        }
        return level;
    }

    @Override
    public int archive(Borrow data) {
        int count = 0;
        count = borrowDAO.updateArchive(data);
        return count;
    }

}
