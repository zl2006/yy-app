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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.yy.framework.base.validator.ValidateError;
import org.yy.framework.base.validator.ValidateService;
import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.SystemDao;
import org.yy.user.dto.SystemDTO;
import org.yy.user.model.System;
import org.yy.user.model.User;

/**
* 系统资源服务
* 
* @author  zhouliang
* @version  [1.0, 2013年12月4日]
* @since  [app-user/1.0]
*/
@Service("systemService")
public class SystemServiceImpl implements SystemService {
    
    @Resource(name="systemDAO")
    private SystemDao systemDao;
    
    /** {@inheritDoc} */
    @Override
    public List<System> findSystem(String loginID) {
        User user = new User(); 
        user.setLoginID(loginID);
        return systemDao.findSystem(user);
    }
    
    /**
    * @param 对systemDao进行赋值
    */
    public void setSystemDao(SystemDao systemDao) {
        this.systemDao = systemDao;
    }
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<System> findSystem(SystemDTO systemDTO)
        throws ServiceException {
        return systemDao.findSystem(systemDTO);
    }

    /** {@inheritDoc} */
	@Override
	public void insertSystem(System system) throws ServiceException {
		//数据验证
        List<ValidateError> errors = ValidateService.validate(system);
        if (errors.size() > 0) {
            throw new ServiceException("SYSTEM_VLIDA_ERROR", errors.toString());
        }
        
        systemDao.insertSystem(system);
	}

	/** {@inheritDoc} */
	@Override
	public System findSystemByCode(String systemCode) throws ServiceException {
		return systemDao.findSystem(systemCode);
	}

	@Override
	public void updateSystem(System system) throws ServiceException {
		systemDao.updateSystem(system);
	}
    
}
