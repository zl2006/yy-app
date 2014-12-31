/*
* 文 件 名:  ExpirationPolicy.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  令牌过期策略
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket;

/**
* 令牌过期策略
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public interface ExpirationPolicy {
    
    /**
     * 判断是否令牌过期
     * @param ticketState 令牌状态
     * @return true 表示过期
     */
    boolean isExpired(TicketState ticketState);
}
