/*
* 文 件 名:  TicketState.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  令牌状态
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket;

import java.io.Serializable;

/**
* 令牌状态, 令牌使用时间、次数等情况
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public interface TicketState extends Serializable {
    
    /**
     * 获取令牌的创建时间
     * 
     * @return 令牌创建时间
     */
    long getCreationTime();
    
    /**
     * 获取令牌被使用次数
     * @return 获取令牌被使用次数
     */
    int getCountOfUses();
    
    /**
     * 获取令牌最后一次使用时间
     * @return 令牌最后一次使用时间
     */
    long getLastTimeUsed();
    
    /**
     * 获取上一次使用时间
     * @return 令牌上一次使用时间
     */
    long getPreviousTimeUsed();
    
}
