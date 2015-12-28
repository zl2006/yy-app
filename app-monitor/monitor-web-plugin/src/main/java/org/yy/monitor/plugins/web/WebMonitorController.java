package org.yy.monitor.plugins.web;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.yy.framework.base.controller.AbstractController;
import org.yy.monitor.core.DefaultMonitorTask;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.entity.TestResult;
import org.yy.monitor.core.manager.SchedulerManager;
import org.yy.monitor.core.persistence.EntityItemService;
import org.yy.monitor.core.persistence.EntityItemTestService;
import org.yy.monitor.core.persistence.EntityService;
import org.yy.monitor.core.util.BeanUtilEx;
import org.yy.monitor.core.util.EntityUtil;
import org.yy.monitor.plugins.web.data.WebEntity;
import org.yy.monitor.plugins.web.data.WebEntityItem;

@Controller
@RequestMapping("/plugins/web")
public class WebMonitorController extends AbstractController {

	@Resource(name = "entityItemTestService")
	private EntityItemTestService entityItemTestService;

	@Resource(name = "entityItemService")
	private EntityItemService entityItemService;

	@Resource(name = "entityService")
	private EntityService entityService;

	@Resource(name = "webmonitortest")
	private WebMonitorTest webmonitortest;

	// 测试web监控项
	@RequestMapping("/test_entity_item")
	public ModelAndView testEntityItem(@RequestParam("itemId") Integer itemId,
			@RequestParam("url") String url) {

		Map<String, Object> result = new HashMap<String, Object>();
		EntityItem entityItem = entityItemService
				.findEntityItemByItemId(itemId);
		Entity entity = entityService.findEntity(entityItem.getEntityCfgID());

		WebEntity webEntity = EntityUtil.toPlugEntity(entity, WebEntity.class);
		WebEntityItem webEntityItem = EntityUtil.toPlugEntityItem(entityItem,
				WebEntityItem.class);
		TestResult testResult = webmonitortest.test(webEntity, webEntityItem);

		result.put("entity", webEntity);
		result.put("entityItem", webEntityItem);
		result.put("result", testResult);
		return processSuccess("", result);
	}

	// web监控项测试结果列表
	@RequestMapping("/list_test_entity")
	public ModelAndView listTestEntity(@RequestParam("itemId") Integer itemId) {
		return processSuccess("",
				entityItemTestService.findEntityItemTest(itemId));
	}

