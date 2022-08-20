package com.cy.store.mapper;

import com.cy.store.entity.Menu;
import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.service.impl.LoginServiceImpl;
import com.cy.store.util.JsonResult;
import com.cy.store.util.JwtUtil;
import com.cy.store.util.RedisCache;
import com.cy.store.util.SpringUtils;
import io.jsonwebtoken.Claims;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    IUserService userService;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    RedisCache redisCache;
    @Test
    public void contextLoads() throws Exception {
//        int i = userService.deleteUserByUid(134);
//        Object cacheObject = redisCache.getCacheObject("login:20");
//        System.out.println(cacheObject);
//        JsonResult jsonResult = userService.deleteUserByUid(133);
//        System.out.println();
//        User byUsername = userMapper.findByUsername("");
//        System.out.println(byUsername);
//        Menu menu = menuMapper.selectpermsByUserId(1);
//        System.out.println(menu);
//        User root = userService.findByUsername("root");
//        System.out.println(root);
//        try {
//            Integer uid = 9;
//            String username = "xiao";
//            String oldPassword = "123456";
//            String newPassword = "888888";
//            userService.ChangePassword(uid, username, oldPassword, newPassword);
//            System.out.println("密码修改成功！");
//        } catch (ServiceException e) {
//            System.out.println("密码修改失败！" + e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }


//        User user = new User();
//        user.setUid(9);
//        user.setPhone("17858802222");
//        user.setEmail("admin@cy.com");
//        user.setGender(1);
//        user.setModifiedUser("系统管理员");
//        user.setModifiedTime(new Date());
//        Integer rows = userMapper.updateInfoByUid(user);
//        System.out.println("rows=" + rows);
//        try {
//            Integer uid = 5;
//            User user = userService.getByUid(uid);
//            System.out.println(user);
//        } catch (ServiceException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
//        try {
//            Integer uid = 5;
//            String username = "数据管理员";
//            User user = new User();
//            user.setPhone("15512328888");
//            user.setEmail("admin03@cy.cn");
//            user.setGender(2);
//            userService.changeInfo(uid, username, user);
//            System.out.println("OK.");
//         } catch (ServiceException e) {
//            System.out.println(e.getClass().getSimpleName());
//            System.out.println(e.getMessage());
//        }
//        Integer uid = 5;
//        String avatar = "/upload/avatar.png";
//        String modifiedUser = "超级管理员";
//        Date modifiedTime = new Date();
//        Integer rows = userMapper.updateAvatarByUid(uid, avatar, modifiedUser, modifiedTime);
//        System.out.println("rows=" + rows);

//

//        List<User> root = userMapper.findUserByUserName("ro");
//        System.out.println(root);
//        Claims claims = JwtUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMCIsImV4cCI6MTY2MDgzNDMzN30.A3cXu6X-eWjC4ciZNjfng00HQJY75qFC_45MNn6e8PE");
//        String token = JwtUtil.parseToken("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMCIsImV4cCI6MTY2MDg3MTIyMn0.Js4TL48CDIiBSG0ANamNZzbTgKbfFNuJFd1GbVaD-Lc");
//
//        List<String> list = menuMapper.selectpermsByUserId(20);
//        System.out.println(list);
//        System.out.println(claims.getSubject());

    }
}
