package org.yy.monitor.core.util;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * velocity工具类
 * 
 * @author zhouliang
 *
 */
public final class VelocityUtil {

	private static Logger logger = LoggerFactory.getLogger(VelocityUtil.class);

	/**
	 * 合并模板并返回字符串
	 * 
	 * @param vmPath
	 *            模板文件路径
	 * @param data
	 *            模板数据
	 * @return
	 */
	public static String mergeTemplate(String vmPath, Object data) {

		StringWriter w = new StringWriter();
		VelocityContext context = new VelocityContext();
		context.put("data", data);
		try {
			Velocity.mergeTemplate(vmPath, "UTF-8", context, w);
			return w.toString();
		} catch (Exception e) {
			logger.error("Problem merging template : " + e);
		}
		return null;

	}

}
