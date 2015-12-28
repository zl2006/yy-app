package org.yy.monitor.plugins.ds;

import org.springframework.stereotype.Component;
import org.yy.monitor.core.AbsMonitorView;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;

/**
 * web监控插件视图
 * 
 * @author zhouliang
 *
 */
@Component("dsmonitorview")
public class DSMonitorView extends AbsMonitorView<Entity, EntityItem> {

	public DSMonitorView() {
		super(Entity.class, EntityItem.class);
	}

}
