/*
* 文 件 名:  UserGroupService.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述: 用户组服务
* 修 改 人:  zhouliang
* 修改时间:  2014年1月8日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.List;

import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.UserGroupDTO;
import org.yy.user.model.User;
import org.yy.user.model.UserGroup;

/**
* 用户组服务
* 
* @author  zhouliang
* @version  [1.0, 2014年1月8日]
* @since  [app-user/模块版本]
*/
public interface UserGroupService {
    
    /**
     * 查找用户组列表
     * 
     * @param userGroupDTO 查找条件
     * @return 用户组列表
     */
    public ResultDto<UserGroup> findUserGroup(UserGroupDTO userGroupDTO)
        throws ServiceException;
    
    /**
     * 保存用户组
     * 
     * @param userGroup 用户组信息
     * @throws ServiceException {USERGROUP_VALIDATE_ERROR:用户验证错误}
     */
    public void insertUserGroup(UserGroup userGroup)
        throws ServiceException;
    
    /**
     * 查找用户组
     * 
     * @param userGroupID 用户组ID
     * @return 用户组 
     * @throws ServiceException {USERGROUP_USERGROUPID_EMPTY:用户组ID为空}
     */
    public UserGroup findUserGroup(Long userGroupID)
        throws ServiceException;
    
    /**
     * 更新用户组
     *
     * @param userGroup 用户组信息
     * @throws ServiceException {USERGROUP_VALIDATE_ERROR:用户验证错误}
     */
    public void updateUserGroup(UserGroup userGroup)
        throws ServiceException;
    
    
    /**
     * 查询用户的用户组信息
     * @param user 用户 
     * @return 用户组
     * @throws ServiceException {USERGROUP_USER_EMPTY:用户为空}
     */
    public List<UserGroup> findUserGroup(User user)throws ServiceException;
    
    
    /**
     * 配置用户组的角色
     * 
     * @param userGroupID 用户组ＩＤ
     * @param roleCodes 用户组角色列表
     */
    public void config(Long userGroupID, List<String> roleCodes)throws ServiceException;
}
