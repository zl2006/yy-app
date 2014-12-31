/*
* 文 件 名:  Log.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  日志信息
* 修 改 人:  zhouliang
* 修改时间:  2013年11月19日
* 修改内容:  
*/
package org.yy.user.model;

import java.io.Serializable;
import java.util.Date;

import org.yy.framework.document.anno.Field;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.TypeEnum;


/**
* 日志信息
* 
* @author  zhouliang
* @version  [1.0, 2013年11月19日]
* @since  [app-user/1.0]
*
*/
@Model(name = "日志信息", desc = "日志信息")
public class Log implements Serializable {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = 181042419441915777L;
    
    @Field(cnname = "日志ID", desc = "日志ID", nullable = false, type = TypeEnum.INTETER)
    private Long logID;
    
    @Field(cnname = "操作者ID", desc = "操作者ID", nullable = false, type = TypeEnum.INTETER)
    private Integer operatorID;
    
    @Field(cnname = "操作者名称", desc = "操作者名称", nullable = false, length = 32)
    private String operName;
    
    @Field(cnname = "操作类型", desc = "操作类型：增、删、改、查、审核等操作", nullable = false, length = 8)
    private String operType;
    
    @Field(cnname = "业务数据类型", desc = "业务数据类型:需求要字典中定义，格式一般为：系统编号_模块编号_业务编号组成", length = 32)
    private String busiDataType;
    
    @Field(cnname = "业务数据ID", desc = "业务数据ID", type = TypeEnum.INTETER)
    private Integer busiDataID;
    
    @Field(cnname = "描述", desc = "描述", length = 255)
    private String remark;
    
    private Date createTime;
    
    private Date updateTime;
    
    /**
    * @return 返回 logID
    */
    public Long getLogID() {
        return logID;
    }
    
    /**
    * @param 对logID进行赋值
    */
    public void setLogID(Long logID) {
        this.logID = logID;
    }
    
    /**
    * @return 返回 operatorID
    */
    public Integer getOperatorID() {
        return operatorID;
    }
    
    /**
    * @param 对operatorID进行赋值
    */
    public void setOperatorID(Integer operatorID) {
        this.operatorID = operatorID;
    }
    
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
    * @return 返回 operType
    */
    public String getOperType() {
        return operType;
    }
    
    /**
    * @param 对operType进行赋值
    */
    public void setOperType(String operType) {
        this.operType = operType;
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
    * @return 返回 busiDataID
    */
    public Integer getBusiDataID() {
        return busiDataID;
    }
    
    /**
    * @param 对busiDataID进行赋值
    */
    public void setBusiDataID(Integer busiDataID) {
        this.busiDataID = busiDataID;
    }
    
    /**
    * @return 返回 remark
    */
    public String getRemark() {
        return remark;
    }
    
    /**
    * @param 对remark进行赋值
    */
    public void setRemark(String remark) {
        this.remark = remark;
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
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Log [logID=" + logID + ", operatorID=" + operatorID + ", operName=" + operName + ", operType="
            + operType + ", busiDataType=" + busiDataType + ", busiDataID=" + busiDataID + ", remark=" + remark + "]";
    }
    
}
