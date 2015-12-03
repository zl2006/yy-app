package org.yy.monitor.plugins.web;

import junit.framework.TestResult;

import org.springframework.stereotype.Component;
import org.yy.monitor.core.PluginTest;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;

@Component("webmonitortest")
public class WebMonitorTest implements PluginTest {

	@Override
	public TestResult test(Entity entity, EntityItem entityItem) {
		
		
		return null;
	}

}
