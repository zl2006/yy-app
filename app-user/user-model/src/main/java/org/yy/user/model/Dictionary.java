/*
* 文 件 名:  Dictionary.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  字典信息
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
* 字典信息
* 
* @author  zhouliang
* @version  [1.0, 2013年11月19日]
* @since  [app-user/1.0]
*/
@Model(name = "字典信息", desc = "字典信息,编码+类型唯一标识一个字典数据 ")
public class Dictionary implements Serializable {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = -5046417547409248886L;
    
    @Field(cnname = "字典ID", desc = "字典ID", type = TypeEnum.INTETER, nullable = false)
    private Long dicID;
    
    @Field(cnname = "字典编码", desc = "字典编码", nullable = false, length = 32)
    @NotEmpty(message = "字典编码不能为空！")
    @Size(min = 0, max = 32, message = "字典编码长度必须在0到32之间")
    private String dicCode;
    
    @Field(cnname = "字典名称", desc = "字典名称", nullable = false, length = 64)
    @NotEmpty(message = "字典名称不能为空！")
    @Size(min = 0, max = 64, message = "字典名称长度必须在0到64之间")
    private String name;
    
    @Field(cnname = "字典值", desc = "字典值", nullable = false, length = 255)
    @NotEmpty(message = "字典值不能为空！")
    @Size(min = 0, max = 255, message = "字典值长度必须在0到255之间")
    private String value;
    
    @Field(cnname = "序号", desc = "序号", type = TypeEnum.INTETER, nullable = false)
    private Integer orderNO = 0;
    
    @Field(cnname = "字典类型", desc = "字典类型", nullable = false, length = 16)
    @NotEmpty(message = "字典类型不能为空！")
    @Size(min = 0, max = 16, message = "字典类型必须在0到16之间")
    private String type;
    
    @Field(cnname = "状态", desc = "状态,0表示无效,1表示有效", type = TypeEnum.INTETER, nullable = false)
    private Integer status = 1;
    
    @Field(cnname = "描述", desc = "描述", length = 255)
    private String description;
    
    /**
     *  创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
    
    /**
    * @return 返回 dicID
    */
    @CacheKeyMethod
    public Long getDicID() {
        return dicID;
    }
    
    /**
    * @param 对dicID进行赋值
    */
    public void setDicID(Long dicID) {
        this.dicID = dicID;
    }
    
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
    * @return 返回 value
    */
    public String getValue() {
        return value;
    }
    
    /**
    * @param 对value进行赋值
    */
    public void setValue(String value) {
        this.value = value;
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
    
    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Dictionary [dicID=" + dicID + ", dicCode=" + dicCode + ", name=" + name + ", value=" + value
            + ", orderNO=" + orderNO + ", type=" + type + ", status=" + status + ", description=" + description
            + ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dicCode == null) ? 0 : dicCode.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
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
        Dictionary other = (Dictionary)obj;
        if (dicCode == null) {
            if (other.dicCode != null)
                return false;
        }
        else if (!dicCode.equals(other.dicCode))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        }
        else if (!type.equals(other.type))
            return false;
        return true;
    }
    
    /***********************************************此处为处理列表实时缓存所添加**************************************/
    //数据实体的命名空间(系统名称:实体名称:版本),此版本是因为实体属性变更引起（例如增减属性，属性名称变更）
    //尽量简称，但必须保证不重复
    public static final transient String namespace = "user:dic:1";
    
    //默认缓存过期时间, 列表默认缓存10天
    public static final transient int expiration = 60 * 60 * 24;
    
    //数据列表查询时用到此版本, 数据实际值存储在分页式缓存中
    public static transient long version = 1;
    /***********************************************end********************************************************/
    
}
