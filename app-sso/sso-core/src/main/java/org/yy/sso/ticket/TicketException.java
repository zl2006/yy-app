/*
* 文 件 名:  TicketExceptoin.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  令牌异常
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket;

/**
* 令牌异常
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public class TicketException extends Exception {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 550105762989024281L;
    
    /** 
     * 构建令牌异常
    * @param errorMessage 令牌异常消息
    */
    public TicketException(String errorMessage) {
        super(errorMessage);
    }
}
