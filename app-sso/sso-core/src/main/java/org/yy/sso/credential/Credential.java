/*
* 文 件 名:  Credential.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  身份信息
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.credential;

import java.io.Serializable;

/**
* 身份信息,用户授权时，需要提交身份信息并认证才能正常访问系统
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public interface Credential extends Serializable {
    String getId();
}
