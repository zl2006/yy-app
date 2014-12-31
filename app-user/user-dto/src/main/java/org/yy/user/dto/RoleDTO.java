/*
* 文 件 名:  RoleDTO.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  角色传输对象
* 修 改 人:  zhouliang
* 修改时间:  2013年11月24日
* 修改内容:  <修改内容>
*/
package org.yy.user.dto;

import org.yy.framework.basedata.query.AbstractQueryDto;

/**
* 角色传输对象
* 
* @author  zhouliang
* @version  [1.0, 2013年11月24日]
* @since  [app-user/1.0]
*/
public class RoleDTO extends AbstractQueryDto {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 3281399909508645641L;
    
    /**
     * 角色编号
     */
    private String roleCode;
    
    /**
     * 角色名称
     */
    private String name;
    
    /**
     * 角色所属系统编码
     */
    private String systemCode;
    
    /**
     * 状态
     */
    private Integer status;
    
    /**
    * @return 返回 roleCode
    */
    public String getRoleCode() {
        return roleCode;
    }
    
    /**
    * @param 对roleCode进行赋值
    */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
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
    * @return 返回 status
    */
    public Integer getStatus() {
        return status;
    }
    
    /**
    * @param 对status进行赋值
    */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
    
    public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/** {@inheritDoc} */
    @Override
    public String toString() {
        return "RoleDTO [roleCode=" + roleCode + ", name=" + name + ", status=" + status + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((roleCode == null) ? 0 : roleCode.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        RoleDTO other = (RoleDTO)obj;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (roleCode == null) {
            if (other.roleCode != null)
                return false;
        }
        else if (!roleCode.equals(other.roleCode))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        }
        else if (!status.equals(other.status))
            return false;
        return true;
    }
    
}
