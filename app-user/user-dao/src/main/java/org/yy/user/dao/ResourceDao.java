/*
* 文 件 名:  ResourceDao.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  资源访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月23日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao;

import java.util.List;
import java.util.Map;

import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.ResourceDTO;
import org.yy.user.model.Resource;
import org.yy.user.model.Role;
import org.yy.user.model.User;

/**
* 资源访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月23日]
* @since  [app-user/1.0]
*/
public interface ResourceDao {
    
    /**
     * 保存资源
     * @param resource 资源
     * @return 资源
     */
    public Resource insertResource(Resource resource);
    
    /**
     * 更新资源
     * @param resource 资源
     */
    public void updateResource(Resource resource);
    
    /**
     * 根据主键标识获取资源
     * 
     * @param resID 主键标
     * @return 资源
     */
    public Resource findResource(Long resID);
    
    /**
     * 根据条件查询资源
     * @param resourceDTO 查询条件
     * @return
     */
    public ResultDto<Resource> findResource(ResourceDTO resourceDTO);
    
    /********************************************************************************************/
    /**
     * 查询角色的资源
     * @param role 角色,,只需要roleCode
     * @return 资源
     */
    public List<Resource> findResource(Role role);
    
    /**
     * 插入角色的指定权限关系
     * @param roleCode 角色编码
     * @param resID 资源ID
     */
    public void insertRoleResRel(String roleCode, Long resID);
    
    /**
     * 删除角色的指定权限关系
     * @param roleCode 角色编码
     * @param resID 资源ID
     */
    public void deleteRoleResRel(String roleCode, Long resID);
    
    /**
     * 删除角色所有的权限
     * @param roleCode 角色编码
     */
    public void deleteRoleResRel(String roleCode);
    
    /********************************************************************************************/
    /**
     * 查询系统的资源
     * @param system 系统,只需要systemCode
     * @return 资源
     */
    public List<Resource> findResource(org.yy.user.model.System system);
    
    /**
     * 获取用户的系统资源
     * @param system 系统,只需要systemCode
     * @param user  用户,只需要loginID
     * @return 资源
     */
    public List<Resource> findResource(org.yy.user.model.System system, User user);
    
    /**
     * 获取用户的系统资源
     * @param system 系统,只需要systemCode
     * @param user  用户,只需要loginID
     * @return 资源
     */
    public Map<String,Resource> findResourceForMap(org.yy.user.model.System system, User user);
}
