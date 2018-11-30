package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IBankcardBO;
import com.cdkj.ylq.bo.IJourBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.PropertiesUtil;
import com.cdkj.ylq.core.AccountUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IAccountDAO;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.domain.BaofooPay;
import com.cdkj.ylq.dto.req.XN802012Req;
import com.cdkj.ylq.dto.req.XN802165Req;
import com.cdkj.ylq.dto.req.XN802166Req;
import com.cdkj.ylq.dto.req.XN802167Req;
import com.cdkj.ylq.dto.res.XN802167Res;
import com.cdkj.ylq.enums.EAccountStatus;
import com.cdkj.ylq.enums.EAccountType;
import com.cdkj.ylq.enums.EChannelType;
import com.cdkj.ylq.enums.ECurrency;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EJourBizTypeBoss;
import com.cdkj.ylq.enums.ESysUser;
import com.cdkj.ylq.enums.ESystemAccount;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;
import com.cdkj.ylq.http.BizConnecter;

@Component
public class AccountBOImpl extends PaginableBOImpl<Account> implements
        IAccountBO {
    static Logger logger = Logger.getLogger(AccountBOImpl.class);

    @Autowired
    private IAccountDAO accountDAO;

    @Autowired
    private IJourBO jourBO;

    @Autowired
    private IBankcardBO bankcardBO;

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public Account distributeAccount(String userId, EAccountType accountType,
            String currency) {
        String accountNumber = null;
        Account data = new Account();
        if (StringUtils.isNotBlank(userId)) {

            accountNumber = OrderNoGenerater.generateM(EGeneratePrefix.Account
                .getCode());

            data.setAccountNumber(accountNumber);
            data.setUserId(userId);
            data.setType(accountType.getCode());
            data.setCurrency(currency);
            data.setStatus(EAccountStatus.NORMAL.getCode());
            data.setAmount(BigDecimal.ZERO);
            data.setFrozenAmount(BigDecimal.ZERO);

            data.setTotalAmount(BigDecimal.ZERO);
            data.setMd5(AccountUtil.md5(data.getAmount()));
            data.setInAmount(BigDecimal.ZERO);
            data.setOutAmount(BigDecimal.ZERO);
            data.setCreateDatetime(new Date());

            accountDAO.insert(data);

        }
        return data;
    }

    @Override
    public Account changeAmount(Account dbAccount, BigDecimal transAmount,
            EChannelType channelType, String channelOrder, String refNo,
            String bizType, String bizNote) {

        // 如果变动金额为0，直接返回
        if (transAmount.compareTo(BigDecimal.ZERO) == 0) {
            return dbAccount;
        }
        // 金额变动之后可用余额
        BigDecimal avaliableAmount = dbAccount.getAmount()
            .subtract(dbAccount.getFrozenAmount()).add(transAmount);
        if (!dbAccount.getUserId().startsWith(ESysUser.SYS_USER.getCode())
                && avaliableAmount.compareTo(BigDecimal.ZERO) == -1) {// 特定账户余额可为负
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "账户可用余额不足");
        }
        BigDecimal nowAmount = dbAccount.getAmount().add(transAmount);

        // 记录流水
        String lastOrder = jourBO.addJour(dbAccount, channelType, channelOrder,
            refNo, bizType, bizNote, transAmount);

        // 更改余额
        dbAccount.setAmount(nowAmount);
        if (transAmount.longValue() > 0) {
            dbAccount.setTotalAmount(dbAccount.getTotalAmount()
                .add(transAmount));// 增加累计值
        }
        dbAccount.setMd5(AccountUtil.md5(dbAccount.getMd5(),
            dbAccount.getAmount(), nowAmount));

        // 统计累计充值金额
        dbAccount.setInAmount(dbAccount.getInAmount());
        if (EJourBizTypeBoss.CHARGE.getCode().equals(bizType)) {
            dbAccount.setInAmount(dbAccount.getInAmount().add(transAmount));
        }
        dbAccount.setLastOrder(lastOrder);
        accountDAO.updateAmount(dbAccount);
        return dbAccount;
    }

    @Override
    public void changeAmountNotJour(String accountNumber,
            BigDecimal transAmount, String lastOrder) {
        Account dbAccount = this.getAccount(accountNumber);
        BigDecimal nowAmount = dbAccount.getAmount().add(transAmount);
        if (!dbAccount.getUserId().startsWith("SYS_USER")
                && nowAmount.compareTo(BigDecimal.ZERO) == -1) {
            throw new BizException("xn000000", "账户余额不足");
        }
        // 更改余额
        Account data = new Account();
        data.setAccountNumber(accountNumber);
        data.setAmount(nowAmount);
        data.setMd5(AccountUtil.md5(dbAccount.getMd5(), dbAccount.getAmount(),
            nowAmount));

        data.setInAmount(dbAccount.getInAmount());
        data.setLastOrder(lastOrder);
        accountDAO.updateAmount(data);
    }

    @Override
    public Account frozenAmount(Account dbAccount, BigDecimal freezeAmount,
            String bizType, String bizNote, String refNo) {
        if (freezeAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return dbAccount;
        }
        BigDecimal avaliableAmount = dbAccount.getAmount()
            .subtract(dbAccount.getFrozenAmount()).subtract(freezeAmount);
        if (avaliableAmount.compareTo(BigDecimal.ZERO) == -1) {
            throw new BizException("xn000000", "账户余额不足");
        }
        // 记录冻结流水
        String lastOrder = jourBO.addFrozenJour(dbAccount, EChannelType.NBZ,
            null, refNo, bizType, bizNote, freezeAmount.negate());
        BigDecimal nowFrozenAmount = dbAccount.getFrozenAmount().add(
            freezeAmount);
        dbAccount.setAccountNumber(dbAccount.getAccountNumber());
        dbAccount.setFrozenAmount(nowFrozenAmount);
        dbAccount.setLastOrder(lastOrder);
        accountDAO.frozenAmount(dbAccount);
        return dbAccount;
    }

    @Override
    public Account unfrozenAmount(Account dbAccount, BigDecimal unfreezeAmount,
            String bizType, String bizNote, String refNo) {
        if (unfreezeAmount.compareTo(BigDecimal.ZERO) <= 0) {
            return dbAccount;
        }
        BigDecimal nowFrozenAmount = dbAccount.getFrozenAmount().subtract(
            unfreezeAmount);
        if (nowFrozenAmount.compareTo(BigDecimal.ZERO) == -1) {
            throw new BizException("xn000000", "本次解冻会使账户冻结金额小于0");
        }

        // 记录流水
        String lastOrder = jourBO.addFrozenJour(dbAccount, EChannelType.NBZ,
            null, refNo, bizType, bizNote, unfreezeAmount);
        dbAccount.setFrozenAmount(nowFrozenAmount);
        dbAccount.setLastOrder(lastOrder);
        accountDAO.unfrozenAmount(dbAccount);
        return dbAccount;
    }

    @Override
    public void refreshStatus(String accountNumber, EAccountStatus status) {
        if (StringUtils.isNotBlank(accountNumber)) {
            Account data = new Account();
            data.setAccountNumber(accountNumber);
            data.setStatus(status.getCode());
            accountDAO.updateStatus(data);
        }
    }

    @Override
    public Account getAccount(String accountNumber) {
        Account data = null;
        if (StringUtils.isNotBlank(accountNumber)) {
            Account condition = new Account();
            condition.setAccountNumber(accountNumber);
            data = accountDAO.select(condition);
            if (data == null) {
                throw new BizException("xn702502", "无对应账户，请检查账号正确性");
            }
        }
        return data;
    }

    @Override
    public List<Account> queryAccountList(Account data) {
        return accountDAO.selectList(data);
    }

    @Override
    public List<Account> queryAccountList(String userId) {
        Account condition = new Account();
        condition.setUserId(userId);
        return accountDAO.selectList(condition);
    }

    @Override
    public List<Account> queryAccountList(String userId, String currency) {
        Account condition = new Account();
        condition.setUserId(userId);
        condition.setCurrency(currency);
        return accountDAO.selectList(condition);
    }

    /**
    * @see com.std.account.bo.IAccountBO#getAccountByUser(java.lang.String,
    java.lang.String, java.lang.String)
    */
    @Override
    public Account getAccountByUser(String userId, String currency) {
        Account data = null;
        if (StringUtils.isNotBlank(userId) && StringUtils.isNotBlank(currency)) {
            Account condition = new Account();
            condition.setUserId(userId);
            condition.setCurrency(currency);
            List<Account> accountList = accountDAO.selectList(condition);
            if (CollectionUtils.isEmpty(accountList)) {
                throw new BizException(EBizErrorCode.DEFAULT.getCode(), "用户["
                        + userId + ";" + currency + "]无此类型账户");
            }
            if (ESysUser.SYS_USER.getCode().equals(userId)
                    && ECurrency.CNY.getCode().equals(currency)) {
                for (Account account : accountList) {
                    if (ESystemAccount.SYS_ACOUNT_CNY.getCode().equals(
                        account.getAccountNumber())) {
                        data = account;
                    }
                }
                if (data == null) {
                    throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                        "系统账户不存在");
                }
            } else {
                data = accountList.get(0);
            }
        }
        return data;
    }

    @Override
    public void transAmount(String fromUserId, String fromCurrency,
            String toUserId, String toCurrency, BigDecimal transAmount,
            String fromBizType, String toBizType, String fromBizNote,
            String toBizNote, String refNo) {
        if (transAmount.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        Account fromAccount = this.getAccountByUser(fromUserId, fromCurrency);
        Account toAccount = this.getAccountByUser(toUserId, toCurrency);
        transAmount(fromAccount, toAccount, transAmount, fromBizType,
            toBizType, fromBizNote, toBizNote, refNo);
    }

    @Override
    public void transAmount(String fromUserId, String toUserId,
            String currency, BigDecimal transAmount, String fromBizType,
            String toBizType, String fromBizNote, String toBizNote, String refNo) {
        if (transAmount.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        Account fromAccount = getAccountByUser(fromUserId, currency);
        Account toAccount = getAccountByUser(toUserId, currency);
        transAmount(fromAccount, toAccount, transAmount, fromBizType,
            toBizType, fromBizNote, toBizNote, refNo);
    }

    @Override
    public void transAmount(Account fromAccount, Account toAccount,
            BigDecimal transAmount, String fromBizType, String toBizType,
            String fromBizNote, String toBizNote, String refNo) {
        if (transAmount.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        String fromAccountNumber = fromAccount.getAccountNumber();
        String toAccountNumber = toAccount.getAccountNumber();
        if (fromAccountNumber.equals(toAccountNumber)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "来去双方账号一致，无需内部划转");
        }

        BigDecimal fromTransAmount = transRate(fromAccount, toAccount,
            transAmount);
        BigDecimal toTransAmount = transRate(toAccount, fromAccount,
            transAmount);

        this.changeAmount(fromAccount, fromTransAmount.negate(),
            EChannelType.NBZ, null, refNo, fromBizType, fromBizNote);
        this.changeAmount(toAccount, toTransAmount, EChannelType.NBZ, null,
            refNo, toBizType, toBizNote);
    }

    private BigDecimal transRate(Account fromAccount, Account toAccount,
            BigDecimal transAmount) {
        BigDecimal amount = transAmount;

        // if (fromAccount.getCurrency().equals(toAccount.getCurrency())) {
        //
        // // 相同币种划转
        // return amount;
        //
        // } else if (ECurrency.CNY.getCode().equals(toAccount.getCurrency())
        // && ECurrency.JF.getCode().equals(fromAccount.getCurrency())) {
        //
        // // 人民币转积分
        // Map<String, String> configMap = sysConfigBO
        // .getConfigsMap(ESysConfigType.PAY_RULE.getCode());
        // BigDecimal rate = new BigDecimal(
        // configMap.get(SysConstants.CNY2JF_RATE));
        //
        // amount = transAmount.multiply(rate);
        //
        // } else {
        // throw new BizException(EBizErrorCode.DEFAULT.getCode(), "暂不支持币种兑换");
        // }

        return amount;
    }

    @Override
    public List<Account> queryAccountAmountSumList(Account condition) {
        return accountDAO.selectAmountSumList(condition);
    }

    @Override
    public Bankcard getBankcard(String userId) {
        Bankcard bankcard = null;
        Bankcard condition = new Bankcard();
        condition.setUserId(userId);
        List<Bankcard> list = bankcardBO.queryBankcardList(condition);
        if (CollectionUtils.isNotEmpty(list)) {
            bankcard = list.get(0);
        }
        return bankcard;
    }

    @Override
    public void editBankcard(String code, String realName,
            String bankcardNumber, String bankCode, String bankName,
            String status) {
        XN802012Req req = new XN802012Req();
        req.setCode(code);
        req.setRealName(realName);
        req.setBankcardNumber(bankcardNumber);
        req.setBankCode(bankCode);
        req.setBankName(bankName);
        req.setStatus(status);
        BizConnecter.getBizData("802012", JsonUtil.Object2Json(req));
    }

    @Override
    public void baofooPay(List<BaofooPay> baofooPayList) {
        XN802165Req req = new XN802165Req();
        req.setPayInfoList(baofooPayList);
        req.setBackUrl(PropertiesUtil.Config.PAY_BACK_URL);
        req.setCompanyCode(ESystemCode.YLQ.getCode());
        req.setSystemCode(ESystemCode.YLQ.getCode());
        BizConnecter.getBizData("802165", JsonUtil.Object2Json(req));
    }

    @Override
    public void baofooPayQuery(List<String> borrowCodeList) {
        XN802166Req req = new XN802166Req();
        req.setBorrowCodeList(borrowCodeList);
        req.setCompanyCode(ESystemCode.YLQ.getCode());
        req.setSystemCode(ESystemCode.YLQ.getCode());
        BizConnecter.getBizData("802166", JsonUtil.Object2Json(req));
    }

    @Override
    public boolean baofooWithhold(String bankCode, String accountNo,
            String idNo, String realName, String mobile, Long transAmount,
            String refNo) {
        XN802167Req req = new XN802167Req();
        req.setBankCode(bankCode);
        req.setAccountNo(accountNo);
        req.setIdNo(idNo);
        req.setRealName(realName);
        req.setMobile(mobile);
        req.setTransAmount(String.valueOf(transAmount));
        req.setRefNo(refNo);
        req.setCompanyCode(ESystemCode.YLQ.getCode());
        req.setSystemCode(ESystemCode.YLQ.getCode());
        XN802167Res res = BizConnecter.getBizData("802167",
            JsonUtil.Object2Json(req), XN802167Res.class);
        return res.isSuccess();
    }

}
