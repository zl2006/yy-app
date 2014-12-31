package org.yy.user.dto;

import org.yy.framework.document.anno.Field;
import org.yy.framework.document.anno.Model;
/*
 * 文 件 名:  UserDTO.java
 * 版    权:  YY Technologies Co., Ltd. Copyright 1993-2012,  All rights reserved
 * 描    述:  用户传输对象
 * 修 改 人:  zhouliang
 * 修改时间:  2012-9-10
 */
import org.yy.framework.basedata.query.AbstractQueryDto;

/**
 * 用户传输对象
 * 
 * @author zhouliang
 * @version [0.1, 2012-9-10]
 * @since [app-user/1.0]
 */
@Model(name = "用户传输对象", desc = "用户传输对象")
public class UserDTO extends AbstractQueryDto {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 3347064240409453456L;
    
    /**
     * 登录ID
     */
    @Field(cnname = "登录ID", desc = "登录ID", nullable = false, length = 64)
    private String loginID;
    
    /**
     * 用户姓名
     */
    @Field(cnname = "用户姓名", desc = "用户姓名", nullable = false, length = 64)
    private String name;
    
    /**
     * 电话
     */
    @Field(cnname = "电话", desc = "电话", length = 32)
    private String tel;
    
    /**
    * @return 返回 loginID
    */
    public String getLoginID() {
        return loginID;
    }
    
    /**
    * @param 对loginID进行赋值
    */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }
    
    /**
    * @return 返回 name
    */
    public String getName() {
        return name;
    }
    
    /**
    * @param 对name进行赋值
    */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
    * @return 返回 tel
    */
    public String getTel() {
        return tel;
    }
    
    /**
    * @param 对tel进行赋值
    */
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "UserDTO [loginID=" + loginID + ", name=" + name + ", tel=" + tel + ", getPagination()="
            + getPagination() + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((loginID == null) ? 0 : loginID.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((tel == null) ? 0 : tel.hashCode());
        return result;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDTO other = (UserDTO)obj;
        if (loginID == null) {
            if (other.loginID != null)
                return false;
        }
        else if (!loginID.equals(other.loginID))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (tel == null) {
            if (other.tel != null)
                return false;
        }
        else if (!tel.equals(other.tel))
            return false;
        return true;
    }
    
}
