package org.yy.user.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.yy.framework.base.test.AbstractSpringTest;
import org.yy.framework.basedata.query.Pagination;
import org.yy.user.dto.RoleDTO;
import org.yy.user.model.Role;

public class RoleDAOTest extends AbstractSpringTest {
    
    @Resource(name = "roleDAO")
    private RoleDao roleDAO;
    
    @Test
    public void testInsertRole() {
        
       /* Role role = new Role();
        role.setRoleCode("ADMIN");
        role.setName("管理员");
        role.setStatus(1);
        role.setDescription("admin description");
        role.setCreatePerson(999);
        role.setUpdatePerson(999);
        
        roleDAO.insertRole(role);*/
    }
    
    @Test
    public void testUpdateRole()
        throws java.sql.SQLException {
        Role role = new Role();
        role.setRoleID(1l);
        role.setRoleCode("ADMIN");
        role.setName("系统管理员");
        role.setStatus(1);
        role.setDescription("admin description");
        role.setCreatePerson(999l);
        role.setUpdatePerson(999l);
        
        roleDAO.updateRole(role);
        
    }
    
    @Test
    public void testFindRoleByCode()
        throws java.sql.SQLException {
        roleDAO.findRole("ADMIN");
    }
    
    
    @Test
    public void testFindRole()
        throws java.sql.SQLException {
        
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("系统管理员");
        roleDTO.setStatus(1);
        roleDAO.findRole(roleDTO);
        roleDTO.setPagination(new Pagination(50, 3));
        roleDAO.findRole(roleDTO);
    }
    
}
