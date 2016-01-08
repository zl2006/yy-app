/*
* 文 件 名:  SSOFilter.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  sso客户端
* 修 改 人:  zhouliang
* 修改时间:  2013年12月30日
* 修改内容:  <修改内容>
*/
package org.yy.sso.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yy.framework.util.net.WebUtils;

import com.alibaba.fastjson.JSONObject;

/**
* sso客户端
* 
* @author  zhouliang
* @version  [版本号, 2013年12月30日]
* @since  [产品/模块版本]
*/
public class SSOFilter implements Filter {
    
    /**单点登录地址*/
    private String ssoUrl = "";
    
    /**登录成功后的返回地址*/
    private String returnUrl = "";
    
    /**登录页*/
    private String loginUrl = "";
    
    /**令牌验证url*/
    private String ticketValidateUrl = "";
    
    /**排除的特殊资源*/
    private Set<String> excludes = new HashSet<String>();
    
    /** {@inheritDoc} */
    @Override
    public void destroy() {
    }
    
    /** {@inheritDoc} */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        
        //1.当为排除掉的资源时不进行验证处理
        HttpServletRequest httpReq = (HttpServletRequest)request;
        HttpServletResponse httpResp = (HttpServletResponse)response;
        String url = httpReq.getRequestURI();
        for (String item : excludes) {
            if (url.endsWith(item)) {
                chain.doFilter(request, response);
                return;
            }
        }
        
        //2.1 获取TicketID,验证是否登录. 依次从parameter, http head, cooke, session中获取令牌ID
        String ticketId = SSOUtil.getTicketId(httpReq);
        if (ticketId == null || "".equals(ticketId.trim())) {
            httpResp.sendRedirect(loginUrl);
            return;
        }
        SSOUtil.writeTicketIdCookie(httpResp, ticketId);
        SSOUtil.writeTicketIdSession(httpReq, ticketId);
        
        //3.验证令牌
        Map<String, String> params = new HashMap<String, String>();
        params.put("ticketId", ticketId);
        String result = WebUtils.doPost(ticketValidateUrl, params, 3000, 3000);
        try {
            JSONObject jobj = JSONObject.parseObject(result);
            String code = jobj.getString("code");
            if (!"success".endsWith(code)) {
                httpResp.sendRedirect(loginUrl);
                return;
            }
            else {
                SSOUtil.writeUserIdCookie(httpResp, jobj.getString("data"));
                SSOUtil.writeUserIdSession(httpReq, jobj.getString("data"));
            }
        }
        catch (Exception ex) {
            httpResp.sendRedirect(loginUrl);
            return;
        }
        chain.doFilter(request, response);
    }
    
    /** {@inheritDoc} */
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException {
        ssoUrl = filterConfig.getInitParameter("ssoUrl");
        returnUrl = filterConfig.getInitParameter("returnUrl");
        loginUrl = ssoUrl + "login.do?return_url=" + returnUrl;
        ticketValidateUrl = ssoUrl + "validate.json";
        String excludeStr = filterConfig.getInitParameter("excludes");
        if (excludeStr == null || "".equals(excludeStr.trim())) {
            return;
        }
        
        String[] temp = excludeStr.split(",");
        for (String item : temp) {
            excludes.add(item);
        }
    }
}
