/*
* 文 件 名:  UserGroupDTO.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  用户组传输对象
* 修 改 人:  zhouliang
* 修改时间:  2013年11月24日
* 修改内容:  <修改内容>
*/
package org.yy.user.dto;

import org.yy.framework.basedata.query.AbstractQueryDto;

/**
* 用户组传输对象
* 
* @author  zhouliang
* @version  [1.0, 2013年11月24日]
* @since  [app-user/1.0]
*/
public class UserGroupDTO extends AbstractQueryDto {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = -8720198674749217420L;
    
    /**
     * 用户组名称
     */
    private String groupName;
    
    /**
     * 状态
     */
    private Integer status;
    
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
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "UserGroupDTO [groupName=" + groupName + ", status=" + status + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((groupName == null) ? 0 : groupName.hashCode());
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
        UserGroupDTO other = (UserGroupDTO)obj;
        if (groupName == null) {
            if (other.groupName != null)
                return false;
        }
        else if (!groupName.equals(other.groupName))
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
