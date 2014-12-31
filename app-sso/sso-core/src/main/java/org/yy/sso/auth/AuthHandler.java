/*
* 文 件 名:  AuthenticationHandler.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  授权处理器
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.auth;

import org.yy.sso.credential.Credential;

/**
* 授权处理器
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public interface AuthHandler {
    
    /**
     * 授权处理
     * 
     * @param credential 身份信息
     * @param auth  授权结果
     * @throws AuthException
     */
    void authenticate(Credential credential, AuthResult auth)
        throws AuthException;
    
    /**
     * 处理器名称
     */
    String getName();
}
