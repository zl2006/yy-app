/*
* 文 件 名:  DictionaryDTO.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:   数据字典传输对象
* 修 改 人:  zhouliang
* 修改时间:  2013年11月22日
* 修改内容:  <修改内容>
*/
package org.yy.user.dto;

import org.yy.framework.basedata.query.AbstractQueryDto;
import org.yy.user.model.Dictionary;

import com.google.code.ssm.api.CacheKeyMethod;

/**
*  数据字典传输对象
* 
* @author  zhouliang
* @version  [1.0, 2013年11月22日]
* @since  [app-user/1.0]
*/
public class DictionaryDTO extends AbstractQueryDto {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = -2790717992461823265L;
    
    /**
     * 字典编码
     */
    private String dicCode;
    
    /**
     * 字典名称
     */
    private String name;
    
    /**
     * 字典类型
     */
    private String type;
    
    /**
     * 状态
     */
    private Integer status;
    
    /**
    * @return 返回 dicCode
    */
    public String getDicCode() {
        return dicCode;
    }
    
    /**
    * @param 对dicCode进行赋值
    */
    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
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
        return "DictionaryDTO [dicCode=" + dicCode + ", name=" + name + ", type=" + type + ", status=" + status + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dicCode == null) ? 0 : dicCode.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        DictionaryDTO other = (DictionaryDTO)obj;
        if (dicCode == null) {
            if (other.dicCode != null)
                return false;
        }
        else if (!dicCode.equals(other.dicCode))
            return false;
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
        if (type == null) {
            if (other.type != null)
                return false;
        }
        else if (!type.equals(other.type))
            return false;
        return true;
    }
    
    @CacheKeyMethod
    public String generateKey() {
        return String.valueOf(this.hashCode()) + Dictionary.version;
    }
    
}
