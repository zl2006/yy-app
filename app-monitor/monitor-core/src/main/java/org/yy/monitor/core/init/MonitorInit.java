package org.yy.monitor.core.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.yy.monitor.core.DefaultMonitorTask;
import org.yy.monitor.core.MonitorTest;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.entity.Plugin;
import org.yy.monitor.core.manager.MonitorManager;
import org.yy.monitor.core.manager.SchedulerManager;
import org.yy.monitor.core.persistence.EntityItemService;
import org.yy.monitor.core.persistence.EntityService;

/**
 * 初始化插件等,不需要被调用
 * 
 * @author zhouliang
 *
 */
public class MonitorInit implements ApplicationContextAware {

	@Resource(name = "entityItemService")
	private EntityItemService entityItemService;

	@Resource(name = "entityService")
	private EntityService entityService;

	private ApplicationContext ctx;

	public MonitorInit() {
		MonitorManager.newInstance();
		SchedulerManager.newInstance();
	}

	public void init() {
		initTask();
	}

	protected void initTask() {
		Map<Integer, Entity> entitys = new HashMap<Integer, Entity>();
		List<EntityItem> entityItems = entityItemService.findAllEntityItem();
		for (EntityItem item : entityItems) {

			Entity entity = null;
			if (entitys.get(item.getEntityCfgID()) == null) {
				entity = entityService.findEntity(item.getEntityCfgID());
				entitys.put(item.getEntityCfgID(), entity);
			}

			if (entitys.get(item.getEntityCfgID()) == null) {
				continue;
			}

			Plugin plugin = MonitorManager.newInstance().getPlugin(
					entity.getMonitorType());
			if (plugin == null) {
				continue;
			}

			if (item.getSchedulerCron() != null
					&& !"".equals(item.getSchedulerCron().trim())) {
				SchedulerManager.newInstance().start(
						item.getSchedulerCron(),
						new DefaultMonitorTask((MonitorTest) ctx.getBean(plugin
								.getTest()), entity, item));
			}

		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.ctx = applicationContext;
	}
}
