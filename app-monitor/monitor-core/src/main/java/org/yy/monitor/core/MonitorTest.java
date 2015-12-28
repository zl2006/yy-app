package org.yy.monitor.core;

import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;
import org.yy.monitor.core.entity.TestResult;

/**
 * 监控测试
 */
public interface MonitorTest {

	/**
	 * 测试监控项
	 * 
	 * @param entity
	 *            监控实体
	 * @param entityItem
	 *            监控实体项
	 * @return
	 */
	public TestResult test(Entity entity, EntityItem entityItem);
}
