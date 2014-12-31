package org.yy.user.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.yy.framework.base.test.AbstractSpringTest;
import org.yy.framework.basedata.query.Pagination;
import org.yy.user.dto.ResourceDTO;

public class ResourceDAOTest extends AbstractSpringTest {
    
    @Resource(name = "resourceDAO")
    private ResourceDao resDao;
    
    @Test
    public void testInsertResource() {
        /*
        org.yy.user.model.Resource res = new org.yy.user.model.Resource();
        
        res.setSystemCode("001");
        res.setParentResID(-1);
        res.setName("登录");
        res.setUrl("/uhome/login.do");
        res.setType("3");
        res.setIcon("login.jpg");
        res.setOrderNO(1);
        res.setDescription("resource desc");
        res.setStatus(1);
        res.setCreatePerson(999);
        res.setUpdatePerson(999);
        
        resDao.insertResource(res);
        */
    }
    
    @Test
    public void testUpdateResource()
        throws java.sql.SQLException {
        org.yy.user.model.Resource res = new org.yy.user.model.Resource();
        
        res.setSystemCode("001");
        res.setParentResID(-1l);
        res.setName("登录111111111");
        res.setUrl("/uhome/login.do");
        res.setType("3");
        res.setIcon("login.jpg");
        res.setOrderNO(1);
        res.setDescription("resource desc");
        res.setStatus(1);
        res.setCreatePerson(999l);
        res.setUpdatePerson(999l);
        resDao.updateResource(res);
        
    }
    
    @Test
    public void testFindResourceByID()
        throws java.sql.SQLException {
        resDao.findResource(1l);
    }
    
    @Test
    public void testFindResource()
        throws java.sql.SQLException {
        
        ResourceDTO dto = new ResourceDTO();
        dto.setName("登录111111111");
        dto.setUrl("/uhome/login.do");
        resDao.findResource(dto);
        dto.setPagination(new Pagination(50, 3));
        resDao.findResource(dto);
    }
    
}
