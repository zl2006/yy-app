/*
* 文 件 名:  User.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  用户
* 修 改 人:  zhouliang
* 修改时间:  2013年11月19日
* 修改内容:  
*/
package org.yy.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.yy.framework.document.anno.Field;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.TypeEnum;

import com.google.code.ssm.api.CacheKeyMethod;

/**
* 用户
* 
* @author  zhouliang
* @version  [1.0, 2013年11月19日]
* @since  [app-user/1.0]
*/
@Model(name = "用户", desc = "用户")
public class User implements Serializable {
    
    /**
    * 注释内容
    */
    private static final long serialVersionUID = -6250982366251294945L;
    
    @Field(cnname = "用户ID", desc = "用户ID", type = TypeEnum.INTETER, nullable = false)
    private Long userID;
    
    @Field(cnname = "登录ID", desc = "登录ID", nullable = false, length = 64)
    @NotEmpty(message = "登录ID不能为空！")
    @Size(min = 8, max = 64, message = "登录ID长度必须在8到64之间")
    private String loginID;
    
    @Field(cnname = "用户姓名", desc = "用户姓名", nullable = false, length = 64)
    @NotEmpty(message = "用户姓名不能为空")
    private String name;
    
    @Field(cnname = "密码", desc = "密码, 存储时需要加密，不能存储明文", nullable = false, length = 32)
    @NotEmpty(message = "密码不能为空")
    @Size(min = 8, max = 32, message = "密码长度必须在8到32之间")
    private String password;
    
    @Field(cnname = "邮箱", desc = "邮箱", length = 128)
    private String email;
    
    @Field(cnname = "电话", desc = "电话", length = 32)
    private String tel;
    
    @Field(cnname = "性别", desc = "性别, F表示女，M表示男", length = 1)
    private String sex;
    
    @Field(cnname = "状态", desc = "状态, 1表示有效，0表示无效", type = TypeEnum.INTETER, nullable = false)
    @NotNull
    private Integer status;
    
    @Field(cnname = "地址", desc = "地址", length = 255)
    private String address;
    
    @Field(cnname = "生日", type = TypeEnum.DATE, desc = "生日,格式为:年-月-日", format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    
    @Field(cnname = "所在省市", desc = "所在省市, 存储数据为编码", length = 16)
    private String latnNo;
    
    @Field(cnname = "登录次数", desc = "登录次数", type = TypeEnum.INTETER)
    private Integer loginNumber;
    
    @Field(cnname = "登录身份标识", desc = "登录身份标识", length = 64)
    private String token;
    
    @Field(cnname = "登录身份过期时间", type = TypeEnum.DATE, desc = "登录身份过期时间, 格式为:年-月-日 时:分:秒", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date exprieDate;
    
    @Field(cnname = "最后登录时间", type = TypeEnum.DATE, desc = "最后登录时间", format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
    
    private Date createTime;
    
    private Long createPerson;
    
    private Date updateTime;
    
    private Long updatePerson;
    
    private List<Organ> organs;
    
    /**
    * @return 返回 loginID
    */
    @CacheKeyMethod
    public String getLoginID() {
        return loginID;
    }
    
    /**
    * @param 对loginID进行赋值
    */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }
    
    /**
    * @return 返回 userID
    */
    public Long getUserID() {
        return userID;
    }
    
    /**
    * @param 对userID进行赋值
    */
    public void setUserID(Long userID) {
        this.userID = userID;
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
    * @return 返回 password
    */
    public String getPassword() {
        return password;
    }
    
    /**
    * @param 对password进行赋值
    */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
    * @return 返回 email
    */
    public String getEmail() {
        return email;
    }
    
    /**
    * @param 对email进行赋值
    */
    public void setEmail(String email) {
        this.email = email;
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
    * @return 返回 sex
    */
    public String getSex() {
        return sex;
    }
    
    /**
    * @param 对sex进行赋值
    */
    public void setSex(String sex) {
        this.sex = sex;
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
    * @return 返回 address
    */
    public String getAddress() {
        return address;
    }
    
    /**
    * @param 对address进行赋值
    */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
    * @return 返回 birthday
    */
    public Date getBirthday() {
        return birthday;
    }
    
    /**
    * @param 对birthday进行赋值
    */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    /**
    * @return 返回 latnNo
    */
    public String getLatnNo() {
        return latnNo;
    }
    
    /**
    * @param 对latnNo进行赋值
    */
    public void setLatnNo(String latnNo) {
        this.latnNo = latnNo;
    }
    
    /**
    * @return 返回 loginNumber
    */
    public Integer getLoginNumber() {
        return loginNumber;
    }
    
    /**
    * @param 对loginNumber进行赋值
    */
    public void setLoginNumber(Integer loginNumber) {
        this.loginNumber = loginNumber;
    }
    
    /**
    * @return 返回 exprieDate
    */
    public Date getExprieDate() {
        return exprieDate;
    }
    
    /**
    * @param 对exprieDate进行赋值
    */
    public void setExprieDate(Date exprieDate) {
        this.exprieDate = exprieDate;
    }
    
    /**
    * @return 返回 token
    */
    public String getToken() {
        return token;
    }
    
    /**
    * @param 对token进行赋值
    */
    public void setToken(String token) {
        this.token = token;
    }
    
    /**
    * @return 返回 lastLoginTime
    */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    
    /**
    * @param 对lastLoginTime进行赋值
    */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
    
    public List<Organ> getOrgans() {
		return organs;
	}

	public void setOrgans(List<Organ> organs) {
		this.organs = organs;
	}

	/** {@inheritDoc} */
    @Override
    public String toString() {
        return "User [userID=" + userID + ", loginID=" + loginID + ", name=" + name + ", password=" + password
            + ", email=" + email + ", tel=" + tel + ", sex=" + sex + ", status=" + status + ", address=" + address
            + ", birthday=" + birthday + ", latnNo=" + latnNo + ", loginNumber=" + loginNumber + ", token=" + token
            + ", exprieDate=" + exprieDate + ", lastLoginTime=" + lastLoginTime + ", createTime=" + createTime
            + ", createPerson=" + createPerson + ", updateTime=" + updateTime + ", updatePerson=" + updatePerson + "]";
    }
    
    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loginID == null) ? 0 : loginID.hashCode());
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
        User other = (User)obj;
        if (loginID == null) {
            if (other.loginID != null)
                return false;
        }
        else if (!loginID.equals(other.loginID))
            return false;
        return true;
    }
    
    /***********************************************此处为处理列表实时缓存所添加**************************************/
    //数据实体的命名空间(系统名称:实体名称:版本),此版本是因为实体属性变更引起（例如增减属性，属性名称变更）
    //尽量简称，但必须保证不重复
    public static final transient String namespace = "user:user:1";
    
    //默认缓存过期时间, 列表默认缓存10天
    public static final transient int expiration = 60 * 60 * 24 * 10;
    
    //数据列表查询时用到此版本, 数据实际值存储在分页式缓存中
    public static transient long version = 1;
    /***********************************************end********************************************************/
}
