 /*
 * 文 件 名:  LogServiceImpl.java
 * 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
 * 描    述:  日志服务
 * 修 改 人:  zhouliang
 * 修改时间:  2014年3月16日
 * 修改内容:  <修改内容>
 */
package org.yy.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.LogDao;
import org.yy.user.dto.LogDTO;
import org.yy.user.model.Log;

 /**
 * 日志服务
 * 
 * @author  zhouliang
 * @version  [1.0, 2014年3月16日]
 * @since  [app-user/1.0]
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    
    @Resource(name="logDAO")
    private LogDao logDao;
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<Log> findLog(LogDTO logDTO)
        throws ServiceException {
        return logDao.findLog(logDTO);
    }

    public void setLogDao(LogDao logDao) {
        this.logDao = logDao;
    }

     /** {@inheritDoc} */
    @Override
    public Log findLog(Long logID)
        throws ServiceException {
        return logDao.findLog(logID);
    }
    
    
}
