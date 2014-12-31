 /*
 * 文 件 名:  DictionaryServiceImpl.java
 * 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
 * 描    述:  字典服务
 * 修 改 人:  zhouliang
 * 修改时间:  2014年3月16日
 * 修改内容:  <修改内容>
 */
package org.yy.user.service;

import java.util.Date;
import java.util.List;

import org.yy.framework.base.validator.ValidateError;
import org.yy.framework.base.validator.ValidateUtil;
import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dao.DictionaryDao;
import org.yy.user.dto.DictionaryDTO;
import org.yy.user.model.Dictionary;

 /**
 * 字典服务
 * 
 * @author  zhouliang
 * @version  [1.0, 2014年3月16日]
 * @since  [app－user/1.0]
 */
public class DictionaryServiceImpl implements DictionaryService {
    
    private DictionaryDao dictionaryDao;
    
    /** {@inheritDoc} */
    @Override
    public ResultDto<Dictionary> findDictionary(DictionaryDTO dicDTO)
        throws ServiceException {
        return dictionaryDao.findDictionary(dicDTO);
    }

    public void setDictionaryDao(DictionaryDao dictionaryDao) {
        this.dictionaryDao = dictionaryDao;
    }

     /** {@inheritDoc} */
    @Override
    public void insertDictionary(Dictionary dic)
        throws ServiceException {
        dic.setCreateTime(new Date());
        dic.setUpdateTime(new Date());
        
        //数据验证
        List<ValidateError> errors = ValidateUtil.validate(dic);
        if (errors.size() > 0) {
            throw new ServiceException("DIC_VALIDATE_ERROR", errors.toString());
        }
        
        dictionaryDao.insertDictionary(dic);
    }

     /** {@inheritDoc} */
    @Override
    public Dictionary findDictionary(Long dicID)
        throws ServiceException {
        if( dicID == null){
            throw new ServiceException("DIC_PARAM_ERROR","字典ID为空");
        }
        return dictionaryDao.findDictionary(dicID);
    }


     /** {@inheritDoc} */
    @Override
    public void updateDictionary(Dictionary dic)
        throws ServiceException {
      //数据验证
        List<ValidateError> errors = ValidateUtil.validate(dic);
        if (errors.size() > 0) {
            throw new ServiceException("DIC_VALIDATE_ERROR", errors.toString());
        }
        
        dictionaryDao.updateDictionary(dic);
    }
    
}
