/*
* 文 件 名:  LogDaoImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  日志访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月23日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao.impl;

import java.sql.SQLException;
import java.util.Date;

import org.yy.framework.base.dao.AbstractMyBatisDao;
import org.yy.framework.basedata.exception.DaoException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.LogDao;
import org.yy.user.dto.LogDTO;
import org.yy.user.model.Log;

/**
* 日志访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月23日]
* @since  [app-user/1.0]
*/
public class LogDaoImpl extends AbstractMyBatisDao implements LogDao {
    
    /** {@inheritDoc} */
    @Override
    public Log insertLog(Log log) {
        try {
            log.setCreateTime(new Date());
            log.setUpdateTime(new Date());
            sqlSession.insert("log.INSERT_LOG", log);
        }
        catch (Exception e) {
            throw new DaoException("LOG_ADD_ERROR", "新增日志信息异常", e);
        }
        return log;
    }
    
    /** {@inheritDoc} */
    @Override
    public Log findLog(Long logID) {
        try {
            return (Log)sqlSession.selectOne("log.FIND_LOG_BY_ID", logID);
        }
        catch (Exception e) {
            throw new DaoException("LOG_FIND_ERROR", "查询日志信息异常", e);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<Log> findLog(LogDTO logDTO) {
        try {
            return findBypagination("log.FIND_LOG_BY_DTO", logDTO);
        }
        catch (SQLException e) {
            throw new DaoException("LOG_FIND_ERROR", "查询日志信息异常", e);
        }
    }
    
}
