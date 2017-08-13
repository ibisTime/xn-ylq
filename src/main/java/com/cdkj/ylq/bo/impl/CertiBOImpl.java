/**
 * @Title CertiBOImpl.java 
 * @Package com.cdkj.ylq.bo.impl 
 * @Description 
 * @author leo(haiqing)  
 * @date 2017年8月13日 下午12:04:04 
 * @version V1.0   
 */
package com.cdkj.ylq.bo.impl;

import org.springframework.stereotype.Component;

import com.cdkj.ylq.bo.ICertiBO;
import com.cdkj.ylq.domain.InfoAntifraud;
import com.cdkj.ylq.domain.InfoZMCredit;
import com.cdkj.ylq.domain.MxReportData;
import com.cdkj.ylq.dto.req.XN798013Req;
import com.cdkj.ylq.dto.req.XN798014Req;
import com.cdkj.ylq.dto.req.XN798015Req;
import com.cdkj.ylq.dto.req.XN798016Req;
import com.cdkj.ylq.dto.req.XN798019Req;
import com.cdkj.ylq.dto.req.XN798020Req;
import com.cdkj.ylq.dto.req.XN798021Req;
import com.cdkj.ylq.dto.req.XN798552Req;
import com.cdkj.ylq.dto.res.XN798013Res;
import com.cdkj.ylq.dto.res.XN798014Res;
import com.cdkj.ylq.dto.res.XN798015Res;
import com.cdkj.ylq.dto.res.XN798016Res;
import com.cdkj.ylq.dto.res.XN798019Res;
import com.cdkj.ylq.dto.res.XN798020Res;
import com.cdkj.ylq.dto.res.XN798021Res;
import com.cdkj.ylq.http.BizConnecter;
import com.cdkj.ylq.http.JsonUtils;

/** 
 * @author: haiqingzheng 
 * @since: 2017年8月13日 下午12:04:04 
 * @history:
 */
@Component
public class CertiBOImpl implements ICertiBO {

    @Override
    public XN798013Res doZhimaVerify(String systemCode, String companyCode,
            String userId, String idKind, String idNo, String realName,
            String returnUrl, String localCheck, String remark) {
        XN798013Req req = new XN798013Req();
        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        req.setUserId(userId);
        req.setIdKind(idKind);
        req.setIdNo(idNo);
        req.setRealName(realName);
        req.setReturnUrl(returnUrl);
        req.setLocalCheck(localCheck);
        req.setRemark(remark);
        return BizConnecter.getBizData("798013", JsonUtils.object2Json(req),
            XN798013Res.class);
    }

    @Override
    public XN798014Res doZhimaQuery(String systemCode, String companyCode,
            String bizNo) {
        XN798014Req req = new XN798014Req();
        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        req.setBizNo(bizNo);
        return BizConnecter.getBizData("798014", JsonUtils.object2Json(req),
            XN798014Res.class);
    }

    @Override
    public InfoAntifraud doZhimaCreditAntifraud(String mobile, String idNo,
            String realName, String cardNo, String email, String address,
            String ip, String mac, String wifiMac, String imei) {
        InfoAntifraud infoAntifraud = new InfoAntifraud();
        // 欺诈评分
        XN798019Res xn798019Res = this.doZhimaCreditAntifraudScoreGet(mobile,
            idNo, realName, cardNo, email, address, ip, mac, wifiMac, imei);
        // 欺诈信息验证
        XN798020Res xn798020Res = this.doZhimaCreditAntifraudVerify(mobile,
            idNo, realName, cardNo, email, address, ip, mac, wifiMac, imei);
        // 欺诈关注清单
        XN798021Res xn798021Res = this.doZhimaCreditAntifraudRiskList(mobile,
            idNo, realName, cardNo, email, address, ip, mac, wifiMac, imei);
        // 调用结果组装
        infoAntifraud.setScore(xn798019Res.getScore());
        infoAntifraud.setVerifyInfoList(xn798020Res.getVerifyInfoList());
        infoAntifraud.setHit(xn798021Res.getHit());
        infoAntifraud.setRiskInofList(xn798021Res.getRiskInofList());
        return infoAntifraud;
    }

