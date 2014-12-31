/*
* 文 件 名:  RoleService.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  角色服务
* 修 改 人:  zhouliang
* 修改时间:  2014年1月8日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.List;

import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.RoleDTO;
import org.yy.user.model.Role;
import org.yy.user.model.User;
import org.yy.user.model.UserGroup;

/**
* 角色服务
* 
* @author  zhouliang
* @version  [1.0, 2014年1月8日]
* @since  [app-user/1.0]
*/
public interface RoleService {
    
    /**
     * 查询角色列表

     * @param roleDTO 查询条件
     * @return 角色列表
     */
    public ResultDto<Role> findRole(RoleDTO roleDTO)
        throws ServiceException;
    
    /**
     * 查询用户所有角色，不包括用户组对应的角色
     * @param user 用户
     * @return 角色列表
     * @throws ServiceException {ROLE_USER_EMTP:用户为空}
     */
    public List<Role> findRole(User user)throws ServiceException;
    
    
    /**
     * 查询用户组所拥有的角色
     * @param userGroup 用户组
     * @return
     */
    public List<Role> findRole(UserGroup userGroup)throws ServiceException;
    
    /**
     * 更新角色
     * @param role 角色
     */
    public void updateRole(Role role)throws ServiceException;
    
    /**
     * 查询角色
     * @param roleCode 角色编码
     * @return 角色
     */
    public Role findRole(String roleCode)throws ServiceException;
    
    /**
       保存角色
     * @param role 角色
     * @throws ServiceException
     */
    public void saveRole(Role role)throws ServiceException;
    
    /**
     * 配置角色的资源
     * 
     * @param roleCode 角色编码
     * @param resIDs 资源列表
     */
    public void config(String roleCode, List<Long> resIDs);
    
}
