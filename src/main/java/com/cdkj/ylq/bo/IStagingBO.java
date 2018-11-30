package com.cdkj.ylq.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.BorrowOrder;
import com.cdkj.ylq.domain.Staging;

//CHECK ��鲢��ע�� 
public interface IStagingBO extends IPaginableBO<Staging> {

    public boolean isStagingExist(String code);

    public String saveStaging(String applyUser, String orderCode,
            BigDecimal mainAmount, BigDecimal rate, Date startPayDate,
            Date lastPayDate, Long count, Integer batch, String companyCode);

    public List<Staging> queryStagingList(Staging condition);

    public Staging getStaging(String code);

    public void refreshRepay(String code, String payType, String payCode,
            BigDecimal amount);

    public List<Staging> queryBorrowStagings(BorrowOrder order);

    public void refreshOverdue(Staging staging);

    public Staging getNextStaging(Staging staging);

}
