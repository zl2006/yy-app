/*
 * 文 件 名:  MainData.java
 * 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
 * 描    述:  页面结构所需要数据
 * 修 改 人:  zhouliang
 * 修改时间:  2013年12月4日
 * 修改内容:  <修改内容>
 */
package org.yy.user.dto;

import java.util.ArrayList;
import java.util.List;

import org.yy.user.model.Resource;
import org.yy.user.model.System;
import org.yy.user.model.User;
import org.yy.user.model.constants.ResType;

/**
 * 页面结构所需要数据
 * 
 * @author zhouliang
 * @version [app-user, 2013年12月4日]
 * @since [app-user/1.0]
 */
public class MainDataDTO {

	/** 当前用户拥有系统 */
	private List<System> systems;

	/** 当前用户拥有的资源 */
	private List<Resource> resources;

	/** 当前操作用户 */
	private User user;

	/** 当前系统编码 */
	private String systemCode;

	/** 是否门户 */
	private boolean portalEnable = false;
	
	/** 首页*/
	private String indexUrl;
	
	/**资源ID*/
	private Long resID;

	/**  当前系统编码 */
	public String getSystemCode() {
		return this.systemCode;
	}

	/**
	 * 当前操作用户
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * 当前资源ID
	 */
	public Long getResID() {
		return resID;
	}
	
	public void setResID(String url) {
		for( Resource item : resources ){
			if(url.equals(item.getUrl())){
				resID = item.getResID();
				break;
			}
		}
	}

	/**
	 * 当前用户拥有系统，门户中做为头部顶级菜单
	 */
	public List<org.yy.user.model.System> getSystems() {
		return this.systems;
	}

	/**
	 * 当前用户拥有的资源
	 */
	public List<Resource> getResources() {
		return this.resources;
	}

	/**
	 * 当前系统
	 */
	public System getCurrentSystem() {
		if (systemCode == null) {
			return null;
		}
		if (this.systems != null) {
			for (System item : systems) {
				if (item.getSystemCode().equals(systemCode)) {
					return item;
				}
			}
		}
		return null;
	}

	/**
	 * 模块列表（门户中头部子菜单，或单系统中顶级菜单 ）
	 */
	public List<Resource> getModules() {
		List<Resource> temp = new ArrayList<Resource>();
		if (this.resources != null) {
			for (Resource item : this.resources) {
				if (ResType.MODULE.value().equals(item.getType())) {
					temp.add(item);
				}
			}
		}
		return temp;
	}

	/**
	 * 子模块列表（左侧菜单标题）
	 */
	public List<Resource> getSubModules() {
		List<Resource> temp = new ArrayList<Resource>();
		if (this.resources != null) {
			for (Resource item : this.resources) {
				if (ResType.MODULE.value().equals(item.getType())) {
					temp.add(item);
				}
			}
		}
		return temp;
	}

	/**
	 * 获取模块功能
	 */
	public List<Resource> getModuleFunctions() {
		List<Resource> temp = new ArrayList<Resource>();
		if (resID == null) {
			return temp;
		}

		if (this.resources != null) {
			for (Resource item : this.resources) {
				if (ResType.MODULEFUNC.value().equals(item.getType())
						&& item.getParentResID().equals(this.resID)) {
					temp.add(item);
				}
			}
		}
		return temp;
	}

	/**
	 * 获取当前的列表操作
	 */
	public List<Resource> getListOperations() {
		List<Resource> temp = new ArrayList<Resource>();
		if (resID == null) {
			return temp;
		}

		if (this.resources != null) {
			for (Resource item : this.resources) {
				if (ResType.LISTOPER.value().equals(item.getType())
						&& item.getParentResID().equals(resID)) {
					temp.add(item);
				}
			}
		}
		return temp;
	}

	/**
	 * 获取当前面包屑导航
	 */
	public List<Resource> getNavigators() {
		List<Resource> temp = new ArrayList<Resource>();
		if (resID == null) {
			return temp;
		}

		// 最多查询三级
		int level = 0;
		long tempResID = this.resID;
		for (int i = 0; i < this.resources.size() && level < 3; ++i) {
			// 顶级菜单时退出
			if (tempResID == -1) {
				break;
			}
			if (this.resources.get(i).getResID() == tempResID) {
				temp.add(0, this.resources.get(i));
				tempResID = this.resources.get(i).getParentResID();
				i = -1;
				level++;
			}
		}
		return temp;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setSystems(List<System> systems) {
		this.systems = systems;
	}

	public boolean isPortalEnable() {
		return portalEnable;
	}

	public void setPortalEnable(boolean portalEnable) {
		this.portalEnable = portalEnable;
	}

	public String getIndexUrl() {
		return indexUrl;
	}

	public void setIndexUrl(String indexUrl) {
		this.indexUrl = indexUrl;
	}
	
}
