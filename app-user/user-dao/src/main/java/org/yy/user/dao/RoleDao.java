/*
* 文 件 名:  RoleDao.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  角色访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月24日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao;

import java.util.List;

import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.RoleDTO;
import org.yy.user.model.Role;
import org.yy.user.model.System;
import org.yy.user.model.User;
import org.yy.user.model.UserGroup;

/**
* 角色访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月24日]
* @since  [app-user/1.0]
*/
public interface RoleDao {
    
    /**
     * 保存角色
     * @param role 角色
     * @return 角色
     */
    public Role insertRole(Role role);
    
    /**
     * 更新角色
     * @param role 角色
     */
    public void updateRole(Role role);
    
    /**
     * 根据编码获取角色
     * 
     * @param roleCode 编码
     * @return 角色
     */
    public Role findRole(String roleCode);
    
    /**
     * 根据条件查询角色
     * @param roleDTO 查询条件
     * @return
     */
    public ResultDto<Role> findRole(RoleDTO roleDTO);
    
    /***********************************************************************************************/
    /**
     * 查询用户组所拥有的角色
     * @param userGroup 用户组
     * @return
     */
    public List<Role> findRole(UserGroup userGroup);
    
    /**
     * 插入用户组与角色的关系
     * @param roleCode 角色编码
     * @param userGroupID 用户组ID
     */
    public void insertGroupRoleRef(String roleCode, Long userGroupID);
    
    /**
     * 删除用户组与角色的关系
     * @param roleCode 角色编码
     * @param userGroupID 用户组ID
     */
    public void deleteGroupRoleRef(String roleCode, Long userGroupID);
    
    /**
     * 删除用户组与所有角色的关系  
     * @param userGroupID 用户组ID
     */
    public void deleteGroupRoleRef(Long userGroupID);
    
    /***********************************************************************************************/
    /**
     * 查询用户所有角色，不包括用户组对应的角色
     * @param user 用户
     * @return
     */
    public List<Role> findRole(User user);
    
    /**
     * 插入用户与角色的关系
     * @param roleCode 角色编码
     * @param loginID 登录ID
     */
    public void insertUserRoleRef(String roleCode, String loginID);
    
    /**
     * 删除用户与角色的关系
     * @param roleCode 角色编码
     * @param loginID 登录ID
     */
    public void deleteUserRoleRef(String roleCode, String loginID);
    
    /**
     * 删除用户与所有角色的关系  
     * @param loginID 登录ID
     */
    public void deleteUserRoleRef(String loginID);
    
    /***********************************************************************************************/
    /**
     * 查询系统所有角色
     * @param system 系统
     * @return
     */
    public List<Role> findRole(System system);
}
