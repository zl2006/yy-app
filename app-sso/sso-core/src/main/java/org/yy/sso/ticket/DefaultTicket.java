/*
* 文 件 名:  DefaultTicketImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  默认令牌实现
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket;

/**
* 默认令牌实现
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public class DefaultTicket extends AbstractTicket {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 5394530751420029518L;
    
    /** 
    <默认构造函数>
    */
    public DefaultTicket(final String id, final String credentialId)
        throws TicketException {
        super(id, credentialId);
    }
    
}
