/*
* 文 件 名:  OrganService.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  组织机构服务
* 修 改 人:  zhouliang
* 修改时间:  2014年1月8日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.List;

import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.OrganDTO;
import org.yy.user.model.Organ;
import org.yy.user.model.User;


/**
* 组织机构服务
* 
* @author  zhouliang
* @version  [1.0, 2014年1月8日]
* @since  [app-user/1.0]
*/
public interface OrganService {
    
    /**
     * 查找组织机构列表
     * @param organDTO  查询条件
     * @return 组织机构列表
     * @throws ServiceException
     */
    public ResultDto<Organ> findOrgan(OrganDTO organDTO)
        throws ServiceException;
    
    /**
     * 查找组织机构
     * @param organCode  组织机构编码
     * @return 组织机构
     * @throws ServiceException
     */
    public Organ findOrgan(String organCode)
        throws ServiceException;
    
    
    /**
     * 保存组织机构
     * @param organ  组织机构
     * @return 组织机构
     * @throws ServiceException
     */
    public Organ insertOrgan(Organ organ)
            throws ServiceException ; 
    
    /**
     * 更新组织机构
     * @param organ  组织机构
     * @return 组织机构
     * @throws ServiceException
     */
    public void updateOrgan( Organ organ) throws ServiceException;

    /**
     * 检测组织机构是否存在
     * @param organ  组织机构编码
     * @return true 表示存在
     * @throws ServiceException
     */
    public boolean exists(String organCode)throws ServiceException;
    
    /**
     * 查找组织机构列表
     * @param user  用户
     * @throws ServiceException
     */
    public List<Organ> findOrgan(User user)
            throws ServiceException;
}   
