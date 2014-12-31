/*
* 文 件 名:  DefaultCentralAuthService.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  认证授权服务
* 修 改 人:  zhouliang
* 修改时间:  2013年12月16日
* 修改内容:  <修改内容>
*/
package org.yy.sso;

import org.yy.sso.auth.AuthException;
import org.yy.sso.auth.AuthHandler;
import org.yy.sso.auth.AuthHandlerManager;
import org.yy.sso.auth.AuthResult;
import org.yy.sso.credential.Credential;
import org.yy.sso.ticket.DefaultTicket;
import org.yy.sso.ticket.ExpirationPolicy;
import org.yy.sso.ticket.Ticket;
import org.yy.sso.ticket.TicketContainer;
import org.yy.sso.ticket.TicketException;
import org.yy.sso.ticket.TicketIdGenerator;

/**
* 认证授权服务
* 
* @author  zhouliang
* @version  [1.0, 2013年12月16日]
* @since  [app-sso/1.0]
*/
public class DefaultCentralAuthService implements CentralAuthService {
    
    /**授权处理器管理器*/
    private AuthHandlerManager authHandlerManager;
    
    /**令牌标识生成器*/
    private TicketIdGenerator ticketIdGenerator;
    
    /** 令牌存储容器*/
    private TicketContainer ticketContainer;
    
    /**令牌过期机制*/
    private ExpirationPolicy expirationPolicy;
    
    public DefaultCentralAuthService(AuthHandlerManager authHandlerManager, TicketIdGenerator ticketIdGenerator,
        TicketContainer ticketContainer, ExpirationPolicy expirationPolicy) {
        this.authHandlerManager = authHandlerManager;
        this.ticketIdGenerator = ticketIdGenerator;
        this.ticketContainer = ticketContainer;
        this.expirationPolicy = expirationPolicy;
    }
    
    /** {@inheritDoc} */
    @Override
    public AuthResult authentication(Credential credential)
        throws AuthException, TicketException {
        
        // 获取对应的授权处理器
        AuthResult ar = new AuthResult();
        ar.setCredential(credential);
        AuthHandler handler = authHandlerManager.getAuthHandler(credential);
        if (handler == null) {
            ar.setSuccess(false);
            ar.getErrorMessages().put("handler", "authhandler isn't exists!");
            return ar;
        }
        
        //身份授权
        handler.authenticate(credential, ar);
        
        //授权成功时，生成令牌并放入容器中
        if (ar.isSuccess()) {
            Ticket ticket = generateTicket(credential);
            ticketContainer.addTicket(ticket);
            ar.setTicket(ticket);
        }
        return ar;
    }
    
    /** {@inheritDoc} */
    @Override
    public ValidateResult validateTicket(String ticketId) {
        
        //获取令牌，判断令牌是否存在
        ValidateResult vr = new ValidateResult();
        Ticket ticket = ticketContainer.getTicket(ticketId);
        if (ticket == null) {
            vr.setSuccess(false);
            vr.getErrorMessages().put(ValidateResult.TICKET_ERROR, "ticket isn't exists!");
            return vr;
        }
        
        //判断令牌是否过期
        if (ticket.isExpired(expirationPolicy)) {
            vr.setSuccess(false);
            vr.getErrorMessages().put(ValidateResult.TICKET_ERROR, "ticket is expire!");
            return vr;
        }
        
        vr.setSuccess(true);
        vr.setTicket(ticket);
        ticketContainer.updateTicket(ticket);
        return vr;
    }
    
    /**生成一个Ticket*/
    protected Ticket generateTicket(Credential credential)
        throws TicketException {
        return new DefaultTicket(ticketIdGenerator.generatorTicketId(credential.getId()), credential.getId());
    }
    
}
