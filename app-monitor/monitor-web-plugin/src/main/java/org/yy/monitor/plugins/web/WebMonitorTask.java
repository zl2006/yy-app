package org.yy.monitor.plugins.web;

import org.yy.monitor.core.PlugTask;
import org.yy.monitor.plugins.web.data.WebEntity;
import org.yy.monitor.plugins.web.data.WebEntityItem;

/**
 * web监控task
 * 
 * @author zhouliang
 *
 */
public class WebMonitorTask implements PlugTask {

	private WebEntity entity;
	private WebEntityItem entityItem;
	private WebMonitorTest test;

	public WebMonitorTask(WebEntity entity, WebEntityItem entityItem,
			WebMonitorTest test) {
		this.entity = entity;
		this.entityItem = entityItem;
		this.test = test;
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
