package com.cdkj.ylq.tongdun;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.common.JsonUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.dto.res.XN623050Res;
import com.cdkj.ylq.exception.BizException;

/**
 * 数聚魔盒
 * @author: chenshan 
 * @since: 2017年12月12日 下午8:04:49 
 * @history:
 */
@Component
public class YunYingShang {

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    ICertificationAO certificationAO;

    private static final Log log = LogFactory.getLog(YunYingShang.class);

    private HttpsURLConnection conn;

    private SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory
        .getDefault();

    public String query(String taskId, String userId) {
        Map<String, Object> params = put(taskId, userId);
        String data = null;
        try {
            String urlString = new StringBuilder()
                .append(sysConfigBO.getStringValue(SysConstants.SJ_QUERY_URL))
                .append("?partner_code=")
                .append(
                    sysConfigBO.getStringValue(SysConstants.SJ_PARTNER_CODE))
                .append("&partner_key=")
                .append(sysConfigBO.getStringValue(SysConstants.SJ_PARTNER_KEY))
                .toString();
            URL url = new URL(urlString);
            StringBuilder postBody = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() == null)
                    continue;
                postBody
                    .append(entry.getKey())
                    .append("=")
                    .append(
                        URLEncoder.encode(entry.getValue().toString(), "utf-8"))
                    .append("&");
            }

            if (!params.isEmpty()) {
                postBody.deleteCharAt(postBody.length() - 1);
            }
            // System.out.println(postBody.toString());
            conn = (HttpsURLConnection) url.openConnection();
            // 设置https
            conn.setSSLSocketFactory(ssf);
            // 设置长链接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置连接超时
            conn.setConnectTimeout(1000);
            // 设置读取超时
            conn.setReadTimeout(500);
            // 提交参数
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.getOutputStream().write(postBody.toString().getBytes());
            conn.getOutputStream().flush();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
                YYSResponse yysResponse = JsonUtil.json2Bean(result.toString()
                    .trim(), YYSResponse.class);
                if ("1".equals(yysResponse.getCode())) {
                    log.error("请反馈问题给技术支持 \n" + yysResponse.getMessage());//
                } else if ("1070".equals(yysResponse.getCode())) {
                    log.error("合作方编号与密钥不匹配 \n" + yysResponse.getMessage());//
                } else if ("4000".equals(yysResponse.getCode())) {
                    log.error("请反馈问题给技术支持 \n" + yysResponse.getMessage());
                } else if ("4001".equals(yysResponse.getCode())) {// 报告生成中，需等待10秒
                    try {
                        Thread.sleep(10 * 1000);
                        query(taskId, userId);
                    } catch (Exception e) {

                    }
                } else if ("4003".equals(yysResponse.getCode())) {
                    log.error("task_id错误 \n" + yysResponse.getMessage());//
                } else {
                    data = gunzip(yysResponse.getData());
                    // log.info("数据打印：" + data);
                }
            }
        } catch (Exception e) {
            throw new BizException("xn000000", "数据魔盒运营商报告查询异常，原因："
                    + e.getMessage());
        }
        return data;
    }

    private Map<String, Object> put(String taskId, String userId) {
        XN623050Res res = certificationAO.getCertiInfo(userId);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("task_id", taskId); // 授权爬取任务的任务编号
        params.put("real_name", res.getInfoIdentify().getRealName()); // 真实姓名
        params.put("identity_code", res.getInfoIdentify().getIdNo()); // 身份证号
        params.put("email", res.getInfoBasic().getEmail()); // 邮箱地址
        params.put("home_addr", res.getInfoBasic().getProvinceCity()
                + res.getInfoBasic().getAddress());// 居住地址

        params.put("work_addr", res.getInfoOccupation().getProvinceCity()
                + res.getInfoOccupation().getAddress());// 工作地址
        params.put("work_tel", res.getInfoOccupation().getPhone()); // 工作电话
        params.put("company_name", res.getInfoOccupation().getCompany());// 公司名称

        String familyRelation = null;
        if ("0".equals(res.getInfoContact().getFamilyRelation())) {
            familyRelation = "FATHER";
        } else if ("1".equals(res.getInfoContact().getFamilyRelation())) {
            familyRelation = "SPOUSE";
        } else if ("2".equals(res.getInfoContact().getFamilyRelation())) {
            familyRelation = "OTHER_RELATIVE";
        }

        String societyRelation = "";
        if ("0".equals(res.getInfoContact().getSocietyRelation())) {
            societyRelation = "OTHERS";
        } else if ("1".equals(res.getInfoContact().getSocietyRelation())) {
            societyRelation = "COWORKER";
        } else if ("2".equals(res.getInfoContact().getSocietyRelation())) {
            societyRelation = "FRIEND";
        }
        params.put("contact1_name", res.getInfoContact().getFamilyName());
        params.put("contact1_relation", familyRelation);
        params.put("contact1_mobile", res.getInfoContact().getFamilyMobile());
        params.put("contact2_name", res.getInfoContact().getSocietyName());
        params.put("contact2_relation", societyRelation);
        params.put("contact2_mobile", res.getInfoContact().getSocietyMobile());
        return params;
    }

    public String gunzip(String data) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {// 对返回数据BASE64解码
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(data);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
            // 解码后对数据gzip解压缩
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            // 最后对数据进行utf-8转码
            decompressed = out.toString("utf-8");
        } catch (IOException e) {
            log.error("gunzip error ", e);
        } finally {
        }
        return decompressed;
    }
}
