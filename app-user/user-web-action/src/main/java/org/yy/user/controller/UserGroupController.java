/*
* 文 件 名:  UserGroupController.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  用户组控制器
* 修 改 人:  zhouliang
* 修改时间:  2014年1月8日
* 修改内容:  <修改内容>
*/
package org.yy.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import org.yy.user.dto.RoleDTO;
import org.yy.user.dto.SystemDTO;
import org.yy.user.dto.UserGroupDTO;
import org.yy.user.model.Role;
import org.yy.user.model.UserGroup;
import org.yy.user.service.RoleService;
import org.yy.user.service.SystemService;
import org.yy.user.service.UserGroupService;
import static org.yy.framework.basedata.Constants.*;

/**
* 用户组控制器
* 
* @author  zhouliang
* @version  [1.0, 2014年1月8日]
* @since  [app/模块版本]
*/
@Controller
@RequestMapping("group")
@Service(name = "用户组服务", desc = "新增、配置、维护等操作", role = "管理员", models = {@Model(name = "userGroup", desc = "用户组实体", clazz = UserGroup.class)})
public class UserGroupController extends AbsUserController {
    
    @Resource(name = "userGroupService")
    private UserGroupService userGroupService;
    
    @Resource(name = "roleService")
    private RoleService roleService;
    
    @Resource(name = "systemService")
    private SystemService systemService;
    
    @RequestMapping(value = "list")
    @Method(name = "查询用户组", desc = "查询用户组", returns = {@Return(name = "用户组", clazz = UserGroup.class)})
    public ModelAndView list(@Param
    UserGroupDTO userGroupDTO)
        throws ServiceException {
        return processSuccess(moduleName + LIST_PAGE, userGroupService.findUserGroup(userGroupDTO), userGroupDTO);
    }
    
    @RequestMapping(value = "save", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView saveGroup(@Param
    UserGroup group, HttpServletRequest request) {
        Long userID = getCurrentUserID(request);
        group.setCreatePerson(userID);
        group.setUpdatePerson(userID);
        try {
            userGroupService.insertUserGroup(group);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE, moduleName + ADD_PAGE, group, "insert group failure!", e.getMessage());
        }
        return processSuccess(SUCCESS_PAGE, group);
    }
    
    @RequestMapping(value = "view")
    public ModelAndView view(@RequestParam("userGroupID")
    Long userGroupID)
        throws ServiceException {
        UserGroup userGroup = userGroupService.findUserGroup(userGroupID);
        List<Role> roles = null;
        
        if (userGroup != null) {
            roles = roleService.findRole(userGroup);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("userGroup", userGroup);
        result.put("roles", roles);
        return processSuccess(moduleName + VIEW_PAGE, result, userGroupID);
    }
    
    @RequestMapping(value = "disable")
    public ModelAndView disable(@RequestParam("userGroupID")
    Long userGroupID)
        throws ServiceException {
        UserGroup userGroup = userGroupService.findUserGroup(userGroupID);
        if (userGroup != null && userGroup.getStatus() != 0) {
            userGroup.setStatus(0);
            try {
                userGroupService.updateUserGroup(userGroup);
            }
            catch (ServiceException ex) {
                return processFailure(ERROR_500_CODE,
                    moduleName + LIST_PAGE,
                    userGroup,
                    "update usergroup status failure!",
                    ex.getMessage());
            }
        }
        return processSuccess(moduleName + LIST_PAGE, userGroup, userGroupID);
    }
    
    @RequestMapping(value = "enable")
    public ModelAndView enable(@RequestParam("userGroupID")
    Long userGroupID, HttpServletRequest request)
        throws ServiceException {
        UserGroup userGroup = userGroupService.findUserGroup(userGroupID);
        if (userGroup != null && userGroup.getStatus() != 1) {
            userGroup.setStatus(1);
            try {
                userGroupService.updateUserGroup(userGroup);
            }
            catch (ServiceException ex) {
                return processFailure(ERROR_500_CODE,
                    moduleName + LIST_PAGE,
                    userGroupID,
                    "update usergroup status failure!",
                    ex.getMessage());
            }
        }
        return processSuccess(moduleName + LIST_PAGE, userGroup, userGroupID);
    }
    
    @RequestMapping(value = "preedit")
    public ModelAndView preEdit(@RequestParam("userGroupID")
    Long userGroupID, HttpServletRequest request)
        throws ServiceException {
        return processSuccess("module/group/edit", userGroupService.findUserGroup(userGroupID), userGroupID);
    }
    
    @RequestMapping(value = "edit")
    public ModelAndView edit(UserGroup userGroup, HttpServletRequest request)
        throws ServiceException {
        try {
            userGroupService.updateUserGroup(userGroup);
        }
        catch (ServiceException e) {
            return processFailure(ERROR_500_CODE,
                "module/group/edit",
                userGroup,
                "update usergroup failure!",
                e.getMessage());
        }
        return processSuccess(moduleName + LIST_PAGE, userGroup);
    }
    
    @RequestMapping(value = "preconfig")
    public ModelAndView preConfig(@RequestParam("userGroupID")
    Long userGroupID)
        throws ServiceException {
        
        //1, 获取用户组信息及关联的和角色
        UserGroup userGroup = userGroupService.findUserGroup(userGroupID);
        List<Role> roles = null;
        
        if (userGroupID != null) {
            roles = roleService.findRole(userGroup);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("userGroup", userGroup);
        result.put("roles", roles);
        
        //2, 获取所有的系统和角色，以便配置
        SystemDTO sysDto = new SystemDTO();
        Pagination page = new Pagination();
        page.setPageSize(10000);
        sysDto.setPagination(page);
        result.put("allSystems", systemService.findSystem(sysDto));
        
        RoleDTO roleDto = new RoleDTO();
        page.setPageSize(10000);
        roleDto.setPagination(page);
        result.put("allRoles", roleService.findRole(roleDto));
        
        return processSuccess("module/group/config", result, userGroupID);
    }
    
    @RequestMapping(value = "config")
    public ModelAndView config(@RequestParam("userGroupID")
    Long userGroupID, @RequestParam("roles")
    List<String> roleCodes)
        throws ServiceException {
        
        userGroupService.config(userGroupID, roleCodes);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userGroupID", userGroupID);
        params.put("roleCodes", roleCodes);
        return processSuccess("module/group/config", null, params);
        
    }
    
    /** {@inheritDoc} */
    @Override
    protected void setModuleName() {
        this.moduleName = "module/group/";
    }
}
