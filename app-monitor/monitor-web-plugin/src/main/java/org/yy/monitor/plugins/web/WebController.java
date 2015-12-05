package org.yy.monitor.plugins.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.yy.framework.base.controller.AbstractController;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.entity.TestResult;
import org.yy.monitor.core.persistence.EntityItemService;
import org.yy.monitor.core.persistence.EntityItemTestService;
import org.yy.monitor.core.persistence.EntityService;
import org.yy.monitor.core.util.EntityUtil;
import org.yy.monitor.plugins.web.data.WebEntity;
import org.yy.monitor.plugins.web.data.WebEntityItem;

@Controller
@RequestMapping("/plugins/web")
public class WebController extends AbstractController {

	
	@Resource(name="entityItemTestService")
	private EntityItemTestService entityItemTestService;
	
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
	public ModelAndView test(@RequestParam("itemId") Integer itemId,
			@RequestParam("url") String url) {

		Map<String, Object> result = new HashMap<String, Object>();
		EntityItem entityItem = entityItemService
				.findEntityItemByItemId(itemId);
		Entity entity = entityService.findEntity(entityItem.getEntityCfgID());

		WebEntity webEntity =  EntityUtil.toPlugEntity(entity, WebEntity.class);
		WebEntityItem webEntityItem  = EntityUtil.toPlugEntityItem(entityItem, WebEntityItem.class);
		TestResult testResult = webmonitortest.test(webEntity,webEntityItem );

		result.put("entity", webEntity);
		result.put("entityItem", webEntityItem);
		result.put("result", testResult);
		return processSuccess("", result);
	}
	
	/**
	 * web监控项测试结果列表
	 * 
	 * @return
	 */
	@RequestMapping("/list_test")
	public ModelAndView listEntityItemTest(@RequestParam("itemId") Integer itemId){
		return processSuccess("", entityItemTestService.findEntityItemTest(itemId));
	}
	

	@Override
	protected void setModuleName() {

	}

}
