package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.enums.EProductLevel;

public interface IBorrowBO extends IPaginableBO<Borrow> {

    public String saveBorrow(Borrow data);

    public List<Borrow> queryBorrowList(Borrow condition);

    public Borrow getBorrow(String code);

    public Borrow getCurrentBorrow(String userId);

    public int loan(Borrow data);

    public String addPayGroup(String code);

    public int confirmBad(Borrow data);

    public int overdue(Borrow data);

    public int repaySuccess(Borrow borrow, Long payAmount, String payCode,
            String payType);

    public int archive(Borrow data);

    public List<Borrow> queryBorrowListByPayGroup(String payGroup);

    // 用户当前可借产品等级
    public EProductLevel getUserBorrowLevel(String userId);

}
