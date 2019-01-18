package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.bo.IAccountBO;
import com.cdkj.ylq.bo.IBusinessManBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.domain.Account;
import com.cdkj.ylq.domain.BusinessMan;
import com.cdkj.ylq.dto.req.XN001200Req;
import com.cdkj.ylq.dto.req.XN804080Req;
import com.cdkj.ylq.dto.req.XN804081Req;
import com.cdkj.ylq.dto.req.XN804082Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.dto.res.PKCodeRes;
import com.cdkj.ylq.enums.EChannelType;
import com.cdkj.ylq.enums.ECurrency;
import com.cdkj.ylq.enums.EJourBizTypeBoss;
import com.cdkj.ylq.enums.EJourBizTypePlat;
import com.cdkj.ylq.enums.ESmsFeeType;
import com.cdkj.ylq.enums.ESystemAccount;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.http.BizConnecter;
import com.cdkj.ylq.http.JsonUtils;

/** 
 * @author: xieyj 
 * @since: 2016年5月25日 上午8:15:46 
 * @history:
 */
@Component
public class SmsOutBOImpl implements ISmsOutBO {
    static Logger logger = Logger.getLogger(SmsOutBOImpl.class);

    @Autowired
    private ISYSConfigBO sysConfigBO;

    @Autowired
    private IBusinessManBO businessManBO;

    @Autowired
    private IAccountBO accountBO;

