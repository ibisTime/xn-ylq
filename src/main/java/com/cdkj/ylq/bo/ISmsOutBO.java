package com.cdkj.ylq.bo;

public interface ISmsOutBO {
    // 根据用户编号发送短信
    void sentContent(String userId, String content);

    // 根据手机号发送短信
    public void sendContent(String mobile, String content, String companyCode,
            String systemCode);
}
