/*
* 文 件 名:  ResType.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  资源类型
* 修 改 人:  zhouliang
* 修改时间:  2014年1月2日
* 修改内容:  <修改内容>
*/
package org.yy.user.model.constants;

/**
* 资源类型
* 
* @author  zhouliang
* @version  [1.0, 2014年1月2日]
* @since  [app-user/1.0]
*/
public enum ResType {
    
    //资源类型：0表示菜单（模块 ），1表示功能, 2表示数据列表中的权限。其中功能与数据列表中的权限都是挂在菜单下。
    MENU("0"), FUNCTION("1"), OPERATION("2");
    
    private String type;
    
    ResType(String type) {
        this.type = type;
    }
    
    public String value() {
        return this.type;
    }
}
