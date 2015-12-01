package org.yy.monitor.core.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * json解析工具
 * 
 * @author zhouliang
 *
 */
public final class JsonUtil {

	/**
	 * 解析json字符串为对象
	 */
	public static <T> T parseObject(String json, Class<T> clazz) {
		return JSON.parseObject(json, clazz);
	}

	/**
	 * 解析json为数组对象
	 */
	public static <T> List<T> parseList(String json, Class<T> clazz) {
		return JSON.parseArray(json, clazz);
	}
}
