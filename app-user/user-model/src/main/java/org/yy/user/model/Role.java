/*
* 文 件 名:  Role.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  角色
* 修 改 人:  zhouliang
* 修改时间:  2013年11月19日
* 修改内容: 
*/
package org.yy.user.model;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.yy.framework.document.anno.Field;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.TypeEnum;

import com.google.code.ssm.api.CacheKeyMethod;

/**
* 角色
* 
* @author  zhouliang
* @version  [1.0, 2013年11月19日]
* @since  [app-user/1.0]
*/
@Model(name = "角色", desc = "角色")
public class Role implements Serializable {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = -7703762354376738268L;
    
    @Field(cnname = "角色ID", desc = "角色ID", type = TypeEnum.INTETER, nullable = false)
    private Long roleID;
    
    @Field(cnname = "角色编号", desc = "角色编号", nullable = false, length = 32)
    @NotEmpty
    private String roleCode;
    
    @Field(cnname = "系统编码", desc = "系统编码", nullable = false, length = 32)
    private String systemCode;
    
    @Field(cnname = "角色名称", desc = "角色名称", nullable = false, length = 64)
    private String name;
    
    @Field(cnname = "角色描述", desc = "角色描述", length = 255)
    private String description;
    
    @Field(cnname = "状态", desc = "状态:0表示无效，1表示有效", type = TypeEnum.INTETER, nullable = false)
    private Integer status;
    
    private Date createTime;
    
    private Long createPerson;
    
    private Date updateTime;
    
    private Long updatePerson;
    
    /**
     * @return 返回 roleCode
     */
    @CacheKeyMethod
    public String getRoleCode() {
        return roleCode;
    }
    
    /**
    * @param 对roleCode进行赋值
    */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    
    
    
    public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	/**
    * @return 返回 roleID
    */
    public Long getRoleID() {
        return roleID;
    }
    
    /**
    * @param 对roleID进行赋值
    */
    public void setRoleID(Long roleID) {
        this.roleID = roleID;
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
    * @return 返回 description
    */
    public String getDescription() {
        return description;
    }
    
    /**
    * @param 对description进行赋值
    */
    public void setDescription(String description) {
        this.description = description;
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
    
    /**
    * @return 返回 createTime
    */
    public Date getCreateTime() {
        return createTime;
    }
    
    /**
    * @param 对createTime进行赋值
    */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    /**
    * @return 返回 createPerson
    */
    public Long getCreatePerson() {
        return createPerson;
    }
    
    /**
    * @param 对createPerson进行赋值
    */
    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }
    
    /**
    * @return 返回 updateTime
    */
    public Date getUpdateTime() {
        return updateTime;
    }
    
    /**
    * @param 对updateTime进行赋值
    */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    /**
    * @return 返回 updatePerson
    */
    public Long getUpdatePerson() {
        return updatePerson;
    }
    
    /**
    * @param 对updatePerson进行赋值
    */
    public void setUpdatePerson(Long updatePerson) {
        this.updatePerson = updatePerson;
    }
    
    
    
    @Override
	public String toString() {
		return "Role [roleID=" + roleID + ", roleCode=" + roleCode
				+ ", systemCode=" + systemCode + ", name=" + name
				+ ", description=" + description + ", status=" + status
				+ ", createTime=" + createTime + ", createPerson="
				+ createPerson + ", updateTime=" + updateTime
				+ ", updatePerson=" + updatePerson + "]";
	}

	/** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((roleCode == null) ? 0 : roleCode.hashCode());
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
        Role other = (Role)obj;
        if (roleCode == null) {
            if (other.roleCode != null)
                return false;
        }
        else if (!roleCode.equals(other.roleCode))
            return false;
        return true;
    }
    
    /***********************************************此处为处理列表实时缓存所添加**************************************/
    //数据实体的命名空间(系统名称:实体名称:版本),此版本是因为实体属性变更引起（例如增减属性，属性名称变更）
    //尽量简称，但必须保证不重复
    public static final transient String namespace = "user:role:1";
    
    //默认缓存过期时间, 列表默认缓存10天
    public static final transient int expiration = 60 * 60 * 24;
    
    //数据列表查询时用到此版本, 数据实际值存储在分页式缓存中
    public static transient long version = 1;
    /***********************************************end********************************************************/
}
