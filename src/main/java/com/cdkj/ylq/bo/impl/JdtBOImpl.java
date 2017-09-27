/**
 * @Title JdtBOImpl.java 
 * @Package com.cdkj.ylq.bo.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月27日 下午3:35:52 
 * @version V1.0   
 */
package com.cdkj.ylq.bo.impl;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IJdtBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.http.HttpUtil;
import com.cdkj.ylq.jdt.JdtAccessTokenRes;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月27日 下午3:35:52 
 * @history:
 */
@Component
public class JdtBOImpl implements IJdtBO {

    protected static final Logger logger = LoggerFactory
        .getLogger(JdtBOImpl.class);

    @Autowired
    ISYSConfigBO sysConfigBO;

    /** 
     * @see com.cdkj.ylq.bo.IJdtBO#getAccessToken()
     */
    @Override
    public String getAccessToken() {
        String accessToken = null;
        String jtdUrl = sysConfigBO.getStringValue(SysConstants.JDT_URL);
        String jtdOrg = sysConfigBO.getStringValue(SysConstants.JDT_ORG);
        String jtdSecret = sysConfigBO.getStringValue(SysConstants.JDT_SECRET);
        String urlString = jtdUrl + "/AccessToken?org=" + jtdOrg + "&secret="
                + jtdSecret;
        logger.info("借贷通-获取accessToken请求链接：" + urlString);
        try {
            String response = HttpUtil.requestGet(urlString, null, null);
            JdtAccessTokenRes res = JsonUtil.json2Bean(response,
                JdtAccessTokenRes.class);
            if ("SUCCESS".equals(res.getResult())) {
                accessToken = res.getAccessToken();
            } else {
                throw new BizException("借贷通-获取accessToken失败,原因："
                        + res.getInfo());
            }
        } catch (Exception e) {
            logger.error("借贷通-获取accessToken异常,原因：" + e.getMessage());
        }
        return accessToken;
    }

    @Override
    public String getSSPData(String accessToken, String token) {
        String report = null;
        String jtdUrl = sysConfigBO.getStringValue(SysConstants.JDT_URL);
        String jtdOrg = sysConfigBO.getStringValue(SysConstants.JDT_ORG);
        String jtdSecret = sysConfigBO.getStringValue(SysConstants.JDT_SECRET);
        String urlString = jtdUrl + "/GetSSPData?org=" + jtdOrg + "&secret="
                + jtdSecret + "&accessToken=" + accessToken + "&token=" + token;
        logger.info("借贷通-获取个人信用报告请求链接：" + urlString);
        try {
            String response = HttpUtil.requestGet(urlString, null, null);
            JSONObject dataObj = JSONObject.fromObject(response);
            if ((Integer) dataObj.get("statusCode") == 100) {
                report = response;
            } else {
                throw new BizException("借贷通-获取个人信用报告失败,原因："
                        + dataObj.get("info"));
            }
            if (report == null) {
                throw new BizException("借贷通-获取个人信用报告失败");
            }
        } catch (Exception e) {
            logger.error("借贷通-获取个人信用报告异常,原因：" + e.getMessage());
        }
        return report;
    }
}
