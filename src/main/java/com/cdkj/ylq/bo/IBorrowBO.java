package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Borrow;

public interface IBorrowBO extends IPaginableBO<Borrow> {

    public String saveBorrow(Borrow data);

    public List<Borrow> queryBorrowList(Borrow condition);

    public Borrow getBorrow(String code);

}
