package com.cdkj.ylq.dao;

import com.cdkj.ylq.dao.base.IBaseDAO;
import com.cdkj.ylq.domain.Bankcard;

/**
 * 
 * @author: lei 
 * @since: 2018年9月11日 下午5:41:48 
 * @history:
 */
public interface IBankCardDAO extends IBaseDAO<Bankcard> {
    String NAMESPACE = IBankCardDAO.class.getName().concat(".");

    public int update(Bankcard data);
}
