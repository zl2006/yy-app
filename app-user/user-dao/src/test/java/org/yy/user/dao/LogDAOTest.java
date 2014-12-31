package org.yy.user.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.yy.framework.base.test.AbstractSpringTest;
import org.yy.framework.basedata.query.Pagination;
import org.yy.user.dto.LogDTO;

public class LogDAOTest extends AbstractSpringTest {
    
    @Resource(name = "logDAO")
    private LogDao logDao;
    
    @Test
    public void testInsertLog() {
        /*Log log = new Log();
        log.setOperatorID(123);
        log.setOperName("zhouliang");
        log.setOperType("delete");
        log.setBusiDataType("001001");
        log.setBusiDataID(100);
        log.setRemark("删除用户数据");
        
        logDao.insertLog(log);*/
    }

    @Test
    public void testFindLogByID()
        throws java.sql.SQLException {
        logDao.findLog(1l);
    }
    
    @Test
    public void testFindLog()
        throws java.sql.SQLException {
        
        LogDTO logDTO = new LogDTO();
        
        logDTO.setBusiDataType("001001");
        logDTO.setEndCreateTime(new Date());
        logDTO.setStartCreateTime(new Date());
        logDTO.setOperName("zhouliang");
        logDao.findLog(logDTO);
        
        logDTO.setPagination( new Pagination(50, 3) );
        
        logDao.findLog(logDTO);
    }
    
}
