/**
 * @Title ContractBOImpl.java 
 * @Package com.xnjr.pop.bo.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年11月14日 下午3:38:26 
 * @version V1.0   
 */
package com.cdkj.ylq.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.IContractBO;
import com.cdkj.ylq.bo.ISYSConfigBO;
import com.cdkj.ylq.bo.base.PaginableBOImpl;
import com.cdkj.ylq.common.AmountUtil;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.SysConstants;
import com.cdkj.ylq.core.CalculationUtil;
import com.cdkj.ylq.core.OrderNoGenerater;
import com.cdkj.ylq.dao.IContractDAO;
import com.cdkj.ylq.domain.Bankcard;
import com.cdkj.ylq.domain.Borrow;
import com.cdkj.ylq.domain.Contract;
import com.cdkj.ylq.domain.Product;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.enums.EContractStatus;
import com.cdkj.ylq.enums.EGeneratePrefix;

/** 
 * @author: haiqingzheng 
 * @since: 2015年11月14日 下午3:38:26 
 * @history:
 */
@Component
public class ContractBOImpl extends PaginableBOImpl<Contract> implements
        IContractBO {
    @Autowired
    IContractDAO contractDAO;

    @Autowired
    ISYSConfigBO sysConfigBO;

    private String replace(String template, Map<String, String> data)
            throws Exception {
        Matcher matcher = Pattern.compile("\\[(.+?)\\]").matcher(template);
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (matcher.find()) {
            String name = matcher.group(1);// 键名
            String value = (String) data.get(name);
            if (StringUtils.isBlank(value)) {
                value = "";
            }
            matcher.appendReplacement(sb, value);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    @Override
    public String preview(User user, Bankcard bankcard, Product product,
            Long borrowAmount) {
        String contact = null;
        // 拿到合同模板
        String contracttemplate = sysConfigBO
            .getStringValue(SysConstants.BORROW_PROTOCOL);
        try {
            String contractCode = OrderNoGenerater
                .generateM(EGeneratePrefix.BORROW.getCode());
            String signDate = DateUtil.getToday(DateUtil.DATA_TIME_PATTERN_6);
            String amount = CalculationUtil.diviUp(borrowAmount);
            String lxAmount = CalculationUtil.diviUp(product.getLxRate()
                .longValue() * product.getDuration() * borrowAmount);
            Double fwRate = sysConfigBO.getDoubleValue(SysConstants.FW_RATE);
            String fwAmount = CalculationUtil.diviUp(AmountUtil.mul(
                borrowAmount, fwRate));
            Date jxDatetime = DateUtil.getTomorrowStart(new Date());
            Date hkDatetime = DateUtil.getRelativeDate(jxDatetime,
                product.getDuration() * 24 * 3600 - 1);
            String repayDate = DateUtil.dateToStr(hkDatetime,
                DateUtil.DATA_TIME_PATTERN_6);
            Map<String, String> argsMap = new HashMap<String, String>();
            argsMap.put("contractCode", contractCode); // 合同编号
            argsMap.put("signDate", signDate); // 签订日期
            argsMap.put("partyB", user.getRealName());// 乙方（借款人）
            argsMap.put("partyBIdNo", user.getIdNo());// 乙方（出借人）身份证号码
            argsMap.put("partyBBankcard", bankcard.getBankcardNumber());// 银行卡号
            argsMap.put("borrowAmount", amount);// 借款本金
            argsMap.put("borrowDays", String.valueOf(product.getDuration()));// 借款期限
            argsMap.put("borrowRate", String.valueOf(product.getLxRate()
                .multiply(new BigDecimal(100))));// 利率
            argsMap.put("lxAmount", lxAmount);// 利息
            argsMap.put("fwAmount", fwAmount);// 服务费
            argsMap.put("repayDate", repayDate);// 还款日期
            argsMap.put("repayAmount", amount);// 还款本金
            argsMap.put("partyBBank", bankcard.getBankName());// 开户行
            contact = replace(contracttemplate, argsMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public Contract getContract(String code) {
        Contract contract = null;
        if (StringUtils.isNotBlank(code)) {
            Contract condition = new Contract();
            condition.setCode(code);
            contract = contractDAO.select(condition);
        }
        return contract;
    }

    /** 
     * @see com.xnjr.pop.bo.IContractBO#queryContractList(com.xnjr.pop.domain.Contract)
     */
    @Override
    public List<Contract> queryContractList(Contract condition) {
        return contractDAO.selectList(condition);
    }

    @Override
    public String generate(User user, Bankcard bankcard, Borrow borrow) {
        String contact = null;
        // 拿到合同模板
        String contracttemplate = sysConfigBO
            .getStringValue(SysConstants.BORROW_PROTOCOL);
        try {
            String contractCode = OrderNoGenerater
                .generateM(EGeneratePrefix.BORROW.getCode());
            String signDate = DateUtil.getToday(DateUtil.DATA_TIME_PATTERN_6);
            Long borrowAmount = borrow.getAmount();
            String amount = CalculationUtil.diviUp(borrowAmount);
            String lxAmount = CalculationUtil.diviUp(AmountUtil.mul(
                borrowAmount, borrow.getLxRate()) * borrow.getDuration());
            Double fwRate = sysConfigBO.getDoubleValue(SysConstants.FW_RATE);
            String fwAmount = CalculationUtil.diviUp(AmountUtil.mul(
                borrowAmount, fwRate));
            Date jxDatetime = DateUtil.getTomorrowStart(new Date());
            Date hkDatetime = DateUtil.getRelativeDate(jxDatetime,
                borrow.getDuration() * 24 * 3600 - 1);
            String repayDate = DateUtil.dateToStr(hkDatetime,
                DateUtil.DATA_TIME_PATTERN_6);
            Map<String, String> argsMap = new HashMap<String, String>();
            argsMap.put("contractCode", contractCode); // 合同编号
            argsMap.put("signDate", signDate); // 签订日期
            argsMap.put("partyB", user.getRealName());// 乙方（借款人）
            argsMap.put("partyBIdNo", user.getIdNo());// 乙方（出借人）身份证号码
            argsMap.put("partyBBankcard", bankcard.getBankcardNumber());// 银行卡号
            argsMap.put("borrowAmount", amount);// 借款本金
            argsMap.put("borrowDays", String.valueOf(borrow.getDuration()));// 借款期限
            argsMap.put("borrowRate", String.valueOf(borrow.getLxRate() * 100));// 利率
            argsMap.put("lxAmount", lxAmount);// 利息
            argsMap.put("fwAmount", fwAmount);// 服务费
            argsMap.put("repayDate", repayDate);// 还款日期
            argsMap.put("repayAmount", amount);// 还款本金
            argsMap.put("partyBBank", bankcard.getBankName());// 开户行
            contact = replace(contracttemplate, argsMap);

            Contract data = new Contract();
            data.setCode(borrow.getCode());
            data.setUserId(borrow.getApplyUser());
            data.setBorrowCode(borrow.getCode());
            data.setTitle(user.getRealName() + "-借款协议"
                    + DateUtil.getToday(DateUtil.DB_DATE_FORMAT_STRING));
            data.setContent(contact);
            data.setType("0");
            data.setCreateDatetime(new Date());
            data.setStatus(EContractStatus.ING.getCode());
            contractDAO.insert(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contact;
    }
}
