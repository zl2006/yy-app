/*
* 文 件 名:  RoleDaoImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:   角色访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月24日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yy.framework.base.dao.AbstractMyBatisDao;
import org.yy.framework.basedata.exception.DaoException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.RoleDao;
import org.yy.user.dto.RoleDTO;
import org.yy.user.model.Role;
import org.yy.user.model.System;
import org.yy.user.model.User;
import org.yy.user.model.UserGroup;

import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateSingleCache;

import static org.yy.user.model.Role.*;

/**
*  角色访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月24日]
* @since  [app-user/1.0]
*/
public class RoleDaoImpl extends AbstractMyBatisDao implements RoleDao {
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public Role insertRole(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    Role role) {
        try {
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());
            sqlSession.insert("role.INSERT_ROLE", role);
        }
        catch (Exception e) {
            throw new DaoException("ROLE_SAVE_ERROR", "保存角色信息异常", e);
        }
        return role;
    }
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public void updateRole(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    Role role) {
        try {
            role.setUpdateTime(new Date());
            sqlSession.update("role.UPDATE_ROLE", role);
        }
        catch (Exception e) {
            throw new DaoException("ROLE_UPDATE_ERROR", "更新角色信息异常", e);
        }
        
    }
    
    /** {@inheritDoc} */
    @Override
    @ReadThroughSingleCache(namespace = namespace, expiration = expiration)
    public Role findRole(@ParameterValueKeyProvider
    String roleCode) {
        try {
            return (Role)sqlSession.selectOne("role.FIND_ROLE_BY_CODE", roleCode);
        }
        catch (Exception e) {
            throw new DaoException("ROLE_FIND_ERROR", "查询角色信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<Role> findRole(RoleDTO roleDTO) {
        try {
            return findBypagination("role.FIND_ROLE_BY_DTO", roleDTO);
        }
        catch (SQLException e) {
            throw new DaoException("ROLE_FIND_ERROR", "查询角色信息异常", e);
        }
    }
    
    /***********************************************************************************************/
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> findRole(UserGroup userGroup) {
        try {
            if (userGroup == null || userGroup.getUserGroupID() == null) {
                return new ArrayList<Role>();
            }
            return sqlSession.selectList("role.FIND_ROLE_BY_GROUP", userGroup.getUserGroupID());
        }
        catch (Exception e) {
            throw new DaoException("ROLE_FIND_ERROR", "查询用户组与角色关系信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void insertGroupRoleRef(String roleCode, Long userGroupID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleCode", roleCode);
        params.put("userGroupID", userGroupID);
        params.put("createTime", new Date());
        params.put("updateTime", new Date());
        try {
            sqlSession.insert("role.INSERT_GROUPROLEREF", params);
        }
        catch (Exception e) {
            throw new DaoException("ROLE_SAVE_ERROR", "保存用户组与角色关系信息异常", e);
        }
        
    }
    
    /** {@inheritDoc} */
    @Override
    public void deleteGroupRoleRef(String roleCode, Long userGroupID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleCode", roleCode);
        params.put("userGroupID", userGroupID);
        try {
            sqlSession.delete("role.DELETE_USERGROUPREF", params);
        }
        catch (Exception e) {
            throw new DaoException("ROLE_DELETE_ERROR", "删除用户组与角色关系信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void deleteGroupRoleRef(Long userGroupID) {
        deleteGroupRoleRef(null, userGroupID);
    }
    
    /***********************************************************************************************/
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> findRole(User user) {
        try {
            if (user == null || "".equals(user.getLoginID().trim())) {
                return new ArrayList<Role>();
            }
            return sqlSession.selectList("role.FIND_ROLE_BY_USER", user.getLoginID());
        }
        catch (Exception e) {
            throw new DaoException("ROLE_FIND_ERROR", "查询用户与角色关系信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void insertUserRoleRef(String roleCode, String loginID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleCode", roleCode);
        params.put("loginID", loginID);
        params.put("createTime", new Date());
        params.put("updateTime", new Date());
        try {
            sqlSession.insert("role.INSERT_USERROLEREF", params);
        }
        catch (Exception e) {
            throw new DaoException("ROLE_SAVE_ERROR", "保存用户与角色关系信息异常", e);
        }
        
    }
    
    /** {@inheritDoc} */
    @Override
    public void deleteUserRoleRef(String roleCode, String loginID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleCode", roleCode);
        params.put("loginID", loginID);
        try {
            sqlSession.delete("role.DELETE_USERROLEREF", params);
        }
        catch (Exception e) {
            throw new DaoException("ROLE_DELETE_ERROR", "删除用户与角色关系信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void deleteUserRoleRef(String loginID) {
        deleteUserRoleRef(loginID, null);
    }
    
    /***********************************************************************************************/
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> findRole(System system) {
        try {
            if (system == null || "".equals(system.getSystemCode().trim())) {
                return new ArrayList<Role>();
            }
            return sqlSession.selectList("role.FIND_ROLE_BY_SYSTEM", system.getSystemCode());
        }
        catch (Exception e) {
            throw new DaoException("ROLE_FIND_ERROR", "查询角色信息异常", e);
        }
    }
}
