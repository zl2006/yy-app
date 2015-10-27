/*
 * 文 件 名:  UserController.java
 * 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
 * 描    述:  用户操作控制层
 * 修 改 人:  zhouliang
 * 修改时间:  2013年11月26日
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
import org.yy.framework.document.anno.Exception;
import org.yy.user.dto.RoleDTO;
import org.yy.user.dto.SystemDTO;
import org.yy.user.dto.UserDTO;
import org.yy.user.dto.UserGroupDTO;
import org.yy.user.model.Role;
import org.yy.user.model.User;
import org.yy.user.model.UserGroup;
import org.yy.user.service.OrganService;
import org.yy.user.service.RoleService;
import org.yy.user.service.SystemService;
import org.yy.user.service.UserGroupService;
import org.yy.user.service.UserService;

import static org.yy.framework.basedata.Constants.*;

/**
 * 用户服务
 * 
 * @author zhouliang
 * @version [1.0, 2013年11月26日]
 * @since [app-user/1.0]
 */
@Controller
@RequestMapping("user")
@Service(name = "用户服务", desc = "用户注册、注销、重置密码、维护等操作", role = "管理员、新注册用户", models = { @Model(name = "user", desc = "用户实体", clazz = User.class) })
public class UserController extends AbsUserController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "organService")
	private OrganService organService;

	@Resource(name = "userGroupService")
	private UserGroupService userGroupService;

	@Resource(name = "roleService")
	private RoleService roleService;

	@Resource(name = "systemService")
	private SystemService systemService;
	
	@RequestMapping(value = "presave", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView saveUser() {
		return processSuccess(moduleName + ADD_PAGE, null);
	}

	@RequestMapping(value = "save", method = { RequestMethod.GET,
			RequestMethod.POST })
	@Method(name = "保存用户", desc = "保存用户", exceps = {
			@Exception(code = "001001010", message = "用户登录ID重复"),
			@Exception(code = "001001008", message = "用户数据验证失败") })
	public ModelAndView saveUser(@Param User user, HttpServletRequest request) {
		Long userID = getCurrentUserID(request);
		user.setCreatePerson(userID);
		user.setUpdatePerson(userID);

		try {
			userService.insertUser(user);
		} catch (ServiceException e) {
			return processFailure(ERROR_500_CODE, moduleName + ADD_PAGE, user,
					"insert user failure!", e.getMessage());
		}
		return processSuccess(SUCCESS_PAGE, user);
	}

	@RequestMapping(value = {"list","module"})
	@Method(name = "查询用户", desc = "查询用户", returns = { @Return(name = "用户", clazz = User.class) })
	public ModelAndView list(@Param UserDTO userDTO) throws ServiceException {
		return processSuccess(moduleName + LIST_PAGE,
				userService.findUser(userDTO), userDTO);
	}

	@RequestMapping(value = "preedit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView preEdit(@RequestParam("loginID") String loginID)
			throws ServiceException {
		User user = userService.findUser(loginID);
		if (user != null) {
			user.setOrgans(organService.findOrgan(user));
		}
		return processSuccess(moduleName + EDIT_PAGE, user);
	}

	@RequestMapping(value = "edit", method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView edit(@Param User user, HttpServletRequest request)
			throws ServiceException {
		Long userID = getCurrentUserID(request);
		user.setUpdatePerson(userID);
		try {
			userService.updateUser(user);
		} catch (ServiceException e) {
			return processFailure(ERROR_500_CODE, moduleName + EDIT_PAGE, user,
					"update user failure!", e.getMessage());
		}
		return processSuccess(SUCCESS_PAGE, user);
	}

	@RequestMapping(value = "view")
	public ModelAndView view(@RequestParam("loginID") String loginID)
			throws ServiceException {
		User user = userService.findUser(loginID);
		List<UserGroup> groups = null;
		List<Role> roles = null;

		if (user != null) {
			user.setOrgans(organService.findOrgan(user));
			groups = userGroupService.findUserGroup(user);
			roles = roleService.findRole(user);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("user", user);
		result.put("groups", groups);
		result.put("roles", roles);
		return processSuccess(moduleName + VIEW_PAGE, result, loginID);
	}

	@RequestMapping(value = "preconfig")
	public ModelAndView preConfig(@RequestParam("loginID") String loginID)
			throws ServiceException {

		// 1, 获取用户信息及关联的组织机构、用户组和角色
		User user = userService.findUser(loginID);
		List<UserGroup> groups = null;
		List<Role> roles = null;

		if (user != null) {
			user.setOrgans(organService.findOrgan(user));
			groups = userGroupService.findUserGroup(user);
			roles = roleService.findRole(user);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("user", user);
		result.put("groups", groups);
		result.put("roles", roles);

		// 2, 获取所有的系统及用户组和角色，以便配置
		SystemDTO sysDto = new SystemDTO();
		Pagination page = new Pagination();
		page.setPageSize(10000);
		sysDto.setPagination(page);
		result.put("allSystems", systemService.findSystem(sysDto));

		UserGroupDTO groupDto = new UserGroupDTO();
		page.setPageSize(10000);
		groupDto.setPagination(page);
		result.put("allGroups", userGroupService.findUserGroup(groupDto));

		RoleDTO roleDto = new RoleDTO();
		page.setPageSize(10000);
		roleDto.setPagination(page);
		result.put("allRoles", roleService.findRole(roleDto));

		return processSuccess(moduleName + "/config", result, loginID);
	}

	@RequestMapping(value = "config")
	public ModelAndView config(@RequestParam("loginID") String loginID,
			@RequestParam("userID") Long userID,
			@RequestParam("groups") List<Long> groupIDs,
			@RequestParam("roles") List<String> roleCodes)
			throws ServiceException {

		userService.config(loginID, groupIDs, roleCodes);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginID", loginID);
		params.put("userID", userID);
		params.put("groups", groupIDs);
		params.put("roles", roleCodes);
		return processSuccess(moduleName + "/config", null, params);
	}

	/** {@inheritDoc} */
	@Override
	protected void setModuleName() {
		this.moduleName = "user/";
	}
}
