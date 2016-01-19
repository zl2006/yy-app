package org.yy.user.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AppListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(sce.getServletContext());
		sce.getServletContext().setAttribute("pageConfig",
				ctx.getBean("pageConfig"));
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
