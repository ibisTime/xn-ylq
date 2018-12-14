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

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.bo.ICertificationBO;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.domain.Certification;
import com.cdkj.ylq.domain.MxAlipayNotification;
import com.cdkj.ylq.domain.MxCarrierNofification;
import com.cdkj.ylq.enums.ECertiKey;

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
    ICertificationAO certificationAO;

    @Autowired
    ICertificationBO certificationBO;

    // ******魔蝎报告回调处理

    @RequestMapping("/mxcarrier/notifications")
    public synchronized void doCallbackMxCarrier(@RequestBody String body,
            ServletRequest request, ServletResponse response)
            throws IOException {

        logger.info("******************回掉开始*******************");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 返回201
        writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
            "default eventtype");
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

        if (StringUtils.equalsIgnoreCase(eventType, "carrier")) {
            // 登录完成后的通知，包括登录成功或者失败
            if (StringUtils.equals(eventName.toLowerCase(), "task")) {
                MxCarrierNofification notification = JsonUtil.json2Bean(body,
                    MxCarrierNofification.class);
                String userId = notification.getUser_id();
                Certification carrier = certificationBO.getCertification(
                    userId, ECertiKey.INFO_CARRIER);
                if (carrier.getResult() == null) {
                    certificationAO.doMxCarrierTaskCallback(notification);
                }
            }

            // 任务采集失败
            if (StringUtils.equals(eventName.toLowerCase(), "task.fail")) {
                // 通知状态变更为 '认证失败'
                MxCarrierNofification notification = JsonUtil.json2Bean(body,
                    MxCarrierNofification.class);
                String userId = notification.getUser_id();
                Certification carrier = certificationBO.getCertification(
                    userId, ECertiKey.INFO_CARRIER);
                if (carrier.getResult() == null) {
                    certificationAO.doMxCarrierTaskFailCallback(notification);
                }
            }

            // 如果事件类型是report(用户报告通知)
            if (StringUtils.equals(eventName.toLowerCase(), "report")) {
                MxCarrierNofification notification = JsonUtil.json2Bean(body,
                    MxCarrierNofification.class);
                logger.info(notification.getUser_id());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                certificationAO.doMxCarrierReportCallback(notification);
            }
        } else if (StringUtils.equalsIgnoreCase(eventType, "alipay")) {
            if (StringUtils.equalsIgnoreCase(eventName, "task")) {
                MxAlipayNotification notification = JsonUtil.json2Bean(body,
                    MxAlipayNotification.class);
                String userId = notification.getUser_id();
                Certification alipay = certificationBO.getCertification(userId,
                    ECertiKey.INFO_ZHIFUBAO);
                if (alipay.getResult() == null) {
                    certificationAO.doMxAlipayTaskCallback(notification);
                }
            }
            if (StringUtils.equalsIgnoreCase(eventName, "task.fail")) {
                MxAlipayNotification notification = JsonUtil.json2Bean(body,
                    MxAlipayNotification.class);
                String userId = notification.getUser_id();
                Certification alipay = certificationBO.getCertification(userId,
                    ECertiKey.INFO_ZHIFUBAO);
                if (alipay.getResult() == null) {
                    certificationAO.doMxAlipayTaskFailCallback(notification);
                }
            }
            if (StringUtils.equalsIgnoreCase(eventName, "report")) {
                MxAlipayNotification notification = JsonUtil.json2Bean(body,
                    MxAlipayNotification.class);
                logger.info(notification.getTask_id());
                logger.info(notification.getUser_id());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                certificationAO.doMxAlipayReportCallback(notification);
            }
        } else {
            logger.error("暂未开通此业务");
        }

    }

    private void writeMessage(HttpServletResponse response, int status,
            String content) {
        response.setStatus(status);
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content);
        } catch (IOException ignored) {
            logger.info(ignored);
        }
    }
}