    @Override
    public void sentContent(String ownerId, String content) {
        try {
            XN001200Req req = new XN001200Req();
            req.setTokenId(ownerId);
            req.setUserId(ownerId);
            req.setContent(content);
            BizConnecter.getBizData("001200", JsonUtils.object2Json(req),
                Object.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常, 原因：" + e.getMessage());
        }
    }

    @Override
    @Transactional
    public void sendContent(String mobile, String content, String companyCode,
            String systemCode) {
        try {
            XN804080Req req = new XN804080Req();
            req.setMobile(mobile);
            req.setContent(content);
            req.setType("M");
            req.setCompanyCode(companyCode);
            req.setSystemCode(systemCode);
            BizConnecter.getBizData("804080", JsonUtils.object2Json(req),
                Object.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常, 原因：" + e.getMessage());
            System.out.println("调用短信发送服务异常, 原因：" + e.getMessage());
        }
        BigDecimal fee = sysConfigBO.getBigDecimalValue(
            ESmsFeeType.BOSSDXFEE.getCode(), ESystemCode.YLQ.getCode());
        fee = fee.multiply(new BigDecimal(1000));
        // 短信费用
        BusinessMan man = businessManBO
            .getBusinessManByCompanyCode(companyCode);
        Account toAccount = accountBO.getAccount(ESystemAccount.SYS_ACOUNT_CNY
            .getCode());
        Account fromAccount = accountBO.getAccountByUser(man.getUserId(),
            ECurrency.CNY.getCode());
        accountBO.transAmount(fromAccount, toAccount, fee,
            EJourBizTypeBoss.SMSOUT.getCode(),
            EJourBizTypePlat.SMSIN.getCode(),
            EJourBizTypeBoss.SMSOUT.getValue(),
            EJourBizTypePlat.SMSIN.getValue(), mobile);
        BigDecimal outFee = sysConfigBO.getBigDecimalValue(
            ESmsFeeType.PLATDXFEE.getCode(), ESystemCode.YLQ.getCode())
            .multiply(new BigDecimal(1000));
        accountBO.changeAmount(toAccount, outFee.negate(), EChannelType.NBZ,
            null, mobile, EJourBizTypePlat.SMSOUT.getCode(),
            EJourBizTypePlat.SMSOUT.getValue());
    }

    @Override
    public void sendCaptcha(String mobile, String bizType, String companyCode) {
        try {
            XN804081Req req = new XN804081Req();
            req.setMobile(mobile);
            req.setBizType(bizType);
            req.setCompanyCode(companyCode);
            req.setSystemCode(ESystemCode.YLQ.getCode());
            BizConnecter.getBizData("804081", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用验证码发送服务异常");
        }
        BigDecimal fee = sysConfigBO.getBigDecimalValue(
            ESmsFeeType.BOSSDXFEE.getCode(), ESystemCode.YLQ.getCode());
        fee = fee.multiply(new BigDecimal(1000));
        // 短信费用
        BusinessMan man = businessManBO
            .getBusinessManByCompanyCode(companyCode);
        Account toAccount = accountBO.getAccount(ESystemAccount.SYS_ACOUNT_CNY
            .getCode());
        Account fromAccount = accountBO.getAccountByUser(man.getUserId(),
            ECurrency.CNY.getCode());
        accountBO.transAmount(fromAccount, toAccount, fee,
            EJourBizTypeBoss.SMSOUT.getCode(),
            EJourBizTypePlat.SMSIN.getCode(),
            EJourBizTypeBoss.SMSOUT.getValue(),
            EJourBizTypePlat.SMSIN.getValue(), mobile);
        BigDecimal outFee = sysConfigBO.getBigDecimalValue(
            ESmsFeeType.PLATDXFEE.getCode(), ESystemCode.YLQ.getCode())
            .multiply(new BigDecimal(1000));
        accountBO.changeAmount(toAccount, outFee.negate(), EChannelType.NBZ,
            null, mobile, EJourBizTypePlat.SMSOUT.getCode(),
            EJourBizTypePlat.SMSOUT.getValue());
    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType) {
        XN804082Req req = new XN804082Req();
        req.setMobile(mobile);
        req.setCaptcha(captcha);
        req.setBizType(bizType);
        req.setSystemCode(ESystemCode.YLQ.getCode());
        BizConnecter.getBizData("804082", JsonUtils.object2Json(req),
            BooleanRes.class);
    }

    @Override
    public void checkCaptcha(String mobile, String captcha, String bizType,
            String companyCode) {
        XN804082Req req = new XN804082Req();
        req.setMobile(mobile);
        req.setCaptcha(captcha);
        req.setBizType(bizType);
        req.setCompanyCode(companyCode);
        req.setSystemCode(ESystemCode.YLQ.getCode());
        BizConnecter.getBizData("804082", JsonUtils.object2Json(req),
            BooleanRes.class);
    }

    @Override
    public void sendSmsOut(String mobile, String content, String bizType,
            String companyCode) {
        try {
            XN804080Req req = new XN804080Req();
            req.setMobile(mobile);
            req.setContent(content);
            req.setType("M");
            req.setCompanyCode(companyCode);
            req.setSystemCode(ESystemCode.YLQ.getCode());
            BizConnecter.getBizData("804080", JsonUtils.object2Json(req),
                PKCodeRes.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常");
        }
        BigDecimal fee = sysConfigBO.getBigDecimalValue(
            ESmsFeeType.BOSSDXFEE.getCode(), ESystemCode.YLQ.getCode());
        fee = fee.multiply(new BigDecimal(1000));
        // 短信费用
        BusinessMan man = businessManBO
            .getBusinessManByCompanyCode(companyCode);
        Account toAccount = accountBO.getAccount(ESystemAccount.SYS_ACOUNT_CNY
            .getCode());
        Account fromAccount = accountBO.getAccountByUser(man.getUserId(),
            ECurrency.CNY.getCode());
        accountBO.transAmount(fromAccount, toAccount, fee,
            EJourBizTypeBoss.SMSOUT.getCode(),
            EJourBizTypePlat.SMSIN.getCode(),
            EJourBizTypeBoss.SMSOUT.getValue(),
            EJourBizTypePlat.SMSIN.getValue(), mobile);
        BigDecimal outFee = sysConfigBO.getBigDecimalValue(
            ESmsFeeType.PLATDXFEE.getCode(), ESystemCode.YLQ.getCode())
            .multiply(new BigDecimal(1000));
        accountBO.changeAmount(toAccount, outFee.negate(), EChannelType.NBZ,
            null, mobile, EJourBizTypePlat.SMSOUT.getCode(),
            EJourBizTypePlat.SMSOUT.getValue());
    }

}
