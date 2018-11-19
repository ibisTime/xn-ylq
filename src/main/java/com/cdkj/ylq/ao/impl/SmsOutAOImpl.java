package com.cdkj.ylq.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.ISmsOutAO;
import com.cdkj.ylq.bo.ISmsOutBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.enums.ECaptchaType;
import com.cdkj.ylq.exception.BizException;

@Service
public class SmsOutAOImpl implements ISmsOutAO {

    @Autowired
    ISmsOutBO smsOutBO;

    @Autowired
    IUserBO userBO;

    @Override
    public void sendSmsCaptcha(String mobile, String bizType, String companyCode) {
        if (ECaptchaType.C_REG.getCode().equals(bizType)
                || ECaptchaType.C_WX_RED.getCode().equals(bizType)) {
            userBO.isMobileExist(mobile, companyCode);
        }
        smsOutBO.sendCaptcha(mobile, bizType, companyCode);
    }

    @Override
    public void sendEmailCaptcha(String email, String bizType) {
        if ("805043".equals(bizType)) {
            User condition = new User();
            condition.setEmail(email);
            if (userBO.getTotalCount(condition) > 0) {
                throw new BizException("xn000000", "邮箱已经被使用");
            }
        }
        smsOutBO.sendCaptcha(email, bizType, null);
    }
}
