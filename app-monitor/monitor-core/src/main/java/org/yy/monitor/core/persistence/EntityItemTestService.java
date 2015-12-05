package org.yy.monitor.core.persistence;

import java.util.List;

import org.springframework.stereotype.Service;
import org.yy.monitor.core.entity.EntityItemTest;

/**
 * 监控项测试结果持久化
 * 
 * @author zhouliang
 *
 */
@Service("entityItemTestService")
public class EntityItemTestService extends AbsMonitorService {

	/**
	 * 查看监控项的前20项测试结果
	 */
	public List<EntityItemTest> findEntityItemTest(Integer entityItemID) {
		return sqlSession.selectList("monitor.FIND_ENTITYITEMTEST_BY_ITEMID",
				entityItemID);
	}

	/**
	 * 查看某一条测试结果
	 */
	public EntityItemTest findEntityItemTestByID(Integer testID) {
		return sqlSession.selectOne("monitor.FIND_ENTITYITEMTEST_BY_TESTID",
				testID);
	}

	/**
	 * 插件监控结果
	 * 
	 * @param test
	 * @return
	 */
	public int insertEntityItemTest(EntityItemTest test) {
		return sqlSession.insert("monitor.INSERT_ENTITYITEMTEST", test);
	}
}
