/**
 * @Title AccountAOImpl.java 
 * @Package com.cdkj.ylq.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月10日 上午11:09:14 
 * @version V1.0   
 */
package com.cdkj.ylq.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IAccountAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月10日 上午11:09:14 
 * @history:
 */
@Service
public class AccountAOImpl implements IAccountAO {

    @Autowired
    private IAccountBO accountBO;

    @Autowired
    private IBorrowBO borrowBO;

    /** 
     * @see com.cdkj.ylq.ao.IAccountAO#editBankcard(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void editBankcard(String userId, String code, String realName,
            String bankcardNumber, String bankCode, String bankName,
            String status) {
        Borrow condition = new Borrow();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        statusList.add(EBorrowStatus.OVERDUE.getCode());
        condition.setApplyUser(userId);
        condition.setStatusList(statusList);
        List<Borrow> borrows = borrowBO.queryBorrowList(condition);
        if (borrows.size() > 0) {
            throw new BizException("xn623850", "您当前有正在进行的借款，暂时无法修改银行卡！");
        }
        accountBO.editBankcard(code, realName, bankcardNumber, bankCode,
            bankName, status);
    }
}
