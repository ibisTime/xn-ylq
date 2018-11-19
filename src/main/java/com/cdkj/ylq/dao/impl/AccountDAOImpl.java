/**
 * @Title AccountDAOImpl.java 
 * @Package com.cdkj.ylq.dao.impl 
 * @Description 
 * @author taojian  
 * @date 2018年11月19日 上午11:36:23 
 * @version V1.0   
 */
package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IAccountDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.Account;

/** 
 * @author: taojian 
 * @since: 2018年11月19日 上午11:36:23 
 * @history:
 */
@Repository("accountDAOImpl")
public class AccountDAOImpl extends AMybatisTemplate implements IAccountDAO {

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(Account data) {
        return super.insert(NAMESPACE.concat("insert_account"), data);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(Account data) {
        return 0;
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public Account select(Account condition) {
        return super.select(NAMESPACE.concat("select_account"), condition,
            Account.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public Long selectTotalCount(Account condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_account_count"),
            condition);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<Account> selectList(Account condition) {
        return super.selectList(NAMESPACE.concat("select_account"), condition,
            Account.class);
    }

    /**
     * @see com.ogc.standard.dao.IAccountDAO#selectAmountSumList()
     */
    @Override
    public List<Account> selectAmountSumList(Account data) {
        return super.selectList(NAMESPACE.concat("select_accountAmountSum"),
            data, Account.class);
    }

    /** 
     * @see com.ibis.account.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<Account> selectList(Account condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_account"), start,
            count, condition, Account.class);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.account.dao.IAccountDAO#updateAmount(com.ibis.account.domain.Account)
     */
    @Override
    public int updateAmount(Account data) {
        return super.update(NAMESPACE.concat("update_account_amount"), data);
    }

    /** 
     * @see com.std.account.dao.IAccountDAO#updateRealName(com.std.account.domain.Account)
     */
    @Override
    public int updateRealName(Account data) {
        return super.update(NAMESPACE.concat("update_account_realName"), data);
    }

    /** 
     * @see com.xnjr.account.dao.ibis.account.dao.IAccountDAO#updateStatus(com.ibis.account.domain.Account)
     */
    @Override
    public int updateStatus(Account data) {
        return super.update(NAMESPACE.concat("update_account_status"), data);
    }

    @Override
    public int frozenAmount(Account data) {
        return super.update(NAMESPACE.concat("update_frozenAmount"), data);
    }

    @Override
    public int unfrozenAmount(Account data) {
        return super.update(NAMESPACE.concat("update_unfrozenAmount"), data);
    }

}
