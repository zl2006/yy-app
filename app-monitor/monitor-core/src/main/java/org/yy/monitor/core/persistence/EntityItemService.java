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
	 * 查询所有的监控实体
	 * 
	 * @return
	 */
	public List<EntityItem> findAllEntityItem() {
		return this.sqlSession.selectList("FIND_ENTITYITEM_ALL");
	}

	/**
	 * 根据监控实体查询监控项列表
	 * 
	 * @param entityCfgID
	 *            监控实体ID
	 * @return
	 */
	public List<EntityItem> findEntityItem(Integer entityCfgID) {
		return this.sqlSession.selectList(
				"monitor.FIND_ENTITYITEM_BY_ENTITYCFGID", entityCfgID);
	}

	/**
	 * 查询监控项
	 * 
	 * @param entityItemID
	 *            监控项ID
	 * @return
	 */
	public EntityItem findEntityItemByItemId(Integer entityItemID) {
		return this.sqlSession.selectOne("monitor.FIND_ENTITYITEM_BY_ITEMID",
				entityItemID);
	}

	/**
	 * 保存监控项
	 */
	public int saveEntityItem(EntityItem entityItem) {
		return this.sqlSession.insert("monitor.SAVE_ENTITYITEM", entityItem);
	}

	/**
	 * 保存监控项
	 */
	public int updateEntityItem(EntityItem entityItem) {
		return this.sqlSession.update("monitor.UPDATE_ENTITYITEM", entityItem);
	}

	/**
	 * 删除监控项
	 * 
	 * @param entityItemID
	 *            监控项ID
	 * @return
	 */
	public int deleteEntityItem(Integer entityItemID) {
		return this.sqlSession
				.delete("monitor.DELETE_ENTITYITEM", entityItemID);
	}

	/**
	 * 保存监控项，测试后
	 */
	public int updateEntityItemByTest(EntityItem entityItem) {
		return this.sqlSession.update("monitor.UPDATE_ENTITYITEM_BY_TEST",
				entityItem);
	}

}
