/*
* 文 件 名:  DataStatus.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  数据状态
* 修 改 人:  zhouliang
* 修改时间:  2013年12月2日
* 修改内容:  <修改内容>
*/
package org.yy.user.model.constants;

/**
* 数据状态
* 
* @author  zhouliang
* @version  [1.0, 2013年12月2日]
* @since  [app-user/1.0]
*/
public enum DataStatus {
    
    // enable(有效)/disable(无效)
    ENABLE(1), DISABLE(0);
    
    private int status;
    
    DataStatus(int status) {
        this.status = status;
    }
    
    public int value() {
        return this.status;
    }
}
