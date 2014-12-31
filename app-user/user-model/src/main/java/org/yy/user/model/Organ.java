/*
* 文 件 名:  Organ.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  组织机构
* 修 改 人:  zhouliang
* 修改时间:  2013年11月19日
* 修改内容:  
*/
package org.yy.user.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.yy.framework.document.anno.Field;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.TypeEnum;

import com.google.code.ssm.api.CacheKeyMethod;

/**
* 组织机构
* 
* @author  zhouliang
* @version  [1.0, 2013年11月19日]
* @since  [app-user/1.0]
*/
@Model(name = "组织机构", desc = "组织机构")
public class Organ implements Serializable {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 6376312389344508096L;
    
    @Field(cnname = "组织机构ID", desc = "组织机构ID", type = TypeEnum.INTETER, nullable = false)
    private Long organID;
    
    @Field(cnname = "组织机构编号", desc = "组织机构编号", nullable = false, length = 32)
    @NotEmpty(message = "组织机构编号不能为空！")
    @Size(min = 0, max = 32, message = "组织机构编号长度必须在0到32之间")
    private String organCode;
    
    @Field(cnname = "父组织机构编号", desc = "父组织机构编号,-1表示顶级组织机构", nullable = false, length = 32)
    @Size(min = 0, max = 32, message = "父组织机构编号长度必须在0到32之间")
    private String parentOrganCode = "-1";
    
    @Field(cnname = "机构名称", desc = "机构名称", nullable = false, length = 128)
    @NotEmpty(message = "机构名称不能为空！")
    @Size(min = 0, max = 128, message = "机构名称必须在0到128之间")
    private String name;
    
    @Field(cnname = "机构简称", desc = "机构简称", length = 128)
    private String sname;
    
    @Field(cnname = "负责人", desc = "负责人", length = 64)
    private String principal;
    
    @Field(cnname = "第二负责人", desc = "第二负责人", length = 64)
    private String secondPrincipal;
    
    @Field(cnname = "电话", desc = "电话", length = 32)
    private String tel;
    
    @Field(cnname = "传真", desc = "传真", length = 32)
    private String fax;
    
    @Field(cnname = "邮编", desc = "邮编", length = 32)
    private String postCode;
    
    @Field(cnname = "状态", desc = "组状态 ： 1表示有效，0表示无效", type = TypeEnum.INTETER, nullable = false)
    @NotNull(message = "状态不能为空！")
    private Integer status = 1 ;
    
    @Field(cnname = "机构描述", desc = "机构描述", length = 255)
    private String description;
    
    @Field(cnname = "是否包含下级组织机构", desc = "是否包含下级组织机构 ： 1表示有，0表示无", type = TypeEnum.INTETER, nullable = false)
    private Integer hasChild = 0;
    
    private Date createTime;
    
    private Long createPerson;
    
    private Date updateTime;
    
    private Long updatePerson;
    
    /**
    * @return 返回 organCode
    */
    @CacheKeyMethod
    public String getOrganCode() {
        return organCode;
    }
    
    /**
     * @param 对organCode进行赋值
     */
    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }
    
    /**
    * @return 返回 organID
    */
    public Long getOrganID() {
        return organID;
    }
    
    /**
    * @param 对organID进行赋值
    */
    public void setOrganID(Long organID) {
        this.organID = organID;
    }
    
    /**
    * @return 返回 parentOrganCode
    */
    public String getParentOrganCode() {
        return parentOrganCode;
    }
    
    /**
    * @param 对parentOrganCode进行赋值
    */
    public void setParentOrganCode(String parentOrganCode) {
        this.parentOrganCode = parentOrganCode;
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
    * @return 返回 sname
    */
    public String getSname() {
        return sname;
    }
    
    /**
    * @param 对sname进行赋值
    */
    public void setSname(String sname) {
        this.sname = sname;
    }
    
    
    
    public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getSecondPrincipal() {
		return secondPrincipal;
	}

	public void setSecondPrincipal(String secondPrincipal) {
		this.secondPrincipal = secondPrincipal;
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
    
    /**
    * @return 返回 fax
    */
    public String getFax() {
        return fax;
    }
    
    /**
    * @param 对fax进行赋值
    */
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    /**
    * @return 返回 postCode
    */
    public String getPostCode() {
        return postCode;
    }
    
    /**
    * @param 对postCode进行赋值
    */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
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
    
   
    
    
    
    public Long getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Long createPerson) {
		this.createPerson = createPerson;
	}

	public Long getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Long updatePerson) {
		this.updatePerson = updatePerson;
	}

	public Integer getHasChild() {
		return hasChild;
	}

	public void setHasChild(Integer hasChild) {
		this.hasChild = hasChild;
	}

	
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((organCode == null) ? 0 : organCode.hashCode());
        return result;
    }
    
    @Override
	public String toString() {
		return "Organ [organID=" + organID + ", organCode=" + organCode
				+ ", parentOrganCode=" + parentOrganCode + ", name=" + name
				+ ", sname=" + sname + ", principal=" + principal
				+ ", secondPrincipal=" + secondPrincipal + ", tel=" + tel
				+ ", fax=" + fax + ", postCode=" + postCode + ", status="
				+ status + ", description=" + description + ", hasChild="
				+ hasChild + ", createTime=" + createTime + ", createPerson="
				+ createPerson + ", updateTime=" + updateTime
				+ ", updatePerson=" + updatePerson + "]";
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
        Organ other = (Organ)obj;
        if (organCode == null) {
            if (other.organCode != null)
                return false;
        }
        else if (!organCode.equals(other.organCode))
            return false;
        return true;
    }
    
    
    /***********************************************此处为处理列表实时缓存所添加**************************************/
    //数据实体的命名空间(系统名称:实体名称:版本),此版本是因为实体属性变更引起（例如增减属性，属性名称变更）
    //尽量简称，但必须保证不重复
    public static final transient String namespace = "user:organ:1";
    
    //默认缓存过期时间, 列表默认缓存10天
    public static final transient int expiration = 60 * 60 * 24;
    
    //数据列表查询时用到此版本, 数据实际值存储在分页式缓存中
    public static transient long version = 1;
    /***********************************************end********************************************************/
}
