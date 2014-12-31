/*
* 文 件 名:  ValidateResult.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  令牌验证结果
* 修 改 人:  zhouliang
* 修改时间:  2013年12月16日
* 修改内容:  <修改内容>
*/
package org.yy.sso;

import java.util.HashMap;
import java.util.Map;

import org.yy.sso.ticket.Ticket;

/**
* 令牌验证结果
* 
* @author  zhouliang
* @version  [版本号, 2013年12月16日]
* @since  [产品/模块版本]
*/
public class ValidateResult {
    
    public static final String TICKET_ERROR = "ticket";
    
    /**令牌验证结果*/
    private boolean success;
    
    /**令牌验证错误信息, 令牌不存在，令牌过期等*/
    private Map<String, String> errorMessages = new HashMap<String, String>();
    
    private Ticket ticket;
    
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
    * @return 返回 errorMessages
    */
    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
    
    /**
    * @param 对errorMessages进行赋值
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
