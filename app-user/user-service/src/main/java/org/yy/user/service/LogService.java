 /*
 * 文 件 名:  LogService.java
 * 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
 * 描    述:  日志服务
 * 修 改 人:  zhouliang
 * 修改时间:  2014年3月16日
 * 修改内容:  <修改内容>
 */
package org.yy.user.service;

import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.LogDTO;
import org.yy.user.model.Log;

 /**
 * 日志服务接口
 * 
 * @author  zhouliang
 * @version  [1.0, 2014年3月16日]
 * @since  ［app-user/1.0]
 */
public interface LogService {
    
    /**
     * 
     *  查询日志列表
     *  
     * @param logDTO 查询条件
     * @return 日志列表
     * @throws ServiceException
     */
    ResultDto<Log> findLog(LogDTO logDTO)throws ServiceException;
    
    /**
     * 查询日志详情

     * @param logID 日志ID
     * @return 日志
     * @throws ServiceException
     */
    Log findLog(Long logID)throws ServiceException;
}
