/*
* 文 件 名:  UserGroup.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述: 用户组
* 修 改 人:  zhouliang
* 修改时间:  2013年11月19日
* 修改内容:  
*/
package org.yy.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.yy.framework.base.view.CustomDateSerializer;
import org.yy.framework.document.anno.Field;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.TypeEnum;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.code.ssm.api.CacheKeyMethod;

/**
* 用户组
* 
* @author  zhouliang
* @version  [1.0, 2013年11月19日]
* @since  [app-user/1.0]
*/
@Model(name = "用户组", desc = "用户组")
public class UserGroup implements Serializable {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 5144868717069629234L;
    
    @Field(cnname = "用户组ID", desc = "用户组ID", type = TypeEnum.INTETER, nullable = false)
    private Long userGroupID;
    
    @Field(cnname = "用户组名称", desc = "用户组名称", nullable = false, length = 64)
    @NotEmpty(message = "用户组名称不能为空！")
    @Size(min = 0, max = 64, message = "用户组名称长度必须在0到64之间")
    private String groupName;
    
    @Field(cnname = "状态", desc = "状态, 表示有效，0表示无效", type = TypeEnum.INTETER, nullable = false)
    private Integer status = 1;
    
    @Field(cnname = "描述", desc = "描述", length = 255)
    @Size(min = 0, max = 255, message = "用户组名称长度必须在0到64之间")
    private String description;
    
    private Date createTime;
    
    private Long createPerson;
    
    private Date updateTime;
    
    private Long updatePerson;
    
    /**
    * @return 返回 userGroupID
    */
    @CacheKeyMethod
    public Long getUserGroupID() {
        return userGroupID;
    }
    
    /**
    * @param 对userGroupID进行赋值
    */
    public void setUserGroupID(Long userGroupID) {
        this.userGroupID = userGroupID;
    }
    
    /**
    * @return 返回 groupName
    */
    public String getGroupName() {
        return groupName;
    }
    
    /**
    * @param 对groupName进行赋值
    */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
    @JsonSerialize(using = CustomDateSerializer.class)
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
    @JsonSerialize(using = CustomDateSerializer.class)
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
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "UserGroup [userGroupID=" + userGroupID + ", groupName=" + groupName + ", status=" + status
            + ", description=" + description + ", createTime=" + createTime + ", createPerson=" + createPerson
            + ", updateTime=" + updateTime + ", updatePerson=" + updatePerson + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
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
        UserGroup other = (UserGroup)obj;
        if (groupName == null) {
            if (other.groupName != null)
                return false;
        }
        else if (!groupName.equals(other.groupName))
            return false;
        return true;
    }
    
    /***********************************************此处为处理列表实时缓存所添加**************************************/
    //数据实体的命名空间(系统名称:实体名称:版本),此版本是因为实体属性变更引起（例如增减属性，属性名称变更）
    //尽量简称，但必须保证不重复
    public static final transient String namespace = "user:usergrp:1";
    
    //默认缓存过期时间, 列表默认缓存10天
    public static final transient int expiration = 60 * 60 * 24;
    
    //数据列表查询时用到此版本, 数据实际值存储在分页式缓存中
    public static transient long version = 1;
    /***********************************************end********************************************************/
}
