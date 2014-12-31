/*
* 文 件 名:  Sha1PasswordEncoder.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  密码进行加密编码
* 修 改 人:  zhouliang
* 修改时间:  2013年12月16日
* 修改内容:  <修改内容>
*/
package org.yy.sso.auth;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

/**
* 密码进行加密编码
* 
* @author  zhouliang
* @version  [app-sso, 2013年12月16日]
* @since  [app-sso/1.0]
*/
public class Sha1PasswordEncoder implements PasswordEncoder {
    
    private static final String CHARSET = "UTF-8";
    
    /** {@inheritDoc} */
    @Override
    public String encoder(String password) {
        return encoder(password, CHARSET);
    }
    
    /** {@inheritDoc} */
    @Override
    public String encoder(String password, String chartset) {
        try {
            return DigestUtils.sha1Hex(password.getBytes(chartset));
        }
        catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
            // 错误忽略直接返回空
            return null;
        }
    }
    
}
