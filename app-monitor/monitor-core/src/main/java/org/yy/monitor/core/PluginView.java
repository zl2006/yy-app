package org.yy.monitor.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yy.monitor.core.entity.Plugin;

/**
 * 插件视图，用于显示
 * 
 * @author zhouliang
 *
 */
public interface PluginView {

	/**
	 * 获取插件视图内容
	 * 
	 * @param plugin
	 *            插件
	 * @param request
	 * @param response
	 */
	public String render(Plugin plugin, HttpServletRequest request,
			HttpServletResponse response);
}
