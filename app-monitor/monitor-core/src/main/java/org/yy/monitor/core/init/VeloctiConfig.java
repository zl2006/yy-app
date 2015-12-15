package org.yy.monitor.core.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 初始化velocity配置
 * 
 * @author zhouliang
 *
 */
@Component
public class VeloctiConfig {
	private static Logger logger = LoggerFactory.getLogger(VeloctiConfig.class);
	static {
		InputStream inputStream = VeloctiConfig.class.getClassLoader()
				.getResourceAsStream("velocity.properties");
		Properties p = new Properties();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("init velocity used velocity.properties!");
			}
			p.load(inputStream);
			Velocity.init(p);
		} catch (IOException ex) {
			logger.error("init velocity error!", ex);
		}

	}

	public static void main(String[] args) {
		// VeloctiConfig v = new VeloctiConfig();
	}

}
