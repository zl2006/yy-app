package org.yy.monitor.plugins.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import junit.framework.TestResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.yy.framework.base.controller.AbstractController;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.persistence.EntityItemService;
import org.yy.monitor.core.persistence.EntityService;

@Controller
@RequestMapping("/plugins/web")
public class WebController extends AbstractController {

	@Resource(name = "entityItemService")
	private EntityItemService entityItemService;

	@Resource(name = "entityService")
	private EntityService entityService;

	@Resource(name = "webmonitortest")
	private WebMonitorTest webmonitortest;

	/**
	 * 测试web请求
	 * 
	 * @return
	 */
	@RequestMapping("/test")
	public ModelAndView test(@RequestParam("itemId")Integer itemId,@RequestParam("url") String url ){
		
		Map<String, Object> result = new HashMap<String, Object>();
		EntityItem entityItem = entityItemService.findEntityItemByItemId(itemId);
		Entity entity = entityService.findEntity(entityItem.getEntityCfgID());
		TestResult testResult =  webmonitortest.test(entity, entityItem);
		
		result.put("entity", entity);
		result.put("entityItem", entityItem);
		result.put("result", testResult);
		return processSuccess("", result);
	}

	@Override
	protected void setModuleName() {

	}

}
