/**
 * @Title UserExtDAOImpl.java 
 * @Package com.cdkj.ylq.dao.impl 
 * @Description 
 * @author xieyj  
 * @date 2016年9月18日 上午11:13:06 
 * @version V1.0   
 */
package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IUserExtDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.UserExt;

/** 
 * @author: xieyj 
 * @since: 2016年9月18日 上午11:13:06 
 * @history:
 */
@Repository("userExtDAOImpl")
public class UserExtDAOImpl extends AMybatisTemplate implements IUserExtDAO {

    /** 
     * @see com.cdkj.ylq.dao.base.IBaseDAO#insert(java.lang.Object)
     */
    @Override
    public int insert(UserExt data) {
        return super.insert(NAMESPACE.concat("insert_userExt"), data);
    }

    /** 
     * @see com.cdkj.ylq.dao.base.IBaseDAO#delete(java.lang.Object)
     */
    @Override
    public int delete(UserExt data) {
        // TODO Auto-generated method stub
        return 0;
    }

    /** 
     * @see com.cdkj.ylq.dao.base.IBaseDAO#select(java.lang.Object)
     */
    @Override
    public UserExt select(UserExt condition) {
        return super.select(NAMESPACE.concat("select_userExt"), condition,
            UserExt.class);
    }

    /** 
     * @see com.cdkj.ylq.dao.base.IBaseDAO#selectTotalCount(java.lang.Object)
     */
    @Override
    public Long selectTotalCount(UserExt condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_userExt_count"),
            condition);
    }

    /** 
     * @see com.cdkj.ylq.dao.base.IBaseDAO#selectList(java.lang.Object)
     */
    @Override
    public List<UserExt> selectList(UserExt condition) {
        return super.selectList(NAMESPACE.concat("select_userExt"), condition,
            UserExt.class);
    }

    /** 
     * @see com.cdkj.ylq.dao.base.IBaseDAO#selectList(java.lang.Object, int, int)
     */
    @Override
    public List<UserExt> selectList(UserExt condition, int start, int count) {
        // TODO Auto-generated method stub
        return null;
    }

    /** 
     * @see com.cdkj.ylq.dao.IUserExtDAO#updateUserPhoto(com.cdkj.ylq.domain.UserExt)
     */
    @Override
    public int updateUserPhoto(UserExt data) {
        return super.update(NAMESPACE.concat("update_userExtPhoto"), data);
    }

    @Override
    public int updateUserExt(UserExt data) {
        return super.update(NAMESPACE.concat("update_userExt"), data);
    }

    /** 
     * @see com.cdkj.ylq.dao.IUserExtDAO#updateUserExtLonLat(com.cdkj.ylq.domain.UserExt)
     */
    @Override
    public int updateUserExtLngLat(UserExt data) {
        return super.update(NAMESPACE.concat("update_userExtLngLat"), data);
    }

    @Override
    public int updateWxInfo(UserExt data) {
        return super.update(NAMESPACE.concat("update_wx_info"), data);
    }

}
