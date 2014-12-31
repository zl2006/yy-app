/*
* 文 件 名:  RememberMe.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  记住我
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.credential;

/**
* 记住我, 在登录时设置是否记住用户名
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public interface RememberMe {
    
    /**
     * 获取记住我标志
     * @return true,表示记住
     */
    boolean isRememberMe();
    
    /**
     * 设置记住我标志
     * @param rememberMe  记住我标志
     */
    void setRememberMe(boolean rememberMe);
}
