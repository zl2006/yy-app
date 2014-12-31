/*
* 文 件 名:  AbsUserController.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  用户模块抽像控制器
* 修 改 人:  zhouliang
* 修改时间:  2014年1月8日
* 修改内容:  <修改内容>
*/
package org.yy.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.yy.framework.base.controller.AbstractController;
import org.yy.user.common.constants.AppUserConstants;
import org.yy.user.dto.MainDataDTO;
import org.yy.user.model.User;

/**
* 用户模块抽像控制器
* 
* @author  zhouliang
* @version  [1.0, 2014年1月8日]
* @since  [app-user/1.0]
*/
public abstract class AbsUserController extends AbstractController {
    
    /**
     *  获取用户
     */
    protected User getCurrentUser(HttpServletRequest request) {
        User user = null;
        MainDataDTO mainData = (MainDataDTO)request.getAttribute(AppUserConstants.SITE_MAIN_DATA);
        if (mainData != null && mainData.getUser() != null) {
            user = mainData.getUser();
        }
        return user;
    }
    
    /**
     *  获取用户ID
     */
    protected Long getCurrentUserID(HttpServletRequest request) {
        MainDataDTO mainData = (MainDataDTO)request.getAttribute(AppUserConstants.SITE_MAIN_DATA);
        Long userID = null;
        if (mainData != null && mainData.getUser() != null) {
            userID = mainData.getUser().getUserID();
        }
        return userID;
    }
}
