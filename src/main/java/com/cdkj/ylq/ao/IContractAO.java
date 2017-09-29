/**
 * @Title IContractAO.java 
 * @Package com.xnjr.pop.ao 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年11月14日 下午3:39:53 
 * @version V1.0   
 */
package com.cdkj.ylq.ao;

import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.domain.Contract;
import com.cdkj.ylq.spring.ServiceModule;

/** 
 * @author: haiqingzheng 
 * @since: 2015年11月14日 下午3:39:53 
 * @history:
 */
@ServiceModule
public interface IContractAO {

    public String DEFAULT_ORDER_COLUMN = "code";

    // 预览合同
    public String preview(String userId, Long couponId);

    public Paginable<Contract> queryContractPage(int start, int limit,
            Contract condition);

    public Contract getContract(String contractCode);
}
