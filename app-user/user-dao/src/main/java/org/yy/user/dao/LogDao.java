/*
* 文 件 名:  LogDao.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  日志访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月23日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao;

import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.LogDTO;
import org.yy.user.model.Log;

/**
* 日志访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月23日]
* @since  [app-user/1.0]
*/
public interface LogDao {
    
    /**
     * 保存日志
     * @param log 日志
     * @return 日志
     */
    public Log insertLog(Log log);
    
    /**
     * 根据主键标识获取日志
     * 
     * @param log 主键标
     * @return 日志
     */
    public Log findLog(Long logID);
    
    /**
     * 根据条件查询日志
     * @param logDTO 查询条件
     * @return 日志
     */
    public ResultDto<Log> findLog(LogDTO logDTO);
}
