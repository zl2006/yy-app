package org.yy.monitor.core;

import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;

/**
 * 默认监控任务实现
 * 
 * @author zhouliang
 *
 */
public class DefaultMonitorTask implements MonitorTask {

	private MonitorTest test;
	private Entity entity;
	private EntityItem entityItem;

	public DefaultMonitorTask(MonitorTest test, Entity entity,
			EntityItem entityItem) {
		this.test = test;
		this.entity = entity;
		this.entityItem = entityItem;
	}

	@Override
	public void run() {
		test.test(entity, entityItem);
	}

	@Override
	public String getName() {
		return entityItem.getItemID() + "";
	}

}
