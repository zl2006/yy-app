 /*
 * 文 件 名:  DictionaryService.java
 * 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
 * 描    述:  字典服务
 * 修 改 人:  zhouliang
 * 修改时间:  2014年3月16日
 * 修改内容:  <修改内容>
 */
package org.yy.user.service;

import org.yy.framework.basedata.exception.ServiceException;
import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.DictionaryDTO;
import org.yy.user.model.Dictionary;

 /**
 * 字典服务接口
 * 
 * @author  zhouliang
 * @version  [1.0, 2014年3月16日]
 * @since  [app-user/1.0]
 */
public interface DictionaryService {
    
    /**
     * 查询字典
     * 
     * @param dicDTO 查询条件
     * @return 字典列表
     * @throws ServiceException
     */
    ResultDto<Dictionary> findDictionary(DictionaryDTO dicDTO)throws ServiceException;
    
    /**
     * 插入字典数据

     * @param dic 字典信息
     * @throws ServiceException
     */
    void insertDictionary(Dictionary dic)throws ServiceException;
    
    /**
     * 查找字典数据

     * @param dicID 字典ID
     * @return 字典数据
     * @throws ServiceException
     */
    Dictionary findDictionary(Long dicID)throws ServiceException;
    
    /**
     * 更新字典
     * @param dic 字典信息
     * @return
     * @throws ServiceException
     */
    void updateDictionary(Dictionary dic)throws ServiceException;
}
