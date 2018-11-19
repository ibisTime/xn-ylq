/**
 * @Title AccountAOImpl.java 
 * @Package com.cdkj.ylq.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月10日 上午11:09:14 
 * @version V1.0   
 */
package com.cdkj.ylq.ao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IAccountAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IBorrowBO;
import com.cdkj.ylq.bo.IJourBO;
import com.cdkj.ylq.bo.ISYSUserBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.SYSUser;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.EJourDirection;
import com.cdkj.ylq.enums.ESystemAccount;
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

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IJourBO jourBO;

    @Override
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition) {
        Paginable<Account> page = accountBO.getPaginable(start, limit,
            condition);
        if (null != page) {
            List<Account> list = page.getList();
            for (Account account : list) {
                initAccount(account);
            }
        }
        return page;
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account account = accountBO.getAccount(accountNumber);
        initAccount(account);
        return account;
    }

    @Override
    @Transactional
    public List<Account> getAccountByUserId(String userId, String currency) {
        List<Account> list = accountBO.queryAccountList(userId, currency);

        if (CollectionUtils.isNotEmpty(list)) {
            for (Account account : list) {
                // 历史总发放/回收额(积分/碳泡泡)
                if (ESystemAccount.SYS_ACOUNT_JF_POOL.getCode().equals(
                    account.getAccountNumber())
                        || ESystemAccount.SYS_ACOUNT_TPP_POOL.getCode().equals(
                            account.getAccountNumber())) {

                    BigDecimal historyInAmount = jourBO
                        .getHistoryAmount(account.getAccountNumber(),
                            EJourDirection.IN.getCode());
                    BigDecimal historyOutAmount = jourBO.getHistoryAmount(
                        account.getAccountNumber(),
                        EJourDirection.OUT.getCode());

                    account.setHistoryInAmount(historyInAmount);
                    account.setHistoryOutAmount(historyOutAmount);
                }
            }
        }

        return list;
    }

    private void initAccount(Account account) {

        // 户名
        String realName = null;

        // 其他用户
        SYSUser sysUser = sysUserBO.getSYSUserUnCheck(account.getUserId());
        if (null != sysUser) {
            realName = sysUser.getMobile();
            account.setMobile(realName);
            if (StringUtils.isNotBlank(sysUser.getRealName())) {
                realName = sysUser.getRealName().concat("-").concat(realName);
            }

        }

        account.setRealName(realName);

    }

    @Override
    public List<Account> getAccountAmountSumList(String currency,
            String status, String type) {
        Account condition = new Account();
        condition.setCurrency(currency);
        condition.setStatus(status);
        condition.setType(type);
        return accountBO.queryAccountAmountSumList(condition);
    }

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
