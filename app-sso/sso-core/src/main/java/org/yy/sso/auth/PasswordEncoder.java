/*
* 文 件 名:  PasswordEncoder.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:   密码进行加密编码
* 修 改 人:  zhouliang
* 修改时间:  2013年12月16日
* 修改内容:  <修改内容>
*/
package org.yy.sso.auth;

/**
*  密码进行加密编码
* 
* @author  zhouliang
* @version  [1.0, 2013年12月16日]
* @since  [app-sso/1.0]
*/
public interface PasswordEncoder {
    
    /**
     * 编码
     * 
     * @param password   密码
     * @return
     */
    public String encoder(String password);
    
    /**
     * 编码
     * @param password  密码
     * @param chartset 字符集编码
     * @return
     */
    public String encoder(String password, String chartset);
}
