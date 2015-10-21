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
	
	/*public MainDataDTO(){
	}*/

	public MainDataDTO(User user, String systemCode, boolean portalEnable) {
		super();
		this.user = user;
		this.systemCode = systemCode;
		this.portalEnable = portalEnable;
	}

	/**
	 * 当前用户拥有的所有系统
	 */
	public List<org.yy.user.model.System> getSystems() {
		return systems;
	}

	/**
	 * 当前用户拥有的所有资源, 用来做权限判断
	 */
	public List<Resource> getResources() {
		return resources;
	}
	
	/**
	 * 当前访问的资源ID
	 */
	public Long getResID() {
		return resID;
	}
	
	
	/**  当前系统编码 */
	public String getSystemCode() {
		return systemCode;
	}

	/**
	 * 当前操作用户
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * 当前访问的资源
	 */
	public Resource getCurrentResource() {
		if (resID == null) {
			return null;
		}
		for (int i = 0; i < resources.size(); ++i) {
			if (resources.get(i).getResID()== resID ) {
				return resources.get(i);
			}
		}
		return null;
	}

	/**
	 * 当前访问的系统
	 */
	public System getCurrentSystem() {
		if (systemCode == null) {
			return null;
		}
		if (systems != null) {
			for (System item : systems) {
				if (item.getSystemCode().equals(systemCode)) {
					return item;
				}
			}
		}
		return null;
	}
	
	/**
	 * 当前访问的模块
	 */
	public Resource getCurrentModule() {

		Long currentResId = this.resID;
		if (currentResId == null) {
			return null;
		}

		// 最多查询四级
		int level = 0;
		for (int i = 0; i < this.resources.size() && level < 4; ++i) {
			// 顶级菜单时退出
			if (currentResId == -1) {
				break;
			}
			
			Resource currentRes = resources.get(i);
			if (currentRes.getResID() == currentResId ) {
				//当前资源为模板时直接返回，否则查找父资源
				if(currentRes.getType().equals(ResType.MODULE.value())){
					return currentRes;
				}else{
					currentResId = currentRes.getParentResID();
					level++;
					i = -1;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 当前访问的子模块
	 */
	public Resource getCurrentSubModule() {

		Long currentResId = this.resID;
		if (currentResId == null) {
			return null;
		}

		// 最多查询四级
		int level = 0;
		for (int i = 0; i < resources.size() && level < 4; ++i) {
			// 顶级菜单时退出
			if (currentResId == -1) {
				break;
			}
			
			Resource currentRes = resources.get(i);
			if (currentRes.getResID() == currentResId ) {
				//当前资源为模板时直接返回，否则查找父资源
				if(currentRes.getType().equals(ResType.SUBMODULE)){
					return currentRes;
				}else{
					currentResId = currentRes.getParentResID();
					level++;
					i = -1;
				}
			}
		}
		return null;
	}

	/**
	 * 当前系统所有的模块列表
	 */
	public List<Resource> getModules() {
		List<Resource> temp = new ArrayList<Resource>();
		if (this.resources != null) {
			for (Resource item : resources) {
				if (ResType.MODULE.value().equals(item.getType())) {
					temp.add(item);
				}
			}
		}
		return temp;
	}

	/**
	 * 当前访问模块下的子模块列表
	 */
	public List<Resource> getSubModules() {
		List<Resource> temp = new ArrayList<Resource>();
		
		Resource currentModule = getCurrentModule();
		if(currentModule == null){
			return temp;
		}
		if (this.resources != null) {
			for (Resource item : resources) {
				if (ResType.SUBMODULE.value().equals(item.getType())  && item.getParentResID() == currentModule.getResID()) {
					temp.add(item);
				}
			}
		}
		return temp;
	}

	/**
	 * 当前访问模块下的模块功能
	 */
	public List<Resource> getModuleFunctions() {
		List<Resource> temp = new ArrayList<Resource>();
		if (resID == null) {
			return temp;
		}
		
		Resource currentModule = getCurrentModule();
		if(currentModule == null){
			return temp;
		}

		if (this.resources != null) {
			for (Resource item : this.resources) {
				if (ResType.MODULEFUNC.value().equals(item.getType()) && item.getParentResID() == currentModule.getResID()) {
					temp.add(item);
				}
			}
		}
		return temp;
	}
	
	
	/**
	 * 当前访问模块下所有子模块的模块功能
	 */
	public List<Resource> getSubModuleFunctions() {
		List<Resource> temp = new ArrayList<Resource>();
		if (resID == null) {
			return temp;
		}
		
		List<Resource> subModules = getSubModules();
		if(subModules == null || subModules.size() == 0){
			return temp;
		}

		if (resources != null) {
			for (Resource item : resources) {
				for(Resource subMd : subModules){
					if (ResType.MODULEFUNC.value().equals(item.getType()) && item.getParentResID() == subMd.getResID()) {
						temp.add(item);
						break;
					}
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

		if (resources != null) {
			for (Resource item : resources) {
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

		// 最多查询四级
		int level = 0;
		long tempResID = this.resID;
		for (int i = 0; i < resources.size() && level < 4; ++i) {
			// 顶级菜单时退出
			if (tempResID == -1) {
				break;
			}
			if (resources.get(i).getResID() == tempResID) {
				temp.add(0, resources.get(i));
				tempResID = resources.get(i).getParentResID();
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
	
	public void setResID(String url) {
		for( Resource item : resources ){
			if(url.equals(item.getUrl())){
				resID = item.getResID();
				break;
			}
		}
	}
	
}
