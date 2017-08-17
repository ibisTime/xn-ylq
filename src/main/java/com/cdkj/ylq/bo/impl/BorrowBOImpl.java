package com.cdkj.ylq.bo.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IBorrowDAO;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;
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
    public int loan(Borrow data) {
        int count = 0;
        if (data != null) {
            count = borrowDAO.updateLoan(data);
        }
        return count;
    }

    @Override
    public int repay(Borrow data) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Borrow> queryBorrowListByPayGroup(String payGroup) {
        Borrow condition = new Borrow();
        condition.setPayGroup(payGroup);
        return borrowDAO.selectList(condition);
    }

    @Override
    public int paySuccess(Borrow borrow, Long payAmount, String payCode,
            String payType) {
        int count = 0;
        if (borrow != null && StringUtils.isNotBlank(borrow.getCode())) {
            Date now = new Date();
            borrow.setRealHkDatetime(now);
            borrow.setRealHkAmount(payAmount);
            borrow.setPayCode(payCode);
            borrow.setPayType(payType);
            borrow.setStatus(EBorrowStatus.REPAY.getCode());
            count = borrowDAO.updateRepaySuccess(borrow);
        }
        return count;
    }
}
