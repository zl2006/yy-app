package org.yy.monitor.core.persistence;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.yy.framework.base.dao.AbstractMyBatisDao;

public abstract class AbsMonitorService extends AbstractMyBatisDao{

	@Override
	@Resource(name = "sqlSession")
	public void setSqlSession(SqlSession sqlSession) {
		super.setSqlSession(sqlSession);
	}
}
