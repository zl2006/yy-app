/*
* 文 件 名:  TicketIdGenerator.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  生成唯一的令牌标识
* 修 改 人:  zhouliang
* 修改时间:  2013年12月16日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket;

/**
* 令牌标识生成器
* 
* @author  zhouliang
* @version  [1.0, 2013年12月16日]
* @since  [app-sso/1.0]
*/
public interface TicketIdGenerator {
    
    /**
     * 生成唯一的令牌标识
     * @param seed  种子
     * @return 令牌标识
     */
    String generatorTicketId(String seed);
}
