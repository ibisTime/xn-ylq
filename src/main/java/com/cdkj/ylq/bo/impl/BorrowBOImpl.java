package com.cdkj.ylq.bo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IBorrowDAO;
import com.cdkj.ylq.domain.Borrow;
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
                throw new BizException("xn0000", "记录不存在");
            }
        }
        return data;
    }
}
