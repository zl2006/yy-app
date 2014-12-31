/*
* 文 件 名:  UserServiceTest.java
* 版    权:  YY Technologies Co., Ltd. Copyright 2012-2013,  All rights reserved
* 描    述:  <描述>
* 修 改 人:  zhouliang
* 修改时间:  2013年12月1日
* 修改内容:  <修改内容>
*/
package org.yy.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.yy.framework.base.test.AbstractSpringTest;
import org.yy.framework.base.validator.ValidateUtil;
import org.yy.user.model.Role;
import org.yy.user.model.User;

/**
* <一句话功能简述>
* <功能详细描述>
* 
* @author  zhouliang
* @version  [版本号, 2013年12月1日]
* @since  [产品/模块版本]
*/
public class UserServiceTest extends AbstractSpringTest {
    @Resource(name = "userService")
    private UserService userService;
    
    @Test
    public void testInsertUser() {
        User u = new User();
        u.setName("");
        
        Role role = new Role();
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        
        System.out.println( ValidateUtil.validate(u) );
        
        System.out.println( ValidateUtil.validate(u,"sex","name") );
    }
}
