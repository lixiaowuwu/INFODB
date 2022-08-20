package com.cy.store.service.impl;

import com.cy.store.entity.LoginUser;
import com.cy.store.entity.Menu;
import com.cy.store.entity.User;
import com.cy.store.mapper.MenuMapper;
import com.cy.store.mapper.UserMapper;
import com.cy.store.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        User user = userMapper.findByUsername(username);
        log.info("查询到的登录用户名字"+userMapper.findByUsername(username));
        if (user==null){
            System.out.println("用户名或密码错误");
            throw new RuntimeException("用户名或密码错误");
        }
        List<String> menuList=menuMapper.selectpermsByUserId(user.getUid());
        //把数据封装为UserDetail
        return new LoginUser(user,menuList);
    }
}
