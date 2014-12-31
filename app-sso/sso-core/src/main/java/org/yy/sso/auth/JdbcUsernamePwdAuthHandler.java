/*
* 文 件 名:  JdbcUsernamePwdAuthHandler.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  基于jdbc的验证用户名和密码
* 修 改 人:  zhouliang
* 修改时间:  2013年12月16日
* 修改内容:  <修改内容>
*/
package org.yy.sso.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.yy.sso.credential.Credential;
import org.yy.sso.credential.UsernamePwdCredential;

/**
* 基于jdbc的验证用户名和密码
* 
* @author  zhouliang
* @version  [1.0, 2013年12月16日]
* @since  [app-sso/1.0]
*/
public class JdbcUsernamePwdAuthHandler extends AbsUsernamePwdAuthHandler {
    
    /**select field1 as username, field2 as password from table1 where field1 = ? */
    private String querySql = "";
    
    /**数据源*/
    private DataSource dataSource;
    
    public JdbcUsernamePwdAuthHandler(PasswordEncoder encoder, DataSource datasource, String querySql) {
        super(encoder);
        this.dataSource = datasource;
        this.querySql = querySql;
    }
    
    /** {@inheritDoc} */
    @Override
    protected User findUser(Credential credential)
        throws AuthException {
        
        UsernamePwdCredential cre = (UsernamePwdCredential)credential;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(querySql);
            statement.setString(1, cre.getUsername());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        }
        catch (SQLException e) {
            throw new AuthException(e.getMessage(), e);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                }
                catch (SQLException e) {
                    throw new AuthException(e.getMessage(), e);
                }
            }
        }
        
        return null;
    }
    
    /** {@inheritDoc} */
    @Override
    public String getName() {
        return UsernamePwdCredential.class.getName();
    }
    
    public static void main(String[] args) {
        System.out.println(UsernamePwdCredential.class.getName());
    }
}
