/*
* 文 件 名:  TimeoutExpirationPolicy.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  时间过期策略
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket.policy;

import org.yy.sso.ticket.ExpirationPolicy;
import org.yy.sso.ticket.TicketState;

/**
* 时间过期策略
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public final class TimeoutExpirationPolicy implements ExpirationPolicy {
    
    /**过期时间，单位为毫秒*/
    private final long timeoutMilliSeconds;
    
    /**
     * 构建过期策略
     * @param timeoutSeconds 过期时间
     */
    public TimeoutExpirationPolicy(final long timeoutMilliSeconds) {
        this.timeoutMilliSeconds = timeoutMilliSeconds;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean isExpired(TicketState ticketState) {
        return (ticketState == null)
            || (System.currentTimeMillis() - ticketState.getLastTimeUsed() >= this.timeoutMilliSeconds);
    }
    
}
