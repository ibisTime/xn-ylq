/**
 * @Title IUserAO.java 
 * @Package com.cdkj.ylq.ao 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月21日 下午9:20:22 
 * @version V1.0   
 */
package com.cdkj.ylq.ao;

import com.cdkj.ylq.dto.res.XN805041Res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月21日 下午9:20:22 
 * @history:
 */
public interface IUserAO {
    // 注册前端用户
    public XN805041Res doRegister(String mobile, String loginPwd,
            String userReferee, String userRefereeKind, String smsCaptcha,
            String kind, String isRegHx, String province, String city,
            String area, String address, String companyCode, String systemCode);
}
