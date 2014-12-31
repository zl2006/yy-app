/*
* 文 件 名:  CentralAuthenticationService.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  认证授权服务
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso;

import org.yy.sso.auth.AuthException;
import org.yy.sso.auth.AuthResult;
import org.yy.sso.credential.Credential;
import org.yy.sso.ticket.TicketException;

/**
* 认证授权服务
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public interface CentralAuthService {
    
    /**
     * 认证并得到授权结果
     * 
     * @param credential  身份信息
     * @return 授权结果
     */
    AuthResult authentication(Credential credential)
        throws AuthException, TicketException;
    
    /**
     * 验证TOKEN
     * 
     * @param ticketId 令牌ID
     * @return 验证结果，令牌不存在/令牌过期等
     */
    ValidateResult validateTicket(String ticketId);
}
