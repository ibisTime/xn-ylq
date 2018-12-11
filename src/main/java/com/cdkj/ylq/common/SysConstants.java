package com.cdkj.ylq.common;

public class SysConstants {

    public static final String IDENTIFY_VALID_DAYS = "identifyValidDays"; // 身份认证有效天数

    public static final String PERSONAL_VALID_DAYS = "personalValidDays"; // 个人信息认证有效天数

    public static final String Alipay_VALID_DAYS = "alipayValidDays"; // 支付宝认证有效天数

    public static final String CARRIER_VALID_DAYS = "carrierValidDays"; // 运营商认证有效天数

    public static final String ADDRESS_BOOK_VALID_DAYS = "addressBookValidDays"; // 通讯录认证有效天数

    public static final String TONGDUN_PRELOAN_VALID_DAYS = "tongdunPreLoanValidDays"; // 同盾贷前审核报告有效天数

    public static final String AMOUNT_VALID_DAYS = "amountValidDays"; // 额度有效天数

    // public static final String SEND_SMS_COUNT = "sendSmsCount"; // 催收联系人数量

    public static final String RENEWAL_STEP = "renewalStep"; // 续期步长

    public static final String WAY_URL = "domainURL"; // 渠道url

    public static final String RENEWAL_LIMIT = "renewalLimit"; // 最大续期次数

    public static final String YQLX_FD_RATE = "yqlxFdRate"; // 逾期利息封顶（本金乘以该数值）

    public static final String MX_CARRIER_URL = "mxCarrierUrl"; // 魔蝎URL

    public static final String MX_ALIPAY_URL = "mxAlipayUrl"; // 魔蝎URL

    public static final String MX_TOKEN = "mxToken"; // 魔蝎token

    public static final String MX_SECRET = "mxSecret"; // 魔蝎secret

    public static final String MX_APP_ID = "mxAppId"; // 魔蝎AppId

    public static final String MX_PRIVATE_KEY = "mxPrivateKey"; // 魔蝎私钥

    public static final String MX_PUBLIC_KEY = "mxPublicKey"; // 魔蝎公钥

    public static final String SMS_CUISHOU = "smsCuishou"; // 催收短信

    public static final String TD_SUBMIT_URL = "tdSubmitUrl"; // 同盾贷前审核信息提交URL

    public static final String TD_QUERY_URL = "tdQueryUrl"; // 同盾贷前审核查询URL

    public static final String TD_PARTNER_CODE = "tdPartnerCode"; // 合作方标识

    public static final String TD_PARTNER_KEY = "tdPartnerKey"; // 合作方密钥

    public static final String TD_PARTNER_APP = "tdPartnerApp"; // 应用名

    public static final String JDT_URL = "jtdUrl"; // 主机名称

    public static final String JDT_ORG = "jtdOrg"; // 企业客户账号名

    public static final String JDT_SECRET = "jtdSecret"; // 客户秘钥

    public static final String BORROW_PROTOCOL = "borrowProtocol";// 借款协议模板

    public static final String FW_RATE = "fwRate";// 服务利率

    public static final String SJ_QUERY_URL = "sjQueryUrl"; // 数聚磨合查询URL

    public static final String SJ_PARTNER_CODE = "sjPartnerCode"; // 数聚磨合合作方标识

    public static final String SJ_PARTNER_KEY = "sjPartnerKey"; // 数聚磨合合作方密钥

    // 取现规则配置
    public static String USERQXBS = "USERQXBS"; // C端用户取现倍数

    public static String USERQXFL = "USERQXFL"; // C端用户取现费率

    public static String USERQXSX = "USERQXSX"; // C端用户取现时效

    public static String USERMONTIMES = "USERMONTIMES"; // C端用户每月取现次数

    public static String QXDBZDJE = "QXDBZDJE"; // 取现单笔最大金额

    // 短信模板
    // 有人提现通知运营人员
    public static String WITHDRAW_CN = "您有一个新的取现订单（编号：%s），请及时处理！";

    // 代注册
    public static String DO_ADD_USER_CN = "尊敬的%s用户，您已成功注册，您的默认登录密码是%s，请及时登录平台修改。";

    // 修改手机号
    public static String DO_CHANGE_MOBILE_CN = "尊敬的%s用户，您于%s提交的更改绑定手机号码服务已完成，现绑定手机号码为%s，请妥善保管您的账户相关信息。";

    // 修改登录密码
    public static String DO_MODIFY_LOGIN_PWD_CN = "尊敬的%s用户，您的登录密码修改成功。请妥善保管您的账户相关信息。";

    // 重置登录密码
    public static String DO_RESET_LOGIN_PWD_CN = "尊敬的%s用户，您的登录密码重置成功。请妥善保管您的账户相关信息。";

    // 修改资金密码
    public static String DO_MODIFY_TRADE_PWD_CN = "尊敬的%s用户，您的资金密码修改成功。请妥善保管您的账户相关信息。";

    // 重置资金密码
    public static String DO_RESET_TRADE_PWD_CN = "尊敬的%s用户，您的资金密码重置成功。请妥善保管您的账户相关信息。";

    // 封禁用户
    public static String DO_LOCK_USER_CN = "尊敬的%s用户，您的账号已被管理员封禁，请遵守平台相关规则。";

    // 解禁用户
    public static String DO_UNLOCK_USER_CN = "尊敬的%s用户，您的账号已被管理员解封，请遵守平台相关规则。";

    // 绑定手机
    public static String DO_BIND_MOBILE_CN = "尊敬的%s用户，您于%s提交的绑定手机号码服务已完成，现绑定手机号码为%s，请妥善保管您的账户相关信息。";

    // 七牛云图片配置
    public static String QINIU_ACCESS_KEY = "qiniu_access_key";

    public static String QINIU_SECRET_KEY = "qiniu_secret_key";

    public static String QINIU_BUCKET = "qiniu_bucket";

    public static String QINIU_DOMAIN = "qiniu_domain";

    // 管理员
    public static String ADMIN = "admin";

}
