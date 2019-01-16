package com.cdkj.ylq.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdkj.ylq.ao.IWayerAO;
import com.cdkj.ylq.bo.IUserBO;
import com.cdkj.ylq.bo.IWayBO;
import com.cdkj.ylq.bo.IWayerBO;
import com.cdkj.ylq.bo.base.Page;
import com.cdkj.ylq.bo.base.Paginable;
import com.cdkj.ylq.common.DateUtil;
import com.cdkj.ylq.common.MD5Util;
import com.cdkj.ylq.domain.User;
import com.cdkj.ylq.domain.Way;
import com.cdkj.ylq.domain.Wayer;
import com.cdkj.ylq.dto.res.XN623208Res;
import com.cdkj.ylq.enums.EUserStatus;
import com.cdkj.ylq.exception.BizException;
import com.cdkj.ylq.exception.EBizErrorCode;

@Service
public class WayerAOImpl implements IWayerAO {

    @Autowired
    private IWayerBO wayerBO;

    @Autowired
    private IUserBO userBO;

    @Autowired
    private IWayBO wayBO;

    @Override
    @Transactional
    public String addWayer(String name, String loginName, String loginPwd,
            String updater, String remark, String companyCode) {
        if (wayerBO.isNameExist(name, companyCode)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "渠道名已存在，请换一个渠道名");
        }

        if (wayerBO.isLoginNameExist(loginName, companyCode)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(),
                "登陆名已存在，请换一个登陆名");
        }

        return wayerBO.saveWayer(name, loginName, loginPwd, updater, remark,
            companyCode);
    }

    @Override
    public int dropWayer(String code) {
        if (!wayerBO.isWayerExist(code)) {
            throw new BizException("xn0000", "记录编号不存在");
        }
        return wayerBO.removeWayer(code);
    }

    @Override
    public Paginable<Wayer> queryWayerPage(int start, int limit, Wayer condition) {
        return wayerBO.getPaginable(start, limit, condition);
    }

    @Override
    public List<Wayer> queryWayerList(Wayer condition) {
        return wayerBO.queryWayerList(condition);
    }

    @Override
    public Wayer getWayer(String code) {
        return wayerBO.getWayer(code);
    }

    @Override
    public Wayer doLogin(String loginName, String loginPwd) {
        if (!wayerBO.isLoginNameExist(loginName, null)) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "登录名不存在");
        }
        Wayer condition = new Wayer();
        condition.setLoginName(loginName);
        condition.setLoginPwd(MD5Util.md5(loginPwd));
        List<Wayer> wayerList = wayerBO.queryWayerList(condition);
        if (wayerList.isEmpty()) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "密码不正确");
        }
        if (!EUserStatus.NORMAL.getCode().equals(wayerList.get(0).getStatus())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "该账号"
                    + EUserStatus.getMap().get(wayerList.get(0).getStatus())
                        .getValue() + "，请联系工作人员");
        }
        return wayerList.get(0);
    }

    @Override
    public void editPwd(String userId, String oldPwd, String newPwd) {
        Wayer wayer = wayerBO.getWayer(userId);
        if (!MD5Util.md5(oldPwd).equals(wayer.getLoginPwd())) {
            throw new BizException(EBizErrorCode.DEFAULT.getCode(), "原密码不正确");
        }
        wayerBO.refreshPwd(wayer, newPwd);
    }

    @Override
    public void editStatus(String userId, String updater) {
        Wayer wayer = wayerBO.getWayer(userId);
        if (EUserStatus.NORMAL.getCode().equals(wayer.getStatus())) {
            wayerBO.refreshStatus(wayer, EUserStatus.Ren_Locked.getCode(),
                updater, "人工锁定");
        } else {
            wayerBO.refreshStatus(wayer, EUserStatus.NORMAL.getCode(), updater,
                "已恢复使用");
        }
    }

    @Override
    public Page<XN623208Res> getUsersOfWayer(String userId, int start,
            int limit, String dateStart, String dateEnd) {
        wayerBO.getWayer(userId);
        List<XN623208Res> ress = new ArrayList<XN623208Res>();
        Way conditionWay = new Way();
        conditionWay.setUserId(userId);
        List<Way> wayList = wayBO.queryWayList(conditionWay);
        for (Way way : wayList) {
            User conditionUser = new User();
            conditionUser.setUserReferee(way.getCode());
            conditionUser.setCreateDatetimeStart(DateUtil.strToDate(dateStart,
                DateUtil.FRONT_DATE_FORMAT_STRING));
            conditionUser.setCreateDatetimeEnd(DateUtil.strToDate(dateEnd,
                DateUtil.FRONT_DATE_FORMAT_STRING));
            List<User> userList = userBO.queryUserList(conditionUser);
            for (User user : userList) {
                XN623208Res res = new XN623208Res();
                res.setMobile(user.getMobile());
                res.setWayName(way.getName());
                res.setRegDatetime(user.getCreateDatetime());
                ress.add(res);
            }
        }
        // 组装page
        Page<XN623208Res> page = new Page<XN623208Res>(start, limit,
            ress.size());
        List<XN623208Res> list = new ArrayList<XN623208Res>();
        for (int i = page.getStart(); i < ress.size(); i++) {
            list.add(ress.get(i));
            if (list.size() == page.getPageSize()) {
                break;
            }
        }

        page.setList(list);
        return page;
    }

}
