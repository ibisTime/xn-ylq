package com.cdkj.ylq.callback;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdkj.ylq.ao.IBorrowAO;
import com.cdkj.ylq.enums.EBizType;
import com.cdkj.ylq.enums.EChannelType;
import com.cdkj.ylq.enums.EPayType;

/** 
 * @author: haiqingzheng 
 * @since: 2016年12月26日 下午1:44:16 
 * @history:
 */
@Controller
public class CallbackConroller {

    private static Logger logger = Logger.getLogger(CallbackConroller.class);

    @Autowired
    IBorrowAO borrowAO;

    @RequestMapping("/thirdPay/callback")
    public synchronized void doCallbackZhpay(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        boolean isSuccess = Boolean.valueOf(request.getParameter("isSuccess"));
        String channelType = request.getParameter("channelType");
        String payGroup = request.getParameter("payGroup");
        String payCode = request.getParameter("payCode");
        Long amount = Long.valueOf(request.getParameter("transAmount"));
        String bizType = request.getParameter("bizType");
        String payType = getPayType(channelType);
        // 支付成功，商户处理后同步返回给微信参数
        if (!isSuccess) {
            logger.info("****业务类型<" + bizType + "> payGroup <" + payGroup
                    + "> payCode <" + payCode + ">回调失败****");
        } else {
            try {
                if (EBizType.YLQ_REPAY.getCode().equals(bizType)) {
                    borrowAO.repaySuccess(payGroup, payType, payCode, amount);
                }
            } catch (Exception e) {
                logger.error("支付回调异常payGroup <" + payGroup + "> payCode <"
                        + payCode + ">异常如下：" + e.getMessage());
            }
        }
    }

    private String getPayType(String channelType) {
        String payType = null;
        if (EChannelType.Alipay.getCode().equals(channelType)) {
            payType = EPayType.ALIPAY.getCode();
        } else if (EChannelType.WeChat_APP.getCode().equals(channelType)) {
            payType = EPayType.WEIXIN_APP.getCode();
        }
        return payType;
    }
}
