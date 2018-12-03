package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.dao.ISYSConfigDAO;
import com.cdkj.ylq.domain.SYSConfig;
import com.cdkj.ylq.enums.ECompanyCodeModel;
import com.cdkj.ylq.exception.BizException;

/**
 * 
 * @author: Gejin 
 * @since: 2016年4月17日 下午7:56:03 
 * @history:
 */

@Component
public class SYSConfigBOImpl extends PaginableBOImpl<SYSConfig> implements
        ISYSConfigBO {

    static Logger logger = Logger.getLogger(SYSConfigBOImpl.class);

    @Autowired
    private ISYSConfigDAO sysConfigDAO;

    @Override
    public int refreshSYSConfig(Long id, String cvalue, String updater,
            String remark) {
        SYSConfig data = new SYSConfig();
        data.setId(id);
        data.setCvalue(cvalue);

        data.setUpdater(updater);
        data.setUpdateDatetime(new Date());
        data.setRemark(remark);
        return sysConfigDAO.updateValue(data);
    }

    @Override
    public SYSConfig getSYSConfig(Long id) {
        SYSConfig sysConfig = null;
        if (id > 0) {
            SYSConfig condition = new SYSConfig();
            condition.setId(id);
            sysConfig = sysConfigDAO.select(condition);
        }
        if (sysConfig == null) {
            throw new BizException("xn000000", "id记录不存在");
        }
        return sysConfig;
    }

    @Override
    public Map<String, String> getConfigsMap(String type) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isNotBlank(type)) {
            SYSConfig condition = new SYSConfig();
            condition.setType(type);
            List<SYSConfig> list = sysConfigDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(list)) {
                for (SYSConfig sysConfig : list) {
                    map.put(sysConfig.getCkey(), sysConfig.getCvalue());
                }
            }
        }
        return map;

    }

    @Override
    public SYSConfig getSYSConfig(String key, String companyCode,
            String systemCode) {
        SYSConfig sysConfig = null;
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(systemCode)
                && StringUtils.isNotBlank(companyCode)) {
            SYSConfig condition = new SYSConfig();
            condition.setCkey(key);
            condition.setCompanyCode(companyCode);
            condition.setSystemCode(systemCode);
            List<SYSConfig> sysConfigList = sysConfigDAO.selectList(condition);
            if (CollectionUtils.isNotEmpty(sysConfigList)) {
                sysConfig = sysConfigList.get(0);
            } else {
                throw new BizException("xn000000", key + "对应记录不存在");
            }
        }
        return sysConfig;
    }

    @Override
    public SYSConfig getSYSConfig(String key, String systemCode) {
        return getSYSConfig(key, systemCode, systemCode);
    }

    @Override
    public Double getDoubleValue(String key, String companyCode) {
        Double result = 0.0;
        SYSConfig config = getSYSConfig(key, companyCode);
        try {
            result = Double.valueOf(config.getCvalue());
        } catch (Exception e) {
            logger.error("参数名为" + key + "的配置转换成Double类型发生错误, 原因："
                    + e.getMessage());
        }
        return result;
    }

    @Override
    public Integer getIntegerValue(String key, String companyCode) {
        Integer result = 0;
        SYSConfig config = getSYSConfig(key, companyCode);
        try {
            result = Integer.valueOf(config.getCvalue());
        } catch (Exception e) {
            logger.error("参数名为" + key + "的配置转换成Integer类型发生错误, 原因："
                    + e.getMessage());
        }
        return result;
    }

    @Override
    public String getStringValue(String key, String companyCode) {
        SYSConfig config = getSYSConfig(key, companyCode);
        return config.getCvalue();
    }

    @Override
    public BigDecimal getBigDecimalValue(String key, String companyCode) {
        BigDecimal result = BigDecimal.ZERO;
        SYSConfig config = getSYSConfig(key, companyCode);
        try {
            result = new BigDecimal(config.getCvalue());
        } catch (Exception e) {
            logger.error("参数名为" + key + "的配置转换成bigdecimal类型发生错误, 原因："
                    + e.getMessage());
        }
        return result;
    }

    @Override
    public void saveConfig(String type, String ckey, String cvalue,
            String updater, String remark, String companyCode) {
        SYSConfig config = new SYSConfig();
        config.setType(type);
        config.setCkey(ckey);
        config.setCvalue(cvalue);
        config.setUpdater(updater);
        config.setUpdateDatetime(new Date());
        config.setRemark(remark);
        config.setCompanyCode(companyCode);
        sysConfigDAO.insert(config);

    }

    @Override
    public List<SYSConfig> queryModelConfigs() {
        SYSConfig condition = new SYSConfig();
        condition.setCompanyCode(ECompanyCodeModel.MODEL.getCode());
        return sysConfigDAO.selectList(condition);
    }

}
