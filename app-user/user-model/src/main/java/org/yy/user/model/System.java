/*
* 文 件 名:  System.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述: 系统
* 修 改 人:  zhouliang
* 修改时间:  2013年11月19日
* 修改内容:  
*/
package org.yy.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.yy.framework.document.anno.Field;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.TypeEnum;

import com.google.code.ssm.api.CacheKeyMethod;

/**
* 系统
* 
* @author  zhouliang
* @version  [1.0, 2013年11月19日]
* @since  [app-user/1.0]
*/
@Model(name = "系统", desc = "系统")
public class System implements Serializable {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = -158883134382793209L;
    
    @Field(cnname = "应用系统ID", desc = "应用系统ID", type = TypeEnum.INTETER, nullable = false)
    private Long systemID;
    
    @Field(cnname = "应用系统编号", desc = "应用系统编号", nullable = false, length = 32)
    @NotEmpty(message = "应用系统编号不能为空！")
    @Size(min = 0, max = 32, message = "应用系统编号长度必须在0到32之间")
    private String systemCode;
    
    @Field(cnname = "应用名称", desc = "应用名称", nullable = false, length = 64)
    @NotEmpty(message = "应用名称不能为空！")
    @Size(min = 0, max = 64, message = "应用名称长度必须在0到64之间")
    private String name;
    
    @Field(cnname = "应用图标", desc = "应用图标", length = 128)
    private String icon;
    
    @Field(cnname = "应用URL", desc = "应用URL", length = 128)
    private String url;
    
    @Field(cnname = "描述", desc = "描述", length = 255)
    private String description;
    
    @Field(cnname = "状态", desc = "状态，0表示无效，1表示有效", type = TypeEnum.INTETER, nullable = false)
    private Integer status = 1;
    
    private Date createTime;
    
    private Long createPerson;
    
    private Date updateTime;
    
    private Long updatePerson;
    
    /**
    * @return 返回 systemCode
    */
    @CacheKeyMethod
    public String getSystemCode() {
        return systemCode;
    }
    
    /**
    * @param 对systemCode进行赋值
    */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
    
    /**
    * @return 返回 systemID
    */
    public Long getSystemID() {
        return systemID;
    }
    
    /**
    * @param 对systemID进行赋值
    */
    public void setSystemID(Long systemID) {
        this.systemID = systemID;
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
    * @return 返回 icon
    */
    public String getIcon() {
        return icon;
    }
    
    /**
    * @param 对icon进行赋值
    */
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    /**
    * @return 返回 url
    */
    public String getUrl() {
        return url;
    }
    
    /**
    * @param 对url进行赋值
    */
    public void setUrl(String url) {
        this.url = url;
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
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "System [systemID=" + systemID + ", systemCode=" + systemCode + ", name=" + name + ", icon=" + icon
            + ", url=" + url + ", description=" + description + ", status=" + status + ", createPerson=" + createPerson
            + ", updatePerson=" + updatePerson + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((systemCode == null) ? 0 : systemCode.hashCode());
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
        System other = (System)obj;
        if (systemCode == null) {
            if (other.systemCode != null)
                return false;
        }
        else if (!systemCode.equals(other.systemCode))
            return false;
        return true;
    }
    
    /***********************************************此处为处理列表实时缓存所添加**************************************/
    //数据实体的命名空间(系统名称:实体名称:版本),此版本是因为实体属性变更引起（例如增减属性，属性名称变更）
    //尽量简称，但必须保证不重复
    public static final transient String namespace = "user:system:1";
    
    //默认缓存过期时间, 列表默认缓存10天
    public static final transient int expiration = 60 * 60 * 24 * 10;
    
    //数据列表查询时用到此版本, 数据实际值存储在分页式缓存中
    public static transient long version = 1;
    /***********************************************end********************************************************/
    
}
