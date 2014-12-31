package org.yy.user.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.yy.framework.base.test.AbstractSpringTest;
import org.yy.framework.basedata.query.Pagination;
import org.yy.user.dto.DictionaryDTO;
import org.yy.user.model.Dictionary;

public class DictionaryDAOTest extends AbstractSpringTest {
    
    @Resource(name = "dictionaryDAO")
    private DictionaryDao dictionaryDAO;
    
    @Test
    public void testInsertDictionary() {
        
        /*
        Dictionary dic = new Dictionary();
        
        dic.setDicCode("CITY_YUEYANG");
        dic.setName("岳阳");
        dic.setValue("yueyang");
        dic.setOrderNO(0);
        dic.setType("CITY");
        dic.setStatus(1);
        dic.setDescription("*******");
        dictionaryDAO.insertDictionary(dic);
        
        dic.setDicID(null);
        dic.setDicCode("CITY_CHANGSHA");
        dic.setName("长沙");
        dic.setValue("changsha");
        dic.setOrderNO(0);
        dic.setType("CITY");
        dic.setStatus(1);
        dic.setDescription("*******");
        dictionaryDAO.insertDictionary(dic);*/
    }
    
    @Test
    public void testDeleteDicByID() {
        dictionaryDAO.deleteDictionary(7l);
    }
    
    @Test
    public void testUpdateDicy() {
        Dictionary dic = new Dictionary();
        dic.setDicID(5l);
        dic.setDicCode("CITY_YUEYANG");
        dic.setName("岳阳");
        dic.setValue("yueyang");
        dic.setOrderNO(0);
        dic.setType("CITY");
        dic.setStatus(1);
        dic.setDescription("abcdef");
        dictionaryDAO.updateDictionary(dic);
    }
    
    
    @Test
    public void testFindDic() {
        DictionaryDTO dto = new DictionaryDTO();
        dto.setName("岳阳");
        dto.setStatus(1);
        dictionaryDAO.findDictionary(dto);
        dto.setPagination(new Pagination(50, 3));
        dictionaryDAO.findDictionary(dto);
    }
}
