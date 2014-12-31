/*
* 文 件 名:  SystemService.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  系统资源服务
* 修 改 人:  zhouliang
* 修改时间:  2013年12月4日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.List;

import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.SystemDTO;
import org.yy.user.model.System;


/**
* 系统资源服务
* 
* @author  zhouliang
* @version  [1.0, 2013年12月4日]
* @since  [app-user/1.0]
*/
public interface SystemService {
    
    /**
     * 获取用户所拥有的系统
     * @param loginID 用户
     * @return 系统
     */
    public List<System> findSystem(String loginID)
        throws ServiceException;
    
    /**
     * 获取系统编号查询系统
     * @param systemCode 系统编号
     * @return 系统
     */
    public System findSystemByCode(String systemCode)
        throws ServiceException;
    
    /**
     * 查找系统列表
     * @param systemDTO  查询条件
     * @return 系统列表
     * @throws ServiceException
     */
    public ResultDto<System> findSystem(SystemDTO systemDTO)
        throws ServiceException;
    
    /**
     * 保存系统
     * @param system  系统
     * @throws ServiceException
     */
    public void insertSystem(org.yy.user.model.System system)throws ServiceException ;
    
    /**
     * 更新系统
     * @param system  系统
     * @throws ServiceException
     */
    void updateSystem(  org.yy.user.model.System system) throws ServiceException ;
}
