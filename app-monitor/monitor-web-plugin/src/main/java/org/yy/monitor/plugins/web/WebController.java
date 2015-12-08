package org.yy.monitor.plugins.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import org.yy.monitor.core.util.BeanUtilEx;
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
	 */
	@RequestMapping("/test_entity_item")
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
	 */
	@RequestMapping("/list_test_entity")
	public ModelAndView listEntityItemTest(@RequestParam("itemId") Integer itemId){
		return processSuccess("", entityItemTestService.findEntityItemTest(itemId));
	}
	
	
	/**
	 * 增加实体
	 */
	@RequestMapping("/add_entity")
	public ModelAndView addEntity(WebEntity webEntity, @RequestParam("entity_nodes") String entityNodes,HttpServletRequest request ) throws InvocationTargetException, IllegalAccessException{
		
		Entity entity = new Entity();
		BeanUtilEx.copyProperties(entity,webEntity);
		entity.setMonitorEntityCfg("{\"icon\":\"" + webEntity.getIcon() +  "\",\"url\":\"" + webEntity.getUrl() + "\"}");

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(StringUtils.isNotEmpty(entityNodes)){
			String[] nodes = entityNodes.split(";");
			for( int i = 0; nodes.length > 0 && i < nodes.length - 1; ++i){
				sb.append("\"");
				sb.append(nodes[i].trim());
				sb.append("\"");
				sb.append(",");
			}
			if( nodes.length > 0){
				sb.append("\"");
				sb.append(nodes[nodes.length - 1].trim());
				sb.append("\"");
			}
		}
		sb.append("]");
		entity.setMonitorEntityNodes(sb.toString());
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		entity.setStatus(1);
		
		entityService.saveEntity(entity);
		entity.setManagerID(null);
		return processSuccess("" ,entity);
	}
	
	
	@RequestMapping("/del_entity")
	public ModelAndView delEntity(@RequestParam("cfgID") Integer cfgID){
		entityService.deleteEntity(cfgID);
		return processSuccess("" ,cfgID);
	}
	

	@Override
	protected void setModuleName() {

	}

}
