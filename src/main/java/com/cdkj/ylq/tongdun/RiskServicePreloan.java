package com.cdkj.ylq.tongdun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cdkj.ylq.common.JsonUtil;

/**
 * @author: haiqingzheng 
 * @since: 2017年9月19日 下午4:28:28 
 * @history:
 */
public class RiskServicePreloan {

    private static final Log log = LogFactory.getLog(RiskServicePreloan.class);

    private static final String submitUrl = "https://apitest.tongdun.cn/preloan/apply/v5";

    private static final String queryUrl = "https://apitest.tongdun.cn/preloan/report/v9";

    private static final long WAIT_TIME = 5 * 1000;

    private static final String PARTNER_CODE = "jiuzhou";// 合作方标识

    private static final String PARTNER_KEY = "af52d445b7344653b1bb8ca8b5ce3878";// 合作方密钥

    private static final String PARTNER_APP = "jiuzhou_and";// 应用名

    private HttpsURLConnection conn;

    private SSLSocketFactory ssf = (SSLSocketFactory) SSLSocketFactory
        .getDefault();

    /**
     * submit接口示例
     *
     * @param params
     * @return
     */
    public PreloanSubmitResponse apply(Map<String, Object> params) {

        PreloanSubmitResponse submitResponse = new PreloanSubmitResponse();
        try {
            String urlString = new StringBuilder().append(submitUrl)
                .append("?partner_code=").append(PARTNER_CODE)
                .append("&partner_key=").append(PARTNER_KEY)
                .append("&app_name=").append(PARTNER_APP).toString();
            URL url = new URL(urlString);
            // 组织请求参数
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
            System.out.println(postBody.toString());
            conn = (HttpsURLConnection) url.openConnection();
            // 设置https
            conn.setSSLSocketFactory(ssf);
            // 设置长链接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置连接超时
            conn.setConnectTimeout(1000);
            // 设置读取超时，建议设置为3000ms。若同时调用了信息核验服务，请与客户经理协商确认具体时间”
            conn.setReadTimeout(3000);
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
                // return JSON.parseObject(result.toString().trim(),
                // PreloanSubmitResponse.class);
                return JsonUtil.json2Bean(result.toString().trim(),
                    PreloanSubmitResponse.class);
            }
        } catch (Exception e) {
            log.error("[RiskServicePreloan] apply throw exception, details: "
                    + e);
        }
        return submitResponse;
    }

    public PreloanQueryResponse query(String reportId) {
        PreloanQueryResponse queryResponse = new PreloanQueryResponse();
        try {
            String urlString = new StringBuilder().append(queryUrl)
                .append("?partner_code=").append(PARTNER_CODE)
                .append("&partner_key=").append(PARTNER_KEY)
                .append("&report_id=").append(reportId).toString();
            URL url = new URL(urlString);

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
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
                // return JSON.parseObject(result.toString().trim(),
                // PreloanQueryResponse.class);
                return JsonUtil.json2Bean(result.toString().trim(),
                    PreloanQueryResponse.class);
            }
        } catch (Exception e) {
            log.error("[RiskServicePreloan] query throw exception, details: "
                    + e);
        }
        return queryResponse;
    }

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loan_amount", "10000"); // 申请借款金额
        params.put("loan_term", "12"); // 申请借款期限
        params.put("loan_term_unit", "DAY"); // 期限单位
        params.put("loan_date", "2018-11-11"); // 申请借款日期
        params.put("purpose", ""); // 借款用途
        params.put("apply_province", ""); // 进件省份
        params.put("apply_city", ""); // 进件城市
        params.put("apply_channel", ""); // 进件渠道
        params.put("name", "demo1111"); // 姓名
        params.put("id_number", "433127198404230289"); // 身份证号
        params.put("mobile", "13260665731"); // 手机号
        params.put("card_number", ""); // 银行卡
        params.put("work_phone", ""); // 单位座机
        params.put("home_phone", ""); // 家庭座机
        params.put("qq", ""); // qq
        params.put("email", ""); // 电子邮箱
        params.put("diploma", ""); // 学历
        params.put("marriage", ""); // 婚姻
        params.put("house_property", ""); // 房产情况
        params.put("house_type", ""); // 房产类型
        params.put("registered_address", ""); // 户籍地址
        params.put("home_address", ""); // 家庭地址
        params.put("contact_address", ""); // 通讯地址
        params.put("career", ""); // 职业
        params.put("applyer_type", ""); // 申请人类别
        params.put("work_time", ""); // 工作时间
        params.put("company_name", ""); // 工作单位
        params.put("company_address", ""); // 单位地址
        params.put("company_industry", ""); // 公司行业
        params.put("company_type", ""); // 公司性质
        params.put("occupation", ""); // 职位
        params.put("annual_income", ""); // 年收入
        params.put("is_cross_loan", ""); // 三个月内是否跨平台申请借款
        params.put("cross_loan_count", ""); // 三个月内跨平台申请借款平台个数
        params.put("is_liability_loan", ""); // 三个月内是否跨平台借款负债
        params.put("liability_loan_count", ""); // 三个月内跨平台借款负债平台个数
        params.put("is_id_checked", ""); // 是否通过实名认证
        params.put("contact1_relation", ""); // 第一联系人社会关系
        params.put("concatc1_name", ""); // 第一联系人姓名
        params.put("contact1_id_number", ""); // 第一联系人身份证
        params.put("contact1_mobile", ""); // 第一联系人手机号
        params.put("contact1_addr", ""); // 第一联系人家庭地址
        params.put("contact1_com_name", ""); // 第一联系人工作单位
        params.put("contact1_com_addr", ""); // 第一联系人工作地址
        params.put("contact2_relation", "");
        params.put("contact2_name", "");
        params.put("contact2_id_number", "");
        params.put("contact2_mobile", "");
        params.put("contact2_addr", "");
        params.put("contact2_com_name", "");
        params.put("contact2_com_addr", "");
        params.put("contact3_relation", "");
        params.put("contact3_name", "");
        params.put("contact3_id_number", "");
        params.put("contact3_mobile", "");
        params.put("contact3_addr", "");
        params.put("contact3_com_name", "");
        params.put("contact3_com_addr", "");
        params.put("contact4_relation", "");
        params.put("contact4_name", "");
        params.put("contact4_id_number", "");
        params.put("contact4_mobile", "");
        params.put("contact4_addr", "");
        params.put("contact4_com_name", "");
        params.put("contact4_com_addr", "");
        params.put("contact5_relation", "");
        params.put("contact5_name", "");
        params.put("contact5_id_number", "");
        params.put("contact5_mobile", "");
        params.put("contact5_addr", "");
        params.put("contact5_com_name", "");
        params.put("contact5_com_addr", "");
        params.put("ip_address", ""); // IP地址
        params.put("token_id", ""); // token_id
        params.put("black_box", ""); // black_box
        RiskServicePreloan service = new RiskServicePreloan();
        PreloanSubmitResponse riskPreloanResponse = service.apply(params);
        System.out.println(riskPreloanResponse.toString());

        if (riskPreloanResponse.getSuccess()) {
            // 等待一定时间后，可调用query接口查询结果。
            // 时间建议：5s后可调用
            try {
                Thread.sleep(WAIT_TIME);
            } catch (Exception e) {
                //
            }
            // query接口获取结果
            PreloanQueryResponse response = service.query(riskPreloanResponse
                .getReport_id());
            System.out.println(response.getReport_id());
            // 其他处理 。。。
        }
    }

    public String getDiploma(String diploma) {
        String result = "";
        if ("0".equals(diploma) || "1".equals(diploma)) {
            result = "PRE_HIGH_SCHOOL";
        } else if ("2".equals(diploma)) {
            result = "HIGH_SCHOOL";
        } else if ("3".equals(diploma)) {
            result = "JUNIOR_COLLEGE";
        } else if ("4".equals(diploma)) {
            result = "UNDER_GRADUATE";
        } else if ("5".equals(diploma) || "6".equals(diploma)) {
            result = "POST_GRADUATE";
        }
        return result;
    }

    public String getMarriage(String marriage) {
        String result = "";
        if ("0".equals(marriage)) {
            result = "SPINSTERHOOD";
        } else if ("1".equals(marriage)) {
            result = "MARRIED";
        } else if ("2".equals(marriage)) {
            result = "DIVORCED";
        } else if ("3".equals(marriage)) {
            result = "WIDOWED";
        }
        return result;
    }

    public String getAnnualIncome(String annualIncome) {
        String result = "";
        if ("0".equals(annualIncome)) {
            result = "10000以下";
        } else if ("1".equals(annualIncome) || "2".equals(annualIncome)) {
            result = "10000-50000";
        } else if ("3".equals(annualIncome)) {
            result = "50000-100000";
        } else if ("4".equals(annualIncome)) {
            result = "100000-200000";
        }
        return result;
    }
}
