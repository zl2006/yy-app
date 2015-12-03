package org.yy.monitor.core.persistence;

import java.util.List;

import org.springframework.stereotype.Service;
import org.yy.monitor.core.entity.EntityItem;

/**
 * 监控项持久化服务
 * 
 * @author zhouliang
 *
 */
@Service("entityItemService")
public class EntityItemService extends AbsMonitorService {

	/**
	 * 根据监控实体查询监控项列表
	 * @param entityCfgID 监控实体ID
	 * @return
	 */
	public List<EntityItem> findEntityItem(Integer entityCfgID) {
		return this.sqlSession.selectList("monitor.FIND_ENTITYITEM_BY_ENTITYCFGID", entityCfgID);
	}
	
	
	/**
	 * 查询监控项
	 * @param entityItemID 监控项ID
	 * @return
	 */
	public EntityItem findEntityItemByItemId(Integer entityItemID){
		return this.sqlSession.selectOne("monitor.FIND_ENTITYITEM_BY_ITEMID", entityItemID);
	}
}
