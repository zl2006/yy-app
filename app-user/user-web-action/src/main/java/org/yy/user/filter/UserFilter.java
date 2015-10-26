/*
* 文 件 名:  UserFilter.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  用户系统过滤器
* 修 改 人:  zhouliang
* 修改时间:  2013年12月4日
* 修改内容:  <修改内容>
*/
package org.yy.user.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.yy.sso.client.SSOUtil;
import org.yy.user.common.constants.AppUserConstants;
import org.yy.user.dto.MainDataDTO;
import org.yy.user.model.User;
import org.yy.user.service.ResourceService;
import org.yy.user.service.SystemService;
import org.yy.user.service.UserService;


/**
* 用户系统过滤器
* 
* @author  zhouliang
* @version  [1.0, 2013年12月4日]
* @since  [app-user/1.0]
*/
public class UserFilter implements Filter {
    
    private SystemService systemService;
    
    private ResourceService resourceService;
    
    private UserService userService;
    
    //系统编码
    private String systemCode = "";
    
    //是否门户
    private boolean portalEnable =  false;
    
    //门户首页
    private String portalIndexUrl = "http://www.baidu.com";
    
    // 排除的特殊资源
    private Set<String> excludes = new HashSet<String>();
    
    /** {@inheritDoc} */
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException {
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        systemService = (SystemService)ctx.getBean("systemService");
        resourceService = (ResourceService)ctx.getBean("resourceService");
        userService = (UserService)ctx.getBean("userService");
        systemCode = filterConfig.getInitParameter("systemCode");
        
        //Step 1： 初始化排除地址 
        String excludeStr = filterConfig.getInitParameter("excludes");
        if (!StringUtils.isEmpty(excludeStr)) {
	        String[] temp = excludeStr.split(",");
	        for (String item : temp) {
	            excludes.add(item);
	        }
        }
        
        //Step 2: 初始化是否门户
        String portalEnableStr = filterConfig.getInitParameter("portalEnable");
        if (!StringUtils.isEmpty(portalEnableStr) && "true".equals(portalEnableStr.trim())) {
        	portalEnable = true;
        }
        
        //Step 3: 初始化门户首页
        String portalIndexUrlStr = filterConfig.getInitParameter("portalIndexUrl");
        if (!StringUtils.isEmpty(portalIndexUrlStr) && "true".equals(portalIndexUrlStr.trim())) {
        	portalIndexUrl = portalIndexUrlStr.trim();
        }
       
    }
    
    /** {@inheritDoc} */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        
        //Step 1. 当为排除掉的资源时不进行验证处理
        HttpServletRequest httpReq = (HttpServletRequest)request;
        String url  = "http://" + request.getServerName() //服务器地址  
                + ":"   
                + httpReq.getServerPort()           //端口号  
                + httpReq.getContextPath()      //项目名称  
                + httpReq.getServletPath();     //请求页面或其他地址 
        for (String item : excludes) {
            if (url.endsWith(item)) {
                chain.doFilter(request, response);
                return;
            }
        }
        
        //Step 2. 从cookie或session中获取用户ID
        String loginID = SSOUtil.getUserID(httpReq);
        
        //Step 3. 获取用户,菜单以及系统资源等公共资源.
        try {
            User user = userService.findUser(loginID);
            if (user == null) {
                throw new IOException("user isn't exists");
            }
            //获取默认系统及系统的资源
            MainDataDTO mainData = new MainDataDTO(user,systemCode,portalEnable, url);
            
            //只有PC端用户才调用/////////////////////////
            String source = httpReq.getHeader("source");
            if (!"MOBILE".equals(source) && !"PAD".equals(source)) {
            	//初始化系统与资源
            	mainData.initSysAndRes(systemService.findSystem(user.getLoginID()) , resourceService.findResource(systemCode, user.getLoginID()));
                //设置首面
                if(portalEnable){
                	mainData.setIndexUrl(portalIndexUrl);
                }else if( mainData.getCurrentSystem() != null){
                	mainData.setIndexUrl(mainData.getCurrentSystem().getUrl());
                }
            }
            
            /////////////////////////////////////////
            request.setAttribute(AppUserConstants.SITE_MAIN_DATA, mainData);
            //request.setAttribute(AppUserConstants.OPERATOR_JSON,  JSON.toJSONString(mainData.getListOperations()));
            chain.doFilter(request, response);
        }
        catch (Exception ex) {
        	//ex.printStackTrace();
            throw new IOException("fetch menu failure",ex);
        }
    }
    
    /** {@inheritDoc} */
    @Override
    public void destroy() {
    }
    
}
