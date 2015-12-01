package org.yy.monitor.core.entity;

import java.util.Date;

/**
 * 监控管理员
 * 
 * @author zhouliang
 *
 */
public class Manager {

	/**
	 * 管理员ID
	 */
	private Integer managerID;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String pwd;

	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;

	/**
	 * 状态
	 */
	private Integer status;

	public Integer getManagerID() {
		return managerID;
	}

	public void setManagerID(Integer managerID) {
		this.managerID = managerID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
