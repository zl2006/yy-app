/*
* 文 件 名:  DictionaryDaoImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  字典信息访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月22日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao.impl;

import static org.yy.user.model.Dictionary.expiration;
import static org.yy.user.model.Dictionary.namespace;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.yy.framework.base.dao.AbstractMyBatisDao;
import org.yy.framework.basedata.exception.DaoException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.DictionaryDao;
import org.yy.user.dto.DictionaryDTO;
import org.yy.user.model.Dictionary;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterDataUpdateContent;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.UpdateSingleCache;

/**
* 字典信息访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月22日]
* @since  [app-user/1.0]
*/
@Repository("dictionaryDAO")
public class DictionaryDaoImpl extends AbstractMyBatisDao implements DictionaryDao {
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public Dictionary insertDictionary(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    Dictionary dictionary) {
        try {
            dictionary.setCreateTime(new Date());
            dictionary.setUpdateTime(new Date());
            sqlSession.insert("dic.INSERT_DICTIONARY", dictionary);
        }
        catch (Exception e) {
            throw new DaoException("DIC_ADD_ERROR", "新增字典信息异常", e);
        }
        return dictionary;
    }
    
    /** {@inheritDoc} */
    @Override
    @InvalidateSingleCache(namespace = namespace)
    public void deleteDictionary(@ParameterValueKeyProvider
    Long dicID) {
        try {
            sqlSession.delete("dic.DELETE_DICTIONARY", dicID);
        }
        catch (Exception e) {
            throw new DaoException("DIC_DELETE_ERROR", "删除字典信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    @UpdateSingleCache(namespace = namespace, expiration = expiration)
    public void updateDictionary(@ParameterValueKeyProvider
    @ParameterDataUpdateContent
    Dictionary dictionary) {
        try {
            dictionary.setUpdateTime(new Date());
            sqlSession.update("dic.UPDATE_DICTIONARY", dictionary);
        }
        catch (Exception e) {
            throw new DaoException("DIC_UPDATE_ERROR", "更新字典信息异常", e);
        }
    }
    
    public List<Dictionary> findDictionary() {
        try {
            return sqlSession.selectList("dic.FIND_ALL_DICTIONARY");
        }
        catch (Exception e) {
            throw new DaoException("DIC_FIND_ERROR", "查询字典信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<Dictionary> findDictionary(DictionaryDTO dicDTO) {
        try {
            return findBypagination("dic.FIND_DICTIONARY_BY_DTO", dicDTO);
        }
        catch (Exception e) {
            throw new DaoException("DIC_FIND_ERROR", "查询字典信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public Dictionary findDictionary(Long dicID) {
        try {
            return (Dictionary)sqlSession.selectOne("dic.FIND_DICTIONARY_BY_ID", dicID);
        }
        catch (Exception e) {
            throw new DaoException("DIC_FIND_ERROR", "查询字典信息异常", e);
        }
    }
    
}
