/**
 * @Title XN623070Req.java 
 * @Package com.cdkj.ylq.dto.req 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月16日 下午5:03:02 
 * @version V1.0   
 */
package com.cdkj.ylq.dto.req;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月16日 下午5:03:02 
 * @history:
 */
public class XN623090Req extends APageReq {

    private static final long serialVersionUID = -7585865863094749310L;

    // 关联编号（必填）
    private String borrowCode;

    public String getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
    }

}
