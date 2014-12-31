/*
* 文 件 名:  DictionaryController.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  字典控制器
* 修 改 人:  zhouliang
* 修改时间:  2014年3月16日
* 修改内容:  <修改内容>
*/
package org.yy.user.controller;

import static org.yy.framework.basedata.Constants.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.yy.framework.basedata.exception.ServiceException;
import org.yy.user.dto.DictionaryDTO;
import org.yy.user.model.Dictionary;
import org.yy.user.service.DictionaryService;

/**
*
* 字典控制器
* @author  zhouliang
* @version  [1.0, 2014年3月16日]
* @since  [app-user/1.0]
*/
@Controller
@RequestMapping("dic")
public class DictionaryController extends AbsUserController {
    
    @Resource(name = "dictionaryService")
    private DictionaryService dictionaryService;
    
    //列表
    @RequestMapping(value = "list")
    public ModelAndView list(DictionaryDTO dicDTO)
        throws ServiceException {
        return processSuccess(moduleName + LIST_PAGE, dictionaryService.findDictionary(dicDTO), dicDTO);
        
    }
    
    //保存
    @RequestMapping(value = "save")
    public ModelAndView saveDic(Dictionary dic, HttpServletRequest request)
        throws ServiceException {
        try {
            dictionaryService.insertDictionary(dic);
        }
        catch (ServiceException e) {
            return processFailure(e.getCode(), moduleName + ADD_PAGE, dic, "insert dic failure!", e.getMessage());
            
        }
        return processSuccess(SUCCESS_PAGE, dic);
    }
    
    //查看
    @RequestMapping(value = "view")
    public ModelAndView view(@RequestParam("dicID")
    Long dicID)
        throws ServiceException {
        return processSuccess(moduleName + VIEW_PAGE, dictionaryService.findDictionary(dicID), dicID);
    }
    
    //禁用
    @RequestMapping(value = "disable")
    public ModelAndView disable(@RequestParam("dicID")
    Long dicID)
        throws ServiceException {
        Dictionary dic = dictionaryService.findDictionary(dicID);
        if (dic != null && dic.getStatus() != 0) {
            dic.setStatus(0);
            try {
                dictionaryService.updateDictionary(dic);
            }
            catch (ServiceException ex) {
                return processFailure(ex.getCode(),
                    moduleName + LIST_PAGE,
                    dic,
                    "update dic status failure!",
                    ex.getMessage());
            }
        }
        return processSuccess(moduleName + LIST_PAGE, dic);
    }
    
    //启用
    @RequestMapping(value = "enable")
    public ModelAndView enable(@RequestParam("dicID")
    Long dicID, HttpServletRequest request)
        throws ServiceException {
        Dictionary dic = dictionaryService.findDictionary(dicID);
        if (dic != null && dic.getStatus() != 1) {
            dic.setStatus(1);
            try {
                dictionaryService.updateDictionary(dic);
            }
            catch (ServiceException ex) {
                return processFailure(ex.getCode(),
                    moduleName + LIST_PAGE,
                    dic,
                    "update dic status failure!",
                    ex.getMessage());
            }
        }
        return processSuccess(moduleName + LIST_PAGE, dic);
    }
    
    //编辑
    @RequestMapping(value = "edit")
    public ModelAndView edit(Dictionary dic, HttpServletRequest request)
        throws ServiceException {
        try {
            dictionaryService.updateDictionary(dic);
        }
        catch (ServiceException e) {
            return processFailure(e.getCode(), moduleName + EDIT_PAGE, dic, "update dic failure!", e.getMessage());
        }
        return processSuccess(moduleName + LIST_PAGE, dic);
    }
    
    //进入编辑
    @RequestMapping(value = "preedit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView preEdit(@RequestParam("dicID")
    Long dicID)
        throws ServiceException {
        return processSuccess(moduleName + EDIT_PAGE, dictionaryService.findDictionary(dicID));
    }
    
    /** {@inheritDoc} */
    @Override
    public void setModuleName() {
        this.moduleName = "module/dictionary/";
    }
    
}
