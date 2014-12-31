/*
* 文 件 名:  ResourceDTO.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  资源传输对象
* 修 改 人:  zhouliang
* 修改时间:  2013年11月23日
* 修改内容:  <修改内容>
*/
package org.yy.user.dto;

import org.yy.framework.basedata.query.AbstractQueryDto;

/**
* 资源传输对象
* 
* @author  zhouliang
* @version  [1.0, 2013年11月23日]
* @since  [app-user/1.0]
*/
public class ResourceDTO extends AbstractQueryDto {
    
     /**
     * 注释内容
     */
    private static final long serialVersionUID = 7174025936716481993L;

    
    /**
     * 资源ID
     */
    private Long resID;
    
    /**
     * 应用系统编号
     */
    private String systemCode;
    
    /**
     * 父资源编号
     */
    private Long parentResID;
    
    /**
     * 资源名称
     */
    private String name;
    
    /**
     * 资源位置
     */
    private String url;
    
    /**
     * 资源类型
     */
    private String type;
    
    /**
     * 状态
     */
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
    * @return 返回 resID
    */
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
        return "ResourceDTO [systemCode=" + systemCode + ", parentResID=" + parentResID + ", resID=" + resID
            + ", name=" + name + ", url=" + url + ", type=" + type + ", status=" + status + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((parentResID == null) ? 0 : parentResID.hashCode());
        result = prime * result + ((resID == null) ? 0 : resID.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((systemCode == null) ? 0 : systemCode.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
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
        ResourceDTO other = (ResourceDTO)obj;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (parentResID == null) {
            if (other.parentResID != null)
                return false;
        }
        else if (!parentResID.equals(other.parentResID))
            return false;
        if (resID == null) {
            if (other.resID != null)
                return false;
        }
        else if (!resID.equals(other.resID))
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
        if (type == null) {
            if (other.type != null)
                return false;
        }
        else if (!type.equals(other.type))
            return false;
        if (url == null) {
            if (other.url != null)
                return false;
        }
        else if (!url.equals(other.url))
            return false;
        return true;
    }
    
}
