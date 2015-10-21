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
 	//资源类型：一个系统有多个模块，一个模块有多个子模块，一个子模块有多个功能项，列表中的功能操作一般属于某个列表功能项， 按钮或其它操作可以属于系统／模块／子模块／功能项。<br>
 	 例：
 	 系统：开店助手｜超级营销【属于门户】
 	 模块：产品管理【属于系统】
 	 子模块 ：类目管理，产品管理【属于模块】
 	 功能项：新增类目，类目列表【属于模块／子模块】
 	 列表操作：查看类目，编辑类目【属于功能项】
 	 按钮操作：保存，更新【属于系统／模块／子模块／功能项】
 	 其它操作：保存，更新【属于系统／模块／子模块／功能项】
 	  
	// 系统：portal系统中做为顶部主菜单
	// 模块 ：portal系统中做为顶部子菜单，单系统中做为顶部主菜单 
	// 子模块：系统中左侧菜单标题项，当无子模块时，模块充当左侧菜单标题项
	// 模块功能项：左侧菜单项
	// 列表操作：数据列表中操作
	// 按钮操作权限 ：任何地方 
	// 其它操作权限 ：任何地方
* 
* @author  zhouliang
* @version  [1.0, 2014年1月2日]
* @since  [app-user/1.0]
*/
public enum ResType {
    
    SYSTEM("-1"), MODULE("0"), SUBMODULE("1"), MODULEFUNC("2"),  LISTOPER("3"), BTNOPER("4"),OTHEROPER("5");
    
    private String type;
    
    ResType(String type) {
        this.type = type;
    }
    
    public String value() {
        return this.type;
    }
}
