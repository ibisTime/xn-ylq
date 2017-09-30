/**
 * @Title IContractBO.java 
 * @Package com.xnjr.pop.bo 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年11月14日 下午3:37:17 
 * @version V1.0   
 */
package com.cdkj.ylq.bo;

import java.util.List;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.Contract;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.User;

/** 
 * @author: haiqingzheng 
 * @since: 2015年11月14日 下午3:37:17 
 * @history:
 */
public interface IContractBO extends IPaginableBO<Contract> {

    // 生成借款合同
    public String generate(User user, Bankcard bankcard, Borrow borrow);

    // 预览借款合同
    public String preview(User user, Bankcard bankcard, Product product,
            Long borrowAmount);

    public Contract getContract(String code);

    public List<Contract> queryContractList(Contract condition);
}
