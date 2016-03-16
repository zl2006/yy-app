/*
* 文 件 名:  OrganServiceImpl.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  组织机构服务
* 修 改 人:  zhouliang
* 修改时间:  2014年1月8日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.yy.framework.base.validator.ValidateError;
import org.yy.framework.base.validator.ValidateService;
import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.Pagination;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.OrganDao;
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
@Service("organService")
public class OrganServiceImpl implements OrganService {
    
    @Resource(name="organDAO")
    private OrganDao organDao;
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<Organ> findOrgan(OrganDTO organDTO)
        throws ServiceException {
        return organDao.findOrgan(organDTO);
    }
    
    /** {@inheritDoc} */
    @Override
    public List<Organ> findOrgan(User user)
        throws ServiceException {
        return organDao.findOrgan(user);
    }
    
    /** {@inheritDoc} */
    @Override
    public Organ insertOrgan(Organ organ)
        throws ServiceException {
        
        //数据验证
        List<ValidateError> errors = ValidateService.validate(organ);
        if (errors.size() > 0) {
            throw new ServiceException("ORGAN_VLIDA_ERROR", errors.toString());
        }
        
        //验证组织机构编码是否存在
        if (exists(organ.getOrganCode())) {
            throw new ServiceException("ORGAN_CODE_EXISTS", "组织机构编码已存在");
        }
        
        validateOrgan(organ);
        organDao.insertOrgan(organ);
        
        //增加节点为顶层节点时直接返回
        if ("-1".equals(organ.getParentOrganCode()) || StringUtils.isEmpty(organ.getParentOrganCode())) {
            return organ;
        }
        
        //设置其父节点是否有子节点的标志位
        Organ parentOrgan = organDao.findOrgan(organ.getParentOrganCode());
        if (0 == parentOrgan.getHasChild()) {
            parentOrgan.setHasChild(1);
            organDao.updateOrgan(parentOrgan);
        }
        return organ;
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean exists(String organCode)
        throws ServiceException {
        if (StringUtils.isEmpty(organCode)) {
            throw new ServiceException("ORGAN_CODE_EMPTY", "组织机构编码为空");
        }
        Organ organ = organDao.findOrgan(organCode);
        return organ != null;
    }
    
    /** {@inheritDoc} */
    @Override
    public Organ findOrgan(String organCode)
        throws ServiceException {
        if (StringUtils.isEmpty(organCode)) {
            throw new ServiceException("ORGAN_CODE_EMPTY", "组织机构编码为空");
        }
        return organDao.findOrgan(organCode);
    }
    
    /** {@inheritDoc} */
    @Override
    public void updateOrgan(Organ organ)
        throws ServiceException {
        
        //数据验证
        List<ValidateError> errors = ValidateService.validate(organ);
        if (errors.size() > 0) {
            throw new ServiceException("ORGAN_VLIDA_ERROR", errors.toString());
        }
        
        //验证组织机构编码是否存在
        if (!exists(organ.getOrganCode())) {
            throw new ServiceException("ORGAN_CODE_EXISTS", "组织机构编码不存在");
        }
        
        validateOrgan(organ);
        String oldParentOrgan = organDao.findOrgan(organ.getOrganCode()).getParentOrganCode();
        organDao.updateOrgan(organ);
        
        //没有更新父编码时返回
        if (oldParentOrgan.equals(organ.getParentOrganCode())) {
            return;
        }
        
        //重置标志位
        if (!"-1".equals(oldParentOrgan)) {
            resetHasChild(oldParentOrgan);
        }
        if (!"-1".equals(organ.getParentOrganCode())) {
            resetHasChild(organ.getParentOrganCode());
        }
    }
    
    /**
    * @param 对organDao进行赋值
    */
    public void setOrganDao(OrganDao organDao) {
        this.organDao = organDao;
    }
    
    private void validateOrgan(Organ organ)
        throws ServiceException {
        //当父组织机构为空时，设置层顶级组织机构
        if (StringUtils.isEmpty(organ.getParentOrganCode())) {
            organ.setParentOrganCode("-1");
        }
        
        //组织机构编码与父组织机构编码不能一致
        if (organ.getParentOrganCode().equals(organ.getOrganCode())) {
            throw new ServiceException("ORGAN_CODE_ERROR", "组织机构编码与父组织机构编码不能一致");
        }
        
        //判断父组织机构是否存在
        if (!organ.getParentOrganCode().equals("-1") && !exists(organ.getParentOrganCode())) {
            throw new ServiceException("ORGAN_PARENTCODE_EXISTS", "父组织机构编码不存在");
        }
    }
    
    private void resetHasChild(String organCode)
        throws ServiceException {
        OrganDTO organDTO = new OrganDTO();
        Pagination pagination = new Pagination();
        pagination.setPageSize(10000);
        organDTO.setPagination(pagination);
        organDTO.setParentOrganCode(organCode);
        ResultDto<Organ> result = organDao.findOrgan(organDTO); //获取所有的子节点
        
        if (result.getResult() != null && result.getResult().size() > 0) {
            Organ parentOrgan = organDao.findOrgan(organCode);
            if (parentOrgan != null && 0 == parentOrgan.getHasChild()) {
                parentOrgan.setHasChild(1);
                organDao.updateOrgan(parentOrgan);
            }
        }
        else {
            Organ parentOrgan = organDao.findOrgan(organCode);
            if (parentOrgan != null && 1 == parentOrgan.getHasChild()) {
                parentOrgan.setHasChild(0);
                organDao.updateOrgan(parentOrgan);
            }
        }
    }
    
}
