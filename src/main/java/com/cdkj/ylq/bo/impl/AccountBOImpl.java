package com.cdkj.ylq.bo.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.PropertiesUtil;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.domain.BaofooPay;
import com.cdkj.ylq.dto.req.XN002050Req;
import com.cdkj.ylq.dto.req.XN002100Req;
import com.cdkj.ylq.dto.req.XN002500Req;
import com.cdkj.ylq.dto.req.XN002501Req;
import com.cdkj.ylq.dto.req.XN002510Req;
import com.cdkj.ylq.dto.req.XN802012Req;
import com.cdkj.ylq.dto.req.XN802016Req;
import com.cdkj.ylq.dto.req.XN802165Req;
import com.cdkj.ylq.dto.req.XN802166Req;
import com.cdkj.ylq.dto.req.XN802167Req;
import com.cdkj.ylq.dto.res.XN002050Res;
import com.cdkj.ylq.dto.res.XN002500Res;
import com.cdkj.ylq.dto.res.XN002501Res;
import com.cdkj.ylq.dto.res.XN002510Res;
import com.cdkj.ylq.dto.res.XN802167Res;
import com.cdkj.ylq.enums.EBizType;
import com.cdkj.ylq.enums.ECurrency;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.http.BizConnecter;
import com.cdkj.ylq.http.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class AccountBOImpl implements IAccountBO {
    static Logger logger = Logger.getLogger(AccountBOImpl.class);

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Override
    public Account getRemoteAccount(String userId, ECurrency currency) {
        XN002050Req req = new XN002050Req();
        req.setUserId(userId);
        req.setCurrency(currency.getCode());
        String jsonStr = BizConnecter.getBizData("002050",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<XN002050Res> list = gson.fromJson(jsonStr,
            new TypeToken<List<XN002050Res>>() {
            }.getType());
        if (CollectionUtils.isEmpty(list)) {
            throw new BizException("xn000000", "用户[" + userId + "]账户不存在");
        }
        XN002050Res res = list.get(0);
        Account account = new Account();
        account.setAccountNumber(res.getAccountNumber());
        account.setUserId(res.getUserId());
        account.setRealName(res.getRealName());
        account.setType(res.getType());
        account.setStatus(res.getStatus());

        account.setCurrency(res.getCurrency());
        account.setAmount(res.getAmount());
        account.setFrozenAmount(res.getFrozenAmount());
        account.setCreateDatetime(res.getCreateDatetime());
        account.setLastOrder(res.getLastOrder());

        account.setSystemCode(res.getSystemCode());
        return account;
    }

    @Override
    public void doTransferAmountRemote(String fromUserId, String toUserId,
            ECurrency currency, Long amount, EBizType bizType,
            String fromBizNote, String toBizNote, String refNo) {
        if (amount != null && amount != 0) {
            XN002100Req req = new XN002100Req();
            req.setFromUserId(fromUserId);
            req.setFromCurrency(currency.getCode());
            req.setToUserId(toUserId);
            req.setToCurrency(currency.getCode());
            req.setTransAmount(String.valueOf(amount));
            req.setBizType(bizType.getCode());
            req.setFromBizNote(fromBizNote);
            req.setToBizNote(toBizNote);
            req.setRefNo(refNo);
            BizConnecter.getBizData("002100", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    @Override
    public void doTransferAmountRemote(String fromUserId,
            ECurrency fromCurrency, String toUserId, ECurrency toCurrency,
            Long amount, EBizType bizType, String fromBizNote,
            String toBizNote, String refNo) {
        if (amount != null && amount != 0) {
            XN002100Req req = new XN002100Req();
            req.setFromUserId(fromUserId);
            req.setFromCurrency(fromCurrency.getCode());
            req.setToUserId(toUserId);
            req.setToCurrency(toCurrency.getCode());
            req.setTransAmount(String.valueOf(amount));
            req.setBizType(bizType.getCode());
            req.setFromBizNote(fromBizNote);
            req.setToBizNote(toBizNote);
            req.setRefNo(refNo);
            BizConnecter.getBizData("002100", JsonUtils.object2Json(req),
                Object.class);
        }
    }

    @Override
    public XN002500Res doWeiXinPayRemote(String applyUser, String toUser,
            String payGroup, String refNo, EBizType bizType, String bizNote,
            Long amount) {
        // 获取微信APP支付信息
        XN002500Req req = new XN002500Req();
        req.setApplyUser(applyUser);
        req.setToUser(toUser);
        req.setPayGroup(payGroup);
        req.setRefNo(refNo);
        req.setBizType(bizType.getCode());
        req.setBizNote(bizNote);
        req.setAmount(String.valueOf(amount));
        req.setBackUrl(PropertiesUtil.Config.PAY_BACK_URL);
        XN002500Res res = BizConnecter.getBizData("002500",
            JsonUtil.Object2Json(req), XN002500Res.class);
        return res;
    }

    /**
     * @see com.cdkj.ylq.bo.IAccountBO#doWeiXinH5PayRemote(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.cdkj.ylq.enums.EBizType, java.lang.String, java.lang.Long, java.lang.String)
     */
    @Override
    public XN002501Res doWeiXinH5PayRemote(String applyUser, String openId,
            String toUser, String payGroup, String refNo, EBizType bizType,
            String bizNote, Long amount) {
        if (StringUtils.isBlank(openId)) {
            throw new BizException("xn0000", "请先微信登录再支付");
        }
        // 获取微信APP支付信息
        XN002501Req req = new XN002501Req();
        req.setApplyUser(applyUser);
        req.setOpenId(openId);
        req.setToUser(toUser);
        req.setPayGroup(payGroup);
        req.setRefNo(refNo);
        req.setBizType(bizType.getCode());
        req.setBizNote(bizNote);
        req.setAmount(String.valueOf(amount));
        req.setBackUrl(PropertiesUtil.Config.PAY_BACK_URL);
        XN002501Res res = BizConnecter.getBizData("002501",
            JsonUtil.Object2Json(req), XN002501Res.class);
        return res;
    }

    @Override
    public XN002510Res doAlipayRemote(String applyUser, String toUser,
            String payGroup, String refNo, EBizType bizType, String bizNote,
            Long amount) {
        // 获取支付宝APP支付信息
        XN002510Req req = new XN002510Req();
        req.setApplyUser(applyUser);
        req.setToUser(toUser);
        req.setPayGroup(payGroup);
        req.setRefNo(refNo);
        req.setBizType(bizType.getCode());
        req.setBizNote(bizNote);
        req.setAmount(String.valueOf(amount));
        req.setBackUrl(PropertiesUtil.Config.PAY_BACK_URL);
        XN002510Res res = BizConnecter.getBizData("002510",
            JsonUtil.Object2Json(req), XN002510Res.class);
        return res;
    }

    @Override
    public Bankcard getBankcard(String userId) {
        Bankcard bankcard = null;
        XN802016Req req = new XN802016Req();
        req.setUserId(userId);
        req.setSystemCode(ESystemCode.YLQ.getCode());
        String jsonStr = BizConnecter.getBizData("802016",
            JsonUtils.object2Json(req));
        Gson gson = new Gson();
        List<Bankcard> list = gson.fromJson(jsonStr,
            new TypeToken<List<Bankcard>>() {
            }.getType());
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
