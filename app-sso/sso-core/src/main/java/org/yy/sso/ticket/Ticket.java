/*
* 文 件 名:  Ticket.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  令牌信息
* 修 改 人:  zhouliang
* 修改时间:  2013年12月14日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket;

import java.io.Serializable;

/**
* 令牌信息, 用户身份验证通过后，由系统发放的令牌。通过令牌可以访问各种相应资源，令牌必须有有效期
* 
* @author  zhouliang
* @version  [1.0, 2013年12月14日]
* @since  [app-sso/1.0]
*/
public interface Ticket extends Serializable {
    
    /**
     * 获取令牌本身标识ID
     * @return 令牌本身标识ID
     */
    String getId();
    
    /**
     * 获取身份标识
     * @return 身份标识, 令牌所有者
     */
    String getCredentialId();
    
    /**
     * 判断令牌是否过期
     * @return  true表示令牌过期
     */
    boolean isExpired(ExpirationPolicy policy);
}
