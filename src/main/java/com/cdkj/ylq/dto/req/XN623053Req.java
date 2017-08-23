/**
 * @Title XN623041Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月12日 下午8:31:49 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

import java.util.List;

import com.cdkj.ylq.domain.InfoAddressBook;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月12日 下午8:31:49 
 * @history:
 */
public class XN623053Req {

    // userId(必填)
    private String userId;

    // 通讯录（必填）
    private List<InfoAddressBook> addressBookList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<InfoAddressBook> getAddressBookList() {
        return addressBookList;
    }

    public void setAddressBookList(List<InfoAddressBook> addressBookList) {
        this.addressBookList = addressBookList;
    }

}
