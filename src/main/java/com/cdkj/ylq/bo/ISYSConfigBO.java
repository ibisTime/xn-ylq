/**
 * @Title ISYSConfigBO.java 
 * @Package com.xnjr.moom.bo 
 * @Description 
 * @author haiqingzheng  
 * @date 2016年4月17日 下午2:40:52 
 * @version V1.0   
 */
package com.cdkj.ylq.bo;

import java.util.Map;

import com.cdkj.ylq.bo.base.IPaginableBO;
import com.cdkj.ylq.domain.SYSConfig;

/** 
 * @author: haiqingzheng 
 * @since: 2016年4月17日 下午2:40:52 
 * @history:
 */
public interface ISYSConfigBO extends IPaginableBO<SYSConfig> {
    public int refreshSYSConfig(Long id, String cvalue, String updater,
            String remark);

    public SYSConfig getSYSConfig(Long id);

    /**
     * systemCode=companyCode时调用此方法
     * @param systemCode
     * @return 
     * @create: 2017年3月22日 下午3:45:36 myb858
     * @history:
     */
    public Map<String, String> getConfigsMap(String systemCode);

    /**
     * systemCode=companyCode时调用此方法
     * @param key
     * @param systemCode
     * @return 
     * @create: 2017年3月22日 下午3:45:54 myb858
     * @history:
     */
    public SYSConfig getSYSConfig(String key, String systemCode);

    public SYSConfig getSYSConfig(String key, String companyCode,
            String systemCode);

    public String getStringValue(String key, String companyCode,
            String systemCode);

    public Double getDoubleValue(String key, String companyCode,
            String systemCode);

    public Integer getIntegerValue(String key, String companyCode,
            String systemCode);

}
