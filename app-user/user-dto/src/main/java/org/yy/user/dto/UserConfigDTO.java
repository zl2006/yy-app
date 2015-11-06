package org.yy.user.dto;

import java.util.List;
/*
 * 文 件 名:  UserDTO.java
 * 版    权:  YY Technologies Co., Ltd. Copyright 1993-2012,  All rights reserved
 * 描    述:  用户传输对象
 * 修 改 人:  zhouliang
 * 修改时间:  2012-9-10
 */

/**
 * 用户传输对象
 * 
 * @author zhouliang
 * @version [0.1, 2012-9-10]
 * @since [app-user/1.0]
 */
public class UserConfigDTO {

	private String loginID;

	private Long userID;

	private List<Long> groupIDs;

	private List<String> roleCodes;

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public List<Long> getGroupIDs() {
		return groupIDs;
	}

	public void setGroupIDs(List<Long> groupIDs) {
		this.groupIDs = groupIDs;
	}

	public List<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(List<String> roleCodes) {
		this.roleCodes = roleCodes;
	}

}
