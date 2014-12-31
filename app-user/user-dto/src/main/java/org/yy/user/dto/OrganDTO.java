/*
 * 文 件 名:  OrganDTO.java
 * 版    权:  YY Technologies Co., Ltd. Copyright 1993-2012,  All rights reserved
 * 描    述:  组织机构传输对象
 * 修 改 人:  zhouliang
 * 修改时间:  2012-9-10
 */
package org.yy.user.dto;

import org.yy.framework.document.anno.Field;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.TypeEnum;
import org.yy.framework.basedata.query.AbstractQueryDto;

/**
 * 组织机构传输对象
 * 
 * @author zhouliang
 * @version [0.1, 2012-9-10]  
 * @since [app-user/1.0]
 */
@Model(name = "组织机构传输对象", desc = "组织机构传输对象")
public class OrganDTO extends AbstractQueryDto {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 1086520384532118072L;
    
    /**
     * 组织机构编号
     */
    @Field(cnname = "组织机构编号", desc = "组织机构编号", nullable = false, length = 32)
    private String organCode;
    
    /**
     * 父组织机构编号
     */
    @Field(cnname = "父组织机构编号", desc = "父组织机构编号,-1表示顶级组织机构", nullable = false, length = 32)
    private String parentOrganCode;
    
    /**
     * 机构名称
     */
    @Field(cnname = "机构名称", desc = "机构名称", nullable = false, length = 128)
    private String name;
    
    /**
     * 状态
     */
    @Field(cnname = "状态", desc = "组状态 ： 1表示有效，0表示无效", type = TypeEnum.INTETER, nullable = false)
    private Integer status;
    
    /**
    * @return 返回 organCode
    */
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
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "OrganDTO [organCode=" + organCode + ", parentOrganCode=" + parentOrganCode + ", name=" + name
            + ", status=" + status + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((organCode == null) ? 0 : organCode.hashCode());
        result = prime * result + ((parentOrganCode == null) ? 0 : parentOrganCode.hashCode());
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
        OrganDTO other = (OrganDTO)obj;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (organCode == null) {
            if (other.organCode != null)
                return false;
        }
        else if (!organCode.equals(other.organCode))
            return false;
        if (parentOrganCode == null) {
            if (other.parentOrganCode != null)
                return false;
        }
        else if (!parentOrganCode.equals(other.parentOrganCode))
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
