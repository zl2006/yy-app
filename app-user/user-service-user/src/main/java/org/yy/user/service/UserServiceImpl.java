/*
* 文 件 名:  UserServiceImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  用户访问服务
* 修 改 人:  zhouliang
* 修改时间:  2013年12月1日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.yy.framework.base.validator.ValidateError;
import org.yy.framework.base.validator.ValidateService;
import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.OrganDao;
import org.yy.user.dao.RoleDao;
import org.yy.user.dao.UserDao;
import org.yy.user.dao.UserGroupDao;
import org.yy.user.dto.UserDTO;
import org.yy.user.model.Organ;
import org.yy.user.model.Role;
import org.yy.user.model.User;
import org.yy.user.model.UserGroup;

/**
* 用户访问服务
* 
* @author  zhouliang
* @version  [1.0, 2013年12月1日]
* @since  [app-user/1.0]
*/
@Service("userService")
public class UserServiceImpl implements UserService {
    
    @Resource(name="userDAO")
    protected UserDao userDao;
    
    @Resource(name="organDAO")
    protected OrganDao organDao;
    
    @Resource(name="userGroupDAO")
    protected UserGroupDao userGroupDao;
    
    @Resource(name="roleDAO")
    protected RoleDao roleDao;
    
    /** {@inheritDoc} */
    @Override
    public User insertUser(User user)
        throws ServiceException {
        
        
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        
        //1,校验用户信息
        List<ValidateError> errors = ValidateService.validate(user);
        if (errors.size() > 0) {
            throw new ServiceException("USER_VALIDATE_ERROR", errors.toString());
        }
        
        if (exists(user.getLoginID())) {
            throw new ServiceException("USER_LOGINID_REPEAT", "用户登录ID重复");
        }
        
        //2,插入用户信息
        user.setPassword( DigestUtils.sha1Hex(user.getPassword()) );
        userDao.insertUser(user);
        
        //3.插入用户与组织机构的关系
        if(user.getOrgans() != null && user.getOrgans().size() >0){
            for( Organ organ : user.getOrgans() ){
                if( StringUtils.isNotEmpty( organ.getOrganCode() )){
                    organDao.insertUserOrganRef(user.getLoginID(), organ.getOrganCode());
                }
            }
        }
        return user;
        
    }
    
    /** {@inheritDoc} */
    @Override
    public void updateUser(User user)
        throws ServiceException {
        //1,校验用户信息
        List<ValidateError> errors = ValidateService.validate(user,"userID","loginID","name");
        if (errors.size() > 0) {
        	 throw new ServiceException("USER_VLIDA_ERROR", errors.toString());
        }
        
        User userTemp = findUser(user.getLoginID());
        if (userTemp != null && !userTemp.getUserID().equals(user.getUserID())) {
        	throw new ServiceException("USER_LOGINID_REPEAT", "用户登录ID重复");
        }
        
        //2,更新用户信息
        userDao.updateUser(user);
        
        //3,更新用户与组织机构的关系
        if(user.getOrgans() != null && user.getOrgans().size() >0){
        	organDao.deleteUserOrganRef(user.getLoginID());
        	for( Organ organ : user.getOrgans() ){
        		organDao.insertUserOrganRef(user.getLoginID(), organ.getOrganCode());
        	}
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public User findUser(String loginID)
        throws ServiceException {
        if (StringUtils.isEmpty(loginID)) {
            throw new ServiceException("USER_LOGINID_EMPTY", "用户登录ID为空");
        }
        User user = userDao.findUser(loginID);
        return user;
    }
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<User> findUser(UserDTO userDTO)
        throws ServiceException {
        return userDao.findUser(userDTO);
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean exists(String loginID)
        throws ServiceException {
        if (StringUtils.isEmpty(loginID)) {
            throw new ServiceException("USER_LOGINID_EMPTY", "用户登录ID为空");
        }
        User user = userDao.findUser(loginID);
        return user != null;
    }
    
    /** {@inheritDoc} */
    @Override
    public void config(String loginID,  List<Long> groupIDs, List<String> roleCodes)
        throws ServiceException {
        //1, 获取用户信息及关联用户组和角色
        User user = userDao.findUser(loginID) ;
        List<UserGroup>  originGroups = null;
        List<Role> originRoles = null;
        
        if(user != null){
            originGroups = userGroupDao.findUserGroup(user);
            originRoles = roleDao.findRole(user);
        }
        if(groupIDs == null){
            groupIDs = new ArrayList<Long>();
        }
        if(roleCodes == null){
            roleCodes = new ArrayList<String>();
        }
        
        
        //2,获取删除的用户组和角色,并进行删除
        List<Long> deleteGroupIDs = new ArrayList<Long>();
        List<String> deleteRoleCodes = new ArrayList<String>();
        if( originGroups != null && originGroups.size() > 0){
            for( UserGroup group : originGroups){
                if( !groupIDs.contains(group.getUserGroupID())){
                    deleteGroupIDs.add(group.getUserGroupID());
                }
            }
        }
        
        if( originRoles != null && originRoles.size() > 0){
            for( Role role : originRoles){
                if( !roleCodes.contains(role.getRoleCode())){
                    deleteRoleCodes.add(role.getRoleCode());
                }
            }
        }
        
        if(deleteGroupIDs.size() > 0 ){
            for( Long userGroupID : deleteGroupIDs ){
                userGroupDao.deleteUserGroupRef(loginID, userGroupID);
            }
        }
        if( deleteRoleCodes.size() > 0){
            for( String roleCode : deleteRoleCodes){
                roleDao.deleteUserRoleRef(roleCode, loginID);
            }
        }
        
        
        //3,获取新增的用户组和角色,并进行新增
        List<Long> addGroupIDs = new ArrayList<Long>();
        List<String> addRoleCodes = new ArrayList<String>();
        boolean addFlag = true;
        if( groupIDs != null && groupIDs.size() > 0){
            for( Long groupID : groupIDs){
                addFlag = true;
                for( UserGroup group : originGroups ){
                    if( group.getUserGroupID().equals( groupID ) ){
                        addFlag = false;
                        break;
                    }
                }
                if( addFlag ){
                    addGroupIDs.add(groupID);
                }
            }
        }
        
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
        
        if( addGroupIDs.size() > 0){
            for(Long groupID : addGroupIDs){
                userGroupDao.insertUserGroupRef(loginID, groupID);
            }
        }
        if( addRoleCodes.size() > 0){
            for( String roleCode : addRoleCodes){
                roleDao.insertUserRoleRef(roleCode, loginID);
            }
        }
        
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

	public void setOrganDao(OrganDao organDao) {
		this.organDao = organDao;
	}

    public void setUserGroupDao(UserGroupDao userGroupDao) {
        this.userGroupDao = userGroupDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    
}
