/**
 * @Title ContractBOImpl.java 
 * @Package com.xnjr.pop.bo.impl 
 * @Description 
 * @author haiqingzheng  
 * @date 2015年11月14日 下午3:38:26 
 * @version V1.0   
 */
package com.cdkj.ylq.bo.impl;

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

    // @Override
    // public String generateChild(Project project, Invest invest, XN702991Res
    // user) {
    // ContractTemplate template = getContractTemplate(EContractType.Child);
    //
    // Contract contract = new Contract();
    // String projectCode = project.getCode();
    // String code = OrderNoGenerater.generateM("GJ-ZQZR-");
    // contract.setCode(code);
    // contract.setUserId(invest.getUserId());
    // contract.setProjectCode(projectCode);
    // contract.setTitle(EContractType.Child.getValue());
    // String content = "";
    // try {
    // Map<String, String> argsMap = new HashMap<String, String>();
    // // 合同编号
    // argsMap.put("contractCode", code);
    // Company company = companyBO.getCompany(project.getCompanyId());
    // // 甲方：
    // argsMap.put("PartyA", company.getName());
    // argsMap.put("licenceNO", company.getLicenceNo());
    // // 乙方：
    // argsMap.put("PartyB", user.getRealName());
    // argsMap.put("idCode", user.getIdNo());
    // // 借款本金
    // Long corpus = invest.getInvestAmount();
    // argsMap.put("loanAmount", CalculationUtil.divi(corpus));
    // // 利率
    // argsMap.put("interestRate",
    // CalculationUtil.multHundred(project.getInterestRate()));
    //
    // Date repayDatetime = null;
    // Long interest = 0L;
    // // 借款期限
    // if (EProjectType.XS.getCode().equalsIgnoreCase(project.getType())) {
    // int days = project.getInterestDays().intValue();
    // argsMap.put("period", String.valueOf(days));
    // repayDatetime = DateUtil.getNextDayEnd(
    // invest.getInvestDatetime(), days + 1);
    // int interestDays = project.getInterestDays().intValue();
    // interest = ProjectInterestUtil.getInterest(corpus,
    // project.getInterestRate(), interestDays);
    // } else {
    // repayDatetime = project.getRepayDatetime();
    // int days = DateUtil.daysBetween(invest.getInvestDatetime(),
    // repayDatetime);
    // argsMap.put("period", String.valueOf(days));
    //
    // // 还款金额
    // Date interestStartDatetime = DateUtil.getTomorrowStart(invest
    // .getInvestDatetime());
    // interest = ProjectInterestUtil.getInterest(corpus,
    // project.getInterestRate(), interestStartDatetime,
    // project.getRepayDatetime());
    // }
    // argsMap
    // .put("repayDatetime", DateUtil.dateToStr(repayDatetime,
    // DateUtil.DATA_TIME_PATTERN_6));
    //
    // argsMap.put("repayAmount", CalculationUtil.divi(corpus + interest));
    // argsMap.put("repayWay", "到期一次性还本付息");
    // argsMap.put("depositRate", "3%");
    // argsMap.put("chapterUrl", company.getChapterUrl());
    // // 签订时间:
    // argsMap.put("createTime",
    // DateUtil.dateToStr(new Date(), DateUtil.DATA_TIME_PATTERN_6));
    // content = replace(template.getContent(), argsMap);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // contract.setContent(content);
    // contract.setType(EContractType.Child.getCode());
    // contract.setCreateDatetime(new Date());
    // contract.setRemark(EContractType.Child.getValue());
    // contract.setStatus(EContractStatus.ING.getCode());
    // contractDAO.insert(contract);
    // return code;
    // }

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

    // private ContractTemplate getContractTemplate(EContractType father) {
    // ContractTemplate condition = new ContractTemplate();
    // condition.setType(father.getCode());
    // List<ContractTemplate> list = contractTemplateDAO.selectList(condition);
    // if (CollectionUtils.isNotEmpty(list)) {
    // return list.get(0);
    // } else {
    // throw new BizException("XN000000", father.getValue() + "合同模板不存在");
    // }
    // }

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
            String partyA = sysConfigBO.getStringValue(SysConstants.PARTY_A);
            String partyAIdNo = sysConfigBO
                .getStringValue(SysConstants.PARTY_A_ID_NO);
            String amount = CalculationUtil.diviUp(borrowAmount);
            Long lxAmount = AmountUtil.eraseLiUp(AmountUtil.mul(borrowAmount,
                product.getLxRate())) * product.getDuration();
            Date jxDatetime = DateUtil.getTomorrowStart(new Date());
            Date hkDatetime = DateUtil.getRelativeDate(jxDatetime,
                product.getDuration() * 24 * 3600 - 1);
            String repayDate = DateUtil.dateToStr(hkDatetime,
                DateUtil.DATA_TIME_PATTERN_6);
            Map<String, String> argsMap = new HashMap<String, String>();
            argsMap.put("contractCode", contractCode); // 合同编号
            argsMap.put("signDate", signDate); // 签订日期
            argsMap.put("partyA", partyA); // 甲方（出借人）
            argsMap.put("partyAIdNo", partyAIdNo);// 甲方（出借人）身份证号码
            argsMap.put("partyB", user.getRealName());// 乙方（借款人）
            argsMap.put("partyBIdNo", user.getIdNo());// 乙方（出借人）身份证号码
            argsMap.put("partyBBankcard", bankcard.getBankcardNumber());// 银行卡号
            argsMap.put("borrowAmount", amount);// 借款本金
            argsMap.put("borrowDays", String.valueOf(product.getDuration()));// 借款期限
            argsMap
                .put("borrowRate", String.valueOf(product.getLxRate() * 100));// 利率
            argsMap.put("lxAmount", String.valueOf(lxAmount));// 利息
            argsMap.put("fwAmount", String.valueOf(borrowAmount * 0.1965));// 服务费
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
    public String generate(Product product, Borrow borrow, User user) {
        // TODO Auto-generated method stub
        return null;
    }
}
