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
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.domain.MxCarrierNofification;

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

    // ******魔蝎报告回调处理

    @RequestMapping("/mxcarrier/notifications")
    public synchronized void doCallbackMxCarrier(@RequestBody String body,
            ServletRequest request, ServletResponse response)
            throws IOException {

        logger.info("******************回掉开始*******************");

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

        // 任务提交
        // if (StringUtils.equals(eventName.toLowerCase(), "task.submit")) {
        // // 通知状态变更为 '认证中'
        // MxCarrierNofification notification = JsonUtil.json2Bean(body,
        // MxCarrierNofification.class);
        // certificationAO.doMxCarrierTaskSubmitCallback(notification);
        // }

        if (StringUtils.equalsIgnoreCase(eventType, "carrier")) {
            // 登录完成后的通知，包括登录成功或者失败
            if (StringUtils.equals(eventName.toLowerCase(), "task")) {
                MxCarrierNofification notification = JsonUtil.json2Bean(body,
                    MxCarrierNofification.class);
                certificationAO.doMxCarrierTaskCallback(notification);
            }

            // 任务采集失败
            if (StringUtils.equals(eventName.toLowerCase(), "task.fail")) {
                // 通知状态变更为 '认证失败'
                MxCarrierNofification notification = JsonUtil.json2Bean(body,
                    MxCarrierNofification.class);
                certificationAO.doMxCarrierTaskFailCallback(notification);
            }

            // 如果事件类型是report(用户报告通知)
            if (StringUtils.equals(eventName.toLowerCase(), "report")) {
                try {
                    MxCarrierNofification notification = JsonUtil.json2Bean(
                        body, MxCarrierNofification.class);
                    certificationAO.doMxCarrierReportCallback(notification);
                } catch (Exception e) {
                    logger.error("body convert to object error", e);
                }
            }
        } else if (StringUtils.equalsIgnoreCase(eventType, "alipay")) {

        } else {
            logger.error("暂未开通此业务");
        }

        writeMessage(httpServletResponse, HttpServletResponse.SC_CREATED,
            "default eventtype");
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
