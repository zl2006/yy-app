/*
* 文 件 名:  DefaultTicketIdGenerator.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述: 令牌标识生成器
* 修 改 人:  zhouliang
* 修改时间:  2013年12月18日
* 修改内容:  <修改内容>
*/
package org.yy.sso.ticket;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

/**
* 令牌标识生成器
* 
* @author  zhouliang
* @version  [1.0, 2013年12月18日]
* @since  [app-sso/1.0]
*/
public class DefaultTicketIdGenerator implements TicketIdGenerator {
    
    /** {@inheritDoc} */
    @Override
    public String generatorTicketId(String seed) {
        return DigestUtils.sha1Hex(seed + new Date());
    }
    
}
