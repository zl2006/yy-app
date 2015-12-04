package org.yy.monitor.core;

import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.entity.TestResult;

/**
 * 插件测试
 */
public interface PluginTest<T1 extends Entity, T2 extends EntityItem> {

	/**
	 * 测试监控项
	 * 
	 * @param entity
	 *            监控实体
	 * @param entityItem
	 *            监控实体项
	 * @return
	 */
	public TestResult test(T1 entity, T2 entityItem);
}
