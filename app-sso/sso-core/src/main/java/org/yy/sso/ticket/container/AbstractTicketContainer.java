/*
* 文 件 名:  AbstractTicketContainer.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  令牌容器,对用户的令牌进行管理.例如：过期/失效/添加等
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket.container;

import org.yy.sso.ticket.Ticket;
import org.yy.sso.ticket.TicketContainer;

/**
* 令牌容器,对用户的令牌进行管理.例如：过期/失效/添加等
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public abstract class AbstractTicketContainer implements TicketContainer {
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public <T extends Ticket> T getTicket(String ticketId, Class<? extends Ticket> clazz) {
        final Ticket ticket = this.getTicket(ticketId);
        
        if (ticket == null) {
            return null;
        }
        
        if (!clazz.isAssignableFrom(ticket.getClass())) {
            throw new ClassCastException("Ticket [" + ticket.getId() + " is of type " + ticket.getClass()
                + " when we were expecting " + clazz);
        }
        
        return (T)ticket;
    }
    
}
