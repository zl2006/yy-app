package org.yy.monitor.core.persistence;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.yy.framework.base.test.AbstractSpringTest;

public class ManagerServiceTest extends AbstractSpringTest {

	@Resource(name = "managerService")
	private ManagerService managerService;

	@Test
	public void testLogin() {
		Assert.assertFalse(managerService.login("aa", "aa"));
	}

}
