/**
 * @Title IAccountBO.java 
 * @Package com.ibis.account.bo 
 * @Description 
 * @author miyb  
 * @date 2015-3-15 下午3:15:49 
 * @version V1.0   
 */
package com.cdkj.ylq.bo;

import java.math.BigDecimal;
import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.domain.BaofooPay;
import com.cdkj.ylq.dto.res.XN002500Res;
import com.cdkj.ylq.dto.res.XN002501Res;
import com.cdkj.ylq.dto.res.XN002510Res;
import com.cdkj.ylq.enums.EAccountStatus;
import com.cdkj.ylq.enums.EAccountType;
import com.cdkj.ylq.enums.EBizType;
import com.cdkj.ylq.enums.EChannelType;
import com.cdkj.ylq.enums.ECurrency;

/** 
 * @author: miyb 
 * @since: 2015-3-15 下午3:15:49 
 * @history:
 */
public interface IAccountBO extends IPaginableBO<Account> {

    // 根据用户编号和币种获取账户
    public Account getRemoteAccount(String userId, ECurrency currency);

    // 根据用户编号进行账户资金划转
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            ECurrency currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote, String refNo);

    public void doTransferAmountRemote(String fromUserId,
            ECurrency fromCurrency, String toUserId, ECurrency toCurrency,
            Long amount, EBizType bizType, String fromBizNote,
            String toBizNote, String refNo);

    public XN002500Res doWeiXinPayRemote(String applyUser, String toUser,
            String payGroup, String refNo, EBizType bizType, String bizNote,
            Long amount);

    public XN002501Res doWeiXinH5PayRemote(String applyUser, String openId,
            String toUser, String payGroup, String refNo, EBizType bizType,
            String bizNote, Long amount);

    public XN002510Res doAlipayRemote(String applyUser, String toUser,
            String payGroup, String refNo, EBizType bizType, String bizNote,
            Long amount);

    // 银行卡查询
    public Bankcard getBankcard(String userId);

    // 银行卡修改
    public void editBankcard(String code, String realName,
            String bankcardNumber, String bankCode, String bankName,
            String status);

    // 宝付代付
    public void baofooPay(List<BaofooPay> baofooPayList);

    // 宝付代付结果查询
    public void baofooPayQuery(List<String> borrowCodeList);

    // 宝付代扣
    public boolean baofooWithhold(String bankCode, String accountNo,
            String idNo, String realName, String mobile, Long transAmount,
            String refNo);

    // 分配账户
    public String distributeAccount(String userId, EAccountType accountType,
            String currency);

    // 变更账户余额：流水落地
    public Account changeAmount(Account dbAccount, BigDecimal transAmount,
            EChannelType channelType, String channelOrder, String refNo,
            String bizType, String bizNote);

    // 仅变更账户余额：流水不落地
    public void changeAmountNotJour(String accountNumber,
            BigDecimal transAmount, String lastOrder);

    // 冻结金额（余额变动）
    public Account frozenAmount(Account dbAccount, BigDecimal freezeAmount,
            String bizType, String bizNote, String refNo);

    // 解冻账户(冻结金额原路返回)
    public Account unfrozenAmount(Account dbAccount, BigDecimal unfreezeAmount,
            String bizType, String bizNote, String refNo);

    // 内部转账
    public void transAmount(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, BigDecimal transAmount,
            String fromBizType, String toBizType, String fromBizNote,
            String toBizNote, String refNo);

    // 内部转账
    public void transAmount(String fromUserId, String toUserId,
            String currency, BigDecimal transAmount, String fromBizType,
            String toBizType, String fromBizNote, String toBizNote, String refNo);

    // 内部转账
    public void transAmount(Account fromAccount, Account toAccount,
            BigDecimal transAmount, String fromBizType, String toBizType,
            String fromBizNote, String toBizNote, String refNo);

    // 更新账户状态
    public void refreshStatus(String accountNumber, EAccountStatus status);

    // 获取账户
    public Account getAccount(String accountNumber);

    // 通过用户编号和币种获取币种
    public Account getAccountByUser(String userId, String currency);

    // 获取账户列表
    public List<Account> queryAccountList(Account data);

    public List<Account> queryAccountList(String userId);

    public List<Account> queryAccountList(String userId, String currency);

    // 查询各个端人民币账户总余额
    public List<Account> queryAccountAmountSumList(Account condition);
}
