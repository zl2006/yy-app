/*
* 文 件 名:  UserDao.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  用户访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月21日
* 修改内容:  
*/
package org.yy.user.dao;

import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.UserDTO;
import org.yy.user.model.Organ;
import org.yy.user.model.User;

/**
* 用户访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月21日]
* @since  [app-user/1.0]
*/
public interface UserDao {
    
    /**
     * 保存用户信息
     * @param user 用户信息
     * @return 用户信息
     */
    public User insertUser(User user);
    
    /**
     * 更新用户信息
     * @param user 用户信息
     */
    public int updateUser(User user);
    
    /**
     * 根据登录id获取用户信息
     * 
     * @param loginID 登录id
     * @return 用户信息
     */
    public User findUser(String loginID);
    
    /**
     * 根据条件查询用户信息
     * @param userDTO 查询条件
     * @return
     */
    public ResultDto<User> findUser(UserDTO userDTO);
    
    /**
     *  根据组织机构查询用户
     * @param organ 组织机构
     * @return
     */
    public ResultDto<User> findUser(Organ organ);
    
}
