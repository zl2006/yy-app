package org.yy.user.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.yy.framework.base.test.AbstractSpringTest;
import org.yy.framework.basedata.query.Pagination;
import org.yy.user.dto.OrganDTO;
import org.yy.user.model.Organ;

public class OrganDAOTest extends AbstractSpringTest {
    
    @Resource(name = "organDAO")
    private OrganDao organDao;
    
    @Test
    public void testInsertOrgan() {
        /*
        Organ organ = new Organ();
        organ.setOrganCode("TYDIC_PARTMENT_05");
        organ.setParentOrganCode("");
        organ.setName("天源迪科软件五部");
        organ.setSname("tydcrjwb");
        organ.setTel("15818774641");
        organ.setPostCode("518000");
        organ.setStatus(1);
        organ.setDescription("description");
        organ.setCreatePerson(999);
        organ.setUpdatePerson(999);
        organDao.insertOrgan(organ);
        */
    }
    
    @Test
    public void testUpdateOrgan()
        throws java.sql.SQLException {
        Organ organ = new Organ();
        organ.setOrganID(1l);
        organ.setOrganCode("TYDIC_PARTMENT_05");
        organ.setParentOrganCode("");
        organ.setName("天源迪科软件五部");
        organ.setSname("tydcrjwbasdafadsfasdfa");
        organ.setTel("15818774641");
        organ.setFax("sssssssssss");
        organ.setPostCode("518000");
        organ.setStatus(1);
        organ.setDescription("天源迪科软件五部");
        organ.setCreatePerson(999l);
        organ.setUpdatePerson(999l);
        organDao.updateOrgan(organ);
        
    }
    
    @Test
    public void testFindOrganByCode()
        throws java.sql.SQLException {
        organDao.findOrgan("TYDIC_PARTMENT_05");
    }
    
    @Test
    public void testFindOrgan()
        throws java.sql.SQLException {
        
        OrganDTO organDTO = new OrganDTO();
        organDTO.setName("aa");
        organDTO.setOrganCode("bb");
        organDTO.setStatus(1);
        organDTO.setParentOrganCode("cc");
        
        organDTO.setPagination( new Pagination(50, 3) );
        
        organDao.findOrgan(organDTO);
    }
    
}
