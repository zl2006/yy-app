/*
* 文 件 名:  DefaultTicketContainer.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  默认令牌容器, 采用map存储。不支持分布式
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket.container;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.yy.sso.ticket.AbstractTicket;
import org.yy.sso.ticket.Ticket;

/**
* 默认令牌容器, 采用map存储。不支持分布式
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public class DefaultTicketContainer extends AbstractTicketContainer {
    
    /** Ticket存储器*/
    private final Map<String, Ticket> cache;
    
    /** 
    <默认构造函数>
    */
    public DefaultTicketContainer() {
        this.cache = new ConcurrentHashMap<String, Ticket>();
    }
    
    /** {@inheritDoc} */
    @Override
    public void addTicket(Ticket ticket) {
        cache.put(ticket.getId(), ticket);
    }
    
    /** {@inheritDoc} */
    @Override
    public Ticket getTicket(String ticketId) {
        if (ticketId == null) {
            return null;
        }
        return cache.get(ticketId);
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean deleteTicket(String ticketId) {
        if (ticketId == null) {
            return false;
        }
        return (this.cache.remove(ticketId) != null);
    }
    
    /** {@inheritDoc} */
    @Override
    public Collection<Ticket> getTickets() {
        return Collections.unmodifiableCollection(this.cache.values());
    }
    
    /** {@inheritDoc} */
    @Override
    public void updateTicket(Ticket ticket) {
        if (ticket instanceof AbstractTicket) {
            ((AbstractTicket)ticket).updateState();
        }
    }
    
}
