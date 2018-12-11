package com.cdkj.ylq.common;

import java.util.HashMap;
import java.util.Map;

import com.cdkj.ylq.http.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author wanghongzhi
 * @create 2018-08-31 下午12:02
 **/

public class MoxieRequest {

    /**
     * 发送请求到魔蝎 风控网关 demo
     * @throws Exception
     */
    public static String demoToRiskGateway(String name, String idCard,
            String mobile, String appId, String privateKey) throws Exception {
        // 魔杖
        String method = Method.MagicWand2.getMethod();

        /** 请求参数 */
        Map<String, String> reqParams = new HashMap<>();
        reqParams.put(ReqCommonParams.METHOD, method);
        reqParams.put(ReqCommonParams.APP_ID, appId);
        reqParams.put(ReqCommonParams.VERSION, ReqCommonParamsValue.VERSION);
        reqParams.put(ReqCommonParams.FORMAT, ReqCommonParamsValue.FORMAT);
        reqParams
            .put(ReqCommonParams.SIGN_TYPE, ReqCommonParamsValue.SIGN_TYPE);
        reqParams.put(ReqCommonParams.TIMESTAMP,
            String.valueOf(System.currentTimeMillis()));

        /** 业务参数 */
        Map<String, String> bizParams = new HashMap<>();
        bizParams.put("name", name);
        bizParams.put("mobile", mobile);
        bizParams.put("idcard", idCard);
        String bizContent = new ObjectMapper().writeValueAsString(bizParams);

        reqParams.put(ReqCommonParams.BIZ_CONTENT, bizContent);

        // 签名
        String sign = MoxieSignUtils.signSHA1WithRSA(reqParams, privateKey);

        reqParams.put(ReqCommonParams.SIGN, sign);

        String resContent = HttpUtils.get(API_URL, reqParams);

        System.out.println("resContent:\n" + resContent);

        return resContent;
    }

    private final static String API_URL = "https://api.51datakey.com/risk-gateway/api/gateway";

    public enum Method {
        MagicWand2("moxie.api.risk.magicwand2.multi-info", "魔杖2.0多头"), MagicScore(
                "moxie.api.risk.magicscore", "魔分"), MagiccueTags(
                "moxie.api.risk.magiccube.tags", "魔方标签");
        private String method;

        private String desc;

        Method(String method, String desc) {
            this.method = method;
            this.desc = desc;
        }

        public String getMethod() {
            return this.method;
        }
    }

    public interface ReqCommonParams {
        String METHOD = "method";

        String APP_ID = "app_id";

        String VERSION = "version";

        String FORMAT = "format";

        String SIGN_TYPE = "sign_type";

        String TIMESTAMP = "timestamp";

        String BIZ_CONTENT = "biz_content";

        String SIGN = "sign";
    }

    public interface ReqCommonParamsValue {
        String VERSION = "1.0";

        String FORMAT = "JSON";

        String SIGN_TYPE = "RSA";
    }

}
