package com.cdkj.ylq.dto.res;

/**
 * 新增用户统计
 * @author: silver 
 * @since: Oct 22, 2018 3:56:46 PM 
 * @history:
 */
public class XN629901Res {
    // 用户总数
    private long userTotalCount;

    // C端用户总数
    private long userCount;

    // 代理用户人数
    private long agentUserCount;

    public XN629901Res(long userTotalCount) {
        super();
        this.userTotalCount = userTotalCount;
    }

    public XN629901Res(long userTotalCount, long userCount,
            long agentUserCount) {
        super();
        this.userTotalCount = userTotalCount;
        this.userCount = userCount;
        this.agentUserCount = agentUserCount;
    }

    public long getUserCount() {
        return userCount;
    }

    public void setUserCount(long userCount) {
        this.userCount = userCount;
    }

    public long getAgentUserCount() {
        return agentUserCount;
    }

    public void setAgentUserCount(long agentUserCount) {
        this.agentUserCount = agentUserCount;
    }

    public long getUserTotalCount() {
        return userTotalCount;
    }

    public void setUserTotalCount(long userTotalCount) {
        this.userTotalCount = userTotalCount;
    }

}
