/*
* 文 件 名:  Authentication.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  授权信息
* 修 改 人:  zhouliang
* 修改时间:  2013年12月14日
* 修改内容:  <修改内容>
*/
package org.yy.sso.auth;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.yy.sso.credential.Credential;
import org.yy.sso.ticket.Ticket;

/**
* 授权结果信息,授权处理器(AuthenticationHandler)对身份信息(Credential)进行认证后得到的授权信息
* 
* @author  zhouliang
* @version  [1.0, 2013年12月14日]
* @since  [app-sso/1.0]
*/
public class AuthResult implements Serializable {
    
    private static final long serialVersionUID = -4064516532225989836L;
    
    /**身份信息*/
    private Credential credential;
    
    /** 令牌信息*/
    private Ticket ticket;
    
    /** 认证是否成功*/
    private boolean success;
    
    /**错误消息*/
    private Map<String, String> errorMessages = new HashMap<String, String>();
    
    /**
    * @return 返回 credential
    */
    public Credential getCredential() {
        return credential;
    }
    
    /**
    * @param 对credential进行赋值
    */
    public void setCredential(Credential credential) {
        this.credential = credential;
    }
    
    /**
    * @return 返回 success
    */
    public boolean isSuccess() {
        return success;
    }
    
    /**
    * @param 对success进行赋值
    */
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    /**
    * @return 返回 errorMessage
    */
    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
    
    /**
    * @param 对errorMessage进行赋值
    */
    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
    
    /**
    * @return 返回 ticket
    */
    public Ticket getTicket() {
        return ticket;
    }
    
    /**
    * @param 对ticket进行赋值
    */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    
}
