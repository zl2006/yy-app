package org.yy.monitor.core;

import org.yy.monitor.core.entity.Plugin;

/**
 * 插件视图，用于显示
 * 
 * @author zhouliang
 *
 */
public interface PluginView {

	/**
	 * 获取视图
	 */
	public String render(Plugin plugin);
}
