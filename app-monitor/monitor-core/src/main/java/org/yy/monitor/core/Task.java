package org.yy.monitor.core;

/**
 * 监控任务
 * 
 * @author zhouliang
 *
 */
public interface Task extends Runnable {

	/**
	 * 任务名称
	 */
	public String getName();
}