    @Override
    public InfoZMCredit doZhimaCreditGet(String systemCode, String companyCode,
            String realName, String idNo) {
        InfoZMCredit infoZMCredit = new InfoZMCredit();
        // 芝麻分查询
        XN798015Res xn798015Res = doZhimaCreditScoreGet(systemCode,
            companyCode, realName, idNo);
        infoZMCredit.setAuthorized(xn798015Res.isAuthorized());
        // 如果已授权，立马进行行业关注名单查询
        if (xn798015Res.isAuthorized()) {
            XN798016Res xn798016Res = doZhimaCreditWatchlistiiGet(systemCode,
                companyCode, idNo, realName);
            infoZMCredit.setZmScore(xn798015Res.getZmScore());
            infoZMCredit.setMatched(xn798016Res.isMatched());
            infoZMCredit.setDetails(xn798016Res.getDetails());
        } else {// 未授权，返回授权所需参数
            infoZMCredit.setAppId(xn798015Res.getAppId());
            infoZMCredit.setParam(xn798015Res.getParam());
            infoZMCredit.setSignature(xn798015Res.getSignature());
        }
        return infoZMCredit;
    }

    /** 
     * @see com.cdkj.ylq.bo.ICertiBO#doZhimaCreditAntifraudScoreGet(com.cdkj.ylq.dto.req.XN798019Req)
     */
    @Override
    public XN798019Res doZhimaCreditAntifraudScoreGet(String mobile,
            String idNo, String realName, String cardNo, String email,
            String address, String ip, String mac, String wifiMac, String imei) {
        XN798019Req req = new XN798019Req();
        req.setMobile(mobile);
        req.setIdNo(idNo);
        req.setRealName(realName);
        req.setBankCard(cardNo);
        req.setEmail(email);
        req.setAddress(address);
        req.setIp(ip);
        req.setMac(mac);
        req.setWifimac(wifiMac);
        req.setImei(imei);
        return BizConnecter.getBizData("798019", JsonUtils.object2Json(req),
            XN798019Res.class);
    }

    /** 
     * @see com.cdkj.ylq.bo.ICertiBO#doZhimaCreditAntifraudVerify(com.cdkj.ylq.dto.req.XN798020Req)
     */
    @Override
    public XN798020Res doZhimaCreditAntifraudVerify(String mobile, String idNo,
            String realName, String cardNo, String email, String address,
            String ip, String mac, String wifiMac, String imei) {
        XN798020Req req = new XN798020Req();
        req.setMobile(mobile);
        req.setIdNo(idNo);
        req.setRealName(realName);
        req.setBankCard(cardNo);
        req.setEmail(email);
        req.setAddress(address);
        req.setIp(ip);
        req.setMac(mac);
        req.setWifimac(wifiMac);
        req.setImei(imei);
        return BizConnecter.getBizData("798020", JsonUtils.object2Json(req),
            XN798020Res.class);
    }

    /** 
     * @see com.cdkj.ylq.bo.ICertiBO#doZhimaCreditAntifraudRiskList(com.cdkj.ylq.dto.req.XN798021Req)
     */
    @Override
    public XN798021Res doZhimaCreditAntifraudRiskList(String mobile,
            String idNo, String realName, String cardNo, String email,
            String address, String ip, String mac, String wifiMac, String imei) {
        XN798021Req req = new XN798021Req();
        req.setMobile(mobile);
        req.setIdNo(idNo);
        req.setRealName(realName);
        req.setBankCard(cardNo);
        req.setEmail(email);
        req.setAddress(address);
        req.setIp(ip);
        req.setMac(mac);
        req.setWifimac(wifiMac);
        req.setImei(imei);
        return BizConnecter.getBizData("798021", JsonUtils.object2Json(req),
            XN798021Res.class);
    }

    @Override
    public XN798015Res doZhimaCreditScoreGet(String systemCode,
            String companyCode, String realName, String idNo) {
        XN798015Req req = new XN798015Req();
        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        req.setRealName(realName);
        req.setIdNo(idNo);
        return BizConnecter.getBizData("798015", JsonUtils.object2Json(req),
            XN798015Res.class);
    }

    @Override
    public XN798016Res doZhimaCreditWatchlistiiGet(String systemCode,
            String companyCode, String idNo, String realName) {
        XN798016Req req = new XN798016Req();
        req.setSystemCode(systemCode);
        req.setCompanyCode(companyCode);
        req.setRealName(realName);
        req.setIdNo(idNo);
        return BizConnecter.getBizData("798016", JsonUtils.object2Json(req),
            XN798016Res.class);
    }

    @Override
    public MxReportData doMxReportDataGet(String taskId) {
        XN798552Req req = new XN798552Req();
        req.setTaskId(taskId);
        return BizConnecter.getBizData("798552", JsonUtils.object2Json(req),
            MxReportData.class);
    }

}
