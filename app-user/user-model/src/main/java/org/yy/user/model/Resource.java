/*
* 文 件 名:  Resource.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  资源
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
* 资源
* 
* @author  zhouliang
* @version  [1.0, 2013年11月19日]
* @since  [app-user/1.0]
*/
@Model(name = "资源", desc = "资源")
public class Resource implements Serializable {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 389493124350553491L;
    
    @Field(cnname = "资源ID", desc = "资源ID", type = TypeEnum.INTETER, nullable = false)
    private Long resID;
    
    @Field(cnname = "应用系统编号", desc = "应用系统编号", nullable = false, length = 32)
    @NotEmpty(message = "应用系统编号不能为空！")
    @Size(max = 32, message = "应用系统编号长度必须在小于32")
    private String systemCode;
    
    @Field(cnname = "父资源编号", desc = "父资源编号,-1表示顶级系统资源", type = TypeEnum.INTETER, nullable = false)
    @NotEmpty(message = "父资源编号不能为空！")
    private Long parentResID = -1l;
    
    @Field(cnname = "资源名称", desc = "资源名称", nullable = false, length = 64)
    @NotEmpty(message = "资源名称不能为空！")
    @Size(max = 64, message = "资源名称长度必须在小于等于64")
    private String name;
    
    @Field(cnname = "英文资源名称", desc = "英文资源名称",  length = 64)
    private String ename;
    
    @Field(cnname = "资源位置", desc = "资源位置", length = 128)
    private String url;
    
    @Field(cnname = "资源类型", desc = "资源类型：0表示菜单（模块 ），1表示功能, 2表示数据列表中的权限。其中功能与数据列表中的权限都是挂在菜单下。", nullable = false, length = 2)
    @NotEmpty(message = "资源类型不能为空！")
    @Size(max = 2, message = "资源名称长度必须在小于等于2")
    private String type;
    
    @Field(cnname = "资源图标", desc = "资源图标", length = 128)
    private String icon;
    
    @Field(cnname = "资源序号", desc = "资源序号", type = TypeEnum.INTETER)
    @NotEmpty(message = "资源序号不能为空！")
    private Integer orderNO = 1;
    
    @Field(cnname = "备注", desc = "备注", length = 255)
    private String description;
    
    @Field(cnname = "样式", desc = "样式", length = 128)
    private String style;
    
    @Field(cnname = "状态", desc = "状态, 0表示无效，1表示有效", type = TypeEnum.INTETER, nullable = false)
    @NotEmpty(message = "状态不能为空！")
    private Integer status;
    
    private Integer hasChild = 0;
    
    private Date createTime;
    
    private Long createPerson;
    
    private Date updateTime;
    
    private Long updatePerson;
    
    /**
     * @return 返回 resID
     */
    @CacheKeyMethod
    public Long getResID() {
        return resID;
    }
    
    /**
    * @param 对resID进行赋值
    */
    public void setResID(Long resID) {
        this.resID = resID;
    }
    
    /**
    * @return 返回 systemCode
    */
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
    * @return 返回 parentResID
    */
    public Long getParentResID() {
        return parentResID;
    }
    
    /**
    * @param 对parentResID进行赋值
    */
    public void setParentResID(Long parentResID) {
        this.parentResID = parentResID;
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
    
    
    
    public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
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
    * @return 返回 type
    */
    public String getType() {
        return type;
    }
    
    /**
    * @param 对type进行赋值
    */
    public void setType(String type) {
        this.type = type;
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
    * @return 返回 orderNO
    */
    public Integer getOrderNO() {
        return orderNO;
    }
    
    /**
    * @param 对orderNO进行赋值
    */
    public void setOrderNO(Integer orderNO) {
        this.orderNO = orderNO;
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
    
    
    /**
    * @return 返回 style
    */
    public String getStyle() {
        return style;
    }

    /**
    * @param 对style进行赋值
    */
    public void setStyle(String style) {
        this.style = style;
    }
    
    
    public Integer getHasChild() {
        return hasChild;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Resource [resID=" + resID + ", systemCode=" + systemCode + ", parentResID=" + parentResID + ", name="
            + name + ", url=" + url + ", type=" + type + ", icon=" + icon + ", orderNO=" + orderNO + ", description="
            + description + ", status=" + status + ", createTime=" + createTime + ", createPerson=" + createPerson
            + ", updateTime=" + updateTime + ", updatePerson=" + updatePerson + "]";
    }
    
    /***********************************************此处为处理列表实时缓存所添加**************************************/
    //数据实体的命名空间(系统名称:实体名称:版本),此版本是因为实体属性变更引起（例如增减属性，属性名称变更）
    //尽量简称，但必须保证不重复
    public static final transient String namespace = "user:res:1";
    
    //默认缓存过期时间, 列表默认缓存10天
    public static final transient int expiration = 60 * 60 * 24;
    
    //数据列表查询时用到此版本, 数据实际值存储在分页式缓存中
    public static transient long version = 1;
    /***********************************************end********************************************************/
    
}
