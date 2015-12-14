package org.yy.monitor.plugins.web;

import org.yy.monitor.core.Task;
import org.yy.monitor.plugins.web.data.WebEntity;
import org.yy.monitor.plugins.web.data.WebEntityItem;

/**
 * web监控task
 * 
 * @author zhouliang
 *
 */
public class WebMonitorTask implements Task {

	private WebEntity entity;
	private WebEntityItem entityItem;
	private WebMonitorTest test;

	public WebMonitorTask(WebEntity entity, WebEntityItem entityItem,
			WebMonitorTest test) {

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
