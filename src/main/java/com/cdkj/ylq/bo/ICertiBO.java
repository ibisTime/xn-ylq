/**
 * @Title ICertiBO.java 
 * @Package com.cdkj.ylq.bo 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月13日 上午11:44:31 
 * @version V1.0   
 */
package com.cdkj.ylq.bo;

import com.cdkj.ylq.domain.InfoAntifraud;
import com.cdkj.ylq.domain.InfoZMCredit;
import com.cdkj.ylq.domain.MxReportData;
import com.cdkj.ylq.dto.res.XN798013Res;
import com.cdkj.ylq.dto.res.XN798014Res;
import com.cdkj.ylq.dto.res.XN798015Res;
import com.cdkj.ylq.dto.res.XN798016Res;
import com.cdkj.ylq.dto.res.XN798019Res;
import com.cdkj.ylq.dto.res.XN798020Res;
import com.cdkj.ylq.dto.res.XN798021Res;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月13日 上午11:44:31 
 * @history:
 */
public interface ICertiBO {

    // 芝麻认证第一步，返回bizNo
    public XN798013Res doZhimaVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String returnUrl, String localCheck, String remark);

    // 芝麻认证查询
    public XN798014Res doZhimaQuery(String systemCode, String companyCode,
            String bizNo);

    // 开始芝麻欺诈三认证
    public InfoAntifraud doZhimaCreditAntifraud(String mobile, String idNo,
            String realName, String cardNo, String email, String address,
            String ip, String mac, String wifiMac, String imei);

    // 开始芝麻信用认证，信用分查询+行业关注名单查询
    public InfoZMCredit doZhimaCreditGet(String systemCode, String companyCode,
            String realName, String idNo);

    // 申请欺诈评分
    public XN798019Res doZhimaCreditAntifraudScoreGet(String mobile,
            String idNo, String realName, String cardNo, String email,
            String address, String ip, String mac, String wifiMac, String imei);

    // 欺诈信息验证
    public XN798020Res doZhimaCreditAntifraudVerify(String mobile, String idNo,
            String realName, String cardNo, String email, String address,
            String ip, String mac, String wifiMac, String imei);

    // 欺诈关注清单
    public XN798021Res doZhimaCreditAntifraudRiskList(String mobile,
            String idNo, String realName, String cardNo, String email,
            String address, String ip, String mac, String wifiMac, String imei);

    // 芝麻信用分查询
    public XN798015Res doZhimaCreditScoreGet(String systemCode,
            String companyCode, String realName, String idNo);

    // 行业关注名单
    public XN798016Res doZhimaCreditWatchlistiiGet(String systemCode,
            String companyCode, String idNo, String realName);

    // 魔蝎报告查询
    public MxReportData doMxReportDataGet(String taskId);
}
