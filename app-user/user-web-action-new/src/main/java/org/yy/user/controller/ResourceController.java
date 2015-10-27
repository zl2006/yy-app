/*
* 文 件 名:  ResourceController.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  资源控制器
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
import org.yy.user.dto.ResourceDTO;
import org.yy.user.model.System;
import org.yy.user.model.User;
import org.yy.user.service.ResourceService;
import org.yy.user.service.SystemService;
import static org.yy.framework.basedata.Constants.*;

/**
* 资源控制器
* 
* @author  zhouliang
* @version  [1.0, 2014年1月8日]
* @since  [app-user/1.0]
*/
@Controller
@RequestMapping("resource")
@Service(name = "资源服务", desc = "资源新增/删除/维护等操作", role = "管理员、新注册用户", models = {@Model(name = "user", desc = "用户实体", clazz = User.class)})
public class ResourceController extends AbsUserController {
    
    @Resource(name = "resourceService")
    private ResourceService resourceService;
    
    @Resource(name = "systemService")
    private SystemService systemService;
    
    @RequestMapping(value = "list")
    @Method(name = "查询资源", desc = "查询资源", returns = {@Return(name = "资源", clazz = org.yy.user.model.Resource.class)})
    public ModelAndView list(@Param
    ResourceDTO resDTO)
        throws ServiceException {
        resDTO.setResID(null);//与鉴权ID相冲突
        
        //当有参数条件时，使用参数条件，否则查顶层列表
        if (StringUtils.isEmpty(resDTO.getName())) {
            resDTO.setParentResID(-1l);
        }
        
        return processSuccess(moduleName + LIST_PAGE, resourceService.findResource(resDTO), resDTO);
    }
    
    @RequestMapping(value = "listChild")
    public ModelAndView listChild(ResourceDTO resDTO)
        throws ServiceException {
        Pagination pagination = new Pagination();
        pagination.setPageSize(10000);
        resDTO.setPagination(pagination);
        return processSuccess(moduleName + LIST_PAGE, resourceService.findResource(resDTO), resDTO);
    }
    
    @RequestMapping(value = "save")
    public ModelAndView save(org.yy.user.model.Resource res)
        throws ServiceException {
        if (res.getParentResID() == null) {
            res.setParentResID(-1l);
        }
        res.setResID(null); //防止获取到控制权限的资源ＩＤ, 初始化资源ID为空
        resourceService.saveResource(res);
        return processSuccess(SUCCESS_PAGE, res);
    }
    
    @RequestMapping(value = "update")
    public ModelAndView edit(org.yy.user.model.Resource res, HttpServletRequest request)
        throws ServiceException {
        
        Long userID = getCurrentUserID(request);
        res.setUpdatePerson(userID);
        String resID = request.getParameter("myResID");
        res.setResID(Long.valueOf(resID));
        resourceService.updateResource(res);
        return processSuccess(SUCCESS_PAGE, res);
    }
    
    @RequestMapping(value = "enable", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView enable(@RequestParam("myResID")
    Long resID, HttpServletRequest request)
        throws ServiceException {
        org.yy.user.model.Resource res = resourceService.findResource(resID);
        Long userID = getCurrentUserID(request);
        res.setUpdatePerson(userID);
        res.setStatus(1);
        try {
            resourceService.updateResource(res);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                moduleName + LIST_PAGE,
                res,
                "update res status failure!",
                e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, res);
    }
    
    @RequestMapping(value = "disable", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView disable(@RequestParam("myResID")
    Long resID, HttpServletRequest request)
        throws ServiceException {
        org.yy.user.model.Resource res = resourceService.findResource(resID);
        Long userID = getCurrentUserID(request);
        res.setUpdatePerson(userID);
        res.setStatus(0);
        try {
            resourceService.updateResource(res);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                moduleName + LIST_PAGE,
                res,
                "update res status failure!",
                e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, res);
    }
    
    @RequestMapping(value = "view")
    public ModelAndView view(@RequestParam("myResID")
    Long resID, HttpServletRequest request)
        throws ServiceException {
        
        org.yy.user.model.Resource res = resourceService.findResource(resID);
        org.yy.user.model.Resource parentRes = null;
        System system = null;
        if (res != null && res.getParentResID() != null && res.getParentResID() != -1) {
            parentRes = resourceService.findResource(res.getParentResID());
        }
        if (res != null && StringUtils.isNotEmpty(res.getSystemCode())) {
            system = systemService.findSystemByCode(res.getSystemCode());
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("res", res);
        result.put("parentRes", parentRes);
        result.put("system", system);
        return processSuccess(moduleName + VIEW_PAGE, result, resID);
    }
    
    @RequestMapping(value = "preedit")
    public ModelAndView preEdit(@RequestParam("myResID")
    Long resID, HttpServletRequest request)
        throws ServiceException {
        
        org.yy.user.model.Resource res = resourceService.findResource(resID);
        org.yy.user.model.Resource parentRes = null;
        System system = null;
        if (res != null && res.getParentResID() != null && res.getParentResID() != -1) {
            parentRes = resourceService.findResource(res.getParentResID());
        }
        if (res != null && StringUtils.isNotEmpty(res.getSystemCode())) {
            system = systemService.findSystemByCode(res.getSystemCode());
        }
        
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("res", res);
        result.put("parentRes", parentRes);
        result.put("system", system);
        return processSuccess(moduleName + EDIT_PAGE, result, resID);
    }
    
    /** {@inheritDoc} */
    @Override
    protected void setModuleName() {
        this.moduleName = "module/res/";
        
    }
}
