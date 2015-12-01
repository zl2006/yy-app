package org.yy.monitor.core.persistence;

import java.util.List;

import org.springframework.stereotype.Service;
import org.yy.monitor.core.entity.Entity;

/**
 * 
 * 监控对象持久化服务
 * 
 * @author zhouliang
 *
 */
@Service("entityService")
public class EntityService extends AbsMonitorService {

	/**
	 * 查询某插件下的配置的所有监控对象
	 * 
	 * @param monitorType
	 *            监控类型
	 * @return
	 */
	public List<Entity> findEntity(String monitorType) {
		return sqlSession
				.selectList("monitor.FIND_ENTITY_BY_TYPE", monitorType);
	}

	/**
	 * 查询某一个监控对象
	 * 
	 * @param entityID
	 *            监控对象ID
	 * @return
	 */
	public Entity findEntity(Integer entityID) {
		return sqlSession.selectOne("monitor.FIND_ENTITY_BY_CFGID", entityID);
	}

	/**
	 * 查询某一个监控对象
	 * 
	 * @param entity
	 *            监控对象
	 * @return
	 */
	public int saveEntity(Entity entity) {
		return sqlSession.insert("monitor.SAVE_ENTITY", entity);
	}

	/**
	 * 更新一个监控对象
	 * 
	 * @param entity
	 *            监控对象
	 * @return
	 */
	public int updateEntity(Entity entity) {
		return sqlSession.update("monitor.UPDATE_ENTITY", entity);
	}
	
	/**
	 * 删除一个监控对象
	 * @param entityID
	 * @return
	 */
	public int deleteEntity(Integer entityID){
		return sqlSession.delete("monitor.DELETE_ENTITY", entityID);
	}

}
