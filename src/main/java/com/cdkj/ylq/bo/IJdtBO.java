/**
 * @Title IJdtBO.java 
 * @Package com.cdkj.ylq.bo 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月27日 下午3:34:55 
 * @version V1.0   
 */
package com.cdkj.ylq.bo;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月27日 下午3:34:55 
 * @history:
 */
public interface IJdtBO {

    // 获取accessToken
    public String getAccessToken();

    // 个人报告获取
    public String getSSPData(String accessToken, String token);

}
