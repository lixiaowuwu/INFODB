package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.util.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    IUserService userService;
    @Test
    public void in(){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encode=passwordEncoder.encode("szx");
        System.out.println(encode);
//        System.out.println(passwordEncoder.matches("root","$2a$10$RbhTydkFqQmUwtXGa6OZsuvs0hRj7ny15svxKJT79h5jO5l4EYKdW"));
//        User user=new User();
//        user.setUsername("lixiao");
//        user.setPassword("123456");
//        userService.reg(user,2);
    }
}
