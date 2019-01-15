/**
 * @Title UserDAOImpl.java 
 * @Package com.ibis.pz.impl 
 * @Description 
 * @author miyb  
 * @date 2015-2-6 上午10:22:53 
 * @version V1.0   
 */
package com.cdkj.ylq.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cdkj.ylq.dao.IUserDAO;
import com.cdkj.ylq.dao.base.support.AMybatisTemplate;
import com.cdkj.ylq.domain.User;

/** 
 * @author: miyb 
 * @since: 2015-2-6 上午10:22:53 
 * @history:
 */
@Repository("userDAOImpl")
public class UserDAOImpl extends AMybatisTemplate implements IUserDAO {

    @Override
    public int insert(User data) {
        return super.insert(NAMESPACE.concat("insert_user"), data);
    }

    @Override
    public int delete(User data) {
        return 0;
    }

    @Override
    public User select(User condition) {
        return super.select(NAMESPACE.concat("select_user"), condition,
            User.class);
    }

    @Override
    public Long selectTotalCount(User condition) {
        return super.selectTotalCount(NAMESPACE.concat("select_user_count"),
            condition);
    }

    @Override
    public List<User> selectList(User condition) {
        return super.selectList(NAMESPACE.concat("select_user"), condition,
            User.class);
    }

    @Override
    public List<User> selectList(User condition, int start, int count) {
        return super.selectList(NAMESPACE.concat("select_user"), start, count,
            condition, User.class);
    }

    @Override
    public int updateIdentity(User data) {
        return super.update(NAMESPACE.concat("update_identity"), data);
    }

    @Override
    public int updateRealName(User data) {
        return super.update(NAMESPACE.concat("update_real_name"), data);
    }

    @Override
    public int updateDivRate(User data) {
        return super.update(NAMESPACE.concat("update_div_rate"), data);
    }

    @Override
    public int updateTradePwd(User data) {
        return super.update(NAMESPACE.concat("update_trade_pwd"), data);
    }

    @Override
    public int updateLoginPwd(User data) {
        return super.update(NAMESPACE.concat("update_login_pwd"), data);
    }

    @Override
    public int updateMobile(User data) {
        return super.update(NAMESPACE.concat("update_mobile"), data);
    }

    @Override
    public int updateBindMobile(User data) {
        return super.update(NAMESPACE.concat("update_bind_mobile"), data);
    }

    @Override
    public int updateStatus(User data) {
        return super.update(NAMESPACE.concat("update_status"), data);
    }

    @Override
    public int updateRole(User data) {
        return super.update(NAMESPACE.concat("update_role"), data);
    }

    /** 
     * @see com.cdkj.ylq.dao.IUserDAO#updatePdf(com.cdkj.ylq.domain.User)
     */
    @Override
    public int updatePdf(User data) {
        return super.update(NAMESPACE.concat("update_user_pdf"), data);

    }

    /** 
     * @see com.cdkj.ylq.dao.IUserDAO#updateLoginName(com.cdkj.ylq.domain.User)
     */
    @Override
    public int updateLoginName(User data) {
        return super.update(NAMESPACE.concat("update_user_loginName"), data);
    }

    @Override
    public int updateNickname(User data) {
        return super.update(NAMESPACE.concat("update_user_nickname"), data);
    }

    @Override
    public int updatePhoto(User data) {
        return super.update(NAMESPACE.concat("update_user_photo"), data);
    }

    @Override
    public int updateCompany(User data) {
        return super.update(NAMESPACE.concat("update_user_company"), data);
    }

    /** 
     * @see com.cdkj.ylq.dao.IUserDAO#update(com.cdkj.ylq.domain.User)
     */
    @Override
    public int update(User data) {
        return super.update(NAMESPACE.concat("update_user"), data);
    }

    /** 
     * @see com.cdkj.ylq.dao.IUserDAO#updateSupple(com.cdkj.ylq.domain.User)
     */
    @Override
    public int updateSupple(User data) {
        return super.update(NAMESPACE.concat("update_user_supple"), data);
    }

    /** 
     * @see com.cdkj.ylq.dao.IUserDAO#updateLevel(com.cdkj.ylq.domain.User)
     */
    @Override
    public int updateLevel(User data) {
        return super.update(NAMESPACE.concat("update_level"), data);
    }

    @Override
    public int updateWxInfo(User data) {
        return super.update(NAMESPACE.concat("update_wx_info"), data);
    }

    @Override
    public int updateWxOpenId(User data) {
        return super.update(NAMESPACE.concat("update_wx_openId"), data);
    }

    @Override
    public int approveUser(User data) {
        return super.update(NAMESPACE.concat("approve_user"), data);
    }

    @Override
    public int updateBlackList(User data) {
        return super.update(NAMESPACE.concat("update_blacklist"), data);
    }

    @Override
    public int updateWhiteList(User data) {
        return super.update(NAMESPACE.concat("update_whitelist"), data);
    }

    @Override
    public int updateIsCoupon(User data) {
        return super.update(NAMESPACE.concat("update_isCoupon"), data);
    }

    @Override
    public int updateLocation(User data) {
        return super.update(NAMESPACE.concat("update_location"), data);
    }

    @Override
    public User selectWayerUser(User condition) {
        return super.select(NAMESPACE.concat("select_wayer_user"), condition,
            User.class);
    }
}
