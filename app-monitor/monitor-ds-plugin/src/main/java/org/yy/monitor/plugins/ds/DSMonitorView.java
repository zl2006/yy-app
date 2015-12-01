package org.yy.monitor.plugins.ds;

import org.springframework.stereotype.Component;
import org.yy.monitor.core.PluginView;
import org.yy.monitor.core.entity.Plugin;
import org.yy.monitor.core.util.VelocityUtil;

/**
 * web监控插件视图
 * 
 * @author zhouliang
 *
 */
@Component("dsmonitorview")
public class DSMonitorView implements PluginView {

	@Override
	public String render(Plugin plugin) {
		return VelocityUtil.mergeTemplate(plugin.getVmpath() + "index.vm", null);
	}

}
