/*
* 文 件 名:  OrganDaoImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  组件机构访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月23日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yy.framework.base.dao.AbstractMyBatisDao;
import org.yy.framework.basedata.exception.DaoException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.OrganDao;
import org.yy.user.dto.OrganDTO;
import org.yy.user.model.Organ;
import org.yy.user.model.User;

import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.UpdateSingleCache;

import static org.yy.user.model.Organ.*;

/**
* 组件机构访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月23日]
* @since  [app-user/1.0]
*/
public class OrganDaoImpl extends AbstractMyBatisDao implements OrganDao {
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public Organ insertOrgan(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    Organ organ) {
        try {
            organ.setCreateTime(new Date());
            organ.setUpdateTime(new Date());
            sqlSession.insert("organ.INSERT_ORGAN", organ);
        }
        catch (Exception e) {
            throw new DaoException("ORGAN_ADD_ERROR", "保存组织机构异常", e);
        }
        return organ;
    }
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public int updateOrgan(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    Organ organ) {
        try {
            organ.setUpdateTime(new Date());
            return sqlSession.update("organ.UPDATE_ORGAN", organ);
        }
        catch (Exception e) {
            throw new DaoException("ORGAN_UPDATE_ERROR", "更新组织机构异常", e);
        }
        
    }
    
    /** {@inheritDoc} */
    @Override
    @ReadThroughSingleCache(namespace = namespace, expiration = expiration)
    public Organ findOrgan(@ParameterValueKeyProvider
    String organCode) {
        try {
            return (Organ)sqlSession.selectOne("organ.FIND_ORGAN_BY_CODE", organCode);
        }
        catch (Exception e) {
            throw new DaoException("ORGAN_FIND_ERROR", "查询组织机构异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<Organ> findOrgan(OrganDTO organDTO) {
        try {
            return findBypagination("organ.FIND_ORGAN_BY_DTO", organDTO);
        }
        catch (Exception e) {
            throw new DaoException("ORGAN_FIND_ERROR", "查询组件机构异常", e);
        }
    }
    
    /***********************************************************************************************/
    /** {@inheritDoc} */
    @Override
    public List<Organ> findOrgan(User user) {
        try {
            return sqlSession.selectList("organ.FIND_ORGAN_BY_USER", user.getLoginID());
        }
        catch (Exception e) {
            throw new DaoException("ORGAN_FIND_ERROR", "查询组件机构异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void insertUserOrganRef(String loginID, String organCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loginID", loginID);
        params.put("organCode", organCode);
        params.put("createTime", new Date());
        params.put("updateTime", new Date());
        try {
            sqlSession.insert("organ.INSERT_USERORGANREF", params);
        }
        catch (Exception e) {
            throw new DaoException("ORGAN_ADD_ERROR", "保存用户与组织机构关系异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void deleteUserOrganRef(String loginID, String organCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loginID", loginID);
        params.put("organCode", organCode);
        try {
            sqlSession.delete("organ.DELETE_USERORGANREF", params);
        }
        catch (Exception e) {
            throw new DaoException("ORGAN_DELETE_ERROR", "删除用户与组织机构关系异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void deleteUserOrganRef(String loginID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("loginID", loginID);
        try {
            sqlSession.delete("organ.DELETE_USERORGANREF", params);
        }
        catch (Exception e) {
            throw new DaoException("ORGAN_DELETE_ERROR", "删除用户与组织机构关系异常", e);
        }
    }
    
}
