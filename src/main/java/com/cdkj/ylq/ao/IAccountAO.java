/**
 * @Title IAccountAO.java 
 * @Package com.std.user.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年10月10日 上午11:08:04 
 * @version V1.0   
 */
package com.cdkj.ylq.ao;

/** 
 * @author: haiqingzheng 
 * @since: 2017年10月10日 上午11:08:04 
 * @history:
 */
public interface IAccountAO {
    public void editBankcard(String userId, String code, String realName,
            String bankcardNumber, String bankCode, String bankName,
            String status);
}
