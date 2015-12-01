package org.yy.monitor.core;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.entity.Plugin;
import org.yy.monitor.core.persistence.EntityItemService;
import org.yy.monitor.core.persistence.EntityService;
import org.yy.monitor.core.util.VelocityUtil;

/**
 * 
 * 插件视图处理抽象类
 * 
 * @author zhouliang
 *
 */
public abstract class AbsPluginView implements PluginView {

	@Resource(name = "entityService")
	private EntityService entityService;

	@Resource(name = "entityItemService")
	private EntityItemService entityItemService;

	@Override
	public String render(Plugin plugin) {

		// Step 1 :获取该插件类型的所有监控实体
		List<Entity> entitys = entityService.findEntity(plugin.getCode());

		// Step 2: 获取第一个监控实体的监控项
		List<EntityItem> entityItems = new ArrayList<EntityItem>();
		if (entitys != null && entitys.size() > 0) {
			entityItems = entityItemService.findEntityItem(entitys.get(0)
					.getCfgID());
		}

		// Step 3:处理模板
		return VelocityUtil.mergeTemplate(plugin.getVmpath() + "index.vm",
				processData(plugin, entitys, entityItems));
	}

	protected abstract Object processData(Plugin plugin, List<Entity> entitys,
			List<EntityItem> entityItems);
}
