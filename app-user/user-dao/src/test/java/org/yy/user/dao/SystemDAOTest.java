package org.yy.user.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.yy.framework.base.test.AbstractSpringTest;
import org.yy.framework.basedata.query.Pagination;
import org.yy.user.dto.SystemDTO;
import org.yy.user.model.System;

public class SystemDAOTest extends AbstractSpringTest {
    
    @Resource(name = "systemDAO")
    private SystemDao systemDao;
    
    @Test
    public void testInsertSystem() {
        /*
        System s = new System();
        s.setSystemCode("001");
        s.setName("user system");
        s.setName("user.jpg");
        s.setUrl("http://localhost/user");
        s.setDescription("ddddddddddddddddddddd");
        s.setStatus(1);
        s.setCreatePerson(999);
        s.setUpdatePerson(999);
        systemDao.insertSystem(s);*/
    }
    
    @Test
    public void testUpdateSystem()
        throws java.sql.SQLException {
        System s = new System();
        s.setSystemID(1l);
        s.setSystemCode("001");
        s.setName("user system");
        s.setName("user.jpg");
        s.setUrl("http://localhost/user");
        s.setDescription("user system description");
        s.setStatus(1);
        s.setCreatePerson(999l);
        s.setUpdatePerson(999l);
        systemDao.updateSystem(s);
    }
    
    @Test
    public void testFindSystemByCode()
        throws java.sql.SQLException {
        systemDao.findSystem("001");
    }
    
    @Test
    public void testFindOrgan()
        throws java.sql.SQLException {
        
        //systemDao.findSystem(null);
        SystemDTO systemDto = new SystemDTO();
        systemDto.setName("user");
        systemDto.setStatus(1);
        systemDao.findSystem(systemDto);
        systemDto.setPagination(new Pagination(50, 3));
        systemDao.findSystem(systemDto);
    }
    
}
