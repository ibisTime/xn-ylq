/**
 * @Title InfoAddressBook.java 
 * @Package com.cdkj.ylq.domain 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月23日 上午11:34:54 
 * @version V1.0   
 */
package com.cdkj.ylq.domain;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月23日 上午11:34:54 
 * @history:
 */
public class InfoAddressBook {

    // 姓名
    private String name;

    // 电话号码
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "InfoAddressBook [name=" + name + ", mobile=" + mobile + "]";
    }
}
