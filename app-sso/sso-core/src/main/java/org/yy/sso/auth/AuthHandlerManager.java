/*
* 文 件 名:  AuthHandlerManager.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  授权处理器管理
* 修 改 人:  zhouliang
* 修改时间:  2013年12月16日
* 修改内容:  <修改内容>
*/
package org.yy.sso.auth;

import java.util.Map;

import org.yy.sso.credential.Credential;

/**
* 授权处理器管理
* 
* @author  zhouliang
* @version  [1.0, 2013年12月16日]
* @since  [app-sso/1.0]
*/
public class AuthHandlerManager {
    
    private final Map<String, AuthHandler> authHandlers;
    
    public AuthHandlerManager(final Map<String, AuthHandler> authHandlers) {
        this.authHandlers = authHandlers;
    }
    
    /**
     * 注册一个授权处理器
     * 
     * @param authHanlder 授权处理器
     */
    public void addAuthHandler(AuthHandler authHanlder) {
        authHandlers.put(authHanlder.getName(), authHanlder);
    }
    
    /**
     * 获取一个授权处理器
     * 
     * @param handlerName 处理器名称
     * @return 处理器
     */
    public AuthHandler getAuthHandler(String handlerName) {
        return authHandlers.get(handlerName);
    }
    
    /**
     * 根据身份对应的授权处理器
     * 
     * @param credential 身份信息
     * @return 处理器
     */
    public AuthHandler getAuthHandler(Credential credential) {
        return authHandlers.get(credential.getClass().getName());
    }
    
}
