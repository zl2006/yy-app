/*
* 文 件 名:  IndexController.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  单点登录主控制器
* 修 改 人:  zhouliang
* 修改时间:  2013年12月14日
* 修改内容:  <修改内容>
*/
package org.yy.sso.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.yy.framework.base.controller.AbstractController;
import org.yy.sso.CentralAuthService;
import org.yy.sso.SSOConstants;
import org.yy.sso.ValidateResult;
import org.yy.sso.auth.AuthResult;
import org.yy.sso.credential.UsernamePwdCredential;
import static org.yy.framework.basedata.Constants.*;

/**
* 单点登录主控制器
* 
* @author  zhouliang
* @version  [1.0, 2013年12月14日]
* @since  [app-sso/1.0]
*/
@Controller
public class IndexController extends AbstractController {
    
    @Resource(name = "centralAuthService")
    private CentralAuthService centralAuthService;
    
    /**
     * 定位到登录页
     *  
     * @return 登录页
     */
    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }
    
    /**
     * 登录操作
     * 
     * @param user 用户信息
     * @param request http请求
     * @return
     */
    @RequestMapping("/dologin")
    public ModelAndView dologin(UsernamePwdCredential user, HttpServletRequest request, HttpServletResponse response) {
        
        //判断回调url是否为空
        String returnUrl = request.getParameter("return_url");
        if (StringUtils.isEmpty(returnUrl)) {
            return processFailure("error", null, null, null, "return_url param is null!");
        }
        
        //授权操作,返回授权结果及令牌信息。并存储Token到容器中。
        AuthResult authResult = null;
        try {
            authResult = centralAuthService.authentication(user);
            
            // 授权失败
            if (!authResult.isSuccess()) {
                return processFailure(ERROR_500_CODE, "login", user, "login failure!", authResult.getErrorMessages());
            }
        }//授权异常
        catch (Exception e) {
            return processFailure(ERROR_500_CODE, "error", user, "login failure!", authResult.getErrorMessages());
        }
        
        //保存令牌标识到Cookie中
        Cookie cookie = new Cookie(SSOConstants.TICKET_ID, authResult.getTicket().getId());
        response.addCookie(cookie);
        
        if (returnUrl.indexOf("?") > -1) {
            returnUrl += "&";
        }
        else {
            returnUrl += "?";
        }
        returnUrl += SSOConstants.TICKET_ID + "=" + authResult.getTicket().getId();
        //返回回调页，并传递令牌标识。当跨域应用时可以自行处理令牌标识到cookie中.
        ModelAndView mav = new ModelAndView("redirect:" + returnUrl);
        return mav;
    }
    
    /**
     * 验证令牌是否有效, 必须使用.json方式访问
     * @param ticketId 令牌标识
     * @return
     */
    @RequestMapping("/validate")
    public ModelAndView validateTicket(String ticketId) {
        ValidateResult vr = centralAuthService.validateTicket(ticketId);
        if (!vr.isSuccess()) {
            return processFailure(ERROR_500_CODE, "login", "", "validate ticket failure!", vr.getErrorMessages());
        }
        return processSuccess("",  vr.getTicket().getCredentialId());
    }

     /** {@inheritDoc} */
    @Override
    protected void setModuleName() {
    }
    
}
