/*
* 文 件 名:  ResourceService.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  资源服务
* 修 改 人:  zhouliang
* 修改时间:  2013年12月5日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.List;

import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.ResourceDTO;
import org.yy.user.model.Resource;
import org.yy.user.model.Role;

/**
* 资源服务
* 
* @author  zhouliang
* @version  [1.0, 2013年12月5日]
* @since  [app-user/1.0]
*/
public interface ResourceService {
    
    /**
     * 获取用户的系统资源
     * @param loginID 用户登录ID
     * @param systemCode 系统编码
     * @return 资源
     */
    public List<Resource> findResource(String systemCode, String loginID)
        throws ServiceException;
    
    /**
     * 获取系统资源
     * @param systemCode 系统编码
     * @return 资源
     */
    public List<Resource> findResource(String systemCode)
        throws ServiceException;
    
    /**
     * 获取资源列表
     * @param resDTO 查询条件 
     * @return 资源列表
     */
    public ResultDto<Resource> findResource(ResourceDTO resDTO)
        throws ServiceException;
    
    
    /**
     * 保存资源
     * @param resource 资源
     * @return 资源
     */
    public Resource saveResource(Resource resource)throws ServiceException;
    
    /**
     * 获取资源
     * 
     * @param resID 资源ＩＤ
     * @return
     * @throws ServiceException
     */
    public Resource findResource(Long resID)throws ServiceException;
    
    /**
     * 更新资源
     * @param res 资源
     * @throws ServiceException
     */
    public void updateResource(Resource res)throws ServiceException;
    
    /**
     * 查询角色的资源
     * 
     * @param role 角色
     * @return
     * @throws ServiceException
     */
    public List<Resource> findResource(Role role) throws ServiceException;
}
