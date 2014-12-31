/*
* 文 件 名:  AbsUsernamePwdAuthHandler.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  抽像根据用户名和密码授权
* 修 改 人:  zhouliang
* 修改时间:  2013年12月16日
* 修改内容:  <修改内容>
*/
package org.yy.sso.auth;

import org.yy.sso.credential.Credential;
import org.yy.sso.credential.UsernamePwdCredential;

/**
* 抽像根据用户名和密码授权
* 
* @author  zhouliang
* @version  [版本号, 2013年12月16日]
* @since  [产品/模块版本]
*/
public abstract class AbsUsernamePwdAuthHandler implements AuthHandler {
    
    public static final String USERNAME_ERROR = "loginID";
    
    public static final String PASSWORD_ERROR = "password";
    
    /** 密码编码器 */
    private PasswordEncoder passwordEncoder;
    
    public AbsUsernamePwdAuthHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    /** {@inheritDoc} */
    @Override
    public void authenticate(Credential credential, AuthResult auth)
        throws AuthException {
        
        UsernamePwdCredential cre = (UsernamePwdCredential)credential;
        
        //判断账号是否存在
        User user = findUser(credential);
        if (user == null) {
            auth.setSuccess(false);
            auth.getErrorMessages().put(USERNAME_ERROR, "用户账号不存");
            return;
        }
        
        //判断密码是否正确
        String password = passwordEncoder.encoder(cre.getPassword());
        if (!password.equals(user.getPassword())) {
            auth.setSuccess(false);
            auth.getErrorMessages().put(PASSWORD_ERROR, "用户密码不正确");
            return;
        }
        
        auth.setSuccess(true);
        auth.setCredential(credential);
    }
    
    /**
     *  获取用户
     * @param credential  用户身份信息
     * @return 用户
     * @throws AuthException
     */
    protected abstract User findUser(Credential credential)
        throws AuthException;
    
    /**
     *  用户信息
     * 
     * @author  zhouliang
     * @version  [1.0, 2013年12月16日]
     * @since  [app-sso/1.0]
     */
    public static class User {
        
        /** 用户名*/
        private String username;
        
        /**密码*/
        private String password;
        
        /**
        * @return 返回 username
        */
        public String getUsername() {
            return username;
        }
        
        /**
        * @param 对username进行赋值
        */
        public void setUsername(String username) {
            this.username = username;
        }
        
        /**
        * @return 返回 password
        */
        public String getPassword() {
            return password;
        }
        
        /**
        * @param 对password进行赋值
        */
        public void setPassword(String password) {
            this.password = password;
        }
    }
}
