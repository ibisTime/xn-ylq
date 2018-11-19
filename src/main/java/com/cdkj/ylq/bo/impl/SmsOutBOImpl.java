package com.cdkj.ylq.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.dto.req.XN001200Req;
import com.cdkj.ylq.dto.req.XN001201Req;
import com.cdkj.ylq.dto.req.XN804080Req;
import com.cdkj.ylq.dto.req.XN804081Req;
import com.cdkj.ylq.dto.req.XN804082Req;
import com.cdkj.ylq.dto.res.BooleanRes;
import com.cdkj.ylq.dto.res.PKCodeRes;
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
    public void sendContent(String mobile, String content, String companyCode,
            String systemCode) {
        try {
            XN001201Req req = new XN001201Req();
            req.setTokenId(mobile);
            req.setMobile(mobile);
            req.setContent(content);
            req.setCompanyCode(companyCode);
            req.setSystemCode(systemCode);
            BizConnecter.getBizData("001201", JsonUtils.object2Json(req),
                Object.class);
        } catch (Exception e) {
            logger.error("调用短信发送服务异常, 原因：" + e.getMessage());
        }
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
    }
}
