/*
* 文 件 名:  LogDTO.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  日志传输对象
* 修 改 人:  zhouliang
* 修改时间:  2013年11月23日
* 修改内容:  <修改内容>
*/
package org.yy.user.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.yy.framework.basedata.query.AbstractQueryDto;

/**
* 日志传输对象
* 
* @author  zhouliang
* @version  [1.0, 2013年11月23日]
* @since  [app-user/1.0]
*/
public class LogDTO extends AbstractQueryDto {
    
     /**
     * 注释内容
     */
    private static final long serialVersionUID = 2542463454488357759L;

    /**
     * 操作者
     */
    private String operName;
    
    /**
     * 业务类型
     */
    private String busiDataType;
    
    /**
     * 创建起始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startCreateTime;
    
    /**
     * 创建结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endCreateTime;
    
    /**
    * @return 返回 operName
    */
    public String getOperName() {
        return operName;
    }
    
    /**
    * @param 对operName进行赋值
    */
    public void setOperName(String operName) {
        this.operName = operName;
    }
    
    /**
    * @return 返回 busiDataType
    */
    public String getBusiDataType() {
        return busiDataType;
    }
    
    /**
    * @param 对busiDataType进行赋值
    */
    public void setBusiDataType(String busiDataType) {
        this.busiDataType = busiDataType;
    }
    
    /**
    * @return 返回 startCreateTime
    */
    public Date getStartCreateTime() {
        return startCreateTime;
    }
    
    /**
    * @param 对startCreateTime进行赋值
    */
    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }
    
    /**
    * @return 返回 endCreateTime
    */
    public Date getEndCreateTime() {
        return endCreateTime;
    }
    
    /**
    * @param 对endCreateTime进行赋值
    */
    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "LogDTO [operName=" + operName + ", busiDataType=" + busiDataType + ", startCreateTime="
            + startCreateTime + ", endCreateTime=" + endCreateTime + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((busiDataType == null) ? 0 : busiDataType.hashCode());
        result = prime * result + ((endCreateTime == null) ? 0 : endCreateTime.hashCode());
        result = prime * result + ((operName == null) ? 0 : operName.hashCode());
        result = prime * result + ((startCreateTime == null) ? 0 : startCreateTime.hashCode());
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
        LogDTO other = (LogDTO)obj;
        if (busiDataType == null) {
            if (other.busiDataType != null)
                return false;
        }
        else if (!busiDataType.equals(other.busiDataType))
            return false;
        if (endCreateTime == null) {
            if (other.endCreateTime != null)
                return false;
        }
        else if (!endCreateTime.equals(other.endCreateTime))
            return false;
        if (operName == null) {
            if (other.operName != null)
                return false;
        }
        else if (!operName.equals(other.operName))
            return false;
        if (startCreateTime == null) {
            if (other.startCreateTime != null)
                return false;
        }
        else if (!startCreateTime.equals(other.startCreateTime))
            return false;
        return true;
    }
    
}
