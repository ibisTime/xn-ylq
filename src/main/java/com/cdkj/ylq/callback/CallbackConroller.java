package com.cdkj.ylq.callback;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cdkj.ylq.ao.IBorrowAO;
import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.ao.ICouponConditionAO;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.domain.MxCarrierNofification;
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

    private static final String HEADER_MOXIE_EVENT = "X-Moxie-Event";

    private static final String HEADER_MOXIE_TYPE = "X-Moxie-Type";

    private static final String HEADER_MOXIE_SIGNATURE = "X-Moxie-Signature";

    @Autowired
    IBorrowAO borrowAO;

    @Autowired
    ICouponConditionAO couponConditionAO;

    @Autowired
    ICertificationAO certificationAO;

    // ******第三方支付成功回调处理

    @RequestMapping("/thirdPay/callback")
    public synchronized void doCallbackYLQ(HttpServletRequest request,
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
                    String userId = borrowAO.repaySuccess(payGroup, payType,
                        payCode, amount);
                    if (StringUtils.isNotBlank(userId)) {
                        couponConditionAO.repaySuccess(userId);
                    }
                } else if (EBizType.YLQ_RENEWAL.getCode().equals(bizType)) {
                    borrowAO.renewalSuccess(payGroup, payType, payCode, amount);
                }
            } catch (Exception e) {
                logger.error("支付回调异常payGroup <" + payGroup + "> payCode <"
                        + payCode + ">异常如下：" + e.getMessage());
            }
        }
    }

    // ******魔蝎报告回调处理

    @RequestMapping("/mxcarrier/notifications")
    public synchronized void doCallbackMxCarrier(@RequestBody String body,
            ServletRequest request, ServletResponse response)
            throws IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 事件类型：task or bill
        String eventName = httpServletRequest.getHeader(HEADER_MOXIE_EVENT);

        // 业务类型：email、bank、carrier 等
        String eventType = httpServletRequest.getHeader(HEADER_MOXIE_TYPE);

        // body签名
        String signature = httpServletRequest.getHeader(HEADER_MOXIE_SIGNATURE);

        if (StringUtils.isBlank(eventName)) {
            writeMessage(httpServletResponse,
                HttpServletResponse.SC_BAD_REQUEST, "header not found:"
                        + HEADER_MOXIE_EVENT);
            return;
        }

        if (StringUtils.isBlank(eventType)) {
            writeMessage(httpServletResponse,
                HttpServletResponse.SC_BAD_REQUEST, "header not found:"
                        + HEADER_MOXIE_TYPE);
            return;
        }

        if (StringUtils.isBlank(signature)) {
            writeMessage(httpServletResponse,
                HttpServletResponse.SC_BAD_REQUEST, "header not found:"
                        + HEADER_MOXIE_SIGNATURE);
            return;
        }
        if (StringUtils.isBlank(body)) {
            writeMessage(httpServletResponse,
                HttpServletResponse.SC_BAD_REQUEST, "request body is empty");
            return;
        }

        // 如果事件类型是report(用户报告通知)
        if (StringUtils.equals(eventName.toLowerCase(), "report")) {
            try {
                MxCarrierNofification notification = JsonUtil.json2Bean(body,
                    MxCarrierNofification.class);
                certificationAO.doMxCarrierCallback(notification);
            } catch (Exception e) {
                logger.error("body convert to object error", e);
            }
        }

        writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
            "default eventtype");
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

    private void writeMessage(HttpServletResponse response, int status,
            String content) {
        response.setStatus(status);
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content);
        } catch (IOException ignored) {
        }
    }
}
