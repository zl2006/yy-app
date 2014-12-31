/*
* 文 件 名:  MainController.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  主控制器
* 修 改 人:  zhouliang
* 修改时间:  2013年12月31日
* 修改内容:  <修改内容>
*/
package org.yy.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.yy.framework.base.controller.AbstractController;

/**
* 主控制器
* 
* @author  zhouliang
* @version  [1.0, 2013年12月31日]
* @since  [app-user/1.0]
*/
@Controller
public class MainController extends AbstractController {
    
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

     /** {@inheritDoc} */
    @Override
    protected void setModuleName() {
    }
}
