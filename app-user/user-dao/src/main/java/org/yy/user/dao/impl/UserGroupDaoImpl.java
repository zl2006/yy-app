/*
* 文 件 名:  UserGroupDaoImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述: 用户组DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月24日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.yy.framework.base.dao.AbstractMyBatisDao;
import org.yy.framework.basedata.exception.DaoException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.UserGroupDao;
import org.yy.user.dto.UserGroupDTO;
import org.yy.user.model.User;
import org.yy.user.model.UserGroup;

import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateSingleCache;

import static org.yy.user.model.UserGroup.*;

/**
* 用户组DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月24日]
* @since  [app-user/1.0]
*/
@Repository("userGroupDAO")
public class UserGroupDaoImpl extends AbstractMyBatisDao implements UserGroupDao {
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public UserGroup insertUserGroup(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    UserGroup userGroup) {
        try {
            userGroup.setCreateTime(new Date());
            userGroup.setUpdateTime(new Date());
            sqlSession.insert("usergroup.INSERT_USERGROUP", userGroup);
        }
        catch (Exception e) {
            throw new DaoException("USERGROUP_ADD_ERROR", "新增用户组异常", e);
        }
        return userGroup;
    }
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public void updateUserGroup(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    UserGroup userGroup) {
        try {
            userGroup.setUpdateTime(new Date());
            sqlSession.update("usergroup.UPDATE_USERGROUP", userGroup);
        }
        catch (Exception e) {
            throw new DaoException("USERGROUP_UPDATE_ERROR", "更新用户组异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    @ReadThroughSingleCache(namespace = namespace, expiration = expiration)
    public UserGroup findUserGroup(@ParameterValueKeyProvider
    Long userGroupID) {
        try {
            return (UserGroup)sqlSession.selectOne("usergroup.FIND_USERGROUP_BY_ID", userGroupID);
        }
        catch (Exception e) {
            throw new DaoException("USERGROUP_FIND_ERROR", "查询用户组异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<UserGroup> findUserGroup(UserGroupDTO userGroupDTO) {
        try {
            return findBypagination("usergroup.FIND_USERGROUP", userGroupDTO);
        }
        catch (Exception e) {
            throw new DaoException("USERGROUP_FIND_ERROR", "查询用户组异常", e);
        }
    }
    
    /*************************************************************************************************************************/
    /** {@inheritDoc} */
    @Override
    public List<UserGroup> findUserGroup(User user) {
        
        if (user == null || "".equals(user.getLoginID().trim())) {
            return new ArrayList<UserGroup>();
        }
        
        try {
            return sqlSession.selectList("usergroup.FIND_USERGROUP_BY_USER", user.getLoginID());
        }
        catch (Exception e) {
            throw new DaoException("USERGROUP_FIND_ERROR", "查询用户组异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void insertUserGroupRef(String loginID, Long userGroupID) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("loginID", loginID);
            params.put("userGroupID", userGroupID);
            params.put("createTime", new Date());
            params.put("updateTime", new Date());
            sqlSession.insert("usergroup.INSERT_USERGROUPREF", params);
        }
        catch (Exception e) {
            throw new DaoException("USERGROUP_ADD_ERROR", "新增用户组异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void deleteUserGroupRef(String loginID, Long userGroupID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loginID", loginID);
        params.put("userGroupID", userGroupID);
        try {
            sqlSession.delete("usergroup.DELETE_USERGROUPREF", params);
        }
        catch (Exception e) {
            throw new DaoException("USERGROUP_DELETE_ERROR", "删除用户组异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void deleteUserGroupRef(@ParameterValueKeyProvider
    String loginID) {
        deleteUserGroupRef(loginID, null);
    }
    
}
