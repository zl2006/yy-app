/*
* 文 件 名:  AuthenticationException.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:   授权异常
* 修 改 人:  zhouliang
* 修改时间:  2013年12月16日
* 修改内容:  <修改内容>
*/
package org.yy.sso.auth;

/**
*  授权异常
* 
* @author  zhouliang
* @version  [1.0, 2013年12月16日]
* @since  [app-sso/1.0]
*/
public class AuthException extends Exception {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = -359396800693953796L;
    
    /** 
    <默认构造函数>
    */
    public AuthException(String message) {
        super(message);
    }
    
    /** 
    <默认构造函数>
    */
    public AuthException(String message, Throwable ex) {
        super(message, ex);
    }
}
