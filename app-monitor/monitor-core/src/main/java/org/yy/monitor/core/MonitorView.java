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
public interface MonitorView {

	/**
	 * 获取插件视图内容，指定显示的监控实体
	 * 
	 * @param plugin
	 *            插件
	 * @param entityCfgID
	 *            插件中监控实体ID,为null将显示第一个监控实体。 例如WEB监控插件中的帝氏应用监控实体
	 * @param request
	 * @param response
	 */
	public String render(Plugin plugin, Integer entityCfgID,
			HttpServletRequest request, HttpServletResponse response);
}
