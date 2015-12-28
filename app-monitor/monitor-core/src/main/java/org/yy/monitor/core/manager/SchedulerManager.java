package org.yy.monitor.core.manager;

import it.sauronsoftware.cron4j.Scheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.yy.monitor.core.MonitorTask;


/**
 * 调度管理器
 * 
 * @author zhouliang
 *
 */
public class SchedulerManager {

	private Map<String, Scheduler> schedulers = new HashMap<String, Scheduler>();

	private static SchedulerManager sm = new SchedulerManager();

	private SchedulerManager() {
	};

	public static SchedulerManager newInstance() {
		return sm;
	}

	/**
	 * 启动任务
	 * 
	 * @param cron
	 *            Unix Crontab语法 , 分钟　（0-59） 小時　（0-23） 日期　（1-31） 月份　（1-12）
	 *            星期　（0-6）//0代表星期天
	 * @param task
	 */
	public void start(String cron, MonitorTask task) {
		Scheduler s = new Scheduler();
		s.schedule(cron, task);
		s.start();
		schedulers.put(task.getName(), s);
	}

	/**
	 * 取消任务
	 * 
	 * @param taskName
	 */
	public void cancel(String taskName) {
		Scheduler s = schedulers.get(taskName);
		if (s != null) {
			s.stop();
		}
	}

	public static void main(String[] args) {

		SchedulerManager sm = SchedulerManager.newInstance();
		for (int i = 0; i < 200; ++i) {
			sm.start("* * * * *", new MonitorTask() {

				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName() + ":"
							+ new Date().getTime());
					try {
						Thread.sleep(10000l);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				@Override
				public String getName() {
					return "test";
				}
			});
		}
	}
}
