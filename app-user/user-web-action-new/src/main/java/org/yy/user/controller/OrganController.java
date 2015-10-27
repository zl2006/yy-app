/*
* 文 件 名:  OrganController.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  组织机构控制器
* 修 改 人:  zhouliang
* 修改时间:  2014年1月8日
* 修改内容:  <修改内容>
*/
package org.yy.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.Pagination;
import org.yy.framework.document.anno.Method;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.Param;
import org.yy.framework.document.anno.Return;
import org.yy.framework.document.anno.Service;
import org.yy.framework.document.anno.Exception;
import org.yy.user.dto.OrganDTO;
import org.yy.user.model.Organ;
import org.yy.user.service.OrganService;

import static org.yy.framework.basedata.Constants.*;

/**
* 组织机构控制器
* 
* @author  zhouliang
* @version  [1.0, 2014年1月8日]
* @since  [app-user/1.0]
*/
@Controller
@RequestMapping(value = "organ")
@Service(name = "组织机构服务", desc = "组织机构新增/删除/维护等操作", role = "管理员", models = {@Model(name = "organ", desc = "组织机构实体", clazz = Organ.class)})
public class OrganController extends AbsUserController {
    
    @Resource(name = "organService")
    private OrganService organService;
    
    @RequestMapping(value = "list")
    @Method(name = "查询组织机构", desc = "查询组织机构，当有组织机构名称或编码条件时根据条件查询所有范围，否则只查询顶层组织机构范围", returns = {@Return(name = "组织机构", clazz = Organ.class)})
    public ModelAndView list(@Param
    OrganDTO organDTO)
        throws ServiceException {
        
        //当有参数条件时，使用参数条件，否则查顶层列表
        if (StringUtils.isEmpty(organDTO.getName()) && StringUtils.isEmpty(organDTO.getOrganCode())
            && StringUtils.isEmpty(organDTO.getParentOrganCode())) {
            organDTO.setParentOrganCode("-1");
        }
        return processSuccess(moduleName + LIST_PAGE, organService.findOrgan(organDTO), organDTO);
    }
    
    @RequestMapping(value = "listChild")
    @Method(name = "获取下级组织机构", desc = "根据组织机构编码获取下级组织机构列表信息", returns = {@Return(name = "组织机构", clazz = Organ.class)})
    public ModelAndView listChild(@Param({"parentOrganCode"})
    OrganDTO organDTO)
        throws ServiceException {
        Pagination pagination = new Pagination();
        pagination.setPageSize(10000);
        organDTO.setPagination(pagination);
        return processSuccess(moduleName + LIST_PAGE, organService.findOrgan(organDTO), organDTO);
    }
    
    @RequestMapping(value = "save", method = {RequestMethod.GET, RequestMethod.POST})
    @Method(name = "保存组织机构", desc = "保存组织机构", exceps = {@Exception(code = "001001010", message = "用户登录ID重复"),
        @Exception(code = "001001008", message = "用户数据验证失败")})
    public ModelAndView saveOrgan(@Param
    Organ organ, HttpServletRequest request) {
        Long userID = getCurrentUserID(request);
        organ.setCreatePerson(userID);
        organ.setUpdatePerson(userID);
        try {
            organService.insertOrgan(organ);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE, moduleName + ADD_PAGE, organ, "insert organ failure!", e.getMessage());
        }
        return processSuccess(moduleName + LIST_PAGE, organ);
    }
    
    @RequestMapping(value = "preedit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView preEdit(@RequestParam("organCode")
    String organCode)
        throws ServiceException {
        Organ organ = organService.findOrgan(organCode);
        Organ parentOrgan = null;
        if (organ != null && organ.getParentOrganCode() != null && !"-1".equals(organ.getParentOrganCode())) {
            parentOrgan = organService.findOrgan(organ.getParentOrganCode());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("organ", organ);
        result.put("parentOrgan", parentOrgan);
        return processSuccess(moduleName + EDIT_PAGE, result);
    }
    
    @RequestMapping(value = "view", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView view(@RequestParam("organCode")
    String organCode)
        throws ServiceException {
        Organ organ = organService.findOrgan(organCode);
        Organ parentOrgan = null;
        if (organ != null && organ.getParentOrganCode() != null && !"-1".equals(organ.getParentOrganCode())) {
            parentOrgan = organService.findOrgan(organ.getParentOrganCode());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("organ", organ);
        result.put("parentOrgan", parentOrgan);
        return processSuccess(moduleName + VIEW_PAGE, result);
    }
    
    @RequestMapping(value = "edit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView edit(@Param
    Organ organ, HttpServletRequest request)
        throws ServiceException {
        
        Long userID = getCurrentUserID(request);
        organ.setUpdatePerson(userID);
        try {
            organService.updateOrgan(organ);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                moduleName + EDIT_PAGE,
                organ,
                "update organ failure!",
                e.getMessage());
        }
        return processSuccess(moduleName + LIST_PAGE, organ);
    }
    
    @RequestMapping(value = "enable", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView enable(@RequestParam("organCode")
    String organCode, HttpServletRequest request)
        throws ServiceException {
        Organ organ = organService.findOrgan(organCode);
        Long userID = getCurrentUserID(request);
        organ.setUpdatePerson(userID);
        organ.setStatus(1);
        try {
            organService.updateOrgan(organ);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                moduleName + LIST_PAGE,
                organ,
                "update organ status failure!",
                e.getMessage());
        }
        return processSuccess(moduleName + LIST_PAGE, organ);
    }
    
    @RequestMapping(value = "disable", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView disable(@RequestParam("organCode")
    String organCode, HttpServletRequest request)
        throws ServiceException {
        Organ organ = organService.findOrgan(organCode);
        Long userID = getCurrentUserID(request);
        organ.setUpdatePerson(userID);
        organ.setStatus(0);
        try {
            organService.updateOrgan(organ);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                moduleName + LIST_PAGE,
                organ,
                "update organ status failure!",
                e.getMessage());
        }
        return processSuccess(moduleName + LIST_PAGE, organ);
    }
    
    /** {@inheritDoc} */
    @Override
    protected void setModuleName() {
        this.moduleName = "module/organ/";
    }
    
}
