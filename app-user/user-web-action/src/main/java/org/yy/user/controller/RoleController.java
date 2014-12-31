/*
* 文 件 名:  RoleController.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  角色控制器
* 修 改 人:  zhouliang
* 修改时间:  2014年1月8日
* 修改内容:  <修改内容>
*/
package org.yy.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.yy.framework.document.anno.Method;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.Param;
import org.yy.framework.document.anno.Return;
import org.yy.framework.document.anno.Service;
import org.yy.user.dto.RoleDTO;
import org.yy.user.model.Role;
import org.yy.user.service.ResourceService;
import org.yy.user.service.RoleService;
import org.yy.user.service.SystemService;

import static org.yy.framework.basedata.Constants.*;

/**
* 角色控制器
* 
* @author  zhouliang
* @version  [1.0, 2014年1月8日]
* @since  [app-user/1.0]
*/
@Controller
@RequestMapping("role")
@Service(name = "角色服务", desc = "新增、配置、维护等操作", role = "管理员", models = {@Model(name = "role", desc = "角色", clazz = Role.class)})
public class RoleController extends AbsUserController {
    
    @Resource(name = "roleService")
    private RoleService roleService;
    
    @Resource(name = "systemService")
    private SystemService systemService;
    
    @Resource(name = "resourceService")
    private ResourceService resourceService;
    
    @RequestMapping(value = "list")
    @Method(name = "查询角色", desc = "查询角色", returns = {@Return(name = "角色", clazz = Role.class)})
    public ModelAndView list(@Param
    RoleDTO roleDTO)
        throws ServiceException {
        return processSuccess(moduleName + LIST_PAGE, roleService.findRole(roleDTO), roleDTO);
    }
    
    @RequestMapping(value = "save")
    public ModelAndView save(@Param
    Role role, HttpServletRequest request)
        throws ServiceException {
        Long userID = getCurrentUserID(request);
        role.setCreatePerson(userID);
        role.setUpdatePerson(userID);
        try {
            roleService.saveRole(role);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE, moduleName + ADD_PAGE, role, "insert role failure!", e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, role);
    }
    
    @RequestMapping(value = "update")
    public ModelAndView update(@Param
    Role role, HttpServletRequest request)
        throws ServiceException {
        Long userID = getCurrentUserID(request);
        role.setUpdatePerson(userID);
        try {
            roleService.updateRole(role);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE, moduleName + EDIT_PAGE, role, "update role failure!", e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, role);
    }
    
    @RequestMapping(value = "enable", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView enable(@RequestParam("roleCode")
    String roleCode, HttpServletRequest request)
        throws ServiceException {
        
        Role role = roleService.findRole(roleCode);
        Long userID = getCurrentUserID(request);
        role.setUpdatePerson(userID);
        role.setStatus(1);
        try {
            roleService.updateRole(role);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                moduleName + LIST_PAGE,
                role,
                "update role status failure!",
                e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, role);
    }
    
    @RequestMapping(value = "disable", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView disable(@RequestParam("roleCode")
    String roleCode, HttpServletRequest request)
        throws ServiceException {
        Role role = roleService.findRole(roleCode);
        Long userID = getCurrentUserID(request);
        role.setUpdatePerson(userID);
        role.setStatus(0);
        try {
            roleService.updateRole(role);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                moduleName + LIST_PAGE,
                role,
                "update role status failure!",
                e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, role);
    }
    
    @RequestMapping(value = "preedit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView preEdit(@RequestParam("roleCode")
    String roleCode)
        throws ServiceException {
        Role role = roleService.findRole(roleCode);
        org.yy.user.model.System system = null;
        if (role != null && StringUtils.isNotEmpty(role.getSystemCode())) {
            system = systemService.findSystemByCode(role.getSystemCode());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("system", system);
        result.put("role", role);
        return processSuccess(moduleName + EDIT_PAGE, result);
    }
    
    @RequestMapping(value = "view")
    public ModelAndView view(@RequestParam("roleCode")
    String roleCode)
        throws ServiceException {
        
        Role role = roleService.findRole(roleCode);
        org.yy.user.model.System system = null;
        List<org.yy.user.model.Resource> resources = new ArrayList<org.yy.user.model.Resource>();
        if (role != null && StringUtils.isNotEmpty(role.getSystemCode())) {
            system = systemService.findSystemByCode(role.getSystemCode());
            resources = resourceService.findResource(role);
        }
        resourceService.findResource(role);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("system", system);
        result.put("role", role);
        result.put("resources", resources);
        
        return processSuccess(moduleName + VIEW_PAGE, result, roleCode);
    }
    
    @RequestMapping(value = "preconfig")
    public ModelAndView preConfig(@RequestParam("roleCode")
    String roleCode)
        throws ServiceException {
        
        Role role = roleService.findRole(roleCode);
        org.yy.user.model.System system = null;
        List<org.yy.user.model.Resource> resources = new ArrayList<org.yy.user.model.Resource>();
        List<org.yy.user.model.Resource> allResources = new ArrayList<org.yy.user.model.Resource>();
        if (role != null && StringUtils.isNotEmpty(role.getSystemCode())) {
            system = systemService.findSystemByCode(role.getSystemCode());
            resources = resourceService.findResource(role);
            allResources = resourceService.findResource(system.getSystemCode());
        }
        resourceService.findResource(role);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("system", system);
        result.put("role", role);
        result.put("resources", resources);
        result.put("allResources", allResources);
        
        return processSuccess("module/role/config", result, roleCode);
    }
    
    @RequestMapping(value = "config")
    public ModelAndView config(@RequestParam("roleCode")
    String roleCode, @RequestParam("resID")
    List<Long> resIDs)
        throws ServiceException {
        
        roleService.config(roleCode, resIDs);
        
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleCode", roleCode);
        params.put("resIDs", resIDs);
        return processSuccess("module/role/config", "", params);
    }
    
    /** {@inheritDoc} */
    @Override
    protected void setModuleName() {
        this.moduleName = "module/role/";
    }
    
}
