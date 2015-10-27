/*
* 文 件 名:  SystemController.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  系统操作控制层
* 修 改 人:  zhouliang
* 修改时间:  2013年11月26日
* 修改内容:  <修改内容>
*/
package org.yy.user.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.yy.user.dto.SystemDTO;
import org.yy.user.model.System;
import org.yy.user.service.SystemService;

import static org.yy.framework.basedata.Constants.*;

/**
* 系统服务
* 
* @author  zhouliang
* @version  [1.0, 2013年11月26日]
* @since  [app-user/1.0]
*/
@Controller
@RequestMapping("system")
@Service(name = "系统服务", desc = "新增/删除/维护系统等操作", role = "管理员", models = {@Model(name = "system", desc = "系统实体", clazz = org.yy.user.model.System.class)})
public class SystemController extends AbsUserController {
    
    @Resource(name = "systemService")
    private SystemService systemService;
    
    @RequestMapping(value = "list")
    @Method(name = "查询系统", desc = "查询系统", returns = {@Return(name = "系统", clazz = org.yy.user.model.System.class)})
    public ModelAndView list(@Param
    SystemDTO systemDTO)
        throws ServiceException {
        return processSuccess(moduleName + LIST_PAGE, systemService.findSystem(systemDTO), systemDTO);
    }
    
    @RequestMapping(value = "presave", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView save() {
    	return processSuccess(moduleName +ADD_PAGE, null);
    }
    @RequestMapping(value = "save", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView save(@Param
    System system, HttpServletRequest request) {
        Long userID = getCurrentUserID(request);
        system.setCreatePerson(userID);
        system.setUpdatePerson(userID);
        try {
            systemService.insertSystem(system);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                moduleName + ADD_PAGE,
                system,
                "insert system failure!",
                e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, system);
    }
    
    @RequestMapping(value = "preedit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView preEdit(@RequestParam("systemCode")
    String systemCode, HttpServletRequest request)
        throws ServiceException {
        return processSuccess(moduleName + EDIT_PAGE, systemService.findSystemByCode(systemCode));
    }
    
    @RequestMapping(value = "edit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView edit(@Param
    System system, HttpServletRequest request)
        throws ServiceException {
        
        Long userID = getCurrentUserID(request);
        system.setUpdatePerson(userID);
        try {
            systemService.updateSystem(system);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                moduleName + EDIT_PAGE,
                system,
                "update system failure!",
                e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, system);
    }
    
    @RequestMapping(value = "enable", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView enable(@RequestParam("systemCode")
    String systemCode, HttpServletRequest request)
        throws ServiceException {
        System system = systemService.findSystemByCode(systemCode);
        Long userID = getCurrentUserID(request);
        system.setUpdatePerson(userID);
        system.setStatus(1);
        try {
            systemService.updateSystem(system);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE, moduleName + LIST_PAGE, system,
            
            "update system status failure!", e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, system);
    }
    
    @RequestMapping(value = "disable", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView disable(@RequestParam("systemCode")
    String systemCode, HttpServletRequest request)
        throws ServiceException {
        System system = systemService.findSystemByCode(systemCode);
        Long userID = getCurrentUserID(request);
        system.setUpdatePerson(userID);
        system.setStatus(0);
        try {
            systemService.updateSystem(system);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                moduleName + LIST_PAGE,
                system,
                "update system status failure!",
                e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, system);
    }
    
    @RequestMapping(value = "view")
    public ModelAndView view(@RequestParam("systemCode")
    String systemCode)
        throws ServiceException {
        return processSuccess(moduleName + VIEW_PAGE, systemService.findSystemByCode(systemCode), systemCode);
    }
    
    /** {@inheritDoc} */
    @Override
    protected void setModuleName() {
        this.moduleName = "system/";
    }
    
}
