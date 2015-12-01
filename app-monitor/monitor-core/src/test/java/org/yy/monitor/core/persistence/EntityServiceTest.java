package org.yy.monitor.core.persistence;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.yy.framework.base.test.AbstractSpringTest;
import org.yy.monitor.core.entity.Entity;

public class EntityServiceTest extends AbstractSpringTest {

	@Resource(name = "entityService")
	private EntityService entityService;

	@Test
	public void testSaveEntity() {
		Entity e = new Entity();
		e.setCreateTime(new Date());
		e.setUpdateTime(new Date());
		e.setName("应用一");
		e.setDesc("描述一");
		e.setStatus(1);
		e.setMonitorEntityCfg("http://aaa.ico");
		e.setMonitorEntityNodes("['http://192.168.0.1:8080','http://192.168.0.2:8080']");
		e.setMonitorType("WEB_MONITOR");
		int result = entityService.saveEntity(e);
		Assert.assertTrue(1 == result);
		e = new Entity();
		e.setName("应用二");
		e.setDesc("描述二");
		e.setCreateTime(new Date());
		e.setUpdateTime(new Date());
		e.setMonitorEntityCfg("http://bbb.ico");
		e.setMonitorEntityNodes("['http://192.168.0.1:8088','http://192.168.0.2:8088']");
		e.setMonitorType("WEB_MONITOR");
		result = entityService.saveEntity(e);
		Assert.assertTrue(1 == result);
	}

	@Test
	public void testFindEntityString() {
		List<Entity> entitys = entityService.findEntity("WEB_MONITOR");
		Assert.assertTrue(2 <= entitys.size());
	}

	@Test
	public void testFindEntityInteger() {
		List<Entity> entitys = entityService.findEntity("WEB_MONITOR");
		if (entitys != null && entitys.size() > 0) {
			Integer cfgId = entitys.get(0).getCfgID();
			Entity entity = entityService.findEntity(cfgId);
			Assert.assertTrue(entity != null);

		}
	}

	@Test
	public void testUpdateEntity() {
		List<Entity> entitys = entityService.findEntity("WEB_MONITOR");
		if (entitys != null && entitys.size() > 0) {
			Entity entity = entitys.get(0);
			entity.setMonitorEntityCfg("http://ccc.ico");
			int i = entityService.updateEntity(entity);
			Assert.assertTrue(i == 1);

		}
	}

	@Test
	public void testDeleteEntity() {
		List<Entity> entitys = entityService.findEntity("WEB_MONITOR");
		int total = 0;
		for (Entity entity : entitys) {
			if ("应用一".equals(entity.getName())
					|| "应用二".equals(entity.getName())) {
				entityService.deleteEntity(entity.getCfgID());
				total++;
			}
		}
		Assert.assertTrue(2 == total);

	}

}
