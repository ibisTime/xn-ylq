/**
 * @Title JDTAOImpl.java 
 * @Package com.cdkj.ylq.ao.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年9月27日 下午3:30:31 
 * @version V1.0   
 */
package com.cdkj.ylq.ao.impl;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdkj.ylq.ao.ICertificationAO;
import com.cdkj.ylq.ao.IJdtAO;
import com.cdkj.ylq.bo.IApplyBO;
import com.cdkj.ylq.bo.IJdtBO;
import com.cdkj.ylq.bo.IProductBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.domain.Apply;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.dto.req.XN805042Req;
import com.cdkj.ylq.enums.EApplyStatus;
import com.cdkj.ylq.enums.EApplyType;
import com.cdkj.ylq.enums.EGeneratePrefix;
import com.cdkj.ylq.enums.EIDKind;
import com.cdkj.ylq.enums.EProductLevel;
import com.cdkj.ylq.enums.ESystemCode;
import com.cdkj.ylq.enums.EUserKind;
import com.cdkj.ylq.exception.BizException;

/** 
 * @author: haiqingzheng 
 * @since: 2017年9月27日 下午3:30:31 
 * @history:
 */
@Service
public class JdtAOImpl implements IJdtAO {

    protected static final Logger logger = LoggerFactory
        .getLogger(JdtAOImpl.class);

    @Autowired
    IJdtBO jdtBO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    @Autowired
    IUserBO userBO;

    @Autowired
    IApplyBO applyBO;

    @Autowired
    IProductBO productBO;

    @Autowired
    private ICertificationAO certificationAO;

    /** 
     * @see com.cdkj.ylq.ao.IJdtAO#doGetNewMember(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void doGetNewMember(String token, String uid, String demandKey,
            String demandId, String dataTime) {
        if (StringUtils.isBlank(token)) {
            throw new BizException("借贷通-实时通告token获取失败");
        }
        String accessToken = jdtBO.getAccessToken();
        logger.info("借贷通-accessToken=" + accessToken);
        String report = jdtBO.getSSPData(accessToken, token);
        logger.info("借贷通-report获取成功");
        JSONObject reportJson = JSONObject.fromObject(report);
        JSONObject appInfoJson = JSONObject.fromObject(reportJson
            .get("appInfo"));
        JSONObject personInfoJson = JSONObject.fromObject(appInfoJson
            .get("personInfo"));
        String mobile = (String) personInfoJson.get("personMobile");
        String idNo = (String) personInfoJson.get("personIdCard");
        String realName = (String) personInfoJson.get("personName");
        XN805042Req req = new XN805042Req();
        req.setLoginName(mobile);
        req.setLoginPwd("888888");
        req.setMobile(mobile);
        req.setIdKind(EIDKind.IDCard.getCode());
        req.setIdNo(idNo);
        req.setRealName(realName);
        req.setKind(EUserKind.Customer.getCode());
        req.setUpdater("借贷通推送");
        req.setRemark("借贷通推送");
        req.setCompanyCode(ESystemCode.YLQ.getCode());
        req.setSystemCode(ESystemCode.YLQ.getCode());
        String userId = userBO.doAddUser(req);
        if (StringUtils.isNotBlank(userId)) {
            // 分配认证信息
            certificationAO.initialCertification(userId);
            // 待申请
            Product condition = new Product();
            condition.setLevel(EProductLevel.ONE.getCode());
            List<Product> productList = productBO.queryProductList(condition);
            if (CollectionUtils.isNotEmpty(productList)) {
                String productCode = productList.get(0).getCode();
                Apply data = new Apply();
                String code = OrderNoGenerater.generateM(EGeneratePrefix.APPLY
                    .getCode());
                data.setCode(code);
                data.setType(EApplyType.JDT.getCode());
                data.setApplyUser(userId);
                data.setApplyDatetime(new Date());
                data.setProductCode(productCode);
                data.setStatus(EApplyStatus.TO_APPROVE.getCode());
                data.setUpdater(userId);
                data.setUpdateDatetime(new Date());
                data.setRemark("借贷通推送");
                data.setJdtReport(report);
                applyBO.saveApply(data);
            }
        }
    }
}
