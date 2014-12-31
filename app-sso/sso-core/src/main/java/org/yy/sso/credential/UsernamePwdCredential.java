/*
* 文 件 名:  UsernamePwdCredential.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  用户名/密码设定的身份
* 修 改 人:  zhouliang
* 修改时间:  2013年12月15日
* 修改内容:  <修改内容>
*/
package org.yy.sso.credential;

/**
* 用户名/密码设定的身份
* 
* @author  zhouliang
* @version  [1.0, 2013年12月15日]
* @since  [app-sso/1.0]
*/
public class UsernamePwdCredential implements Credential {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 8296983053986837839L;
    
    /**用户名*/
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
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "UsernamePwdCredential [username=" + username + ", password=" + password + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsernamePwdCredential other = (UsernamePwdCredential)obj;
        if (password == null) {
            if (other.password != null)
                return false;
        }
        else if (!password.equals(other.password))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        }
        else if (!username.equals(other.username))
            return false;
        return true;
    }
    
    /** {@inheritDoc} */
    @Override
    public String getId() {
        return username;
    }
}
