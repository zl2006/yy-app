/*
* 文 件 名:  UserGroupDao.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  用户组DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月24日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao;

import java.util.List;

import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.UserGroupDTO;
import org.yy.user.model.User;
import org.yy.user.model.UserGroup;

/**
* 用户组DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月24日]
* @since  [app-user/1.0]
*/
public interface UserGroupDao {
    
    /**
     * 保存用户组
     * @param userGroup 用户组
     * @return 用户组
     */
    public UserGroup insertUserGroup(UserGroup userGroup);
    
    /**
     * 更新用户组
     * @param userGroup 用户组
     */
    public void updateUserGroup(UserGroup userGroup);
    
    /**
     * 根据主键标识获取用户组
     * 
     * @param userGroupID 主键标
     * @return 用户组
     */
    public UserGroup findUserGroup(Long userGroupID);
    
    /**
     * 根据条件查询用户组
     * @param userGroupDTO 查询条件
     * @return
     */
    public ResultDto<UserGroup> findUserGroup(UserGroupDTO userGroupDTO);
    
    /*************************************************************************************************************************/
    /**
     * 查询用户的用户组信息
     * @param loginID 登录ID 
     * @return 用户组
     */
    public List<UserGroup> findUserGroup(User user);
    
    /**
     * 插入用户的用户组关系
     * @param loginID 登录ID
     * @param userGroupID 用户组ID
     */
    public void insertUserGroupRef(String loginID, Long userGroupID);
    
    /**
     * 删除用户的指定用户组关系
     * @param loginID 登录ID
     * @param userGroupID 用户组ID
     */
    public void deleteUserGroupRef(String loginID, Long userGroupID);
    
    /**
     * 删除用户的所有用户组关系
     * @param loginID 登录ID
     */
    public void deleteUserGroupRef(String loginID);
}
