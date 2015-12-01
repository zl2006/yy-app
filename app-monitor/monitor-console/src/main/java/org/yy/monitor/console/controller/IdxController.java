package org.yy.monitor.console.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.yy.monitor.core.AbsMonitorController;
import org.yy.monitor.core.MonitorConstants;
import org.yy.monitor.core.PluginView;
import org.yy.monitor.core.entity.Plugin;

@Controller
public class IdxController extends AbsMonitorController {

	@RequestMapping("/")
	public ModelAndView index(HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();

		// Step 1: 获取当前选择的插件
		String currentPluginCode = request.getParameter("plugin");
		Plugin currentPlugin = null;
		if (StringUtils.isNotEmpty(currentPluginCode)) {
			currentPlugin = fetchPlugin(currentPluginCode);
		} else if (monitorManager.getPlugins().size() > 0) {
			currentPlugin = monitorManager.getPlugins().get(0);
		}
		result.put(MonitorConstants.CURRENT_PLUGIN, currentPlugin);

		// Step 2:获取插件列表
		result.put(MonitorConstants.PLUGINS, monitorManager.getPlugins());
		
		// Step 3: 显示当前插件
		String content = "未配置插件";
		if(currentPlugin != null){
			PluginView view = (PluginView) applicationContext.getBean(currentPlugin.getView());
			content = view.render(currentPlugin);
		}
		
		result.put(MonitorConstants.CONTENT, content);
		return processSuccess("/index", result);
	}

}
