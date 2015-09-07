/*
* 文 件 名:  SystemDaoImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  系统访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月23日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.yy.framework.base.dao.AbstractMyBatisDao;
import org.yy.framework.basedata.exception.DaoException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.SystemDao;
import org.yy.user.dto.SystemDTO;
import org.yy.user.model.System;
import org.yy.user.model.User;

import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateSingleCache;

import static org.yy.user.model.System.*;

/**
* 系统访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月23日]
* @since  [app-user/1.0]
*/
public class SystemDaoImpl extends AbstractMyBatisDao implements SystemDao {
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public System insertSystem(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    org.yy.user.model.System system) {
        try {
            system.setCreateTime(new Date());
            system.setUpdateTime(new Date());
            sqlSession.insert("system.INSERT_SYSTEM", system);
        }
        catch (Exception e) {
            throw new DaoException("SYSTEM_SAVE_ERROR", "保存系统信息异常", e);
        }
        return system;
    }
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public void updateSystem(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    org.yy.user.model.System system) {
        try {
            system.setUpdateTime(new Date());
            sqlSession.update("system.UPDATE_SYSTEM", system);
        }
        catch (Exception e) {
            throw new DaoException("SYSTEM_EDIT_ERROR", "更新系统信息异常", e);
        }
        
    }
    
    /** {@inheritDoc} */
    @Override
    @ReadThroughSingleCache(namespace = namespace, expiration = expiration)
    public System findSystem(@ParameterValueKeyProvider
    String systemCode) {
        try {
            return (System)sqlSession.selectOne("system.FIND_SYSTEM_BY_CODE", systemCode);
        }
        catch (Exception e) {
            throw new DaoException("SYSTEM_FIND_ERROR", "查询系统信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<org.yy.user.model.System> findSystem(SystemDTO systemDTO) {
        try {
            return findBypagination("system.FIND_SYSTEM_BY_DTO", systemDTO);
        }
        catch (Exception e) {
            throw new DaoException("SYSTEM_FIND_ERROR", "查询系统信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public List<System> findSystem() {
        try {
            return sqlSession.selectList("system.FIND_ALL_SYSTEM");
        }
        catch (Exception e) {
            throw new DaoException("SYSTEM_FIND_ERROR", "查询系统信息异常", e);
        }
    }
    
    /** {@inheritDoc} 
    * @throws SQLException */
    @Override
    @ReadThroughSingleCache(namespace = namespace + ":findSystem", expiration = 60 * 5)
    public List<System> findSystem(@ParameterValueKeyProvider
    User user) {
        try {
            if (user == null || "".equals(user.getLoginID())) {
                return new ArrayList<System>();
            }
            return sqlSession.selectList("system.FIND_SYSTEM_BY_USER", user.getLoginID());
        }
        catch (Exception e) {
            throw new DaoException("SYSTEM_FIND_ERROR", "查询系统信息异常", e);
        }
    }
    
}
