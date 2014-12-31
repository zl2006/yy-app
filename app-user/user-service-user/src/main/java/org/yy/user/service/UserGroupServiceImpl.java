/*
* 文 件 名:  UserGroupServiceImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  用户组服务
* 修 改 人:  zhouliang
* 修改时间:  2014年1月16日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.ArrayList;
import java.util.List;

import org.yy.framework.base.validator.ValidateError;
import org.yy.framework.base.validator.ValidateUtil;
import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.RoleDao;
import org.yy.user.dao.UserGroupDao;
import org.yy.user.dto.UserGroupDTO;
import org.yy.user.model.Role;
import org.yy.user.model.User;
import org.yy.user.model.UserGroup;

/**
* 用户组服务
* 
* @author  zhouliang
* @version  [1.0, 2014年1月16日]
* @since  [app-user/1.0]
*/
public class UserGroupServiceImpl implements UserGroupService {
    
    private UserGroupDao userGroupDao;
    
    private RoleDao roleDao;
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<UserGroup> findUserGroup(UserGroupDTO userGroupDTO)
        throws ServiceException {
        return userGroupDao.findUserGroup(userGroupDTO);
    }
    
    @Override
	public void insertUserGroup(UserGroup userGroup) throws ServiceException {
    	//数据验证
        List<ValidateError> errors = ValidateUtil.validate(userGroup);
        if (errors.size() > 0) {
            throw new ServiceException("USERGROUP_VALIDATE_ERROR", errors.toString());
        }
    	userGroupDao.insertUserGroup(userGroup);
	}
    
    /**
    * @param 对userGroupDao进行赋值
    */
    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }
    
    

     public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /** {@inheritDoc} */
    @Override
    public UserGroup findUserGroup(Long userGroupID)
        throws ServiceException {
        if( userGroupID == null){
            throw new ServiceException("USERGROUP_USERGROUPID_EMPTY","用户组ID为空");
        }
        return userGroupDao.findUserGroup(userGroupID);
    }

     /** {@inheritDoc} */
    @Override
    public void updateUserGroup(UserGroup userGroup)
        throws ServiceException {
        List<ValidateError> errors = ValidateUtil.validate(userGroup);
        if (errors.size() > 0) {
            throw new ServiceException("USERGROUP_VALIDATE_ERROR", errors.toString());
        }
        userGroupDao.updateUserGroup(userGroup);
    }

     /** {@inheritDoc} */
    @Override
    public List<UserGroup> findUserGroup(User user) throws ServiceException{
        if( user == null){
            throw new ServiceException("USERGROUP_USER_EMPTY","用户为空");
        }
        return userGroupDao.findUserGroup(user);
    }

     /** {@inheritDoc} */
    @Override
    public void config(Long userGroupID, List<String> roleCodes)
        throws ServiceException {
        
        UserGroup group = null;
        //1, 获取用户信息及关联用户组和角色
        List<Role> originRoles = null;
        
        group = userGroupDao.findUserGroup(userGroupID);
        if(group != null){
            originRoles = roleDao.findRole(group);
        }

        
        
        //2,获取删除的角色,并进行删除
        List<String> deleteRoleCodes = new ArrayList<String>();

        if( originRoles != null && originRoles.size() > 0){
            for( Role role : originRoles){
                if( !roleCodes.contains(role.getRoleCode())){
                    deleteRoleCodes.add(role.getRoleCode());
                }
            }
        }
        
        if( deleteRoleCodes.size() > 0){
            for( String roleCode : deleteRoleCodes){
                roleDao.deleteGroupRoleRef(roleCode, userGroupID);
            }
        }
        
        
        //3,获取新增的角色,并进行新增
        List<String> addRoleCodes = new ArrayList<String>();
        boolean addFlag = true;

        
        if( roleCodes != null && roleCodes.size() >0 ){
            for( String roleCode : roleCodes){
                addFlag = true;
                for( Role role : originRoles ){
                    if( role.getRoleCode().equals(roleCode) ){
                        addFlag = false;
                        break;
                    }
                }
                if( addFlag ){
                    addRoleCodes.add(roleCode);
                }
            }
        }

        if( addRoleCodes.size() > 0){
            for( String roleCode : addRoleCodes){
                roleDao.insertGroupRoleRef(roleCode, userGroupID);
            }
        }
        
    }
    
}
