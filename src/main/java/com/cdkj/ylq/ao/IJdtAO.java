/**
 * @Title IJDTAO.java 
 * @Package com.cdkj.ylq.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月27日 下午3:25:07 
 * @version V1.0   
 */
package com.cdkj.ylq.ao;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月27日 下午3:25:07 
 * @history:
 */
public interface IJdtAO {
    public void doGetNewMember(String token, String uid, String demandKey,
            String demandId, String dataTime);
}
