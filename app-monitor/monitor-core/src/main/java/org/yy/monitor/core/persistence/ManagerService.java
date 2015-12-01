package org.yy.monitor.core.persistence;

import org.springframework.stereotype.Service;
import org.yy.monitor.core.entity.Manager;

/**
 * 管理员持久化服务
 * 
 * @author zhouliang
 *
 */
@Service("managerService")
public class ManagerService extends AbsMonitorService {

	/**
	 * 登录服务
	 * 
	 * @param username
	 *            用户名
	 * @param pwd
	 *            密码
	 * @return
	 */
	public boolean login(String username, String pwd) {
		Manager manager = sqlSession
				.selectOne("monitor.FIND_MANAGER_BY_NAME", username);
		return manager != null && pwd.equals(manager.getPwd());
	}
	
	
	

}
