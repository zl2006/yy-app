/*
* 文 件 名:  SystemDao.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  系统访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月23日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao;

import java.util.List;

import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.SystemDTO;
import org.yy.user.model.System;
import org.yy.user.model.User;

/**
* 系统访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月23日]
* @since  [app-user/1.0]
*/
public interface SystemDao {
    
    /**
     * 保存系统
     * @param system 系统
     * @return 系统
     */
    public org.yy.user.model.System insertSystem(org.yy.user.model.System system);
    
    /**
     * 更新系统
     * @param system 系统
     */
    public void updateSystem(org.yy.user.model.System system);
    
    /**
     * 根据编码获取系统
     * 
     * @param systemCode 编码
     * @return 系统
     */
    public org.yy.user.model.System findSystem(String systemCode);
    
    /**
     * 根据条件查询系统
     * @param systemDTO 查询条件
     * @return 系统
     */
    public ResultDto<org.yy.user.model.System> findSystem(SystemDTO systemDTO);
    
    /**
     * 获取所有系统, 过滤无效系统
     * @return 系统
     */
    public List<System> findSystem();
    
    /*************************************************************************************************************************/
    /**
     * 获取用户所有系统, 过滤无效系统
     * @param user 用户, 只需要用户ID
     * @return 系统
     */
    public List<System> findSystem(User user);
}
