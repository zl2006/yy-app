/*
* 文 件 名:  SSOUtil.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:   SSO工具类
* 修 改 人:  zhouliang
* 修改时间:  2013年12月30日
* 修改内容:  <修改内容>
*/
package org.yy.sso.client;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
*  SSO工具类
* 
* @author  zhouliang
* @version  [1.0, 2013年12月30日]
* @since  [app-sso/1.0]
*/
public final class SSOUtil {
    
    private SSOUtil() {
    }
    
    /**
     * 获取cookie值
     */
    public static String getCookieValue(HttpServletRequest httpReq, String name) {
        String value = "";
        Cookie[] cookies = httpReq.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie item : cookies) {
                if (name.equals(item.getName())) {
                    value = item.getValue();
                }
            }
        }
        return value;
    }
    
    /**
     * 获取head值
     */
    public static String getHttpHeadValue(HttpServletRequest httpReq, String name) {
        return httpReq.getHeader(name);
    }
    
    /**
     * 获取http请求参数
     */
    public static String getHttpParamValue(HttpServletRequest httpReq, String name) {
        return httpReq.getParameter(name);
    }
    
    /**
     * 获取session值
     */
    public static String getSessionValue(HttpServletRequest httpReq, String name) {
        return (String)httpReq.getSession().getAttribute(name);
    }
    
    /**
     *  写cookie
     */
    public static void writeCookie(HttpServletResponse httpResp, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        httpResp.addCookie(cookie);
    }
    
    /**
     *  写session
     */
    public static void writeSession(HttpServletRequest httpReq, String name, Object value) {
        httpReq.getSession().setAttribute(name, value);
    }
    
    /**
     * 获取TicketID,依次从parameter ,cooke , session中获取令牌ID
     */
    public static String getTicketId(HttpServletRequest request) {
        String ticketId = getHttpParamValue(request, "_ticket_id_");
        if (ticketId == null || "".equals(ticketId.trim())) {
            ticketId = getHttpHeadValue(request, "_ticket_id_");
        }
        if (ticketId == null || "".equals(ticketId.trim())) {
            ticketId = getCookieValue(request, "_ticket_id_");
        }
        if (ticketId == null || "".equals(ticketId.trim())) {
            ticketId = getSessionValue(request, "_ticket_id_");
        }
        return ticketId;
    }
    
    /**
     * 获取userID,依次从cooke, parameter, session中获取UserID
     */
    public static String getUserID(HttpServletRequest request) {
        String userID = getCookieValue(request, "_userid_");
        if (userID == null || "".equals(userID.trim())) {
            userID = getHttpParamValue(request, "_userid_");
        }
        if (userID == null || "".equals(userID.trim())) {
            userID = getSessionValue(request, "_userid_");
        }
        return userID;
    }
    
    /**
     * 写userid to cookie
     */
    public static void writeUserIdCookie(HttpServletResponse httpResp, String value) {
        writeCookie(httpResp, "_userid_", value);
    }
    
    /**
     * 写userid to session
     */
    public static void writeUserIdSession(HttpServletRequest httpResp, String value) {
        writeSession(httpResp, "_userid_", value);
    }
    
    /**
     * 写ticketid to cookie
     */
    public static void writeTicketIdCookie(HttpServletResponse httpResp, String value) {
        writeCookie(httpResp, "_ticket_id_", value);
    }
    
    /**
     * 写ticketid to session
     */
    public static void writeTicketIdSession(HttpServletRequest httpResp, String value) {
        writeSession(httpResp, "_ticket_id_", value);
    }
    
}
