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
* @author  zhouliang
* @version  [app-user, 2013年12月4日]
* @since  [app-user/1.0]
*/
public class MainDataDTO {
    
    /**当前用户所有系统*/
    private List<System> systems;
    
    /**当前用户所有资源*/
    private List<Resource> resources;
    
    /**当前用户*/
    private User user;
    
    /**系统编码*/
    private String systemCode;
    
    /**当前资源ID*/
    private Long resID;
    
    public void setSystems(List<System> systems) {
        this.systems = systems;
    }
    
    /**
    * 获取当前用户所有系统
    */
    public List<org.yy.user.model.System> getSystems() {
        return this.systems;
    }
    
    /**
     * 设置系统编码
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }
    
    /**
     * 获取系统编码
     */
    public String getSystemCode() {
        return this.systemCode;
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
     * 获取当前系统
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
    
    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
    
    public List<Resource> getResources() {
        return this.resources;
    }
    
    /**
     * 菜单列表
     */
    public List<Resource> getMenus() {
        List<Resource> temp = new ArrayList<Resource>();
        if (this.resources != null) {
            for (Resource item : this.resources) {
                if (ResType.MENU.value().equals(item.getType())) {
                    temp.add(item);
                }
            }
        }
        return temp;
    }
    
    /**
     * 获取当前功能
     */
    public List<Resource> getFunctions() {
        List<Resource> temp = new ArrayList<Resource>();
        if (resID == null) {
            return temp;
        }
        
        if (this.resources != null) {
            for (Resource item : this.resources) {
                if (ResType.FUNCTION.value().equals(item.getType()) && item.getParentResID().equals(this.resID) ){
                    temp.add(item);
                }
            }
        }
        return temp;
    }
    
    /**
     * 获取当前列表操作
     */
    public List<Resource> getOperations() {
        List<Resource> temp = new ArrayList<Resource>();
        if (resID == null) {
            return temp;
        }
        
        if (this.resources != null) {
            for (Resource item : this.resources) {
                if (ResType.OPERATION.value().equals(item.getType()) && item.getParentResID().equals(resID)) {
                    temp.add(item);
                }
            }
        }
        return temp;
    }
    
    /**
     * 获取当前导航
     */
    public List<Resource> getNavigators() {
        List<Resource> temp = new ArrayList<Resource>();
        if (resID == null) {
            return temp;
        }
        
        //最多查询三级
        int level = 0;
        long tempResID = this.resID;
        for (int i = 0; i < this.resources.size() && level < 3; ++i) {
            //顶级菜单时退出
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
    
    public void setUser(User user) {
        this.user = user;
    }
    
    /**
    * @return 返回 user
    */
    public User getUser() {
        return this.user;
    }
    
}