	// 增加WEB应用实体
	@RequestMapping("/add_entity")
	public ModelAndView addEntity(WebEntity webEntity,
			@RequestParam("entity_nodes") String entityNodes,
			HttpServletRequest request) throws InvocationTargetException,
			IllegalAccessException {

		Entity entity = new Entity();
		BeanUtilEx.copyProperties(entity, webEntity);
		entity.setMonitorEntityCfg("{\"icon\":\"" + webEntity.getIcon()
				+ "\",\"url\":\"" + webEntity.getUrl() + "\"}");

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if (StringUtils.isNotEmpty(entityNodes)) {
			String[] nodes = entityNodes.split(";");
			for (int i = 0; nodes.length > 0 && i < nodes.length - 1; ++i) {
				sb.append("\"");
				sb.append(nodes[i].trim());
				sb.append("\"");
				sb.append(",");
			}
			if (nodes.length > 0) {
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
		return processSuccess("", entity);
	}

	// 删除WEB应用实体
	@RequestMapping("/del_entity")
	public ModelAndView delEntity(@RequestParam("cfgID") Integer cfgID) {
		List<EntityItem> items = entityItemService.findEntityItem(cfgID);
		if (items != null && items.size() > 0) {
			return processFailure("ERROR", "");
		}
		entityService.deleteEntity(cfgID);
		return processSuccess("", cfgID);
	}

	// 编辑WEB应用实体
	@RequestMapping("/edit_entity")
	public ModelAndView editEntity(@RequestParam("cfgID") Integer cfgID) {
		Entity entity = entityService.findEntity(cfgID);
		return processSuccess("",
				EntityUtil.toPlugEntity(entity, WebEntity.class));
	}

	// 编辑保存WEB应用实体
	@RequestMapping("/edit_entity_save")
	public ModelAndView editSaveEntity(WebEntity webEntity,
			@RequestParam("entity_nodes") String entityNodes,
			HttpServletRequest request) throws InvocationTargetException,
			IllegalAccessException {
		Entity entity = new Entity();
		BeanUtilEx.copyProperties(entity, webEntity);
		entity.setMonitorEntityCfg("{\"icon\":\"" + webEntity.getIcon()
				+ "\",\"url\":\"" + webEntity.getUrl() + "\"}");

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if (StringUtils.isNotEmpty(entityNodes)) {
			String[] nodes = entityNodes.split(";");
			for (int i = 0; nodes.length > 0 && i < nodes.length - 1; ++i) {
				sb.append("\"");
				sb.append(nodes[i].trim());
				sb.append("\"");
				sb.append(",");
			}
			if (nodes.length > 0) {
				sb.append("\"");
				sb.append(nodes[nodes.length - 1].trim());
				sb.append("\"");
			}
		}
		sb.append("]");
		entity.setMonitorEntityNodes(sb.toString());
		entity.setUpdateTime(new Date());
		entity.setStatus(1);

		entityService.updateEntity(entity);
		return processSuccess("", entity);
	}

	// 增加监控项
	@RequestMapping("/add_entity_item")
	public ModelAndView addEntityItem(WebEntityItem webEntityItem,
			HttpServletRequest request) throws InvocationTargetException,
			IllegalAccessException {

		// Step 1 : 新增数据
		EntityItem entityItem = new EntityItem();
		BeanUtilEx.copyProperties(entityItem, webEntityItem);
		StringBuilder itemCfg = new StringBuilder();

		itemCfg.append("{");
		itemCfg.append(BeanUtilEx.jsonSeg("url", webEntityItem.getUrl()));
		itemCfg.append(",");
		itemCfg.append(BeanUtilEx.jsonSeg("params", webEntityItem.getParams()));
		itemCfg.append(",");
		itemCfg.append(BeanUtilEx.jsonSeg("resp", webEntityItem.getResp()));
		itemCfg.append(",");
		itemCfg.append(BeanUtilEx.jsonSeg("charset", webEntityItem.getCharset()));
		itemCfg.append(",");
		itemCfg.append(BeanUtilEx.jsonSeg("method", webEntityItem.getMethod()));
		itemCfg.append("}");

		entityItem.setItemCfg(itemCfg.toString());
		entityItem.setStatus(1);
		entityItem.setCreateTime(new Date());
		entityItem.setUpdateTime(new Date());
		entityItemService.saveEntityItem(entityItem);

		// Step 2: 任务处理
		Entity entity = entityService
				.findEntity(webEntityItem.getEntityCfgID());
		if (webEntityItem.getSchedulerCron() != null
				&& !"".equals(webEntityItem.getSchedulerCron().trim())) {
			SchedulerManager.newInstance().start(
					webEntityItem.getSchedulerCron(),
					new DefaultMonitorTask(webmonitortest, entity, entityItem));
		}

		return processSuccess("", entityItem);
	}

	// 删除监控项
	@RequestMapping("/del_entity_item")
	public ModelAndView delEntityItem(@RequestParam("itemId") Integer itemId,
			HttpServletRequest request) {
		entityItemTestService.deleteEntityItemTest(itemId);
		int num = entityItemService.deleteEntityItem(itemId);
		SchedulerManager.newInstance().cancel("" + itemId);
		return processSuccess("", num);
	}

	// 编辑监控项
	@RequestMapping("/edit_entityitem")
	public ModelAndView editEntityItem(@RequestParam("itemId") Integer itemId) {
		EntityItem entityItem = entityItemService
				.findEntityItemByItemId(itemId);
		return processSuccess("",
				EntityUtil.toPlugEntityItem(entityItem, WebEntityItem.class));
	}

	// 编辑保存监控项
	@RequestMapping("/edit_entityitem_save")
	public ModelAndView editSaveEntityItem(WebEntityItem webEntityItem,
			HttpServletRequest request) throws InvocationTargetException,
			IllegalAccessException {

		// Step 1 : 更新数据
		EntityItem entityItem = new EntityItem();
		BeanUtilEx.copyProperties(entityItem, webEntityItem);
		StringBuilder itemCfg = new StringBuilder();

		itemCfg.append("{");
		itemCfg.append(BeanUtilEx.jsonSeg("url", webEntityItem.getUrl()));
		itemCfg.append(",");
		itemCfg.append(BeanUtilEx.jsonSeg("params", webEntityItem.getParams()));
		itemCfg.append(",");
		itemCfg.append(BeanUtilEx.jsonSeg("resp", webEntityItem.getResp()));
		itemCfg.append(",");
		itemCfg.append(BeanUtilEx.jsonSeg("charset", webEntityItem.getCharset()));
		itemCfg.append(",");
		itemCfg.append(BeanUtilEx.jsonSeg("method", webEntityItem.getMethod()));
		itemCfg.append("}");

		entityItem.setItemCfg(itemCfg.toString());
		entityItem.setStatus(1);
		entityItem.setUpdateTime(new Date());
		entityItemService.updateEntityItem(entityItem);

		// Step 2: 任务处理
		Entity entity = entityService
				.findEntity(webEntityItem.getEntityCfgID());
		SchedulerManager.newInstance().cancel("" + webEntityItem.getItemID());
		if (webEntityItem.getSchedulerCron() != null
				&& !"".equals(webEntityItem.getSchedulerCron().trim())) {
			SchedulerManager.newInstance().start(
					webEntityItem.getSchedulerCron(),
					new DefaultMonitorTask(webmonitortest, entity, entityItem));
		}

		return processSuccess("", entityItem);
	}

	@Override
	protected void setModuleName() {

	}

}
