package org.yy.monitor.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.entity.Plugin;
import org.yy.monitor.core.persistence.EntityItemService;
import org.yy.monitor.core.persistence.EntityService;
import org.yy.monitor.core.util.EntityUtil;
import org.yy.monitor.core.util.VelocityUtil;

/**
 * 
 * 插件视图处理抽象类
 * 
 * @author zhouliang
 *
 */
public abstract class AbsPluginView<ENTITY extends Entity, ENTITYITEM extends EntityItem>
		implements PluginView {

	private static Logger logger = LoggerFactory.getLogger(AbsPluginView.class);

	@Resource(name = "entityService")
	private EntityService entityService;

	@Resource(name = "entityItemService")
	private EntityItemService entityItemService;

	private Class<ENTITY> entityClass;

	private Class<ENTITYITEM> entityItemClass;

	public AbsPluginView(Class<ENTITY> entityClass,
			Class<ENTITYITEM> entityItemClass) {
		this.entityClass = entityClass;
		this.entityItemClass = entityItemClass;
	}

	@Override
	public String render(Plugin plugin, HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> data = new HashMap<String, Object>();

		// Step 1 :获取该插件类型的所有监控实体
		List<Entity> entitys = entityService.findEntity(plugin.getCode());

		// Step 2: 获取第一个监控实体的监控项
		List<EntityItem> entityItems = new ArrayList<EntityItem>();
		if (entitys != null && entitys.size() > 0) {
			entityItems = entityItemService.findEntityItem(entitys.get(0)
					.getCfgID());
		}

		// Step 3:处理模板
		data.put("contextPath", request.getContextPath());
		processData(data, plugin, entitys, entityItems);
		return VelocityUtil
				.mergeTemplate(plugin.getVmpath() + "index.vm", data);
	}

	/**
	 * 处理插件数据
	 * 
	 * @param data
	 *            插件数据结果
	 * @param plugin
	 *            插件
	 * @param entitys
	 *            监控实体
	 * @param entityItems
	 *            监控实体项
	 */
	protected void processData(Map<String, Object> data, Plugin plugin,
			List<Entity> entitys, List<EntityItem> entityItems) {

		List<ENTITY> targetEntitys = new ArrayList<ENTITY>();
		List<ENTITYITEM> targetEntityItems = new ArrayList<ENTITYITEM>();

		try {
			for (Entity item : entitys) {
				targetEntitys.add(EntityUtil.toPlugEntity(item, entityClass));
			}

			for (EntityItem item : entityItems) {
				targetEntityItems.add(EntityUtil.toPlugEntityItem(item,
						entityItemClass));
			}

		} catch (Exception ex) {
			logger.error("process data failure!", ex);
		}

		data.put("entitys", targetEntitys);
		data.put("entityItems", targetEntityItems);
	}
}