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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.IAccountAO;
import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IBorrowOrderBO;
import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.ICompanyBO;
import com.cdkj.ylq.bo.IJourBO;
import com.cdkj.ylq.bo.ISYSUserBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.AmountUtil;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.BorrowOrder;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.enums.EAccountType;
import com.cdkj.ylq.enums.EBorrowStatus;
import com.cdkj.ylq.enums.EChannelType;
import com.cdkj.ylq.enums.EJourBizTypePlat;
import com.cdkj.ylq.enums.ESystemAccount;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

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
    private IBorrowOrderBO borrowOrderBO;

    @Autowired
    private IBusinessManBO businessManBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private ISYSUserBO sysUserBO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private ICompanyBO companyBO;

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
    public List<Account> getAccountByUserId(String userId, String currency) {
        List<Account> dataList = accountBO.queryAccountList(userId, currency);
        for (Account account : dataList) {
            initAccount(account);
        }
        return dataList;
    }

    private void initAccount(Account account) {
        // 户名
        String realName = "平台";
        if (EAccountType.BUSINESS.getCode().equals(account.getType())) {
            // 其他用户
            BusinessMan businessMan = businessManBO.getBusinessMan(account
                .getUserId());
            if (null != businessMan) {
                realName = businessMan.getMobile();
                account.setMobile(realName);
                account.setBusinessMan(businessMan);
                if (StringUtils.isNotBlank(businessMan.getRealName())) {
                    realName = businessMan.getRealName().concat("-")
                        .concat(realName);
                }

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
        BorrowOrder condition = new BorrowOrder();
        List<String> statusList = new ArrayList<String>();
        statusList.add(EBorrowStatus.LOANING.getCode());
        statusList.add(EBorrowStatus.OVERDUE.getCode());
        condition.setApplyUser(userId);
        condition.setStatusList(statusList);
        List<BorrowOrder> borrows = borrowOrderBO.queryBorrowList(condition);
        if (borrows.size() > 0) {
            throw new BizException("xn623850", "您当前有正在进行的借款，暂时无法修改银行卡！");
        }
        accountBO.editBankcard(code, realName, bankcardNumber, bankCode,
            bankName, status);
    }

    // 取现回录
    @Override
    public void withdrawEnter(String accountNumber, BigDecimal amount,
            String withDate, String channelOrder, String withNote,
            String updater) {
        if (!ESystemAccount.SYS_ACOUNT_OFFLINE.getCode().equals(accountNumber)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "只支持系统托管账户");
        }

        Account account = accountBO.getAccount(accountNumber);
        if (account.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "账户余额不足");
        }

        String bizNote = "平台于" + withDate + "进行取现"
                + AmountUtil.div(amount, 1000L) + "元";
        if (StringUtils.isNotBlank(withNote)) {
            bizNote = bizNote + withNote;
        }
        accountBO.changeAmount(account, amount.negate(), EChannelType.Offline,
            channelOrder, channelOrder,
            EJourBizTypePlat.WITHDRAW_ENTER.getCode(), bizNote);
    }

}
