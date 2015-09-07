/*
* 文 件 名:  DictionaryDao.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  字典信息访问DAO
* 修 改 人:  zhouliang
* 修改时间:  2013年11月22日
* 修改内容:  <修改内容>
*/
package org.yy.user.dao;

import java.util.List;

import org.yy.framework.basedata.query.ResultDto;
import org.yy.user.dto.DictionaryDTO;
import org.yy.user.model.Dictionary;

/**
* 字典信息访问DAO
* 
* @author  zhouliang
* @version  [1.0, 2013年11月22日]
* @since  [app-user/1.0]
*/
public interface DictionaryDao {
    
    /**
     *  保存字典
     * @param dictionary 字典
     * @return 字典
     */
    public Dictionary insertDictionary(Dictionary dictionary);
    
    /**
     * 删除字典
     * @param dicID 字典ID
     */
    public void deleteDictionary(Long dicID);
    
    /**
     * 更新字典
     * @param dictionary 字典
     */
    public void updateDictionary(Dictionary dictionary);
    
    /**
     * 根据指定条件查询字典
     * @param dicDTO 查询条件
     * @return 字典数据列表
     */
    public ResultDto<Dictionary> findDictionary(DictionaryDTO dicDTO);
    
    /**
     * 获取所有的字典信息
     * @param 用于存储数据的缓存KEY
     * @return 字典数据列表
     */
    public List<Dictionary> findDictionary();
    
    /**
     * 查询字典信息
     * @param dicID 字典ID
     * @return  字典数据
     */
    public Dictionary findDictionary(Long dicID);
}
