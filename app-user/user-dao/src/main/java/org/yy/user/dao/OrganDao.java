/*
* 文 件 名:  OrganDao.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  组织机构访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月21日
* 修改内容:  
*/
package org.yy.user.dao;

import java.util.List;

import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.OrganDTO;
import org.yy.user.model.Organ;
import org.yy.user.model.User;

/**
* 组织机构访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月21日]
* @since  [app-user/1.0]
*/
public interface OrganDao {
    
    /**
     * 保存组组织机构
     * @param organ 组织机构
     * @return 组织机构
     */
    public Organ insertOrgan(Organ organ);
    
    /**
     * 更新组织机构
     * @param organ 组织机构
     */
    public int updateOrgan(Organ organ);
    
    /**
     * 根据机构编码获取组织机构
     * 
     * @param organCode 机构编码
     * @return 组织机构
     */
    public Organ findOrgan(String organCode);
    
    /**
     * 根据条件查询组织机构
     * @param organDTO 查询条件
     * @return
     */
    public ResultDto<Organ> findOrgan(OrganDTO organDTO);
    
    /***********************************************************************************************/
    /**
     * 查询用户的组织机构信息
     * @param user 用户
     * @return 组织机构
     */
    public List<Organ> findOrgan(User user);
    
    /**
     * 插入用户的组织机构关系
     * @param loginID 登录ID
     * @param organCode 组织机构编码
     */
    public void insertUserOrganRef(String loginID, String organCode);
    
    /**
     * 删除用户的指定组织机构关系
     * @param loginID 登录ID
     * @param organCode 组织机构编码
     */
    public void deleteUserOrganRef(String loginID, String organCode);
    
    /**
     * 删除用户的所有组织机构关系
     * @param loginID 登录ID
     */
    public void deleteUserOrganRef(String loginID);
}
