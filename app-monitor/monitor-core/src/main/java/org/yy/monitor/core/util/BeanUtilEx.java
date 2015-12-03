package org.yy.monitor.core.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;

public final class BeanUtilEx extends BeanUtils {

	private BeanUtilEx() {
	}

	static {
		ConvertUtils.register(new DateConverter(null), java.util.Date.class); 
		ConvertUtils.register(new SqlDateConverter(null), java.sql.Date.class);
	}

	public static void copyProperties(Object target, Object source)
			throws InvocationTargetException, IllegalAccessException {
		org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);
	}
	
}