/*
 * 文 件 名:  SystemDTO.java
 * 版    权:  YY Technologies Co., Ltd. Copyright 1993-2012,  All rights reserved
 * 描    述:  系统传输对象
 * 修 改 人:  zhouliang
 * 修改时间:  2012-9-10
 */
package org.yy.user.dto;

import org.yy.framework.document.anno.Field;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.TypeEnum;
import org.yy.framework.basedata.query.AbstractQueryDto;

/**
 * 系统传输对象
 * 
 * @author zhouliang
 * @version [0.1, 2012-9-10]
 * @since [app-user/1.0]
 */
@Model(name = "系统传输对象", desc = "系统传输对象")
public class SystemDTO extends AbstractQueryDto {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 124690089077763618L;
    
    /**
     * 应用系统编号
     */
    @Field(cnname = "应用系统编号", desc = "应用系统编号", nullable = false, length = 32)
    private String systemCode;
    
    /**
     * 应用名称
     */
    @Field(cnname = "应用名称", desc = "应用名称", nullable = false, length = 64)
    private String name;
    
    /**
     * 状态
     */
    @Field(cnname = "状态", desc = "状态，0表示无效，1表示有效", type = TypeEnum.INTETER, nullable = false)
    private Integer status;
    
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
        return "SystemDTO [systemCode=" + systemCode + ", name=" + name + ", status=" + status + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((systemCode == null) ? 0 : systemCode.hashCode());
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
        SystemDTO other = (SystemDTO)obj;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        }
        else if (!status.equals(other.status))
            return false;
        if (systemCode == null) {
            if (other.systemCode != null)
                return false;
        }
        else if (!systemCode.equals(other.systemCode))
            return false;
        return true;
    }
    
}
