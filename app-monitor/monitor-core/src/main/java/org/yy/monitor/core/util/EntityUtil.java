package org.yy.monitor.core.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.yy.monitor.core.entity.Entity;
import org.yy.monitor.core.entity.EntityItem;

/**
 * entity工具
 * 
 * @author zhouliang
 *
 */
public final class EntityUtil {

	/**
	 * 通用监控实体转换成插件实体
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Entity> T toPlugEntity(Entity entity,
			Class<T> clazz) {
		try {
			// 通用属性
			T targetEntity = clazz.newInstance();
			BeanUtilEx.copyProperties(targetEntity, entity);

			// 特殊属性
			if (StringUtils.isNotEmpty(entity.getMonitorEntityCfg())) {
				Map<String, String> temp = JsonUtil.parseObject(
						entity.getMonitorEntityCfg(), Map.class);
				BeanUtilEx.populate(targetEntity, temp);
			}

			// 节点属性
			if (StringUtils.isNotEmpty(entity.getMonitorEntityNodes())) {
				targetEntity.setNodes(JsonUtil.parseList(
						entity.getMonitorEntityNodes(), String.class));
			}

			return targetEntity;
		} catch (Throwable ex) {
			return null;
		}
	}

	/**
	 * 通用监控实体项转换成插件实体项
	 */
	@SuppressWarnings("unchecked")
	public static <T extends EntityItem> T toPlugEntityItem(
			EntityItem entityItem, Class<T> clazz) {

		try {
			// 通用属性
			T targetEntityItem = clazz.newInstance();
			BeanUtilEx.copyProperties(targetEntityItem, entityItem);

			// 特殊属性
			if (StringUtils.isNotEmpty(entityItem.getItemCfg())) {
				Map<String, String> temp = JsonUtil.parseObject(
						entityItem.getItemCfg(), Map.class);
				BeanUtilEx.populate(targetEntityItem, temp);
			}
			return targetEntityItem;
		} catch (Throwable ex) {
			return null;
		}
	}

}
