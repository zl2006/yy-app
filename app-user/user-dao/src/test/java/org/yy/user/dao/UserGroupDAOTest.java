package org.yy.user.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.yy.framework.base.test.AbstractSpringTest;
import org.yy.framework.basedata.query.Pagination;
import org.yy.user.dto.UserGroupDTO;
import org.yy.user.model.UserGroup;

public class UserGroupDAOTest extends AbstractSpringTest {
    
    @Resource(name = "userGroupDAO")
    private UserGroupDao userGroupDAO;
    
    @Test
    public void testInsertUserGroup() {
        /*
        UserGroup userGroup = new UserGroup();
        userGroup.setGroupName("admin");
        userGroup.setStatus(1);
        userGroup.setCreatePerson(999);
        userGroup.setUpdatePerson(999);
        userGroupDAO.insertUserGroup(userGroup);*/
    }
    
    @Test
    public void testUpdateUserGroup()
        throws java.sql.SQLException {
        UserGroup userGroup = new UserGroup();
        userGroup.setUserGroupID(1l);
        userGroup.setGroupName("admin");
        userGroup.setStatus(1);
        userGroup.setUpdatePerson(1999l);
        userGroupDAO.updateUserGroup(userGroup);
    }
    
    @Test
    public void testFindUserGroupByID()
        throws java.sql.SQLException {
        userGroupDAO.findUserGroup(1l);
    }
    
    @Test
    public void testFindUserGroup()
        throws java.sql.SQLException {
        UserGroupDTO dto = new UserGroupDTO();
        userGroupDAO.findUserGroup(dto);
        dto.setGroupName("admin");
        dto.setStatus(1);
        userGroupDAO.findUserGroup(dto);
        dto.setPagination(new Pagination(50, 3));
        userGroupDAO.findUserGroup(dto);
    }
    
}
