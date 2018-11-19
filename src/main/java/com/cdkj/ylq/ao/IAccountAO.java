/**
 * @Title IAccountAO.java 
 * @Package com.std.user.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月10日 上午11:08:04 
 * @version V1.0   
 */
package com.cdkj.ylq.ao;

import java.util.List;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Account;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月10日 上午11:08:04 
 * @history:
 */
public interface IAccountAO {

    String DEFAULT_ORDER_COLUMN = "account_number";

    // 分页查询账户
    public Paginable<Account> queryAccountPage(int start, int limit,
            Account condition);

    // 根据accountNumber查询账户
    public Account getAccount(String accountNumber);

    // 根据用户编号获取账户列表
    public List<Account> getAccountByUserId(String userId, String currency);

    // 查询各个端人民币账户总余额
    public List<Account> getAccountAmountSumList(String currency,
            String status, String type);

    public void editBankcard(String userId, String code, String realName,
            String bankcardNumber, String bankCode, String bankName,
            String status);

}
