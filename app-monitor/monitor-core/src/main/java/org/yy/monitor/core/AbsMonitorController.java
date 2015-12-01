package org.yy.monitor.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.yy.framework.base.controller.AbstractController;
import org.yy.monitor.core.entity.Plugin;

/**
 * 抽象监控控制器
 * 
 * @author zhouliang
 *
 */
public abstract class AbsMonitorController extends AbstractController implements
		ApplicationContextAware {

	protected static MonitorManager monitorManager = MonitorManager
			.newInstance();
	protected ApplicationContext applicationContext;

	@Override
	protected void setModuleName() {

	}

	// 根据插件编码获取插件信息
	public Plugin fetchPlugin(String code) {
		for (Plugin plugin : monitorManager.getPlugins()) {
			if (plugin.getCode().equals(code)) {
				return plugin;
			}
		}

		return null;
	}

	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
}
