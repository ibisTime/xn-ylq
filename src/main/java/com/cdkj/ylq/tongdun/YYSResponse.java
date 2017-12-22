/**
 * @Title PreloanQueryResponse.java 
 * @Package com.cdkj.ylq.tongdun 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月19日 下午4:27:20 
 * @version V1.0   
 */
package com.cdkj.ylq.tongdun;

import java.io.Serializable;

/**
 * 运营商查询
 * @author: chenshan 
 * @since: 2017年12月12日 下午8:48:04 
 * @history:
 */
public class YYSResponse implements Serializable {

    private static final long serialVersionUID = 4152462211121573434L;

    private String code;

    private String data;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
