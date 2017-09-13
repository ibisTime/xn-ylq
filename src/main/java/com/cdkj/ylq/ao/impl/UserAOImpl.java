/**
 * @Title UserAO.java 
 * @Package com.cdkj.ylq.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月21日 下午9:22:32 
 * @version V1.0   
 */
package com.cdkj.ylq.ao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.ao.IUserAO;
import com.cdkj.ylq.bo.ICouponConditionBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.dto.res.XN805041Res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月21日 下午9:22:32 
 * @history:
 */
@Service
public class UserAOImpl implements IUserAO {
    @Autowired
    private IUserBO userBO;

    @Autowired
    private ICouponConditionBO couponConditionBO;

    @Autowired
    private ICertificationAO certificationAO;

    /** 
     * @see com.cdkj.ylq.ao.IUserAO#doRegister(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public XN805041Res doRegister(String mobile, String loginPwd,
            String userReferee, String userRefereeKind, String smsCaptcha,
            String kind, String isRegHx, String province, String city,
            String area, String address, String companyCode, String systemCode) {
        // 注册
        XN805041Res res = userBO.doRegister(mobile, loginPwd, userReferee,
            userRefereeKind, smsCaptcha, kind, isRegHx, province, city, area,
            address, companyCode, systemCode);

        // 分配认证信息
        certificationAO.initialCertification(res.getUserId());

        return res;
    }

}
