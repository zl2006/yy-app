/*
* 文 件 名:  ResourceDaoImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  资源信息DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月23日
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
import org.yy.user.dao.ResourceDao;
import org.yy.user.dto.ResourceDTO;
import org.yy.user.model.Resource;
import org.yy.user.model.Role;
import org.yy.user.model.System;
import org.yy.user.model.User;

import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateSingleCache;

import static org.yy.user.model.Resource.*;

/**
* 资源信息DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月23日]
* @since  [app-user/1.0]
*/
@Repository("resourceDAO")
public class ResourceDaoImpl extends AbstractMyBatisDao implements ResourceDao {
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public Resource insertResource(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    Resource resource) {
        try {
            resource.setCreateTime(new Date());
            resource.setUpdateTime(new Date());
            sqlSession.insert("res.INSERT_RESOURCE", resource);
        }
        catch (Exception e) {
            throw new DaoException("RES_ADD_ERROR", "保存资源信息异常", e);
        }
        return resource;
    }
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public void updateResource(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    Resource resource) {
        try {
            resource.setUpdateTime(new Date());
            sqlSession.update("res.UPDATE_RESOURCE", resource);
        }
        catch (Exception e) {
            throw new DaoException("RES_UPDATE_ERROR", "更新资源信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    @ReadThroughSingleCache(namespace = namespace, expiration = expiration)
    public Resource findResource(@ParameterValueKeyProvider
    Long resID) {
        try {
            return (Resource)sqlSession.selectOne("res.FIND_RESOURCE_BY_ID", resID);
        }
        catch (Exception e) {
            throw new DaoException("RES_FIND_ERROR", "查询资源信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<Resource> findResource(ResourceDTO resourceDTO) {
        try {
            return findBypagination("res.FIND_RESOURCE_BY_DTO", resourceDTO);
        }
        catch (Exception e) {
            throw new DaoException("RES_FIND_ERROR", "查询资源信息异常", e);
        }
    }
    
    /********************************************************************************************/
    /** {@inheritDoc} */
    @Override
    public List<Resource> findResource(Role role) {
        
        if (role == null || "".equals(role.getRoleCode().trim())) {
            return new ArrayList<Resource>();
        }
        
        try {
            return sqlSession.selectList("res.FIND_RESOURCE_BY_ROLE", role.getRoleCode());
        }
        catch (Exception e) {
            throw new DaoException("RES_FIND_ERROR", "查询资源信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void insertRoleResRel(String roleCode, Long resID) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("roleCode", roleCode);
            params.put("resID", resID);
            params.put("createTime", new Date());
            params.put("updateTime", new Date());
            sqlSession.insert("res.INSERT_ROLERESREL", params);
        }
        catch (Exception e) {
            throw new DaoException("RES_ADD_ERROR", "保存角色与资源关系异常", e);
        }
        
    }
    
    /** {@inheritDoc} */
    @Override
    public void deleteRoleResRel(String roleCode, Long resID) {
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleCode", roleCode);
        params.put("resID", resID);
        params.put("createTime", new Date());
        params.put("updateTime", new Date());
        try {
            sqlSession.delete("res.DELETE_ROLERESREL", params);
        }
        catch (Exception e) {
            throw new DaoException("RES_DELETE_ERROR", "删除角色与资源关系异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void deleteRoleResRel(String roleCode) {
        deleteRoleResRel(roleCode, null);
    }
    
    /********************************************************************************************/
    /** {@inheritDoc} */
    @Override
    public List<Resource> findResource(System system) {
        try {
            return sqlSession.selectList("res.FIND_RESOURCE_BY_SYSTEM", system.getSystemCode());
        }
        catch (Exception e) {
            throw new DaoException("RES_FIND_ERROR", "查询资源信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    @ReadThroughSingleCache(namespace = namespace + ":findResource", expiration = 60 * 5)
    public List<Resource> findResource(@ParameterValueKeyProvider(order = 0)
    System system, @ParameterValueKeyProvider(order = 1)
    User user) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("loginID", user.getLoginID());
        params.put("systemCode", system.getSystemCode());
        try {
            return sqlSession.selectList("res.FIND_RESOURCE_BY_USER", params);
        }
        catch (Exception e) {
            throw new DaoException("RES_FIND_ERROR", "查询资源信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    @ReadThroughSingleCache(namespace = namespace + ":findResourceForMap", expiration = 60 * 5)
    public Map<String, Resource> findResourceForMap(@ParameterValueKeyProvider(order = 0)
    System system, @ParameterValueKeyProvider
    User user) {
        List<Resource> resources = findResource(system, user);
        Map<String, Resource> resMap = new HashMap<String, Resource>();
        if (resources != null && resources.size() != 0) {
            for (Resource item : resources) {
                resMap.put(item.getUrl(), item);
            }
        }
        return resMap;
    }
}
