/*
* 文 件 名:  ResourceServiceImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  <描述>
* 修 改 人:  zhouliang
* 修改时间:  2013年12月5日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.Pagination;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.ResourceDao;
import org.yy.user.dto.ResourceDTO;
import org.yy.user.model.Resource;
import org.yy.user.model.Role;
import org.yy.user.model.System;
import org.yy.user.model.User;

/**
* 资源服务
* 
* @author  zhouliang
* @version  [1.0, 2013年12月5日]
* @since  [app-user/1.0]
*/
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    
    @javax.annotation.Resource(name="resourceDAO")
    private ResourceDao resourceDao;
    
    /** {@inheritDoc} */
    @Override
    public List<Resource> findResource(String systemCode, String loginID)
        throws ServiceException {
        if (StringUtils.isEmpty(systemCode)) {
            throw new ServiceException("001005003", "系统编码为空");
        }
        
        if (StringUtils.isEmpty(loginID)) {
            throw new ServiceException("001005003", "用户登录ID为空");
        }
        System s = new System();
        User u = new User();
        s.setSystemCode(systemCode);
        u.setLoginID(loginID);
        return resourceDao.findResource(s, u);
    }
    
    /**
     * @param 对resourceDao进行赋值
     */
    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }
    
    /** {@inheritDoc} */
    @Override
    public List<Resource> findResource(String systemCode)
        throws ServiceException {
        if (StringUtils.isEmpty(systemCode)) {
            throw new ServiceException("001005003", "系统编码为空");
        }
        System s = new System();
        s.setSystemCode(systemCode);
        return resourceDao.findResource(s);
    }
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<Resource> findResource(ResourceDTO resDTO)
        throws ServiceException {
        return resourceDao.findResource(resDTO);
    }

     /** {@inheritDoc} */
    @Override
    public Resource saveResource(Resource resource)
        throws ServiceException {
        if(resource.getParentResID() != -1){
            Resource parentRes = resourceDao.findResource(resource.getParentResID());
            if( parentRes != null){
                parentRes.setHasChild(1);
                resourceDao.updateResource(parentRes);
            }
        }
        return resourceDao.insertResource(resource);
    }

     /** {@inheritDoc} */
    @Override
    public Resource findResource(Long resID)
        throws ServiceException {
        return resourceDao.findResource(resID);
    }

     /** {@inheritDoc} */
    @Override
    public void updateResource(Resource res)
        throws ServiceException {
        

        Long oldParentResID = resourceDao.findResource(res.getResID()).getParentResID();
        resourceDao.updateResource(res);
        
        //没有更新父编码时返回
        if( oldParentResID.equals(res.getParentResID())){
            return;
        }
        
        //重置标志位
        if( !"-1".equals(oldParentResID)){
            resetHasChild(oldParentResID);
        }
        if( !"-1".equals(res.getParentResID())){
            resetHasChild(res.getParentResID());
        }
    }
    
    
    private void resetHasChild(Long resID)throws ServiceException{
        ResourceDTO resDto = new ResourceDTO();
        Pagination pagination = new Pagination();
        pagination.setPageSize(10000);
        resDto.setPagination(pagination);
        resDto.setParentResID(resID);
        ResultDto<Resource> result = resourceDao.findResource(resDto);
        
        if( result.getResult() != null && result.getResult().size() > 0 ){
            Resource res = resourceDao.findResource(resID);
            if( res != null && ( res.getHasChild() == null || 0 == res.getHasChild())  ){
                res.setHasChild(1);
                resourceDao.updateResource(res);
            }
        }else{
            Resource res = resourceDao.findResource(resID);
            if( res != null && (res.getHasChild() == null || 1 == res.getHasChild()) ){
                res.setHasChild(0);
                resourceDao.updateResource(res);
            }
        }
    }

     /** {@inheritDoc} */
    @Override
    public List<Resource> findResource(Role role)
        throws ServiceException {
        return resourceDao.findResource(role);
    }
    
    
}
