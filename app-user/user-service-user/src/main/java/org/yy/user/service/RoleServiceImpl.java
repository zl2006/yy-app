/*
* 文 件 名:  RoleServiceImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  角色服务
* 修 改 人:  zhouliang
* 修改时间:  2014年1月8日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.ArrayList;
import java.util.List;

import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.ResourceDao;
import org.yy.user.dao.RoleDao;
import org.yy.user.dto.RoleDTO;
import org.yy.user.model.Resource;
import org.yy.user.model.Role;
import org.yy.user.model.User;
import org.yy.user.model.UserGroup;

/**
* 角色服务
* 
* @author  zhouliang
* @version  [1.0, 2014年2月8日]
* @since  [app-user/1.0]
*/
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;
	
	private ResourceDao resourceDao;
	
	@Override
	/** {@inheritDoc} */
	public ResultDto<Role> findRole(RoleDTO roleDTO) throws ServiceException {
		return roleDao.findRole(roleDTO);
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	
     public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    /** {@inheritDoc} */
    @Override
    public List<Role> findRole(User user)
        throws ServiceException {
        if( user == null){  
            throw new ServiceException("ROLE_USER_EMPTY","用户为空");
        }
        return roleDao.findRole(user);
    }

     /** {@inheritDoc} */
    @Override
    public List<Role> findRole(UserGroup userGroup)
        throws ServiceException {
        return roleDao.findRole(userGroup);
    }

     /** {@inheritDoc} */
    @Override
    public void updateRole(Role role) {
        roleDao.updateRole(role);
    }

     /** {@inheritDoc} */
    @Override
    public Role findRole(String roleCode) {
        return roleDao.findRole(roleCode);
    }

     /** {@inheritDoc} */
    @Override
    public void saveRole(Role role)
        throws ServiceException {
        roleDao.insertRole(role);
    }

     /** {@inheritDoc} */
    @Override
    public void config(String roleCode, List<Long> resIDs) {
        Role role = null;
        //1, 获取角色及关联资源
        List<Resource> originResources = null;
        
        role = roleDao.findRole(roleCode);
        if(role != null){
            originResources = resourceDao.findResource(role);
        }

        //2,获取删除的资源,并进行删除
        List<Long> deleteResIDS = new ArrayList<Long>();
        if( originResources != null && originResources.size() > 0){
            for( Resource res : originResources){
                if( !resIDs.contains(res.getResID())){
                     deleteResIDS.add(res.getResID());
                }
            }
        }
        
        if( deleteResIDS.size() > 0){
            for( Long resID : deleteResIDS){
                resourceDao.deleteRoleResRel(roleCode, resID);
            }
        }
        
        
        //3,获取新增的角色,并进行新增
        List<Long> addResIDS = new ArrayList<Long>();
        boolean addFlag = true;

        
        if( resIDs != null && resIDs.size() >0 ){
            for( Long resID : resIDs){
                addFlag = true;
                for( Resource res : originResources ){
                    if( res.getResID().equals(resID) ){
                        addFlag = false;
                        break;
                    }
                }
                if( addFlag ){
                    addResIDS.add(resID);
                }
            }
        }

        if( addResIDS.size() > 0){
            for( Long resID : addResIDS){
                resourceDao.insertRoleResRel(roleCode, resID);
            }
        }
    }
    
}
