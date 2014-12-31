/*
* 文 件 名:  LogController.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  日志控制器
* 修 改 人:  zhouliang
* 修改时间:  2014年3月16日
* 修改内容:  <修改内容>
*/
package org.yy.user.controller;


import javax.annotation.Resource;
import static org.yy.framework.basedata.Constants.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.document.anno.Model;
import org.yy.framework.document.anno.Service;
import org.yy.user.dto.LogDTO;
import org.yy.user.model.Log;
import org.yy.user.service.LogService;

/**
* 日志控制器
* 
* @author  zhouliang
* @version  [1.0, 2014年3月16日]
* @since  [app-user/1.0]
*/
@Controller
@RequestMapping("log")
@Service(name = "日志服务", desc = "查看日志等操作", role = "管理员", models = {@Model(name = "log", desc = "日志", clazz = Log.class)})
public class LogController extends AbsUserController {
    
    
    @Resource(name = "logService")
    private LogService logService;
    
    @RequestMapping(value = "list")
    public ModelAndView list(LogDTO logDTO)
        throws ServiceException {
        return processSuccess(moduleName + LIST_PAGE, logService.findLog(logDTO), logDTO);
    }
    
    @RequestMapping(value = "view")
    public ModelAndView view(@RequestParam("logID")
    Long logID)
        throws ServiceException {
        return processSuccess(moduleName + VIEW_PAGE, logService.findLog(logID));
    }

     /** {@inheritDoc} */
    @Override
    protected void setModuleName() {
        this.moduleName = "module/log/";
    }
}
