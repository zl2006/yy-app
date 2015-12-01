package org.yy.monitor.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.yy.monitor.core.entity.Plugin;

/**
 * 监控平台管理器
 * 
 * @author zhouliang
 *
 */
public class MonitorManager {

	private static Logger logger = LoggerFactory
			.getLogger(MonitorManager.class);

	private static MonitorManager manager = new MonitorManager();

	// 插件集合
	protected List<Plugin> plugins = new ArrayList<Plugin>();

	public static MonitorManager newInstance() {
		return manager;
	}

	// 初始化
	private MonitorManager() {
		initPlugins();
	}

	// 初始化插件
	protected void initPlugins() {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		try {
			Resource[] metaInfResources = resourcePatternResolver
					.getResources("classpath*:**/META-INF/plugin.properties");
			for (Resource r : metaInfResources) {
				Properties p = new Properties();
				p.load(r.getInputStream());

				Plugin plugin = new Plugin();
				plugin.setName(p.getProperty("plugin.name"));
				plugin.setCode(p.getProperty("plugin.code"));
				plugin.setOrder(Integer.valueOf(p.getProperty("plugin.order")));
				plugin.setEname(p.getProperty("plugin.ename"));
				plugin.setVmpath(p.getProperty("plugin.vm.package"));
				plugin.setView(p.getProperty("plugin.spring.view"));
				if (logger.isDebugEnabled()) {
					logger.debug("init plugins:" + r.getURL());
				}

				plugins.add(plugin);
				Collections.sort(plugins);
			}

			if (logger.isDebugEnabled()) {
				logger.debug(plugins.toString());
			}
		} catch (IOException e) {
			logger.error("init plugins error", e);
			throw new RuntimeException(e);
		}
	}

	public List<Plugin> getPlugins() {
		return plugins;
	}

	public static void main(String[] args) {
		// MonitorManager m = MonitorManager.newInstance();
	}

}
