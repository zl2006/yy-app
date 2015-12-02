package org.yy.monitor.plugins.ds;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.yy.monitor.core.AbsPluginView;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.entity.Plugin;

/**
 * web监控插件视图
 * 
 * @author zhouliang
 *
 */
@Component("dsmonitorview")
public class DSMonitorView extends AbsPluginView {

	@Override
	protected void processData(Map<String, Object> data, Plugin plugin,
			List<Entity> entitys, List<EntityItem> entityItems) {

	}

}
