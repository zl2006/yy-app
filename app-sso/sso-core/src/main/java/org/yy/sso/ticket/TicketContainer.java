/*
* 文 件 名:  TicketContainer.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  令牌容器
* 修 改 人:  zhouliang
* 修改时间:  2013年12月14日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket;

import java.util.Collection;

/**
* 令牌容器,对用户的令牌进行管理.例如：过期/失效/添加等
* 
* @author  zhouliang
* @version  [1.0, 2013年12月14日]
* @since  [app-sso/1.0]
*/
public interface TicketContainer {
    
    /**
     * 增加一个令牌到容器
     *
     * @param 令牌信息
     */
    void addTicket(Ticket ticket);
    
    /**
     * 根据令牌ID，从容器中获取一个令牌。并根据指定的clazz时进行类型返回
     *
     * @param 令牌ID
     * @param 返回结果类型
     * @return 令牌
     */
    <T extends Ticket> T getTicket(String ticketId, Class<? extends Ticket> clazz);
    
    /**
     * 根据令牌ID，从容器中获取一个令牌。
     *
     * @param ticketId 令牌ID
     * @return 令牌
     */
    Ticket getTicket(String ticketId);
    
    /**
     * 从容器中删除一个令牌
     *
     * @param 令牌ID
     * @return  true表示删除成功，false表示令牌不存在 
     */
    boolean deleteTicket(String ticketId);
    
    /**
     * 获取容器中所有的令牌,  包括无效令牌
     *
     * @return 令牌
     */
    Collection<Ticket> getTickets();
    
    /**
     * 更新令牌
     *
     * @param 令牌
     */
    void updateTicket(Ticket ticket);
}
